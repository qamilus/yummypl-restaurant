package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

/**
 * POJO przechowujące informacje o Restauracji dla bazy danych.
 *
 * @author Kamil Swislowski
 */
public class Restaurant {
    private Long id;
    private String name;
    private Menu menu;
    private Location location;
    private RestaurantInformation information;

    public Restaurant() {
    }

    public Restaurant(String name, Menu menu, Location location, RestaurantInformation information) {
        this.name = name;
        this.menu = menu;
        this.location = location;
        this.information = information;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public RestaurantInformation getInformation() {
        return information;
    }

    public void setInformation(RestaurantInformation information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", restaurantName='" + name + '\'' +
                ", menu=" + menu +
                ", locationModel=" + location +
                ", information=" + information +
                '}';
    }
}
