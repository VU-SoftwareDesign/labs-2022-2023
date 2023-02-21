package deliverySystem.people;

import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.util.DrivingLicence;
import deliverySystem.warehouse.items.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

public class Customer extends Person {
    /*
    * Why not have one ID in person and customer and employees inherit it? Could create some internal distinction between the two
    */
    @Getter @Setter
    private UUID customerID;

    @Setter @Getter
    private Order personalOrder;

    public Customer(String name, String address) {
        super(name, address);
        this.customerID = UUID.randomUUID();
    }

    public Boolean placeOrder(LocalDate deliverDate){
        if(personalOrder.getOrderedProducts().size() <= Vehicle.vehicleTypeToCapacity.get(DrivingLicence.Type.E)){
            OrderCollection.getInstance().addOrder(new Order(Order.Status.PLACED, this, LocalDate.now(), deliverDate));
            return true;
        }else return false;
    }

    public void confirmReceive(){
        for(Order order: OrderCollection.getInstance().getOrders()){
            if(order.getOrderID() == personalOrder.getOrderID()){
                order.setReceived(true);
            }
        }
    }
}
