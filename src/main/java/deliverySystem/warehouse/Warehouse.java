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

    public List<Driver> getAvailableDrivers() {
        return this.drivers.stream().filter(currentDriver-> !currentDriver.isOnLeave() && !currentDriver.isInTransit()).toList();
    }
    public List<Vehicle> getAvailableVehicles() {
        return this.vehicles.stream().filter(Vehicle::isOperable).toList();
    }

    public Product getProduct(Product product){
        for(Product p: stocks){
            if(p.getName().equals(product.getName())){
                return p;
            }
        }
        return null;
    }
}
