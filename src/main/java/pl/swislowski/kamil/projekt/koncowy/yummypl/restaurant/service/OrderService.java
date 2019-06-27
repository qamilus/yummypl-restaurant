package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.api.OrderStatusUtils;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.OrderDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Order;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Usługi związane z logiką biznesową dla Zamówienia. Łącznik pomiędzy warstwą prezentacji(JavaFX) i DAO.
 *
 * @author Kamil Swislowski
 */
public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    private OrderDao orderDao;

    /**
     * Konstruktor do którego przekazujemy DAO dla <code>{@link Order}</code>.
     *
     * @param orderDao DAO dla <code>{@link Order}</code>
     */
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Zwraca listę wszystkich zamówień w postaci modelu dla GUI.
     * Zamienia encje pochodzące z bazy danych <code>{@link Order}</code> na model <code>{@link OrderModel}</code> dla GUI.
     *
     * @param restaurantModel Dostajemy się w taki sposób do <code>{@link RestaurantModel}</code>
     * @return Lista wszystkich zamówień.
     */
    public List<OrderModel> list(RestaurantModel restaurantModel) {

        List<OrderModel> orderModelList = new ArrayList<>();
        try {
            final String restaurantIdString = restaurantModel.getId();
            final long restaurantId = Long.parseLong(restaurantIdString);

            final List<Order> orderEntityList = orderDao.list(restaurantId);

            for (Order order : orderEntityList) {
                OrderModel orderModel = new OrderModel();

                final Long id = order.getId();
                orderModel.setId(String.valueOf(id));
                orderModel.setRestaurantName(restaurantModel.getName());
                orderModel.setItemsQuantity(order.getItemsCount() + "");
                orderModel.setStatus(order.getStatus().getName());

                final Location location = order.getDeliveryLocation();
                if (location != null) {
                    orderModel.setDeliveryAddress(location.getCity() + ", " + location.getStreet() + " " + location.getHouseNumber());
                }
                orderModelList.add(orderModel);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return orderModelList;
    }
//TODO:
    /**
     * @param orderModel
     */
    public void update(OrderModel orderModel) {
        LOGGER.info("Updating model...");
        if (orderModel != null) {
            Order order = new Order();
            order.setId(Long.valueOf(orderModel.getId()));
            order.setStatus(OrderStatusUtils.mapOrderStatus(orderModel.getStatus()));

            try {
                orderDao.update(order);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
