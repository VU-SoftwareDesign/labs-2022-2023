package deliverySystem.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class OrderCollection {

    private static OrderCollection instance = null;
    @Setter @Getter
    private List<Order> orders;

    private OrderCollection(){
        orders = new ArrayList<>();
    }
    public static OrderCollection getInstance(){
        if(instance == null) instance = new OrderCollection();

        return instance;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void removeOrder(Order order){
        this.orders.remove(order);
    }

}
