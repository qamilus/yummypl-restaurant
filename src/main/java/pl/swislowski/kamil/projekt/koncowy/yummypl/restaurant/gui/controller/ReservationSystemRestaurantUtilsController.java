package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa narzędziowa wspomagająca tworzenie nowych komponentów zawierających elementy GUI w JavaFX.
 *
 * @author Kamil Swislowski
 */
public class ReservationSystemRestaurantUtilsController {

    /**
     * Tworzy nowy komponent zawierający elementy GUI w JavaFX.
     *
     * @param loader Obiekt za pomocą którego wczytywane są elementy GUI z pliku *.FXML.
     * @param primaryStage Nadrzędny komponent dla nowo utworzonego komponentu.
     * @param title Tytuł okienka zawierającego komponenty GUI.
     * @return Nowo utworzony komponent zawierający elementy GUI w JavaFX.
     * @throws IOException Wyjątek rzucany, jeżeli nie odnaleziono pliku *.FXML.
     */
    public static Stage createStage(FXMLLoader loader, Stage primaryStage, String title) throws IOException {
        AnchorPane pane = loader.load();

        Stage stage = new Stage();
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        return stage;
    }
}
