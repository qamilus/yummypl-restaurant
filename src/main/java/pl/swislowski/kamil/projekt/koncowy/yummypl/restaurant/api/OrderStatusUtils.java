package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.api;

/**
 * @author Kamil Swislowski
 */
public class OrderStatusUtils {


    public static OrderStatus mapOrderStatus(String orderStatusName) {
        if (orderStatusName != null) {
            if (orderStatusName.toLowerCase().contains("new")) {
                return OrderStatus.NEW;
            } else if (orderStatusName.toLowerCase().contains("progress")) {
                return OrderStatus.IN_PROGRESS;
            } else if (orderStatusName.toLowerCase().contains("done")) {
                return OrderStatus.DONE;
            } else if (orderStatusName.toLowerCase().contains("canceled")) {
                return OrderStatus.CANCELED;
            }
        }
        return null;
    }
}
