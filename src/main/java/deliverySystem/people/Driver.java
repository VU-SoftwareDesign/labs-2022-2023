package deliverySystem.people;

import deliverySystem.orders.Order;
import deliverySystem.util.DrivingLicence;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class Driver extends Employee {
    @Getter @Setter
    private DrivingLicence drivingLicence;
    @Getter @Setter
    private Map<Customer, List<Order>> customerOrderMap;

    public Driver(String name, String address, int employeeID, DrivingLicence drivingLicence, boolean onLeave) {
        super(name, address, employeeID, onLeave);
        this.drivingLicence = drivingLicence;
    }
    public Driver(String name, String address, int employeeID, DrivingLicence drivingLicence, Map<Customer,List<Order>> customerOrderMap, boolean onLeave) {
        super(name, address, employeeID, onLeave);
        this.drivingLicence = drivingLicence;
        this.customerOrderMap = customerOrderMap;
    }

}
