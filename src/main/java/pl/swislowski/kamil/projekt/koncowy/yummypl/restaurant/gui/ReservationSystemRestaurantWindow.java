package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller.ReservationSystemRestaurantController;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;

public class ReservationSystemRestaurantWindow extends Application {
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
}