package deliverySystem.warehouse.items;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Setter @Getter
    private String name;
    @Setter @Getter
    private Double price;
    @Setter @Getter
    private Integer amount;
    @Setter @Getter
    private boolean inStock;

    public Product(String name, Double price, Integer amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.inStock = (amount != 0);
    }
}
