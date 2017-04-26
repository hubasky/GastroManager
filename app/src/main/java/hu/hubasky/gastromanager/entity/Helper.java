package hu.hubasky.gastromanager.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;

/**
 * Created by mirso on 2017. 04. 26..
 */

public final class Helper {

    /**
     * Felkeresi a megadott listában a kívánt alapanyagot.
     *
     * @param search    a lista, amiben keresünk.
     * @param alapanyag a keresett alapanyag.
     * @param <T>       a lista típus.
     * @return a találat vagy {@code null} ha nincs benne.
     */
    public static <T extends Hozzavalo> T find(Set<T> search, Alapanyag alapanyag) {
        for (T each : search) {
            if (each.getAlapanyag().equals(alapanyag)) {
                return each;
            }
        }
        return null;
    }

    /**
     * Az {@code osszes} halmaz cimkéiből kiválasztja a megfelelő típusúakat.
     *
     * @param osszes az összes.
     * @param tipus  a kívánt típus.
     * @return a cimkék listája.
     */
    public static Set<Cimke> filter(Set<Cimke> osszes, ECimkeTipus tipus) {
        if (tipus == ECimkeTipus.MINDEN) {
            return Collections.unmodifiableSet(osszes);
        }

        Set<Cimke> ret = new HashSet<>();
        for (Cimke each : osszes) {
            if (each.isMegfelelo(tipus)) {
                ret.add(each);
            }
        }
        return ret;
    }

    /**
     * Az {@code osszes} halmaz cimkéiből kiválasztja a megfelelő típusúakat.
     *
     * @param osszes az összes.
     * @param tipus  a kívánt típus.
     * @return a cimkék listája.
     */
    public static List<Cimke> filter(List<Cimke> osszes, ECimkeTipus tipus) {
        if (tipus == ECimkeTipus.MINDEN) {
            return Collections.unmodifiableList(osszes);
        }

        List<Cimke> ret = new ArrayList<>();
        for (Cimke each : osszes) {
            if (each.isMegfelelo(tipus)) {
                ret.add(each);
            }
        }
        return ret;
    }


    /**
     * Visszaadja, hogy létezik-e metszete a két halmaznak.
     *
     * @param t1  egyik halmaz.
     * @param t2  másik halmaz.
     * @param <T> elemtípus.
     * @return true, ha igen.
     */
    public static <T> boolean isExistsIntersect(Set<T> t1, Set<T> t2) {
        Set<T> tmp = new HashSet<>(t1.size() > t2.size() ? t1.size() : t2.size());
        tmp.addAll(t1);
        tmp.retainAll(t2);
        return !tmp.isEmpty();
    }

    /**
     * Nem példányosítható.
     */
    private Helper() {
        throw new AssertionError();
    }
}
