package deliverySystem.people;

import deliverySystem.Company;
import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.warehouse.Warehouse;
import deliverySystem.warehouse.items.Product;
import deliverySystem.warehouse.items.Vehicle;

import java.util.logging.Logger;

public class Manager extends Employee{
    Logger log;
    public Manager(String name, String address, boolean onLeave) {
        super(name, address, onLeave);
        this.log = Logger.getLogger(Manager.class.getName());
    }

    public boolean dispatchDriver(Order order) {
        Warehouse warehouse = new Warehouse();
        for (Warehouse w: Company.getInstance().getWarehouses()){
            if(this.getEmployeeID() == w.getManager().getEmployeeID()){
                warehouse = w;
            }
        }
        if(warehouse.getAvailableDrivers().size() == 0 || warehouse.getAvailableVehicles().size() == 0) return false;

        Vehicle optimalVehicle = Vehicle.optimalVehicleType(warehouse.getAvailableVehicles(), order.getOrderSize());

        for(Driver d: warehouse.getAvailableDrivers()){
            if(d.getDrivingLicence().getBiggestVehicleType().toChar() >= optimalVehicle.getVehicleType().toChar()){
                for (Product p: order.getOrderedProducts()) {
                    Product warehouseStock = warehouse.getProduct(p);
                    if( warehouseStock.getAmount() > 0){
                        warehouseStock.reserveProduct();
                    }else{
                        return false;
                    }
                }
                d.assignOrder(order);
                d.setInTransit(true);
                optimalVehicle.setOperable(false);
                order.setStatus(Order.Status.OTW);
                return true;
            }
        }
        return false;
    }

    public void discardCompletedOrders() {
        OrderCollection.getInstance().getOrders().removeAll(OrderCollection.getInstance().getOrders().stream().filter(order -> order.getStatus() == Order.Status.DELIVERED).toList());
    }

}

