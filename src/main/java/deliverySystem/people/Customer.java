package deliverySystem.people;

import lombok.Getter;
import lombok.Setter;

public class Customer extends Person {
    /*
    * Why not have one ID in person and customer and employees inherit it? Could create some internal distinction between the two
    */
    @Getter @Setter
    private int customerID;
    public Customer(String name, String address, int customerID) {
        super(name, address);
        this.customerID = customerID;
    }
}
