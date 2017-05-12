package hu.hubasky.gastromanager.control;

import java.util.List;

import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

/**
 * Az alapanyag nyilvántartó szerkezete.
 * Created by mirso on 2017. 04. 26..
 */

public interface AlapanyagNyilvantarto extends ControlBase{
    /**
     * Alapanyag tárolása.
     * @param alapanyag a példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    void tarolas(Alapanyag alapanyag) throws Exception;

    /**
     * Alapanyag törlése.
     * @param alapanyag a példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    void torles(Alapanyag alapanyag) throws Exception;

    /**
     * Keresés az alapanyagok között.
     * @param jellemzok a keresési jellemzők.
     * @return a találatok listája.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<Alapanyag> keres(AlapanyagKeresesiJellemzok jellemzok) throws Exception;

    /**
     * Aszinkron keresésé végrehajtása.
     * @param jellemzok a keresési jellemzők.
     * @param callback a callback, ahová vissza kell jelezni.
     */
    void keres(AlapanyagKeresesiJellemzok jellemzok,ControlResultListener<Alapanyag> callback);
}
