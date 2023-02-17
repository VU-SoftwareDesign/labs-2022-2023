package deliverySystem.people;

import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.warehouse.items.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Customer extends Person {
    /*
    * Why not have one ID in person and customer and employees inherit it? Could create some internal distinction between the two
    */
    @Getter @Setter
    private UUID customerID;
    public Customer(String name, String address) {
        super(name, address);
        this.customerID = UUID.randomUUID();
    }

    public void placeOrder(List<Product> toOrder, LocalDate deliverDate){
        OrderCollection.getInstance().addOrder(new Order(Order.Status.RECEIVED, this, LocalDate.now(), deliverDate, toOrder));
    }
}
