package deliverySystem.orders;

import deliverySystem.people.Customer;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    @Getter
    private final UUID orderID;
    @Setter @Getter
    private Order.Status status;
    @Setter @Getter
    private Customer customer;
    @Setter @Getter
    private LocalDate orderDate;
    @Setter @Getter
    private LocalDate deliveryDate;
    @Setter @Getter
    private List<Product> orderedProducts;

    public Order(Status status, Customer customer, LocalDate orderDate, LocalDate deliveryDate, List<Product> orderedProducts) {
        this.orderID = UUID.randomUUID();
        this.status = status;
        this.customer = customer;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderedProducts = orderedProducts;
    }
    // Check if this actually is safer than just adding a given order into the framework.
    public Order(Order order) {
        this.orderID = order.getOrderID();
        this.status = order.getStatus();
        this.customer = order.getCustomer();
        this.orderDate = order.getOrderDate();
        this.deliveryDate = order.getDeliveryDate();
        this.orderedProducts = order.getOrderedProducts();
    }

    public int getOrderSize(){
        return orderedProducts.size();
    }
}
