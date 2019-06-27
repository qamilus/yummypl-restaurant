package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.api;

/**
 * Klasa narzędziowa do obsługi statusów zamówienia.
 *
 * @author Kamil Swislowski
 */
public class OrderStatusUtils {

    /**
     * Tłumaczy tekstowe statusy z GUI na statusy w modelu i encji.
     *
     * @param orderStatusName Tekstowa reprezentacja statusu, którą należy przetłumaczyć.
     * @return <code>{@link OrderStatus}</code> Status zamówienia w postaci ENUM, typ wyliczeniowy.
     */
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
