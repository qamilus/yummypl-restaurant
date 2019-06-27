package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.DatabaseUtils;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller.ReservationSystemRestaurantController;

import java.util.logging.Logger;

/**
 * Punkt startowy dla aplikacji JavaFX, tworzy główne okno programu.
 *
 * @author Kamil Swislowski
 */
public class ReservationSystemRestaurantWindow extends Application {

    private static final Logger LOGGER = Logger.getLogger(ReservationSystemRestaurantWindow.class.getName());

    /**
     * Uruchamia aplikację JavaFX.
     *
     * @param args Argumenty wiersza poleceń.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda startująca aplikację JavaFX.
     *
     * @param primaryStage Główny kontener dla GUI w JavaFX.
     * @throws Exception Wyjątek zawierający informacje o błędach aplikacji.
     */
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

    /**
     * Metoda zatrzymująca aplikację JavaFX.
     *
     * @throws Exception Wyjątek zawierający informacje o błędach aplikacji.
     */
    @Override
    public void stop() throws Exception {
        LOGGER.info("Stopping application");
        DatabaseUtils.closeConnection();
    }
}
