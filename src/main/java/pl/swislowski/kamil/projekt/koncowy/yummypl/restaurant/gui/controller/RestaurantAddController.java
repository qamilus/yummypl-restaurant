package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.LocationDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.RestaurantDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.dao.RestaurantInformationDao;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.LocationModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantInformationModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.model.RestaurantModel;
import pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.service.RestaurantService;

public class RestaurantAddController extends AbstractReservationSystemRestaurantController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField locationCityTextField;
    @FXML
    private TextField locationStreetTextField;
    @FXML
    private TextField locationHouseNumberTextField;
    @FXML
    private TextField informationTextField;
    @FXML
    private TextField openningHoursTextField;

    public void saveRestaurantButtonOnAction() {
        RestaurantDao restaurantDao = new RestaurantDao();
        LocationDao locationDao = new LocationDao();
        RestaurantInformationDao restaurantInformationDao = new RestaurantInformationDao();

        RestaurantService restaurantService = new RestaurantService(restaurantDao);
        restaurantService.setLocationDao(locationDao);
        restaurantService.setRestaurantInformationDao(restaurantInformationDao);

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setName(nameTextField.getText());

        LocationModel locationModel = new LocationModel();
        locationModel.setCity(locationCityTextField.getText());
        locationModel.setStreet(locationStreetTextField.getText());
        locationModel.setHouseNumber(locationHouseNumberTextField.getText());
        restaurantModel.setLocationModel(locationModel);

        RestaurantInformationModel restaurantInformationModel = new RestaurantInformationModel();
        restaurantInformationModel.setOpeningHours(openningHoursTextField.getText());
        restaurantModel.setRestaurantInformationModel(restaurantInformationModel);

        restaurantService.create(restaurantModel);

        primaryStage.close();
    }

    public void closeButtonAction() {
        primaryStage.close();
    }
}
