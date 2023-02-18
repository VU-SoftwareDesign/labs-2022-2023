package deliverySystem.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DrivingLicence {
    public enum Type {
        B("Car"),
        C("Small Truck"),
        D("Big Truck"),
        E("Lorry");

        private final String name;
        Type(String name) {this.name = name;}
        public String getName(){return this.name;}
        public char toChar() {return this.toString().toCharArray()[0];}
    }
    @Getter @Setter
    private String ID;

    @Getter @Setter
    private Type biggestVehicleType;
    @Getter @Setter
    private List<DrivingLicence.Type> eligibleVehicleTypes;
    @Getter @Setter
    private String expiryDate;

    public DrivingLicence(String ID, DrivingLicence.Type biggestVehicleType, String expiryDate) {
        this.ID = ID;
        this.biggestVehicleType = biggestVehicleType;
        this.eligibleVehicleTypes = Arrays.stream(Type.values()).filter( t -> t.toChar() <= biggestVehicleType.toChar()).collect(Collectors.toList());
        this.expiryDate = expiryDate;
    }
}
