package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
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
        List<Alapanyag> tmp = Arrays.asList(
                new Alapanyag(
                        EMennyisegiEgyseg.DARAB,
                        new AlapanyagJellemzok(100, 5.4 / 100.0, 4.8 / 100.0, .3 / 100.0, 276),
                        "Tojás", 40),
                new Alapanyag(
                        EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 12.3 / 100.0, 1.3 / 100.0, 76.3 / 100.0, 1461),
                        "Liszt", 100),
                new Alapanyag(
                        EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 0, 0, 99.3 / 100.0, 1670),
                        "Cukor", 100),
                new Alapanyag(
                        EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 13.8 / 100.0, 1.4 / 100.0, 9.8 / 100.0, 412),
                        "Élesztő", 100),
                new Alapanyag(
                        EMennyisegiEgyseg.DARAB,
                        new AlapanyagJellemzok(100, 21.4 / 100.0, 19 / 100.0, 32.4 / 100.0, 1621),
                        "Kakaópor", 100),
                new Alapanyag(
                        EMennyisegiEgyseg.LITER,
                        new AlapanyagJellemzok(100, 3.4 / 100.0, 2.8 / 100.0, 5.3 / 100.0, 252),
                        "Tej", 1000));
        for (Alapanyag x : tmp) {
            try {
                tarolas(x);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean init(Controls controls) {

        CimkeNyilvantarto cimkeNyilvantarto = controls.getCimkeNyilvantarto();
        try {
            // tojás
            List<Cimke> c;
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "állat");
            if (c.isEmpty()) return false;
            adatok.get(0).addCimke(c.get(0));
            // list
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény");
            if (c.isEmpty()) return false;
            adatok.get(1).addCimke(c.get(0));
            // cukor
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "magas");
            if (c.isEmpty()) return false;
            adatok.get(2).addCimke(c.get(0));
            // élesztő
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény");
            if (c.isEmpty()) return false;
            adatok.get(3).addCimke(c.get(0));
            // kakaó
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény");
            if (c.isEmpty()) return false;
            adatok.get(4).addCimke(c.get(0));
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "magas");
            if (c.isEmpty()) return false;
            adatok.get(4).addCimke(c.get(0));
            // tej
            c = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "tej");
            if (c.isEmpty()) return false;
            adatok.get(5).addCimke(c.get(0));
            return true;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void tarolas(Alapanyag alapanyag) throws Exception {
        if (alapanyag == null) throw new IllegalArgumentException("alapanyag nem lehet null!");

        if (!alapanyag.isUnuiqueKey()) {
            alapanyag.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
        }

        if (adatok.contains(alapanyag)) {
            adatok.remove(alapanyag);
        }
        adatok.add(alapanyag);
    }

    @Override
    public void torles(Alapanyag alapanyag) throws Exception {
        if (alapanyag == null) throw new IllegalArgumentException("alapanyag nem lehet null!");
        adatok.remove(alapanyag);
    }

    @Override
    public List<Alapanyag> keres(final AlapanyagKeresesiJellemzok jellemzok) throws Exception {
        if (jellemzok == null) throw new IllegalArgumentException("jellemzok nem lehet null!");
        return Helper.filter(adatok, new Helper.Checker<Alapanyag>() {
            @Override
            public boolean check(Alapanyag param) {
                return jellemzok.isMegfelel(param);
            }
        });

    }
}
