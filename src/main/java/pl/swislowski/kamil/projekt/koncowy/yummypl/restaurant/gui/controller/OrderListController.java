package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.OrderDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.OrderService;

import java.util.logging.Logger;

public class OrderListController extends AbstractReservationSystemRestaurantController {

    private static final Logger LOGGER = Logger.getLogger(OrderListController.class.getName());

    private ObservableList<OrderModel> orders = FXCollections.observableArrayList();
    private int selectedIndex;
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
    private RestaurantModel restaurantModel;

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("restaurantName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("itemsQuantity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("status"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<OrderModel, String>("deliveryAddress"));
    }

    public void populate(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;

        if (this.restaurantModel != null) {
            OrderDao orderDao = new OrderDao();
            OrderService orderService = new OrderService(orderDao);

            orders.addAll(orderService.list(this.restaurantModel));
            ordersListTable.setItems(orders);
        }
    }
}
