package hu.hubasky.gastromanager.entity.felhasznalo;

import hu.hubasky.gastromanager.entity.EgyediKulcs;
import hu.hubasky.gastromanager.entity.recept.Recept;

/**
 * Felhasználó és kedvenc összerendelése.
 * Created by mirso on 2017. 05. 07..
 */

public class FelhasznaloKedvencei extends EgyediKulcs{
    /**
     * A felhasználó.
     */
    private final Felhasznalo felasznalo;
    /**
     * A recept.
     */
    private final Recept recept;

    /**
     * Konstruktor.
     *
     * @param felasznalo a felhasználó.
     * @param recept     a recept.
     */
    public FelhasznaloKedvencei(Felhasznalo felasznalo, Recept recept) {
        if (felasznalo == null) throw new IllegalArgumentException();
        if (recept == null) throw new IllegalArgumentException();
        this.felasznalo = felasznalo;
        this.recept = recept;
    }


    /**
     * A felhasználó.
     */
    public Felhasznalo getFelasznalo() {
        return felasznalo;
    }

    /**
     * A recept.
     */
    public Recept getRecept() {
        return recept;
    }

    /**
     * Visszaadja, hogy a kedvenc összerendelés a paraméterben megadott felhasználóhoz tartozik.
     *
     * @param vizsgaltFelhasznalo a vizsgált felhasználó.
     * @return true, ha igen.
     */
    public boolean isMegfelelo(Felhasznalo vizsgaltFelhasznalo) {
        if (vizsgaltFelhasznalo == null) throw new IllegalArgumentException();
        return felasznalo.isAzonos(vizsgaltFelhasznalo.getUsernev());
    }
}
