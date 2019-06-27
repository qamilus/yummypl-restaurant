package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.api.OrderStatus;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.OrderDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.OrderService;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Kontroler obsługujący widok i model dla GUI. JavaFX MVC - Model View Controller.
 *
 * @author Kamil Swislowski
 */
public class OrderListController extends AbstractReservationSystemRestaurantController {

    private static final Logger LOGGER = Logger.getLogger(OrderListController.class.getName());

    private ObservableList<OrderModel> orders = FXCollections.observableArrayList();
    private RestaurantModel restaurantModel;
    private OrderService orderService;

    @FXML
    private TableView<OrderModel> ordersListTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private ComboBox<OrderStatus> updateStatusComboBoxId;
    @FXML
    private Button changeOfStatusButton;
    @FXML
    private Button orderDetailsButton;

    /**
     * Metoda inicjalizująca elementy widoku na podstawie danych z modelu.
     * Metoda domyślnie wywoływana przez JavaFX.
     */
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("restaurantName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("itemsQuantity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("status"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("deliveryAddress"));

        ObservableList<OrderStatus> orderStatusList = FXCollections.observableArrayList();
        orderStatusList.add(OrderStatus.NEW);
        orderStatusList.add(OrderStatus.IN_PROGRESS);
        orderStatusList.add(OrderStatus.DONE);
        orderStatusList.add(OrderStatus.CANCELED);
        updateStatusComboBoxId.setItems(orderStatusList);
        updateStatusComboBoxId.getSelectionModel().selectFirst();

        changeOfStatusButton.setDisable(true);
        orderDetailsButton.setDisable(true);

        ordersListTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                changeOfStatusButton.setDisable(false);
                orderDetailsButton.setDisable(false);
            }
        });
    }

    /**
     * Metoda uzupełniająca dane w TableView.
     *
     * @param restaurantModel Jako argument restaurantModel.
     */
    public void populate(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;

        if (this.restaurantModel != null) {
            OrderDao orderDao = new OrderDao();
            orderService = new OrderService(orderDao);

            orders.addAll(orderService.list(this.restaurantModel));
            ordersListTable.setItems(orders);
        }
    }

    /**
     * Metoda obsługjąca zmianę statusu dla konkretnego zamówienia.
     */
    public void updateButtonOnAction() {
        LOGGER.info("Updating order...");
        OrderModel orderModel = ordersListTable.getSelectionModel().getSelectedItem();
        OrderStatus orderStatus = updateStatusComboBoxId.getSelectionModel().getSelectedItem();

        if (orderStatus != null) {
            orderModel.setStatus(orderStatus.getName());
            ordersListTable.refresh();
        }

        if (orderModel != null) {
            orderService.update(orderModel);
        }
    }

    /**
     * Metoda wyświetlająca okno ze szczegółami zamówienia.
     */
    public void detailsOfTheOrderButtonAction() {
        LOGGER.info("Loading details of the order...");

        OrderModel orderModel = ordersListTable.getSelectionModel().getSelectedItem();

        if (orderModel != null) {
            FXMLLoader loader = new FXMLLoader(OrderItemListController.class.getClassLoader().getResource("views/orderItemListView.fxml"));

            try {
                Stage stage = ReservationSystemRestaurantUtilsController.createStage(loader, primaryStage, "Szczegóły zamówienia");
                OrderItemListController controller = loader.getController();

                controller.populate(orderModel, restaurantModel);
                controller.setPrimaryStage(stage);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Obsługa przycisku zamykającego oknto z listą zamówień.
     */
    public void closeButtonAction() {
        primaryStage.close();
    }
}
