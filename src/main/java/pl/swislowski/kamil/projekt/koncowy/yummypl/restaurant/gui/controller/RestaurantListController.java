package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.RestaurantDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.RestaurantService;

import java.io.IOException;

/**
 * Kontroler obsługujący widok i model dla GUI. JavaFX MVC - Model View Controller.
 *
 * @author Kamil Swislowski
 */
public class RestaurantListController extends AbstractReservationSystemRestaurantController {

    private RestaurantService restaurantService;

    private ObservableList<RestaurantModel> restaurants = FXCollections.observableArrayList();
    @FXML
    private TableView<RestaurantModel> restaurantListTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn locationColumn;
    @FXML
    private TableColumn openingHoursColumn;
    @FXML
    private Button orderListButton;

    /**
     * Metoda inicjalizująca elementy widoku na podstawie danych z modelu.
     * Metoda domyślnie wywoływana przez JavaFX.
     */
    public void initialize() {

        RestaurantDao restaurantDao = new RestaurantDao();
        restaurantService = new RestaurantService(restaurantDao);

        restaurants.addAll(restaurantService.list());

        idColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("address"));
        openingHoursColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("openingHours"));

        restaurantListTable.setItems(restaurants);
        orderListButton.setDisable(true);

        restaurantListTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                orderListButton.setDisable(false);
            }
        });
    }

    /**
     * Obsługa przycisku wyświetlające listę zamówień.
     */
    public void ordersListButtonOnAction() {
        FXMLLoader loader = new FXMLLoader(OrderListController.class.getClassLoader().getResource("views/orderListView.fxml"));

        try {
            Stage stage = ReservationSystemRestaurantUtilsController.createStage(loader, primaryStage, "Lista zamówień");
            OrderListController controller = loader.getController();

            controller.setPrimaryStage(stage);
            controller.populate(restaurantListTable.getSelectionModel().getSelectedItem());

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obsługa przycisku dodającego nową Restaurację.
     */
    public void addNewRestaurantButtonAction() {
        FXMLLoader loader = new FXMLLoader(OrderListController.class.getClassLoader().getResource("views/restaurantAddView.fxml"));
        try {
            Stage stage = ReservationSystemRestaurantUtilsController.createStage(loader, primaryStage, "Dodawanie nowej restauracji");
            RestaurantAddController controller = loader.getController();

            controller.setPrimaryStage(stage);

            stage.showAndWait();

            restaurants = FXCollections.observableArrayList();
            restaurants.addAll(restaurantService.list());
            restaurantListTable.setItems(restaurants);
            restaurantListTable.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obsługa przycisku zamykającego okno z listą Restauracji.
     */
    public void closeButtonAction() {
        primaryStage.close();
    }
}
