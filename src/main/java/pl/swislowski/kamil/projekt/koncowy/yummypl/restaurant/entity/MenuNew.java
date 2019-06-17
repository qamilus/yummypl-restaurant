package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

import java.util.List;

public class MenuNew {
    private Long id;
    private List<MenuItemNew> menuItems;
    private Long restaurantId;

    public MenuNew() {
    }

    public MenuNew(List<MenuItemNew> menuItems, Long restaurantId) {
        this.menuItems = menuItems;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MenuItemNew> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemNew> menuItems) {
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
        return "MenuNew{" +
                "id=" + id +
                ", menuItems=" + menuItems +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
