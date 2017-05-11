package hu.hubasky.gastromanager.control.impl.firebase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

public final class FirebaseIngredientManager implements AlapanyagNyilvantarto {

    private final FirebaseDatabase db;
    private final DatabaseReference ingredientsDbRef;

    private List<Alapanyag> ingredients;
    public List<Alapanyag> getIngredients() {
        return ingredients;
    }

    private final String INGREDIENTS_SCHEMA = "ingredients";

    public FirebaseIngredientManager() {
        this.db = FirebaseDatabase.getInstance();
        this.ingredientsDbRef = db.getReference(INGREDIENTS_SCHEMA);
        this.ingredients = new ArrayList<Alapanyag>();
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
        // FirebaseAccess.getInstance().getIngredients();
        ingredientsDbRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue() != null) {
                    for (MutableData m : mutableData.getChildren()) {
                        boolean found = false;
                        for (Alapanyag i : ingredients) {
                            if (i.getUniqueKey().equals(m.getKey())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            ingredients.add(m.getValue(FirebaseIngredient.class).convertToIngredient(m.getKey()));
                        }
                    }
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("FB_LOG", "Transaction complete.");
            }
        });
        return null;
    }
}
