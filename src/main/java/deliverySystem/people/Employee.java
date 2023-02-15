package deliverySystem.people;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Employee extends Person {
    @Getter @Setter
    private UUID employeeID;
    @Setter @Getter
    private boolean onLeave;
    @Setter @Getter
    private int workedHours;
    @Setter @Getter
    private Double hourlyRate;

    public Employee(String name, String address, boolean onLeave) {
        super(name, address);
        this.employeeID = UUID.randomUUID();
        this.onLeave = onLeave;
    }

    public Double calculateSalary() {
        return this.workedHours * this.hourlyRate;
    }
}
