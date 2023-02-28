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
    public Product(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.ID = product.getID();
    }

    public void restock(Integer quantity){ amount += quantity;}
    public void reserveProduct() {this.amount--;}

    public boolean canReserve() {return !(amount == 0);}
}
