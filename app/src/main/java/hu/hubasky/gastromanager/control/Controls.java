package hu.hubasky.gastromanager.control;

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
     * @return példány.
     */
    public AlapanyagNyilvantarto getAlapanyagNyilvantarto() {
        return alapanyagNyilvantarto;
    }

    /**
     * Bevásárlólista nyilvántartó.
     * @return példány.
     */
    public BevasarloListaNyilvantarto getBevasarloListaNyilvantarto() {
        return bevasarloListaNyilvantarto;
    }

    /**
     * Cimke nyilvántartó.
     * @return példány.
     */
    public CimkeNyilvantarto getCimkeNyilvantarto() {
        return cimkeNyilvantarto;
    }

    /**
     * Felhasználó nyilvántartó.
     * @return példány.
     */
    public FelhasznaloNyilvantarto getFelhasznaloNyilvantarto() {
        return felhasznaloNyilvantarto;
    }

    /**
     * Recept nyilvántartó.
     * @return példány.
     */
    public ReceptNyilvantarto getReceptNyilvantarto() {
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

    }

    /**
     * Dummy implementációk beállítása.
     */
    private void dummyImpl() {
        alapanyagNyilvantarto = new DmyAlapanyagNyilvantarto();
        bevasarloListaNyilvantarto=new DmyBevasarloListaNyilvantarto();
        cimkeNyilvantarto=new DmyCimkeNyilvantarto();
        felhasznaloNyilvantarto=new DmyFelhasznaloNyilvantarto();
        receptNyilvantarto=new DmyReceptNyilvantarto();
    }

    /**
     * Alapanyag nyilvántartó.
     */
    private AlapanyagNyilvantarto alapanyagNyilvantarto;
    /**
     * Bevásárlólista nyilvántartó.
     */
    private BevasarloListaNyilvantarto bevasarloListaNyilvantarto;
    /**
     * Cimke nyilvántartó.
     */
    private CimkeNyilvantarto cimkeNyilvantarto;
    /**
     * Felhasználó nyilvántartó.
     */
    private FelhasznaloNyilvantarto felhasznaloNyilvantarto;
    /**
     * Recept nyilvántartó.
     */
    private ReceptNyilvantarto receptNyilvantarto;


}
