package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.List;

import hu.hubasky.gastromanager.control.ReceptNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyReceptNyilvantarto implements ReceptNyilvantarto {
    @Override
    public List<Recept> keres(ReceptKeresesiJellemzok jellemzok) throws Exception {
        return null;
    }

    @Override
    public List<VasarlandoAlapanyag> vasarlandoKalkulacio(Recept recept, double adag) throws Exception {
        return null;
    }

    @Override
    public void tarolas(Recept recept) throws Exception {

    }

    @Override
    public void torles(Recept recept) throws Exception {

    }
}
