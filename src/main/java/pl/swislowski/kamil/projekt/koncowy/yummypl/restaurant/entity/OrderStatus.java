package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

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
