package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.DatabaseUtils;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller.ReservationSystemRestaurantController;

import java.util.logging.Logger;

public class ReservationSystemRestaurantWindow extends Application {

    private static final Logger LOGGER = Logger.getLogger(ReservationSystemRestaurantWindow.class.getName());

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ReservationSystemRestaurantWindow.class.getClassLoader().getResource("views/reservationSystemRestaurantView.fxml"));
        Parent root = loader.load();

        ReservationSystemRestaurantController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Okno powitalne");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        LOGGER.info("Stopping application");
        DatabaseUtils.closeConnection();
    }
}
