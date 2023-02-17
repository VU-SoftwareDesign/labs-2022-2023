package deliverySystem.people;

import deliverySystem.orders.OrderCollection;
import deliverySystem.orders.Order;
import deliverySystem.warehouse.Warehouse;
import deliverySystem.warehouse.items.Vehicle;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Manager extends Employee{
    @Getter
    private Map<Driver, Vehicle> dispatchedDrivers;
    Logger log;
    public Manager(String name, String address, boolean onLeave) {
        super(name, address, onLeave);
        this.log = Logger.getLogger(Manager.class.getName());
    }

    public void dispatchOrders(Warehouse warehouse) {
        dispatchOrders(warehouse.getAvailableDrivers(),warehouse.getAvailableVehicles(), OrderCollection.getInstance().getOrders());
    }

    private void dispatchOrders(List<Driver> drivers, List<Vehicle> vehicles, List<Order> orders) {
         this.dispatchedDrivers = assignOrders(getAssignedDriverToVehicle(drivers,vehicles),orders);
    }

    private Map<Driver, Vehicle> getAssignedDriverToVehicle(List<Driver> drivers, List<Vehicle> vehicles) {
        Map<Driver, Vehicle> assignedDriverToVehicle = new HashMap<>();
        List<Vehicle> descentingTypeVehicles = getVehiclesDescendingType(vehicles);
        descentingTypeVehicles.forEach(currentVehicle -> {
            for (Driver currentDriver : drivers) {
                if (!currentDriver.isInTransit() && currentDriver.getDrivingLicence().getEligibleVehicleTypes().contains(currentVehicle.getVehicleType())) {
                    assignedDriverToVehicle.put(currentDriver, currentVehicle);
                    currentDriver.setInTransit(true);
                    currentVehicle.setInUse(true);
                    break;
                }
            }
        });
        return assignedDriverToVehicle;
    }

    private List<Vehicle> getVehiclesDescendingType(List<Vehicle> vehicles) {
        List<Vehicle> bigToSmallVehicles = new ArrayList<>(vehicles);
        for(int i = 1; i < bigToSmallVehicles.size(); i++) {
            Vehicle key = bigToSmallVehicles.get(i);
            int j = i - 1;
            while(j >=0 && bigToSmallVehicles.get(j).getVehicleType().toChar() < key.getVehicleType().toChar()) {
                bigToSmallVehicles.set(j + 1, bigToSmallVehicles.get(j));
                j = j - 1;
            }
            bigToSmallVehicles.set(j + 1, key);
        }
        return bigToSmallVehicles;
    }

    private Map<Driver,Vehicle> assignOrders (Map<Driver,Vehicle> assignedDriversToVehicle, List<Order> ordersToAssign) {
        AtomicInteger counter = new AtomicInteger();
        assignedDriversToVehicle.keySet().forEach(currentDriver-> {
            if(counter.get() < ordersToAssign.size()) {
                currentDriver.assignOrder(ordersToAssign.get(counter.get()));
                ordersToAssign.get(counter.get()).setStatus(Order.Status.OTW);
            }
            counter.addAndGet(1);
        });
        return assignedDriversToVehicle;
    }

    public void discardCompletedOrders() {
        this.dispatchedDrivers.keySet().forEach(currentDriver->{
            if(currentDriver.getAssignedOrder().getStatus().equals(Order.Status.DELIVERED)) {
                OrderCollection.getInstance().removeOrder(currentDriver.getAssignedOrder());
            }
        });
    }

}

