package hu.hubasky.gastromanager.control.impl.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import hu.hubasky.gastromanager.control.AsyncControlBase;
import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.HozzavaloNyilvantarto;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;

public class FirebaseIngredientQuantityManager extends AsyncControlBase implements HozzavaloNyilvantarto {

    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private final DatabaseReference dbRef = db.getReference("ingredient_quantities");

    private final String LOG = "Firebase - Ingredient quantities";

    public FirebaseIngredientQuantityManager() {
    }

    @Override
    public void tarolas(Hozzavalo hozzavalo) throws Exception {
        DatabaseReference iqdbRef;
        if (hozzavalo.getUniqueKey() != null) {
            iqdbRef = dbRef.child(hozzavalo.getUniqueKey());
        } else {
            iqdbRef  = dbRef.push();
            hozzavalo.setUniqueKey(iqdbRef.getKey());
        }

        if (iqdbRef != null) {
            iqdbRef.setValue(new FirebaseIngredientQuantity(hozzavalo));
        } else {
            throw new IllegalArgumentException("Couldn't find ingredient's unique key in database.");
        }
    }

    @Override
    public void torles(Hozzavalo hozzavalo) throws Exception {

    }

    @Override
    public List<Hozzavalo> keres() throws Exception {
        return null;
    }

    @Override
    public void keres(ControlResultListener<Hozzavalo> callback) {

    }

    @Override
    public boolean init(Controls controls) {
        return true;
    }
}
