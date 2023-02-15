package deliverySystem.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<DrivingLicence.Type> eligibleVehicleTypes;
    @Getter @Setter
    private String expiryDate;

    public DrivingLicence(String ID, List<DrivingLicence.Type> eligibleVehicleTypes, String expiryDate) {
        this.ID = ID;
        this.eligibleVehicleTypes = eligibleVehicleTypes;
        this.expiryDate = expiryDate;
    }
}
