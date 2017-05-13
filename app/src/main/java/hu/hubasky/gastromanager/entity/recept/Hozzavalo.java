package hu.hubasky.gastromanager.entity.recept;

import java.text.DecimalFormat;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.EgyediKulcs;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;

import static hu.hubasky.gastromanager.entity.EMennyisegiEgyseg.*;

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

    public String displayQuantity() {
        DecimalFormat df = new DecimalFormat("#.00");
        String valueUnit = "";
        switch (alapanyag.getMennyisegiEgyseg()) {
            case GRAMM:
                if (mennyiseg >= 1000)
                    valueUnit = df.format(mennyiseg / 1000) + " kg";
                else if (mennyiseg >= 10)
                    valueUnit = df.format(mennyiseg / 10) + " dkg";
                else valueUnit = String.valueOf(mennyiseg) + " g";
                break;
            case LITER:
                if (mennyiseg >= 1)
                    valueUnit = String.valueOf(mennyiseg) + " l";
                else if (mennyiseg >= 0.1)
                    valueUnit = String.valueOf(mennyiseg * 10) + " dl";
                else valueUnit = String.valueOf(mennyiseg * 1000) + " ml";
                break;
            case DARAB:
                valueUnit = String.valueOf(mennyiseg) + " db";
                break;
        }
        return valueUnit;
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
