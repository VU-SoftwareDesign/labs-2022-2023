package deliverySystem.orders;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DailyOrders {
    @Setter @Getter
    private List<Order> orders;

    public DailyOrders(List<Order> orders) {
        this.orders = orders;
    }
}
