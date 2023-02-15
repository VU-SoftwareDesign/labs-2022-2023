package deliverySystem.people;

import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Customer extends Person {
    /*
    * Why not have one ID in person and customer and employees inherit it? Could create some internal distinction between the two
    */
    @Getter @Setter
    private int customerID;
    public Customer(String name, String address, int customerID) {
        super(name, address);
        this.customerID = customerID;
    }

    public void placeOrder(List<Product> toOrder, long deliverDate){
        OrderCollection.getInstance().addOrder(new Order(Order.Status.RECEIVED, this, System.currentTimeMillis(), deliverDate, toOrder));
    }
}
