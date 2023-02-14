package deliverySystem.warehouse.items;

import deliverySystem.util.DrivingLicence;
import lombok.Getter;
import lombok.Setter;

public class Vehicle {
    @Setter @Getter
    private DrivingLicence.Type vehicleType;
    @Setter @Getter
    private Integer mileage;
    @Setter @Getter
    private long nextServiceDate;
    @Setter @Getter
    private Integer payLoadCapacity;
    @Setter @Getter
    private boolean isOperable;

    public Vehicle(DrivingLicence.Type vehicleType, Integer mileage, long nextServiceDate, Integer payLoadCapacity) {
        this.vehicleType = vehicleType;
        this.mileage = mileage;
        this.nextServiceDate = nextServiceDate;
        this.payLoadCapacity = payLoadCapacity;
    }
}
