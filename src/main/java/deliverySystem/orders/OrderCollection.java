package deliverySystem.orders;

import deliverySystem.people.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderCollection {

    private static OrderCollection instance = null;
    private List<Order> orders;
    private final Logger log;

    private OrderCollection(){
        orders = new ArrayList<>();
        this.log = Logger.getLogger(this.toString());
    }
    public static OrderCollection getInstance(){
        if(instance == null) instance = new OrderCollection();
        return instance;
    }

    public void addOrder(Order order){
        this.orders.add(new Order(order));
    }

    public void removeOrder(Order toRemove){
        Optional<Order> order = findOrder(toRemove);
        if(order.isEmpty()) {log.warning("Order not present in list");}
        else this.orders.remove(order.get());
    }

    private Optional<Order> findOrder(Order toFind) {
        // UUID.compare to 0 for true, -1 for false.
        for (Order currentOrder: this.orders) {
            if(currentOrder.getOrderID().compareTo(toFind.getOrderID()) == 0)
                return Optional.of(new Order(currentOrder));}
        return Optional.empty();
    }

    public List<Order> getOrders() {
        List<Order> toReturn = new ArrayList<>();
        this.orders.forEach(currentOrder -> toReturn.add(new Order(currentOrder)));
        return toReturn;
    }

    public void setOrders(List<Order> orders) {
        this.orders = new ArrayList<>();
        orders.forEach(currentOrder-> this.orders.add(new Order(currentOrder)));
    }

    public void updateOrder(Order toUpdate) {
        for (int i = 0; i < this.orders.size(); i++) {
            if(toUpdate.getOrderID().compareTo(this.orders.get(i).getOrderID()) == 0) this.orders.set(i,new Order(toUpdate));
        }
    }
}
