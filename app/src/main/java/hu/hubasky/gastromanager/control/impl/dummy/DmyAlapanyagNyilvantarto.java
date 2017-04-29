package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
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
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.DARAB,
                new AlapanyagJellemzok(100, 5.4, 4.8, .3, 276),
                "Tojás", 40));
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, 12.3, 1.3, 76.3, 1461),
                "Liszt", 100));
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, 0, 0, 99.3, 1670),
                "Cukor", 100));
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, 13.8, 1.4, 9.8, 412),
                "Élesztő", 100));
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.DARAB,
                new AlapanyagJellemzok(100, 21.4, 19, 32.4, 1621),
                "Kakaópor", 100));
        adatok.add(new Alapanyag(
                EMennyisegiEgyseg.LITER,
                new AlapanyagJellemzok(100, 3.4, 2.8, 5.3, 252),
                "Tej", 1000));

    }

    @Override
    public void init(Controls controls) {
        CimkeNyilvantarto cimkeNyilvantarto = controls.getCimkeNyilvantarto();
        try {
            // tojás
            adatok.get(0).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "állat").get(0));
            // list
            adatok.get(1).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény").get(0));
            // cukor
            adatok.get(2).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "magas").get(0));
            // élesztő
            adatok.get(3).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény").get(0));
            // kakaó
            adatok.get(4).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény").get(0));
            adatok.get(4).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "magas").get(0));
            // tej
            adatok.get(5).addCimke(cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "tej").get(0));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void tarolas(Alapanyag alapanyag) throws Exception {
        if (adatok.contains(alapanyag)) {
            adatok.remove(alapanyag);
        }
        adatok.add(alapanyag);
    }

    @Override
    public void torles(Alapanyag alapanyag) throws Exception {
        adatok.remove(alapanyag);
    }

    @Override
    public List<Alapanyag> keres(final AlapanyagKeresesiJellemzok jellemzok) throws Exception {
        return Helper.filter(adatok, new Helper.Checker<Alapanyag>() {
            @Override
            public boolean check(Alapanyag param) {
                return jellemzok.isMegfelel(param);
            }
        });

    }
}
