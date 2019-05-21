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

public class RestaurantListController extends AbstractReservationSystemRestaurantController {


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
    private TableColumn informationColumn;
    @FXML
    private TableColumn openingHoursColumn;
    @FXML
    private Button orderListButton;

    public void setRestaurants(ObservableList<RestaurantModel> restaurants) {
        this.restaurants = restaurants;
    }

    public void initialize() {

        RestaurantDao restaurantDao = new RestaurantDao();
        RestaurantService restaurantService = new RestaurantService(restaurantDao);

        restaurants.addAll(restaurantService.list());

        idColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("restaurantName"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("address"));
        informationColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("information"));
        openingHoursColumn.setCellValueFactory(new PropertyValueFactory<RestaurantModel, String>("openingHours"));

        restaurantListTable.setItems(restaurants);
    }

    public void ordersListButtonOnAction() {
        FXMLLoader loader = new FXMLLoader(OrderListController.class.getClassLoader().getResource("views/orderListView.fxml"));

        try {
            Stage stage = ReservationSystemRestaurantUtilsController.createStage(loader, primaryStage, "Lista zamówień");
            OrderListController controller = loader.getController();

            controller.setPrimaryStage(stage);
//            controller.setSelectedModel(restaurantListTable.getSelectionModel().getSelectedItem());
            controller.populate(restaurantListTable.getSelectionModel().getSelectedItem());

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
