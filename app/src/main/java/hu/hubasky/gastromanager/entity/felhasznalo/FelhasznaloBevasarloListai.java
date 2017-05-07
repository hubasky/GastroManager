package hu.hubasky.gastromanager.entity.felhasznalo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.hubasky.gastromanager.entity.EgyediKulcs;
import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;

/**
 * Egy felhasználóhoz rendelt bevásárlólista.
 * Created by mirso on 2017. 04. 26..
 */

public final class FelhasznaloBevasarloListai extends EgyediKulcs {
    /**
     * A felhasználó.
     */
    private final Felhasznalo felhasznalo;
    /**
     * A bevásárlólisták.
     */
    private final List<BevasarloLista> listak = new ArrayList<>();

    /**
     * Konstruktor.
     *
     * @param felhasznalo a felhasználó.
     */
    public FelhasznaloBevasarloListai(Felhasznalo felhasznalo) {
        if (felhasznalo == null) {
            throw new IllegalArgumentException();
        }
        this.felhasznalo = felhasznalo;
    }

    /**
     * Lista felvétele.
     *
     * @param lista a lista.
     */
    public void addLista(BevasarloLista lista) {
        if (lista == null) throw new IllegalArgumentException();
        if (!listak.contains(lista)) {
            listak.add(lista);
        }
    }

    /**
     * Visszaadja a felhasználó listáit.
     *
     * @return a listák.
     */
    public List<BevasarloLista> getListak() {
        return Collections.unmodifiableList(listak);
    }
}
