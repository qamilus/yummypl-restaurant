package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Kontroler obsługujący widok i model dla GUI. JavaFX MVC - Model View Controller.
 *
 * @author Kamil Swislowski
 */
public class ReservationSystemRestaurantController extends AbstractReservationSystemRestaurantController{
    private static final Logger LOGGER = Logger.getLogger(ReservationSystemRestaurantController.class.getName());

    /**
     * Obsługa przycisku wyświetlającego listę Restauracji.
     */
    public void startButtonOnAction() {
        LOGGER.info("Clicked");
        FXMLLoader loader = new FXMLLoader(RestaurantListController.class.getClassLoader().getResource("views/restaurantListView.fxml"));

        try {

            Stage stage = ReservationSystemRestaurantUtilsController.createStage(loader, primaryStage, "Lista restauracji");

            RestaurantListController controller = loader.getController();
            controller.setPrimaryStage(stage);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obsługa przycisku zamykającego okno startowe i całą aplikację.
     */
    public void closeButtonAction(){
        primaryStage.close();
    }


}
