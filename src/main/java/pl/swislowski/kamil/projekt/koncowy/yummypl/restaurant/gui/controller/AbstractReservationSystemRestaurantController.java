package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.stage.Stage;

/**
 * Klasa bazowa dla kontrolerów GUI w JavaFX.
 *
 * @author Kamil Swislowski
 */
public abstract class AbstractReservationSystemRestaurantController {

    protected Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
