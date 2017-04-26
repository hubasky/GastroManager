package hu.hubasky.gastromanager.entity.bevlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Helper;
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

            VasarlandoAlapanyag fnd = Helper.find(vasarlandok, each.getAlapanyag());
            if(fnd!=null){
                fnd.addMennyiseg(each.getMennyiseg());
            }
            else{
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
        VasarlandoAlapanyag fnd;
        while((fnd = Helper.find(vasarlandok, alapanyag))!=null){
            vasarlandok.remove(fnd);
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
