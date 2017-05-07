package hu.hubasky.gastromanager.control;

import java.util.List;

import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

/**
 * A felhasználó nyilvántartás szerkezete.
 * Created by mirso on 2017. 04. 26..
 */

public interface FelhasznaloNyilvantarto extends ControlBase{
    /**
     * Felhasználók keresése névtöredék szerint, az aktuális felhasználó kizárásával.
     *
     * @param nevtoredek a névtöredék.
     * @param kizart     a kizárt felhasználó.
     * @return a találatok listája.
     * @throws Exception Ha kivétel történt.
     */
    List<Felhasznalo> keres(String nevtoredek, Felhasznalo kizart) throws Exception;

    /**
     * Felhasználó bejelentkezése.
     *
     * @param usernev a felhasználói név.
     * @param jelszo  a jelszó.
     * @return a {@link Felhasznalo} példány, ha jók a bejelentkezési adatok, egyébként {@code null}.
     * @throws Exception Ha kivétel lépett fel.
     */
    Felhasznalo login(String usernev, String jelszo) throws Exception;

    /**
     * Új felhasználó regisztrálása.
     *
     * @param usernev a bejelentkezési név.
     * @param jelszo  a jelszó.
     * @param nev     a felhasználó teljes neve.
     * @return sikeres regisztrációkor a {@link Felhasznalo} példány.
     * @throws Exception Ha regisztrációs hiba történt.
     */
    Felhasznalo regiszter(String usernev, String jelszo, String nev) throws Exception;

    /**
     * Visszaadja a felhasználó számára mások által elküldött recepteket.
     *
     * @param felhasznalo a felhasználó.
     * @return a kapott receptjei.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<Recept> getKapottReceptek(Felhasznalo felhasznalo) throws Exception;

    /**
     * A felhasználó eldobja (törli) a kapott receptet.
     *
     * @param felhasznalo a felhasználó.
     * @param recept      a törlendő recept.
     * @throws Exception Ha kivétel lépett fel.
     */
    void remKapottRecept(Felhasznalo felhasznalo, Recept recept) throws Exception;

    /**
     * Visszaadja a felhasználó által kezelt (kapott, továbbított) bevásárlólistákat.
     *
     * @param felhasznalo a felhasználó.
     * @return a bevásálólisták listája.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<BevasarloLista> getBevasarloListak(Felhasznalo felhasznalo) throws Exception;

    /**
     * Felhasználóhoz egy új bevásárlólistát rendel.
     *
     * @param felhasznalo    a felhasználó.
     * @param bevasarloLista a bevásárlólista.
     * @throws Exception Ha kivétel lépett fel.
     */
    void addBevasarloLista(Felhasznalo felhasznalo, BevasarloLista bevasarloLista) throws Exception;

    /**
     * Recept továbbítása (küldése) felhasználónak.
     *
     * @param felhasznalo a cél felhasználó.
     * @param recept      a recept.
     * @throws Exception Ha kivétel lépett fel.
     */
    void kuldes(Felhasznalo felhasznalo, Recept recept) throws Exception;

    /**
     * Felvesz egy receptet kedvencként a felhasználóhoz.
     * @param felhasznalo a felhasználó.
     * @param recept a recept.
     * @throws Exception Ha kivétel lépett fel.
     */
    void addKedvenc(Felhasznalo felhasznalo, Recept recept) throws Exception;

    /**
     * Leválaszt egy receptet kedvencként a felhasználóról.
     * @param felhasznalo a felhasználó.
     * @param recept a recept.
     * @throws Exception Ha kivétel lépett fel.
     */
    void remKedvenc(Felhasznalo felhasznalo, Recept recept) throws Exception;

    /**
     * Visszaadja a felhasználó összes kedvenc receptjét.
     * @param felhasznalo a felhasználó.
     * @return a kedvencek listája.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<Recept> getKedvencek(Felhasznalo felhasznalo) throws Exception;

}
