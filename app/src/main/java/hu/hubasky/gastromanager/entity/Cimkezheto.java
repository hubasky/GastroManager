package hu.hubasky.gastromanager.entity;

import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


/**
 * Cimkézhetőség alaposztálya.
 * Created by mirso on 2017. 04. 26..
 */

public abstract class Cimkezheto {
    /**
     * A cimkék.
     */
    private final Set<Cimke> cimkek = new HashSet<>();

    /**
     * Cimke felvétele.
     *
     * @param cimke a cimke.
     */
    public void addCimke(Cimke cimke) {
        if (cimke == null) throw new AssertionError();
        cimkek.add(cimke);
    }

    /**
     * Cimke leválasztása.
     *
     * @param cimke a cimke.
     */
    public void remCimke(Cimke cimke) {
        if (cimke == null) throw new AssertionError();
        cimkek.remove(cimke);
    }

    /**
     * Visszaadja, hogy a {@code tartalmaz} cimkék közül mindet, bármelyiket tartalmazza-e, illetve
     * hogy a {@code kizart} cimkéket nem.
     *
     * @param tartalmaz  a tartalmazandó cimkék. Lehet {@code null} is.
     * @param mindegyikt true esetén mindegyiket kell tartalmaznia amit a {@code tartalmaz} paraméterben
     *                   megadtunk.
     * @param kizart     milyen cimkéket nem tartalmazhat. Lehet {@code null} is.
     * @return true, ha megfelel a feltételeknek.
     */
    public boolean isMegfelelo(List<Cimke> tartalmaz, boolean mindegyikt, List<Cimke> kizart) {
        List<Cimke> tart = tartalmaz == null ? Collections.<Cimke>emptyList() : tartalmaz;
        List<Cimke> kiza = kizart == null ? Collections.<Cimke>emptyList() : kizart;

        int tartcnt = 0;
        int kizacnt = 0;

        for (Cimke each : tart) {
            if (cimkek.contains(each)) {
                tartcnt++;
            }
        }

        if ((mindegyikt && tartcnt < tart.size()) || tartcnt == 0) {
            return false;
        }


        for (Cimke each : kiza) {
            if (cimkek.contains(each)) {
                return false;
            }
        }

        return true;


    }
}
