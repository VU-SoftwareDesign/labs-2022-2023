package deliverySystem.people;

import lombok.Getter;
import lombok.Setter;

public abstract class Person {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
