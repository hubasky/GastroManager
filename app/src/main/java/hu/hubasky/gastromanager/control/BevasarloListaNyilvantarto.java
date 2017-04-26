package hu.hubasky.gastromanager.control;

import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.felhasznalo.FelhasznaloBevasarloListai;

/**
 * Created by mirso on 2017. 04. 26..
 */

public interface BevasarloListaNyilvantarto {
    /**
     * Létrehoz egy felhasználó-listák összerendelést.
     * @param felhasznalo a felhasználó.
     * @return a példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    FelhasznaloBevasarloListai letrehoz(Felhasznalo felhasznalo) throws Exception;

    /**
     * Egy bevásárlólista tárolása.
     * @param lista a lista.
     * @throws Exception Ha kivétel lépett fel.
     */
    void tarolas(BevasarloLista lista) throws Exception;
}
