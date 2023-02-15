package deliverySystem.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderCollection {
    @Setter @Getter
    private List<Order> orders;
    private static OrderCollection instance;

    private OrderCollection() {}

    public static OrderCollection getInstance() {
        if(instance == null) instance = new OrderCollection();
        return instance;
    }

    public void addOrder(Order order) {
        this.orders.add(new Order(order));
    }

    public void removeOrder(Order order){this.orders.remove(order);}
}
