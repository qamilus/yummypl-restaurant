package pl.swislowski.kamil.projekt.koncowy.yummypl.restaurant.entity;

public enum OrderStatus {
    NEW("New"), INPROGRESS, DONE, CANCELED;

    private String name;

    OrderStatus() {
    }

    OrderStatus(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
