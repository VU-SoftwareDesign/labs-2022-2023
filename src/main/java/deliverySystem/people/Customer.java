package deliverySystem.people;

import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.util.DrivingLicence;
import deliverySystem.warehouse.items.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class Customer extends Person {
    /*
    * Why not have one ID in person and customer and employees inherit it? Could create some internal distinction between the two
    */
    @Getter @Setter
    private UUID customerID;

    @Setter @Getter
    private Optional<Order> personalOrder;

    public Customer(String name, String address) {
        super(name, address);
        this.customerID = UUID.randomUUID();
    }

    public Customer(Customer customer) {
        super(customer.getName(),customer.getAddress());
        this.customerID = customer.getCustomerID();
        this.personalOrder = customer.getPersonalOrder();
    }

    public Boolean placeOrder(LocalDate deliverDate){
        if(personalOrder.get().getOrderedProducts().size() <= Vehicle.vehicleTypeToCapacity.get(DrivingLicence.Type.E)){
            Order order = new Order(Order.Status.PLACED, LocalDate.now(), deliverDate);
            order.setCustomer(this);
            OrderCollection.getInstance().addOrder(order);
            return true;
        }else return false;
    }

    public void confirmReceive(){
        personalOrder.get().setReceived(true);
        OrderCollection.getInstance().updateOrder(this.personalOrder.get());
    }
}
