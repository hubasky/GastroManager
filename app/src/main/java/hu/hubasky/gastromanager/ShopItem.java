package hu.hubasky.gastromanager;

import hu.hubasky.gastromanager.viewmodel.IngerdientUnit;

class ShopItem {

    private String name;
    private int quantity;
    private String quantityType;
    private boolean selected = false;

    public ShopItem(String name, int quantity, String quantityType) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.quantityType = quantityType;

    }

    public String getQuantityType() {
        return quantityType;
    }

    private void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
