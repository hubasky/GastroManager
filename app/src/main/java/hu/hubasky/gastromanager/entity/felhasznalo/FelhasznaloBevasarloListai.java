package hu.hubasky.gastromanager.entity.felhasznalo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;

/**
 * Egy felhasználóhoz rendelt bevásárlólista.
 * Created by mirso on 2017. 04. 26..
 */

public final class FelhasznaloBevasarloListai {
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
        this.felhasznalo = felhasznalo;
    }

    /**
     * Lista felvétele.
     *
     * @param lista a lista.
     */
    public void addLista(BevasarloLista lista) {
        if (lista == null) throw new AssertionError();
        if (!listak.contains(lista)) {
            listak.add(lista);
        }
    }

    /**
     * Visszaadja a felhasználó listáit.
     * @return a listák.
     */
    public List<BevasarloLista> getListak() {
        return Collections.unmodifiableList(listak);
    }
}
