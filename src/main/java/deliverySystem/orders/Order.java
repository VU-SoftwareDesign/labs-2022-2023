package deliverySystem.orders;

import lombok.Getter;
import lombok.Setter;

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
    private String customerID;
    @Setter @Getter
    private long orderDate;
    @Setter @Getter
    private long deliveryDate;

    public Order(Status status, String customerID, long orderDate, long deliveryDate) {
        this.status = status;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }
}
