package deliverySystem;

import deliverySystem.warehouse.Warehouse;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    public void restockProducts() {
        this.warehouses.forEach(currentWarehouse -> {
            currentWarehouse.getStocks().forEach(currentProduct -> {
                if(!currentProduct.canReserve()) currentProduct.restock(50);
            });
        });
    }
    
    public List<Product> getAvailableProducts() {
        List<Product> allProducts = new ArrayList<>();
        this.productRange.forEach(currentProduct -> allProducts.add(new Product(currentProduct)));
        return allProducts.stream().filter(Product::canReserve).toList();
    }
}
