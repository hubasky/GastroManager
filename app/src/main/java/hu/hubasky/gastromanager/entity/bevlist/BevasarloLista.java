package hu.hubasky.gastromanager.entity.bevlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

/**
 * Bevásárlólista példánya.
 * Created by mirso on 2017. 04. 26..
 */

public final class BevasarloLista {
    /**
     * A lista létrehozó tulajdonosa.
     */
    private final Felhasznalo tulajdonos;

    /**
     * A vásárlandó alapanyagokat tartalmazza.
     */
    private final Set<VasarlandoAlapanyag> vasarlandok;

    /**
     * Üres lista létrehozása.
     *
     * @param tulajdonos a tulajdonos.
     */
    public BevasarloLista(Felhasznalo tulajdonos) {
        this.tulajdonos = tulajdonos;
        vasarlandok = new HashSet<>();
    }

    /**
     * Felveszi a listára a vásárlandó alapanyagokat. Ha valamelyikből már van,
     * akkor növeli a mennyiséget.
     *
     * @param alapanyagok az alapanyagok listája.
     */
    public void addVasarlandok(List<VasarlandoAlapanyag> alapanyagok) {
        if (alapanyagok == null) throw new AssertionError();
        for (VasarlandoAlapanyag each : alapanyagok) {
            boolean bentvan = false;
            for (VasarlandoAlapanyag eachv : vasarlandok) {
                if (eachv.getAlapanyag().equals(each.getAlapanyag())) {
                    // ez már megvan
                    eachv.addMennyiseg(each.getMennyiseg());
                    bentvan = true;
                    break;
                }
            }
            if (!bentvan) {
                vasarlandok.add(each);
            }
        }
    }

    /**
     * Egy vásárlandó alapanyag felvétele.
     *
     * @param alapanyag az anyag.
     */
    public void addVasarlando(VasarlandoAlapanyag alapanyag) {
        if (alapanyag == null) throw new AssertionError();
        addVasarlandok(Arrays.asList(alapanyag));
    }

    /**
     * Egy alapanyag törlése.
     * @param alapanyag az anyag.
     */
    public void torol(Alapanyag alapanyag) {
        if (alapanyag == null) throw new AssertionError();
        /*
        Elvileg, minden alapanyag csak egyszer lehetne, de biztos, ami biztos
         */
        boolean wasdel = true;
        while (wasdel) {
            wasdel = false;
            for (VasarlandoAlapanyag each : vasarlandok) {
                if(each.getAlapanyag().equals(alapanyag)){
                    vasarlandok.remove(each);
                    wasdel=true;
                    break;
                }

            }
        }
    }

    /**
     * Beállítja egy alapanyag státuszát.
     * @param status a státusz.
     * @param alapanyag az alapanyag.
     */
    void status(EVasaroltStatus status,Alapanyag alapanyag) {
        if (alapanyag == null) throw new AssertionError();
        for (VasarlandoAlapanyag each : vasarlandok) {
            if (each.getAlapanyag().equals(alapanyag)) {
                each.setStatus(status);
                break;
            }
        }
    }

    /**
     * Bevásárlólista lezárása.
     */
    public void lezaras() {
        for (VasarlandoAlapanyag each : vasarlandok) {
            if (each.getStatus() == EVasaroltStatus.BESZERZENDO) {
                each.setStatus(EVasaroltStatus.KIHAGYOTT);
            }
        }
    }
}
