package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.control.BevasarloListaNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.felhasznalo.FelhasznaloBevasarloListai;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyBevasarloListaNyilvantarto implements BevasarloListaNyilvantarto {

    /**
     * Bevásárlólisták.
     */
    private final List<BevasarloLista> listak = new ArrayList<>();


    @Override
    public void init(Controls controls) {

    }

    @Override
    public FelhasznaloBevasarloListai letrehoz(Felhasznalo felhasznalo) throws Exception {
        return new FelhasznaloBevasarloListai(felhasznalo);
    }

    @Override
    public void tarolas(BevasarloLista lista) throws Exception {
        if (lista == null) throw new IllegalArgumentException("lista nem lehet null!");
        if (!listak.contains(lista)) {
            if (!lista.isUnuiqueKey()) {
                lista.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
            }
            listak.add(lista);
        }
    }
}
