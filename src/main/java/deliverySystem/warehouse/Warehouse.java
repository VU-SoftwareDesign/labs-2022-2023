package deliverySystem.warehouse;

import deliverySystem.people.Driver;
import deliverySystem.people.Employee;
import deliverySystem.people.Manager;
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
    private Manager manager;

    public Warehouse(List<Vehicle> vehicles, List<Driver> drivers, Manager manager) {
        this.vehicles = vehicles;
        this.drivers = drivers;
        this.manager = manager;
    }

    public List<Driver> getAvailableDrivers() {
        return this.drivers.stream().filter(currentDriver-> !currentDriver.isOnLeave()).toList();
    }

    public List<Vehicle> getAvailableVehicles() {
        return this.vehicles.stream().filter(Vehicle::isOperable).toList();
    }

}
