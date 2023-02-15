package deliverySystem.warehouse;

import deliverySystem.people.Driver;
import deliverySystem.people.Employee;
import deliverySystem.people.Manager;
import deliverySystem.warehouse.items.Product;
import deliverySystem.warehouse.items.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Warehouse {
    @Setter @Getter
    private List<Vehicle> vehicles;
    @Setter @Getter
    private List<Driver> drivers;
    @Setter @Getter
    private List<Product> stocks;
    @Setter @Getter
    private Manager manager;
    private static Warehouse instance;
    private Warehouse(){}
    public static Warehouse getInstance() {
        if(instance==null) instance = new Warehouse();
        return instance;
    }

    public List<Driver> getAvailableDrivers() {
        return this.drivers.stream().filter(currentDriver-> !currentDriver.isOnLeave()).toList();
    }

    public List<Vehicle> getAvailableVehicles() {
        return this.vehicles.stream().filter(Vehicle::isOperable).toList();
    }

    public List<Product> getAvailableProducts() {
        return this.stocks.stream().filter(Product::inStock).toList();
    }

    public void substractStock(Product product) {
        this.stocks.forEach(currentProduct-> {
            if(currentProduct.getID().equals(product.getID())) {currentProduct.setAmount(currentProduct.getAmount() - product.getAmount());}
        });
    }

}
