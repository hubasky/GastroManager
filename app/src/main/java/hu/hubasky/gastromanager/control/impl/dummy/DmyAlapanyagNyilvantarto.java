package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
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
    public DmyAlapanyagNyilvantarto() {
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.DARAB,
                new AlapanyagJellemzok(100, 5, 4, 2, 20),
                "Tojás", 130));
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, 5, 4, 2, 20),
                "Liszt", 130));

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
