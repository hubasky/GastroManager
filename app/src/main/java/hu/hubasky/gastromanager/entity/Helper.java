package hu.hubasky.gastromanager.entity;

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
     * Nem példányosítható.
     */
    private Helper() {
        throw new AssertionError();
    }
}
