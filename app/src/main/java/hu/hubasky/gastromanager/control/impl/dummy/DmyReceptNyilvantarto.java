package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.ReceptNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyReceptNyilvantarto implements ReceptNyilvantarto {

    /**
     * Receptek.
     */
    private final List<Recept> receptek = new ArrayList<>();

    @Override
    public void init(Controls controls) {

    }

    @Override
    public List<Recept> keres(final ReceptKeresesiJellemzok jellemzok) throws Exception {
        return Helper.filter(receptek, new Helper.Checker<Recept>() {
            @Override
            public boolean check(Recept param) {
                return jellemzok.isMegfelelo(param);
            }
        });
    }

    @Override
    public List<VasarlandoAlapanyag> vasarlandoKalkulacio(Recept recept, double adag) throws Exception {
        return null;
    }

    @Override
    public void tarolas(Recept recept) throws Exception {
        if (!receptek.contains(recept)) {
            receptek.add(recept);
        }
    }

    @Override
    public void torles(Recept recept) throws Exception {
        receptek.remove(recept);
    }
}
