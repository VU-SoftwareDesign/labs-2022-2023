package deliverySystem.orders;

import deliverySystem.people.Customer;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Order {

    public enum Status {
        PLACED("Received"),
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

    private Optional<Customer> customer;
    @Setter @Getter
    private LocalDate orderDate;
    @Setter @Getter
    private LocalDate deliveryDate;
    private List<Product> orderedProducts;

    @Setter @Getter
    private Boolean received;

    public Order(Status status, LocalDate orderDate, LocalDate deliveryDate) {
        this.orderID = UUID.randomUUID();
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.received = false;
    }
    // Check if this actually is safer than just adding a given order into the framework.
    public Order(Order order) {
        this.orderID = order.getOrderID();
        this.status = order.getStatus();
        this.customer = order.getCustomer();
        this.orderDate = order.getOrderDate();
        this.deliveryDate = order.getDeliveryDate();
        this.orderedProducts = order.getOrderedProducts();
        this.received = order.getReceived();
    }

    public Optional<Customer> getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = Optional.of(new Customer(customer));
    }

    public List<Product> getOrderedProducts() {
        return new ArrayList<>(this.orderedProducts);
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = new ArrayList<>(orderedProducts);
    }
}
