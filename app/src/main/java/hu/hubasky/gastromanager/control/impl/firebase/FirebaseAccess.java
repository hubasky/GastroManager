package hu.hubasky.gastromanager.control.impl.firebase;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;

/**
 * Created by Peet on 2017. 05. 11..
 */

public class FirebaseAccess {

    private static FirebaseAccess instance = null;

    private final FirebaseDatabase db;
    private final DatabaseReference ingredientsRef;


    private final String INGREDIENTS_SCHEMA = "ingredients";


    private final List<Alapanyag> ingredients = new ArrayList<>();
    public List<Alapanyag> getIngredients() {
        return ingredients;
    }

    private FirebaseAccess() {
        db = FirebaseDatabase.getInstance();
        ingredientsRef = db.getReference(INGREDIENTS_SCHEMA);
        ingredientsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FirebaseIngredient fbIng = dataSnapshot.getValue(FirebaseIngredient.class);
                Log.d("FB_LOG", fbIng.getName());
                Alapanyag ingredient = dataSnapshot.getValue(FirebaseIngredient.class).convertToIngredient(dataSnapshot.getKey());
                boolean found = false;
                for (Alapanyag ing : ingredients) {
                    if (ing.getUniqueKey().equals(ingredient.getUniqueKey())) {
                        found = true;
                    }
                }
                if (!found) {
                    ingredients.add(ingredient);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

    public static FirebaseAccess getInstance() {
        if (FirebaseAccess.instance == null) {
            FirebaseAccess.instance = new FirebaseAccess();
        }
        return FirebaseAccess.instance;
    }

}
