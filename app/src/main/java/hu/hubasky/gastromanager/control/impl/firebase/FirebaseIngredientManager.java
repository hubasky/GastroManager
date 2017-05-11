package hu.hubasky.gastromanager.control.impl.firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

public final class FirebaseIngredientManager implements AlapanyagNyilvantarto {

    private final FirebaseDatabase db;
    private final DatabaseReference ingredientsDbRef;

    private final String INGREDIENTS_SCHEMA = "ingredients";

    public FirebaseIngredientManager() {
        this.db = FirebaseDatabase.getInstance();
        this.ingredientsDbRef = db.getReference(INGREDIENTS_SCHEMA);
    }

    @Override
    public boolean init(Controls controls) {
        return true;
    }

    @Override
    public void tarolas(Alapanyag alapanyag) throws Exception {
        DatabaseReference dbRef;
        if (alapanyag.getUniqueKey() != null) {
            dbRef = ingredientsDbRef.child(alapanyag.getUniqueKey());
        } else {
            dbRef = ingredientsDbRef.push();
            alapanyag.setUniqueKey(dbRef.getKey());
        }

        if (dbRef != null) {
            dbRef.setValue(new FirebaseIngredient(alapanyag));
        } else {
            throw new IllegalArgumentException("Couldn't find ingredient's unique key in database.");
        }
    }

    @Override
    public void torles(Alapanyag alapanyag) throws Exception {

    }

    @Override
    public List<Alapanyag> keres(AlapanyagKeresesiJellemzok jellemzok) throws Exception {
        return FirebaseAccess.getInstance().getIngredients();
    }
}
