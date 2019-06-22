package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

/**
 * @author Kamil Swislowski
 */
public class RestaurantInformation {
    private Long id;
    private String openingHours;
    private Long restaurantId;

    public RestaurantInformation() {
    }

    public RestaurantInformation(String openingHours, Long restaurantId) {
        this.openingHours = openingHours;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "RestaurantInformation{" +
                "openingHours='" + openingHours + '\'' +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
