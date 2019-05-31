package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service;

import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.OrderItemDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity.OrderItem;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderItemModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderModel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderItemService {

    private static final Logger LOGGER = Logger.getLogger(OrderItemService.class.getName());

    private OrderItemDao orderItemDao;

    public OrderItemService(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public List<OrderItemModel> list(OrderModel orderModel) {
        List<OrderItemModel> orderItemModelList = new ArrayList<>();

        String orderIdString = orderModel.getId();
        Long orderId = Long.valueOf(orderIdString);

        try {
            List<OrderItem> orderItemEntityList = orderItemDao.list(orderId);

            LOGGER.info("OrderItemsList from DAO : " + orderItemEntityList);

            for (OrderItem orderItem : orderItemEntityList) {
                OrderItemModel orderItemModel = new OrderItemModel();

                Long id = orderItem.getId();
                orderItemModel.setId(String.valueOf(id));

                String name = orderItem.getName();
                orderItemModel.setName(name);

                Double price = orderItem.getPrice();
                orderItemModel.setPrice(String.valueOf(price));

                Integer quantity = orderItem.getQuantity();
                orderItemModel.setQuantity(String.valueOf(quantity));

//                double sum = price * quantity;
                BigDecimal priceBigDecimal = new BigDecimal(price);
                BigDecimal sum = priceBigDecimal.multiply(new BigDecimal(quantity));
                BigDecimal sumScale = sum.setScale(2, BigDecimal.ROUND_HALF_UP);
                orderItemModel.setSum(String.valueOf(sumScale));

                orderItemModelList.add(orderItemModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItemModelList;
    }
}
