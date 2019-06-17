package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

public class MenuItemNew {
    private String name;
    private Double price;
    private Integer quantity;
    private Long menuId;

    public MenuItemNew() {
    }

    public MenuItemNew(String name, Double price, Integer quantity, Long menuId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.menuId = menuId;
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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "MenuItemNew{" +
                "restaurantName='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", menuId=" + menuId +
                '}';
    }
}
