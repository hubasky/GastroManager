package hu.hubasky.gastromanager.control.impl.firebase;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;

@IgnoreExtraProperties
public class FirebaseIngredientQuantity {

    private double quantity;
    private String ingredientId;

    public FirebaseIngredientQuantity(Hozzavalo iq) {
        this.quantity = iq.getMennyiseg();
        this.ingredientId = iq.getAlapanyag().getUniqueKey();
    }

    public Hozzavalo convertToHozzavalo(String id, Alapanyag ingredient) {
        Hozzavalo iq = new Hozzavalo(quantity, ingredient);
        iq.setUniqueKey(id);
        return iq;
    }

    public void updateIngredientQuantity(Hozzavalo iq) {
        iq.setMennyiseg(quantity);
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }
}
