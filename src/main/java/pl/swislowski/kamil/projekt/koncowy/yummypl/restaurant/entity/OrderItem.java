package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

public class OrderItem {
    private String name;
    private Double price;
    private Integer quantity;
    private Long orderId;

    public OrderItem() {
    }

    public OrderItem(String name, Double price, Integer quantity, Long orderId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "restaurantName='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", orderId=" + orderId +
                '}';
    }
}
