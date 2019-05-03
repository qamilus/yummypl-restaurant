package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

import java.util.List;

public class Menu {
    private Long id;
    private List<MenuItem> menuItems;
    private Long restaurantId;

    public Menu() {
    }

    public Menu(List<MenuItem> menuItems, Long restaurantId) {
        this.menuItems = menuItems;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuItems=" + menuItems +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
