package hu.hubasky.gastromanager.viewmodel;

public class IngredientVM {

    private String name;
    private double quantity;
    private IngerdientUnit unit;

    public IngredientVM(String name, double quantity, IngerdientUnit unit) {

        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public IngerdientUnit getUnit() {
        return unit;
    }

    public void setUnit(IngerdientUnit unit) {
        this.unit = unit;
    }

}
