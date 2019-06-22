package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import java.util.logging.Logger;

/**
 * @author Kamil Swislowski
 */
public class OrderAddController extends AbstractReservationSystemRestaurantController {

    private static final Logger LOGGER = Logger.getLogger(OrderAddController.class.getName());

    public void saveButtonAction(){
    LOGGER.info("Saving...");
    }

    public void closeButtonAction() {
        primaryStage.close();
    }
}
