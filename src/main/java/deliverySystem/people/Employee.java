package deliverySystem.people;

import lombok.Getter;
import lombok.Setter;

public class Employee extends Person {
    @Getter @Setter
    private int employeeID;
    @Setter @Getter
    private boolean onLeave;

    public Employee(String name, String address, int employeeID, boolean onLeave) {
        super(name, address);
        this.employeeID = employeeID;
        this.onLeave = onLeave;
    }
}
