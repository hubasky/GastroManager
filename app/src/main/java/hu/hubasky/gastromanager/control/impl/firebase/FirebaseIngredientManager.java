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
import hu.hubasky.gastromanager.control.AsyncControlBase;
import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

public final class FirebaseIngredientManager extends AsyncControlBase implements AlapanyagNyilvantarto {

    private final FirebaseDatabase db;
    private final DatabaseReference ingredientsDbRef;

    private final String TAG = "Firebase DB LOG";
//
//    private List<Alapanyag> ingredients;
//    public List<Alapanyag> getIngredients() {
//        return ingredients;
//    }

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
        return null;
    }

    @Override
    public void keres(AlapanyagKeresesiJellemzok jellemzok,  final ControlResultListener<Alapanyag> callback) {

        ingredientsDbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    final List<Alapanyag> ingredients = FirebaseAccess.getInstance().getIngredients();

                    Alapanyag ingredient = findItem(dataSnapshot, ingredients);
                    if (ingredient == null) {
                        ingredients.add(dataSnapshot.getValue(FirebaseIngredient.class).convertToIngredient(dataSnapshot.getKey()));
                    }

                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(ingredients);
                        }
                    });
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot != null) {
                    final List<Alapanyag> ingredients = FirebaseAccess.getInstance().getIngredients();

                    Alapanyag ingredient = findItem(dataSnapshot, ingredients);
                    if (ingredient != null) {
                        (dataSnapshot.getValue(FirebaseIngredient.class)).updateIngredient(ingredient);
                    }

                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(ingredients);
                        }
                    });
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private Alapanyag findItem(DataSnapshot dataSnapshot, List<Alapanyag> ingredients) {
        boolean found = false;
        Alapanyag result = null;
        for (Alapanyag i : ingredients) {
            if (i.getUniqueKey().equals(dataSnapshot.getKey())) {
                found = true;
                result = i;
                break;
            }
        }
        return result;
    }
}
