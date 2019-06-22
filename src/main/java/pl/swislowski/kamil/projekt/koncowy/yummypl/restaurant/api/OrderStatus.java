package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.api;

/**
 * @author Kamil Swislowski
 */
public enum OrderStatus {
    NEW("New"), IN_PROGRESS("In progress"), DONE("Done"), CANCELED("Canceled");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
