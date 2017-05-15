package hu.hubasky.gastromanager.control.impl.firebase;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.control.AsyncControlBase;
import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.ReceptNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

public final class FirebaseReciepeManager extends AsyncControlBase implements ReceptNyilvantarto {

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference("reciepes");

    public FirebaseReciepeManager() {
    }

    @Override
    public boolean init(Controls controls) {
        return true;
    }

    @Override
    public List<Recept> keres(ReceptKeresesiJellemzok jellemzok) throws Exception {
        return null;
    }

    @Override
    public void keres(ReceptKeresesiJellemzok jellemzok, final ControlResultListener<Recept> callback) {

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    final List<Recept> reciepes = FirebaseAccess.getInstance().getReciepes();

                    Recept reciepe = findItem(dataSnapshot, reciepes);
                    if (reciepe == null) {
                        reciepes.add(dataSnapshot.getValue(FirebaseReciepe.class).convertToReciepe(dataSnapshot.getKey(), null));
                    }

                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(reciepes);
                        }
                    });
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null) {
                    final List<Recept> reciepes = FirebaseAccess.getInstance().getReciepes();

                    Recept reciepe = findItem(dataSnapshot, reciepes);
                    if (reciepe != null) {
                        dataSnapshot.getValue(FirebaseReciepe.class).updateReciepe(reciepe);
                    }

                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(reciepes);
                        }
                    });
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    final List<Recept> reciepes = FirebaseAccess.getInstance().getReciepes();
                    Recept reciepe = findItem(dataSnapshot, reciepes);
                    reciepes.remove(reciepe);

                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(reciepes);
                        }
                    });
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public List<VasarlandoAlapanyag> vasarlandoKalkulacio(Recept recept, double adag) throws Exception {
        return null;
    }

    @Override
    public void vasarlandoKalkulacio(Recept recept, double adag, ControlResultListener<VasarlandoAlapanyag> callback) {

    }

    @Override
    public void tarolas(Recept recept) throws Exception {
        DatabaseReference recRef;

        if (recept.getUniqueKey() != null) {
            recRef = dbRef.child(recept.getUniqueKey());
        } else {
            recRef = dbRef.push();
            recept.setUniqueKey(recRef.getKey());
        }

        if (recRef != null) {
            recRef.setValue(new FirebaseReciepe(recept));
        } else {
            throw new IllegalArgumentException("Couldn't find reciepe's unique key in database.");
        }

    }

    @Override
    public void torles(Recept recept) throws Exception {
        if (recept != null && recept.getUniqueKey() != null) {
            dbRef.child(recept.getUniqueKey()).removeValue();
        }
    }

    private Recept findItem(DataSnapshot ds, List<Recept> list) {
        boolean found = false;
        Recept result = null;
        for (Recept r : list) {
            if (r.getUniqueKey().equals(ds.getKey())) {
                found = true;
                result = r;
                break;
            }
        }
        return result;

    }
}
