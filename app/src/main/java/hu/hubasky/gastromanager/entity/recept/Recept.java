package hu.hubasky.gastromanager.entity.recept;

import java.net.URL;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Helper;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

/**
 * Egy receptet reprezentál.
 * Created by mirso on 2017. 04. 26..
 */

public final class Recept {
    /**
     * A recept tulajdonosa.
     */
    private Felhasznalo tulajdonos;
    /**
     * Az érkezések.
     */
    private final Set<EEtkezesek> ajanlottEtkezesek = EnumSet.noneOf(EEtkezesek.class);
    /**
     * A recept hozzávalói.
     */
    private final Set<Hozzavalo> hozzavalok = new HashSet<>();
    /**
     * A recept státusza.
     */
    private EReceptStatus status;
    /**
     * A recept neve.
     */
    private String neve;
    /**
     * A recept szöveges leírása.
     */
    private String leirasa;
    /**
     * A recept fényképét tartalmazó URL.
     */
    private String fenykepeURL;
    /**
     * Az adag.
     */
    private int adag;

    /**
     * Recept létrehozása.
     *
     * @param tulajdonos  a recept tulajdonosa.
     * @param status      a recept státusza.
     * @param neve        a recept neve.
     * @param leirasa     a szöveges leírása.
     * @param fenykepeURL a fénykép URL.
     * @param adag        hány adagra való.
     */
    public Recept(Felhasznalo tulajdonos, EReceptStatus status, String neve, String leirasa, String fenykepeURL, int adag) {
        if (tulajdonos == null) throw new AssertionError();

        setNeve(neve);
        setLeirasa(leirasa);
        setFenykepeURL(fenykepeURL);
        setAdag(adag);

        this.tulajdonos = tulajdonos;
        this.status = status;
    }

    /**
     * Visszaadja a recept nevét.
     *
     * @return a név.
     */
    public String getNeve() {
        return neve;
    }

    /**
     * Beállítja a recept nevét.
     *
     * @param neve a név
     */
    public void setNeve(String neve) {
        if (neve == null) throw new AssertionError();
        neve = neve.trim();
        if (neve.isEmpty()) {
            throw new IllegalArgumentException("A recept neve nem lehet üres!");
        }
        this.neve = neve;
    }

    /**
     * Visszaadja a recept szöveges leírását.
     *
     * @return a leírás.
     */
    public String getLeirasa() {
        return leirasa;
    }

    /**
     * Beállítja a recept leírását.
     *
     * @param leirasa a leírás
     */
    public void setLeirasa(String leirasa) {
        if (leirasa == null) throw new AssertionError();
        leirasa = leirasa.trim();
        if (leirasa.isEmpty()) {
            throw new IllegalArgumentException("A recept leírása nem lehet üres!");
        }

        this.leirasa = leirasa;
    }

    /**
     * Visszaadja a recept fényképének URL-jét.
     *
     * @return az URL.
     */
    public String getFenykepeURL() {
        return fenykepeURL;
    }

    /**
     * Beállítja a recept fényképének URL-ját.
     *
     * @param fenykepeURL az URL.
     */
    public void setFenykepeURL(String fenykepeURL) {
        if (fenykepeURL == null) throw new AssertionError();
        fenykepeURL = fenykepeURL.trim();
        if (fenykepeURL.isEmpty()) {
            throw new IllegalArgumentException("A recept képe-URL nem lehet üres");
        }
        this.fenykepeURL = fenykepeURL;
    }

    /**
     * Visszaadja, hány adagra vonatkozik a recept.
     *
     * @return az adag.
     */
    public int getAdag() {
        return adag;
    }

    /**
     * Beállítja, hány adagra vonatkozik a recept.
     *
     * @param adag az adag.
     */
    public void setAdag(int adag) {
        if (adag <= 0) {
            throw new IllegalArgumentException("Az adag nem lehet nulla vagy negatív!");
        }
        this.adag = adag;
    }

    /**
     * Felvesz egy hozzávalót.
     *
     * @param hozzavalo a hozzávaló.
     */
    public void addHozzavalo(Hozzavalo hozzavalo) {
        if (hozzavalo == null) throw new AssertionError();
        Hozzavalo fnd = Helper.find(hozzavalok, hozzavalo.getAlapanyag());
        if (fnd != null) {
            fnd.addMennyiseg(hozzavalo.getMennyiseg());
        } else {
            hozzavalok.add(hozzavalo);
        }
    }

    /**
     * Töröl egy alapanyagot a hozzávalók közül.
     *
     * @param alapanyag az alapanyag.
     */
    public void remHozzavalo(Alapanyag alapanyag) {
        if (alapanyag == null) throw new AssertionError();

        Hozzavalo fnd;
        while ((fnd = Helper.find(hozzavalok, alapanyag)) != null) {
            hozzavalok.remove(fnd);
        }
    }

    /**
     * Példány másolása.
     *
     * @return az új példány.
     */
    public Recept masolas() {
        throw new UnsupportedOperationException("Nincs implementálva!");
    }

    /**
     * Priváttá teszi a receptet.
     *
     * @param felhasznalo a felhasználó.
     */
    public void privat(Felhasznalo felhasznalo) {
        tulajdonos = felhasznalo;
        status = EReceptStatus.PRIVAT;
    }

    /**
     * Megosztottá teszi a receptet.
     *
     * @param felhasznalo a felhasználó.
     */
    public void megosztott(Felhasznalo felhasznalo) {
        tulajdonos = felhasznalo;
        status = EReceptStatus.MEGOSZTOTT;
    }

    /**
     * Publikussá teszi a receptet.
     */
    public void publikus() {
        status = EReceptStatus.PUBLIKUS;
    }
}
