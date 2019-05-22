package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.OrderDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Location;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.Order;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

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

//                final Restaurant restaurant = order.getRestaurant();
//                if (restaurant != null) {
//                    orderModel.setRestaurantName(restaurant.getRestaurantName());
//                }

//                final List<OrderItem> orderItems = order.getOrderItems();
//                if (orderItems != null) {
//                    for (OrderItem orderItem : orderItems) {
//                        final Integer quantity = orderItem.getQuantity();
//                        orderModel.setItemsQuantity(String.valueOf(quantity));
//                    }
//                }

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
}
