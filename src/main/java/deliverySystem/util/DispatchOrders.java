package deliverySystem.util;

import deliverySystem.orders.Order;
import deliverySystem.people.Customer;
import deliverySystem.people.Driver;
import deliverySystem.warehouse.items.Product;
import deliverySystem.warehouse.items.Vehicle;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DispatchOrders {

    public static Map<Driver,Vehicle> dispatchOrders(List<Driver> drivers, List<Vehicle> vehicles, List<Order> orders) {
        return assignOrders(getAssignedDriverToVehicle(drivers,vehicles),orders);
    }

    private static Map<Driver, Vehicle> getAssignedDriverToVehicle(List<Driver> drivers, List<Vehicle> vehicles) {
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

    private static List<Vehicle> getVehiclesDescendingType(List<Vehicle> vehicles) {
        vehicles.forEach(currentVehicle->System.out.println(currentVehicle.getVehicleType().toChar()));
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

        bigToSmallVehicles.forEach(currentVehicle->System.out.println(currentVehicle.getVehicleType().toChar()));

        return bigToSmallVehicles;
    }

    private static Map<Driver,Vehicle> assignOrders (Map<Driver,Vehicle> assignedDriversToVehicle, List<Order> ordersToAssign) {
        AtomicInteger counter = new AtomicInteger();
        assignedDriversToVehicle.keySet().forEach(currentDriver-> {
            if(counter.get() < ordersToAssign.size()) {
                currentDriver.assignOrder(ordersToAssign.get(counter.get()));
            }
            counter.addAndGet(1);
        });
        return assignedDriversToVehicle;
    }
    public static void main(String[] args){
        List<Order> orders = new ArrayList<>();
        List<Driver> drivers = new ArrayList<>();
        List<Vehicle> vehicles  = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        Customer customer = new Customer("bob", "homeless", 0);
        for(int i = 0; i < 4; i++) {
            int finalI = i;
            Arrays.stream(DrivingLicence.Type.values()).forEach(currentType -> {
                vehicles.add(new Vehicle(currentType,0,0, "GB-"+ finalI));
            });
        }
        for (int i = 0; i < 10; i++) {
            products.add(new Product("test"+ (i), 99.00, 1000));
            DrivingLicence drivingLicence = new DrivingLicence("1234-testing-testing-"+i, Arrays.asList(DrivingLicence.Type.B, DrivingLicence.Type.C, DrivingLicence.Type.D), "101010");
            drivers.add(new Driver("marly-"+i, "homeless", 99,drivingLicence, false));

            Order order = new Order(Order.Status.RECEIVED, customer, 0,0);
            order.setOrderedProducts(products);
            orders.add(order);
        }
        dispatchOrders(drivers,vehicles,orders);

    }

}
