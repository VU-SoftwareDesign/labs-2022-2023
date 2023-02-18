package deliverySystem;

import deliverySystem.warehouse.Warehouse;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

public class Company {
    @Setter @Getter
    private String name;
    @Setter @Getter
    private List<Product> productRange;
    @Setter @Getter
    private List<Warehouse> warehouses;
    private static Company instance;

    private Company() {}

    public static Company getInstance() {
        if(instance==null) instance = new Company();
        return instance;
    }

    public void discontinue(Product product){
        productRange.removeIf(product1 -> Objects.equals(product1.getName(), product.getName()));
        for(Warehouse w: warehouses){
            w.getStocks().removeIf(product2 -> Objects.equals(product2.getName(), product.getName()));
        }
    }
}
