package hu.hubasky.gastromanager.control;

import java.util.HashSet;
import java.util.Set;

import hu.hubasky.gastromanager.control.impl.dummy.DmyAlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.impl.dummy.DmyBevasarloListaNyilvantarto;
import hu.hubasky.gastromanager.control.impl.dummy.DmyCimkeNyilvantarto;
import hu.hubasky.gastromanager.control.impl.dummy.DmyFelhasznaloNyilvantarto;
import hu.hubasky.gastromanager.control.impl.dummy.DmyReceptNyilvantarto;

/**
 * A vezérlőket tartalmazó egyke.
 * Created by mirso on 2017-04-27.
 */

public final class Controls {

    /**
     * Alapanyag nyilvántartó.
     *
     * @return példány.
     */
    public hu.hubasky.gastromanager.control.AlapanyagNyilvantarto getAlapanyagNyilvantarto() {
        return alapanyagNyilvantarto;
    }

    /**
     * Bevásárlólista nyilvántartó.
     *
     * @return példány.
     */
    public hu.hubasky.gastromanager.control.BevasarloListaNyilvantarto getBevasarloListaNyilvantarto() {
        return bevasarloListaNyilvantarto;
    }

    /**
     * Cimke nyilvántartó.
     *
     * @return példány.
     */
    public hu.hubasky.gastromanager.control.CimkeNyilvantarto getCimkeNyilvantarto() {
        return cimkeNyilvantarto;
    }

    /**
     * Felhasználó nyilvántartó.
     *
     * @return példány.
     */
    public hu.hubasky.gastromanager.control.FelhasznaloNyilvantarto getFelhasznaloNyilvantarto() {
        return felhasznaloNyilvantarto;
    }

    /**
     * Recept nyilvántartó.
     *
     * @return példány.
     */
    public hu.hubasky.gastromanager.control.ReceptNyilvantarto getReceptNyilvantarto() {
        return receptNyilvantarto;
    }

    /**
     * A Singleton tároló.
     */
    private static class Holder {
        private static final Controls INSTANCE = new Controls();
    }

    /**
     * Visszaadja a példányt.
     *
     * @return a példány.
     */
    public static Controls getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Privát konstruktor.
     */
    private Controls() {
        dummyImpl();

        Set<ControlBase> needInit = new HashSet<>(controls);

        while (!needInit.isEmpty()) {
            for (ControlBase each : needInit) {
                if(each.init(this)){
                    needInit.remove(each);
                    break;
                }
            }
        }


    }

    /**
     * Dummy implementációk beállítása.
     */
    private void dummyImpl() {
        controls.add(alapanyagNyilvantarto = new DmyAlapanyagNyilvantarto());
        controls.add(bevasarloListaNyilvantarto = new DmyBevasarloListaNyilvantarto());
        controls.add(cimkeNyilvantarto = new DmyCimkeNyilvantarto());
        controls.add(felhasznaloNyilvantarto = new DmyFelhasznaloNyilvantarto());
        controls.add(receptNyilvantarto = new DmyReceptNyilvantarto());
    }

    /**
     * Alapanyag nyilvántartó.
     */
    private hu.hubasky.gastromanager.control.AlapanyagNyilvantarto alapanyagNyilvantarto;
    /**
     * Bevásárlólista nyilvántartó.
     */
    private hu.hubasky.gastromanager.control.BevasarloListaNyilvantarto bevasarloListaNyilvantarto;
    /**
     * Cimke nyilvántartó.
     */
    private hu.hubasky.gastromanager.control.CimkeNyilvantarto cimkeNyilvantarto;
    /**
     * Felhasználó nyilvántartó.
     */
    private hu.hubasky.gastromanager.control.FelhasznaloNyilvantarto felhasznaloNyilvantarto;
    /**
     * Recept nyilvántartó.
     */
    private hu.hubasky.gastromanager.control.ReceptNyilvantarto receptNyilvantarto;
    /**
     * Vezérlők.
     */
    private final Set<ControlBase> controls = new HashSet<>();


}
