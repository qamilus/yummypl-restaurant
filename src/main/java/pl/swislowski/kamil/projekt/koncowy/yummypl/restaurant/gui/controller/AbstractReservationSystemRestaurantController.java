package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

public abstract class AbstractReservationSystemRestaurantController {

    private ObservableList<RestaurantModel> restaurants;
    protected Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setRestaurants(ObservableList<RestaurantModel> restaurants) {
        this.restaurants = restaurants;
    }
}
