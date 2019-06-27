package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.gui.controller;

import java.util.logging.Logger;

/**
 * Nieużywana klasa. Może zostać zaimplementowana w celu ręcznego dodawania zamówień przez restauratora.
 *
 * @author Kamil Swislowski
 */
public class OrderAddController extends AbstractReservationSystemRestaurantController {

    private static final Logger LOGGER = Logger.getLogger(OrderAddController.class.getName());

    public void saveButtonAction(){
    LOGGER.info("Saving...");
    }

    /**
     * Obsługa przycisku zamykającego okno z dodawaniem nowego zamówienia.
     */
    public void closeButtonAction() {
        primaryStage.close();
    }
}
