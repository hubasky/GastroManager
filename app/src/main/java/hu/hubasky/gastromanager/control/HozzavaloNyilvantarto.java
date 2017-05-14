package hu.hubasky.gastromanager.control;

import java.util.List;

import hu.hubasky.gastromanager.entity.recept.Hozzavalo;

public interface HozzavaloNyilvantarto extends ControlBase {
    /**
     * Hozzavaló tárolása.
     * @param hozzavalo a példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    void tarolas(Hozzavalo hozzavalo) throws Exception;

    /**
     * Hozzavaló törlése.
     * @param hozzavalo a példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    void torles(Hozzavalo hozzavalo) throws Exception;

    /**
     * Keresés az Hozzavalók között.
     * @return a találatok listája.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<Hozzavalo> keres() throws Exception;

    /**
     * Aszinkron keresésé végrehajtása.
     * @param callback a callback, ahová vissza kell jelezni.
     */
    void keres(ControlResultListener<Hozzavalo> callback);
}
