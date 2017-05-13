package hu.hubasky.gastromanager.control.impl.firebase;

import java.util.List;

import hu.hubasky.gastromanager.control.AsyncControlBase;
import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.ReceptNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

public final class FirebaseReciepeManager extends AsyncControlBase implements ReceptNyilvantarto {

    @Override
    public boolean init(Controls controls) {
        return true;
    }

    @Override
    public List<Recept> keres(ReceptKeresesiJellemzok jellemzok) throws Exception {
        return null;
    }

    @Override
    public void keres(ReceptKeresesiJellemzok jellemzok, ControlResultListener<Recept> callback) {

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

    }

    @Override
    public void torles(Recept recept) throws Exception {

    }
}
