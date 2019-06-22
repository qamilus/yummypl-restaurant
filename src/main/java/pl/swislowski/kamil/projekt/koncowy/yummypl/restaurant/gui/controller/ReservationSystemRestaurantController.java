package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Kamil Swislowski
 */
public class ReservationSystemRestaurantController extends AbstractReservationSystemRestaurantController{
    private static final Logger LOGGER = Logger.getLogger(ReservationSystemRestaurantController.class.getName());

    @FXML
    private Button startButton;

    public void initialize() {
    }

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

    public void closeButtonAction(){
        primaryStage.close();
    }


}
