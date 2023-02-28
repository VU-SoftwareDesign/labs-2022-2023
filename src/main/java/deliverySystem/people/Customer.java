package deliverySystem.people;

import deliverySystem.Company;
import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.util.DrivingLicence;
import deliverySystem.warehouse.items.Product;
import deliverySystem.warehouse.items.Vehicle;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public void placeOrder (LocalDate deliverDate) {
        // Make sure the order does not exceed the carrying capacity of the biggest vehicle in warehouse.
        List<Product> productsOrdered = Company.getInstance().getAvailableProducts();
        productsOrdered = productsOrdered.subList(0,50);
        if(productsOrdered.size() <= Vehicle.vehicleTypeToCapacity.get(DrivingLicence.Type.E)){
            createOrder(deliverDate, productsOrdered);
            OrderCollection.getInstance().addOrder(this.personalOrder.get());
        }
    }

    public void createOrder(LocalDate deliverDate, List<Product> productsOrdered) {
        Order order = new Order(Order.Status.PLACED, LocalDate.now(), deliverDate);
        order.setOrderedProducts(productsOrdered);
        order.setCustomer(this);
        this.personalOrder = Optional.of(order);
    }

    public void confirmReceive(){
        personalOrder.get().setReceived(true);
        OrderCollection.getInstance().updateOrder(this.personalOrder.get());
    }
}
