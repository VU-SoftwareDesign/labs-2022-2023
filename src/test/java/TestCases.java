import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.people.Customer;
import deliverySystem.people.Driver;
import deliverySystem.people.Manager;
import deliverySystem.util.DrivingLicence;
import deliverySystem.warehouse.Warehouse;
import deliverySystem.warehouse.items.Product;
import deliverySystem.warehouse.items.Vehicle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCases {
    List<Order> orders = new ArrayList<>();
    List<Driver> drivers = new ArrayList<>();
    OrderCollection orderCollection;
    Warehouse warehouse;
    List<Vehicle> vehicles  = new ArrayList<>();
    List<Product> products = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();

    @Before
    public void populateData() {
        populateCustomers();
        populateProducts();
        populateVehicles();
        populateDrivers();
        populateOrders();
        orderCollection = OrderCollection.getInstance();
        orderCollection.setOrders(orders);
        populateWarehouse();
    }

    private void populateCustomers() {
        for(int i = 0; i < 10; i++) {
            customers.add(new Customer("Bob-"+ i, "homeless"));
        }
    }

    private void populateVehicles() {
        //Change it to 5, handpick creation of vehicles
        for(int i = 0; i < 10; i++) {
            int currentIndex = i;
            Arrays.stream(DrivingLicence.Type.values()).forEach(currentType -> {
                vehicles.add(new Vehicle(currentType, 0, 0, "GB-" + currentIndex));
            });
        }
    }

    private void populateProducts() {
        for(int i = 0; i < 100; i++) {products.add(new Product("Product-"+ (i), 99.00, 1000));}
    }

    private void populateDrivers() {
        for(int i = 0; i < 10; i++) {
            DrivingLicence drivingLicence = new DrivingLicence("1234-testing-testing-"+i, Arrays.asList(DrivingLicence.Type.B, DrivingLicence.Type.C, DrivingLicence.Type.D), "101010");
            drivers.add(new Driver("marly-"+i, "homeless",drivingLicence, false));
        }
    }

    private void populateOrders() {
        List<List<Product>> subListsOfProducts = new ArrayList<>();
        int counter  = -1;
        while(counter < products.size() - 1) {
            subListsOfProducts.add(products.subList(counter+1, counter+20));
            counter += 20;
        }
        for(int i =0; i < subListsOfProducts.size(); i++) {
            orders.add(new Order(Order.Status.RECEIVED, customers.get(i), 0,0, subListsOfProducts.get(i)));
        }
    }
    private void populateWarehouse() {
        warehouse = new Warehouse();
        warehouse.setDrivers(drivers);
        warehouse.setVehicles(vehicles);
        warehouse.setManager(new Manager("bobby", "homeless", false));
    }
    @Test
    public void testDispatch() {
        warehouse.getManager().dispatchOrders(warehouse);
        warehouse.getManager().getDispatchedDrivers().forEach((driver, vehicle)-> {
            System.out.println(driver.getName() + " "+ vehicle.getNumberPlate());
        });
    }
}
