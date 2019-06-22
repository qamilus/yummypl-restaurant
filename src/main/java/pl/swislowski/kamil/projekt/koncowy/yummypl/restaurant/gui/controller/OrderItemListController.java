package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.OrderItemDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderItemModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.OrderModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.OrderItemService;

import java.util.logging.Logger;

/**
 * @author Kamil Swislowski
 */
public class OrderItemListController extends AbstractReservationSystemRestaurantController {

    private static final Logger LOGGER = Logger.getLogger(OrderListController.class.getName());

    private ObservableList<OrderItemModel> orderItems = FXCollections.observableArrayList();
    private OrderModel orderModel;
    private OrderItemService orderItemService;

    @FXML
    private TableView<OrderItemModel> orderItemListTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn priceColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn sumColumn;
    @FXML
    private Label restaurantNameLabelId;
    @FXML
    private Label orderStatusLabelId;
    @FXML
    private Label deliveryAddressLabelId;

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<OrderItemModel, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<OrderItemModel, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<OrderItemModel, String>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<OrderItemModel, String>("quantity"));
        sumColumn.setCellValueFactory(new PropertyValueFactory<OrderItemModel, String>("sum"));
    }

    public void populate(OrderModel orderModel, RestaurantModel restaurantModel) {
        LOGGER.info("OrderModel : " + orderModel);
        LOGGER.info("RestaurantModel : " + restaurantModel);
        this.orderModel = orderModel;


        if (this.orderModel != null) {
            restaurantNameLabelId.setText(restaurantModel.getName());
            orderStatusLabelId.setText(orderModel.getStatus());
            deliveryAddressLabelId.setText(orderModel.getDeliveryAddress());

            OrderItemDao orderItemDao = new OrderItemDao();
            orderItemService = new OrderItemService(orderItemDao);

            orderItems.addAll(orderItemService.list(this.orderModel));
            orderItemListTable.setItems(orderItems);
        }
    }

    public void closeButtonAction(){
        primaryStage.close();
    }
}
