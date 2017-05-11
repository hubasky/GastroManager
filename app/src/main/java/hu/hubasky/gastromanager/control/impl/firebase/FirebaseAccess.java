package hu.hubasky.gastromanager.control.impl.firebase;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.OnDisconnect;
import com.google.firebase.database.Transaction;

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

//    private final List<Alapanyag> ingredients = new ArrayList<>();
    private List<MutableData> ingredients;
    public List<MutableData> getIngredients() {
        return ingredients;
    }

    private FirebaseAccess() {
        db = FirebaseDatabase.getInstance();
        ingredientsRef = db.getReference(INGREDIENTS_SCHEMA);
        getIngredientsList();
    }

    public static FirebaseAccess getInstance() {
        if (FirebaseAccess.instance == null) {
            FirebaseAccess.instance = new FirebaseAccess();
        }
        return FirebaseAccess.instance;
    }

    public void getIngredientsList() {
        ingredientsRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                for (MutableData m : mutableData.getChildren()) {
                    Log.d("FB_LOG", m.getKey());
                }

                FirebaseAccess.getInstance().ingredients = (ArrayList<MutableData>) mutableData.getChildren();

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("FB_LOG", "Transaction complete");

            }
        });
    }

}
