package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyAlapanyagNyilvantarto implements AlapanyagNyilvantarto {

    /**
     * Tesztadatok.
     */
    private final List<Alapanyag> adatok = new ArrayList<>();

    /**
     * Konstruktor.
     */
    public DmyAlapanyagNyilvantarto(){
    }

    @Override
    public void tarolas(Alapanyag alapanyag) throws Exception {
        if (adatok.contains(alapanyag)) {
            adatok.remove(alapanyag);
        }
        adatok.add(alapanyag);
    }

    @Override
    public void torles(Alapanyag alapanyag) throws Exception {
        adatok.remove(alapanyag);
    }

    @Override
    public List<Alapanyag> keres(AlapanyagKeresesiJellemzok jellemzok) throws Exception {
        List<Alapanyag> ret = null;

        for (Alapanyag each : adatok) {
            if (jellemzok.isMegfelel(each)) {
                if (ret == null) {
                    ret = new ArrayList<>();
                }
                ret.add(each);
            }
        }
        return ret == null ? Collections.<Alapanyag>emptyList() : ret;
    }
}
