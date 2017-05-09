package hu.hubasky.gastromanager.entity.felhasznalo;

import hu.hubasky.gastromanager.entity.EgyediKulcs;
import hu.hubasky.gastromanager.entity.recept.Recept;

/**
 * Címzett-Kuldő felhasználó és recept összerendelés.
 * Created by mirso on 2017. 04. 26..
 */

public final class KuldottReceptek extends EgyediKulcs{
    /**
     * A recept.
     */
    private final Recept recept;
    /**
     * A címzett felhasználó.
     */
    private final Felhasznalo cimzett;
    /**
     * A küldő felhasználó.
     */
    private final Felhasznalo kuldo;

    /**
     * Konstruktor.
     *
     * @param recept  a recept.
     * @param kuldo   a küldő felhasználó.
     * @param cimzett a címzett felhasználó.
     */
    public KuldottReceptek(Recept recept, Felhasznalo kuldo, Felhasznalo cimzett) {
        if (recept == null) throw new IllegalArgumentException();
        if (cimzett == null) throw new IllegalArgumentException();
        if (kuldo == null) throw new IllegalArgumentException();
        this.recept = recept;
        this.cimzett = cimzett;
        this.kuldo = kuldo;
    }


    /**
     * A recept.
     *
     * @return a példény.
     */
    public Recept getRecept() {
        return recept;
    }

    /**
     * A címzett felhasználó.
     *
     * @return a példány.
     */
    public Felhasznalo getCimzett() {
        return cimzett;
    }

    /**
     * A küldő felhasználó.
     *
     * @return a példány.
     */
    public Felhasznalo getKuldo() {
        return kuldo;
    }
}
