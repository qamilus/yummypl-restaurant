package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

/**
 * POJO przechowujące informacje o MenuItem dla bazy danych.
 *
 * @author Kamil Swislowski
 */
public class MenuItem {
    private String name;
    private Double price;
    private Integer quantity;
    private Long menuId;

    public MenuItem() {
    }

    public MenuItem(String name, Double price, Integer quantity, Long menuId) {
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
        return "MenuItem{" +
                "restaurantName='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", menuId=" + menuId +
                '}';
    }
}
