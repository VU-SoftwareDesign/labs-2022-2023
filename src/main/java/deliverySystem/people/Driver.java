package deliverySystem.people;

import deliverySystem.orders.Order;
import deliverySystem.orders.OrderCollection;
import deliverySystem.util.DrivingLicence;
import lombok.Getter;
import lombok.Setter;

public class Driver extends Employee {
    @Getter @Setter
    private DrivingLicence drivingLicence;
    @Getter @Setter
    private Order assignedOrder;
    @Getter @Setter
    private boolean inTransit;

    public Driver(String name, String address, DrivingLicence drivingLicence, boolean onLeave) {
        super(name, address, onLeave);
        this.drivingLicence = drivingLicence;
    }

    public void assignOrder(Order order) {
        this.assignedOrder = order;
    }

    public void completeOrder() {
        for(Order order: OrderCollection.getInstance().getOrders()){
            if(order.getOrderID() == assignedOrder.getOrderID()){
                order.setStatus(Order.Status.DELIVERED);
            }
        }
    }
}
