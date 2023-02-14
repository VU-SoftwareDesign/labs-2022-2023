package deliverySystem.people;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Manager extends Employee{
    @Setter @Getter
    private List<Driver> driversToManage;
    public Manager(String name, String address, int employeeID, List<Driver> driversToManage, boolean onLeave) {
        super(name, address, employeeID, onLeave);
        this.driversToManage = driversToManage;
    }
}
