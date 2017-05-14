package hu.hubasky.gastromanager.entity.recept;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.Cimkezheto;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

/**
 * Egy receptet reprezentál.
 * Created by mirso on 2017. 04. 26..
 */

@IgnoreExtraProperties
public final class Recept extends Cimkezheto {
    /**
     * A recept tulajdonosa.
     */
    private Felhasznalo tulajdonos;
    /**
     * Az érkezések.
     */
    //kihagyjuk
    private final Set<EEtkezesek> ajanlottEtkezesek = EnumSet.noneOf(EEtkezesek.class);
    /**
     * A recept hozzávalói.
     */
    //recept details paneljén
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
    private double adag;

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
    public Recept(Felhasznalo tulajdonos, EReceptStatus status, String neve, String leirasa, String fenykepeURL, double adag) {
        setNeve(neve);
        setLeirasa(leirasa);
        setFenykepeURL(fenykepeURL);
        setAdag(adag);
        setTulajdonos(tulajdonos);
        setStatus(status);
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
        if (neve == null) throw new IllegalArgumentException();
        neve = neve.trim();
        if (neve.isEmpty()) {
            throw new IllegalArgumentException("A recept neve nem lehet üres!");
        }
        this.neve = neve;
    }

    /**
     * Visszaadja a recept tulajdonosát.
     *
     * @return az tulajdonos.
     */
    public Felhasznalo getTulajdonos() {
        return tulajdonos;
    }

    public void setTulajdonos(Felhasznalo tulajdonos) {
        if (tulajdonos == null) throw new IllegalArgumentException();
        this.tulajdonos = tulajdonos;
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
        if (leirasa == null) throw new IllegalArgumentException();
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
        if (fenykepeURL == null) throw new IllegalArgumentException();
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
    public double getAdag() {
        return adag;
    }

    /**
     * Beállítja, hány adagra vonatkozik a recept.
     *
     * @param adag az adag.
     */
    public void setAdag(double adag) {
        if (Double.isNaN(adag) || Double.isInfinite(adag)) throw new IllegalArgumentException();
        if (adag <= 0) {
            throw new IllegalArgumentException("Az adag nem lehet nulla vagy negatív!");
        }
        this.adag = adag;
    }

    /**
     * Visszaadja a recept státuszát.
     *
     * @return a státusz.
     */
    public EReceptStatus getStatus() {
        return status;
    }

    /**
     * Beállítja a recept státuszát.
     *
     * @param status a státusz.
     */
    public void setStatus(EReceptStatus status) {
        this.status = status;
    }

    /**
     * Felvesz egy hozzávalót.
     *
     * @param hozzavalo a hozzávaló.
     */
    public void addHozzavalo(Hozzavalo hozzavalo) {
        if (hozzavalo == null) throw new IllegalArgumentException();
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
        if (alapanyag == null) throw new IllegalArgumentException();

        Hozzavalo fnd;
        while ((fnd = Helper.find(hozzavalok, alapanyag)) != null) {
            hozzavalok.remove(fnd);
        }
    }

    /**
     * Visszaadja a hozzávalókat.
     *
     * @return a hozzávalók.
     */
    public Set<Hozzavalo> getHozzavalok() {
        return Collections.unmodifiableSet(hozzavalok);
    }

    /**
     * Hozzáad egy ajánlott étkezést.
     *
     * @param e az érkezés
     */
    public void addEtkezes(EEtkezesek e) {
        ajanlottEtkezesek.add(e);
    }

    /**
     * Leválaszt egy ajánlott étkezést.
     *
     * @param e az étkezés.
     */
    public void remEtkezes(EEtkezesek e) {
        ajanlottEtkezesek.remove(e);
    }

    /**
     * Visszaadja az ajánlott étkezéseket.
     *
     * @return az étkezések.
     */
    public Set<EEtkezesek> getAjanlottEtkezesek() {
        return Collections.unmodifiableSet(ajanlottEtkezesek);
    }

    /**
     * Példány másolása.
     *
     * @return az új példány.
     */
    public Recept masolas() {
        Recept ret = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        // cimkék másolása
        masolasIde(ret);

        for (Hozzavalo each : hozzavalok) {
            ret.addHozzavalo(each.masolas());
        }
        for (EEtkezesek each : ajanlottEtkezesek) {
            ret.addEtkezes(each);
        }

        return ret;
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

    /**
     * Tartalmazza-e valamelyik alapanyagot?
     *
     * @param vizsgalt a vizsgált alapanyagok.
     * @return true, ha igen.
     */
    public boolean isTartalmazottAlapanyag(Set<Alapanyag> vizsgalt) {
        if (vizsgalt == null) throw new IllegalArgumentException();
        for (Alapanyag each : vizsgalt) {
            if (Helper.find(hozzavalok, each) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tartalmaz-e a leírás a megadott szavakból.
     *
     * @param szavak a szavak nagybetűsítve.
     * @param mindet minden szóra keresnük
     * @return true ha igen.
     */
    public boolean isTartalmazottSzavak(Set<String> szavak, boolean mindet) {
        if (szavak == null) throw new IllegalArgumentException();
        if (szavak.isEmpty()) {
            return true;
        }

        Set<String> lszavak = new HashSet<>(Arrays.asList(leirasa.toUpperCase().split("[\\s]")));
        if (mindet) {
            Set<String> tmp = new HashSet<>(szavak);
            tmp.removeAll(lszavak);
            return tmp.isEmpty();
        } else {
            return Helper.isExistsIntersect(lszavak, szavak);
        }
    }

    /**
     * Kalkulációt készít a vásárlandó alapanyagokról a mennyiség függvényében.
     *
     * @param kertAdag a kívánt adag.
     * @return a vásárlandók listája.
     * @throws Exception Ha kivétel lépett fel.
     */
    public List<VasarlandoAlapanyag> vasarlandoKalkulacio(double kertAdag) {
        if (Double.isInfinite(kertAdag) || Double.isNaN(kertAdag) || kertAdag <= 0)
            throw new IllegalArgumentException("az kertAdag értéke érvénytelen!");

        List<VasarlandoAlapanyag> ret = new ArrayList<>();

        double arany = kertAdag / adag;


        for (Hozzavalo h : hozzavalok) {
            double hMennyiseg = h.getMennyiseg() * arany;
            hMennyiseg = h.getAlapanyag().getMennyitVasaroljak(hMennyiseg);
            ret.add(new VasarlandoAlapanyag(hMennyiseg, h.getAlapanyag()));
        }
        return ret;
    }

    @Override
    public boolean isMegfelelo(List<Cimke> tartalmaz, boolean mindegyikt, List<Cimke> kizart) {

        for (Hozzavalo h : hozzavalok) {
            if (!h.getAlapanyag().isMegfelelo(
                    Helper.filter(tartalmaz, ECimkeTipus.ALAPANYAG),
                    mindegyikt,
                    Helper.filter(kizart, ECimkeTipus.ALAPANYAG)

            )) {
                return false;
            }
        }

        // a recepte vonatkozó cimkék
        return super.isMegfelelo(
                Helper.filter(tartalmaz, ECimkeTipus.RECEPT),
                mindegyikt,
                Helper.filter(kizart, ECimkeTipus.RECEPT));

    }
}
