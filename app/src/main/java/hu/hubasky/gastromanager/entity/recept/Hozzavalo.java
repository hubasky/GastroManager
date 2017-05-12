package hu.hubasky.gastromanager.entity.recept;

import hu.hubasky.gastromanager.entity.EgyediKulcs;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;

/**
 * A recept egy hozzávlóját tartalmazzza.
 * Created by mirso on 2017. 04. 26..
 */

public class Hozzavalo extends EgyediKulcs {
    /**
     * Milyen mennyiségben kell megvenni az alapanyagot.
     */
    private double mennyiseg;
    /**
     * Milyen alapanyagot kell megvenni.
     */
    private final Alapanyag alapanyag;

    /**
     * Konstruktor.
     *
     * @param mennyiseg a megvásárolandó mennyiség.
     * @param alapanyag a megvásárolandó alapanyag.
     */
    public Hozzavalo(double mennyiseg, Alapanyag alapanyag) {
        if (alapanyag == null) throw new IllegalArgumentException();
        if (!(!Double.isInfinite(mennyiseg) && !Double.isNaN(mennyiseg)))
            throw new IllegalArgumentException();

        if (mennyiseg <= 0) {
            throw new IllegalArgumentException("A hozzávaló alapanyag mennyisége nem lehet 0 vagy negatív!");
        }

        this.mennyiseg = mennyiseg;
        this.alapanyag = alapanyag;
    }


    /**
     * Milyen mennyiségben kell megvenni az alapanyagot.ű
     *
     * @return a mennyiség.
     */
    public double getMennyiseg() {
        return mennyiseg;
    }

    /**
     * Milyen alapanyagot kell megvenni.
     *
     * @return az alapanyag példány.
     */
    public Alapanyag getAlapanyag() {
        return alapanyag;
    }

    /**
     * Mennyiség növelése.
     *
     * @param novekedes a növekedés.
     */
    public void addMennyiseg(double novekedes) {
        if (Double.isNaN(novekedes) || Double.isInfinite(novekedes))
            throw new IllegalArgumentException();

        if (novekedes <= 0) {
            throw new IllegalArgumentException("A hozzávalók mennyisége 0 vagy negatív értékkel nem növelhető!");
        }
        mennyiseg += novekedes;
    }

    /**
     * Elkészíti a másolatát.
     *
     * @return a másolat.
     */
    public Hozzavalo masolas() {
        return new Hozzavalo(mennyiseg, alapanyag);
    }


}