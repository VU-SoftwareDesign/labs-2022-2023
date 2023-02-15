package deliverySystem.orders;

import deliverySystem.people.Customer;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Order {
    public enum Status {
        RECEIVED("Received"),
        OTW("On the way"),
        DELIVERED("Delivered");

        private final String name;

        Status(String name) {
            this.name = name;
        }

        public String toString(){return this.name;}

    }
    @Setter @Getter
    private Order.Status status;
    @Setter @Getter
    private Customer customer;
    @Setter @Getter
    private long orderDate;
    @Setter @Getter
    private long deliveryDate;
    @Setter @Getter
    private List<Product> orderedProducts;

    public Order(Status status, Customer customer, long orderDate, long deliveryDate, List<Product> orderedProducts) {
        this.status = status;
        this.customer = customer;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderedProducts = orderedProducts;
    }
    // Check if this actually is safer than just adding a given order into the framework.
    public Order(Order order) {
        this.status = order.getStatus();
        this.customer = order.getCustomer();
        this.orderDate = order.getOrderDate();
        this.deliveryDate = order.getDeliveryDate();
        this.orderedProducts = order.getOrderedProducts();
    }
}
