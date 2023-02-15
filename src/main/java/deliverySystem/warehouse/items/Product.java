package deliverySystem.warehouse.items;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Product {
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Double price;
    @Setter @Getter
    private Integer amount;
    @Setter @Getter
    private UUID ID;

    public Product(String name, Double price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.ID = UUID.randomUUID();
    }

    public boolean inStock() {return amount != 0;}
}
