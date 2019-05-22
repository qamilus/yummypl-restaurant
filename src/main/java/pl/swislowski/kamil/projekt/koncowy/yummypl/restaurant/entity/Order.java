package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

import java.util.List;

public class Order {
    private Long id;
    private List<OrderItem> orderItems;
    private Integer itemsCount;
    private OrderStatus status;
    private Location deliveryLocation;
    private Long restaurantId;

    private Restaurant restaurant;

    public Order() {
    }

    public Order(List<OrderItem> orderItems, OrderStatus status, Location deliveryLocation, Long restaurantId) {
        this.orderItems = orderItems;
        this.status = status;
        this.deliveryLocation = deliveryLocation;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Location deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", status=" + status +
                ", deliveryLocation=" + deliveryLocation +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
