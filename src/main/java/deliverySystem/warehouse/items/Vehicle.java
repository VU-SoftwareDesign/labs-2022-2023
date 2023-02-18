package deliverySystem.warehouse.items;

import deliverySystem.util.DrivingLicence;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static java.lang.Math.abs;

public class Vehicle {
    @Setter @Getter
    private DrivingLicence.Type vehicleType;
    @Setter @Getter
    private Integer mileage;
    @Setter @Getter
    private long nextServiceDate;
    // payload capacity is the total number of products a given truck can carry
    @Setter @Getter
    private boolean isOperable;
    @Setter @Getter
    private String numberPlate;
    @Setter @Getter
    private boolean inUse;
    @Setter @Getter
    private int productCapacity;

    @Getter
    public static final Map<DrivingLicence.Type, Integer> vehicleTypeToCapacity = new HashMap<>(){{
        put(DrivingLicence.Type.B, 50);
        put(DrivingLicence.Type.C, 100);
        put(DrivingLicence.Type.D, 200);
        put(DrivingLicence.Type.E, 400);
    }};

    public Vehicle(DrivingLicence.Type vehicleType, Integer mileage, long nextServiceDate, String numberPlate, boolean isOperable) {
        this.vehicleType = vehicleType;
        this.mileage = mileage;
        this.nextServiceDate = nextServiceDate;
        this.productCapacity = vehicleTypeToCapacity.get(this.vehicleType);
        this.numberPlate = numberPlate;
        this.isOperable = isOperable;
    }

    public static Vehicle optimalVehicleType(List<Vehicle> vehicles, int orderSize){
        List<Integer> deltas = new ArrayList<>(vehicles.size());
        for (Vehicle vehicle : vehicles) {
            deltas.add(abs(orderSize - vehicle.productCapacity));
        }
        return vehicles.get(deltas.indexOf(Collections.min(deltas)));
    }
}
