
package hu.hubasky.gastromanager.entity.alapanyag;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Cimkezheto;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;

/**
 * Egy alapanyagot leíró osztály.
 * Created by mirso on 2017. 04. 26..
 */

public final class Alapanyag extends Cimkezheto {

    /**
     * A mennyiségi egysége.
     */
    private EMennyisegiEgyseg mennyisegiEgyseg;
    /**
     * A kereskedelemben vásárolható mennyiségek a {@link EMennyisegiEgyseg}-re vonatkoztatva.
     */
    private final List<Double> vasarolhatoMennyisegek = new LinkedList<>();
    /**
     * Az alapanyag jellemzők.
     */
    private AlapanyagJellemzok jellemzok;
    /**
     * Az alapanyag neve.
     */
    private String neve;
    /**
     * Az egységni {@link EMennyisegiEgyseg} tömege.
     */
    private double egysegeGramm;

    /**
     * Konstruktor.
     *
     * @param mennyisegiEgyseg a mennyiségi egysége.
     * @param jellemzok        az alapanyag jellemzők.
     * @param neve             az alapanyag neve.
     * @param egysegeGramm     a {@link EMennyisegiEgyseg} egységére vonatkozó tömeg.
     */
    public Alapanyag(EMennyisegiEgyseg mennyisegiEgyseg, AlapanyagJellemzok jellemzok, String neve, double egysegeGramm) {
        if (jellemzok == null) throw new IllegalArgumentException();

        this.jellemzok = jellemzok;
        setMennyisegiEgyseg(mennyisegiEgyseg);
        setNeve(neve);
        setEgysegeGramm(egysegeGramm);
    }

    /**
     * A mennyiségi egység, amiben kapható.
     *
     * @return a mennyiségi egysége.
     */
    public EMennyisegiEgyseg getMennyisegiEgyseg() {
        return mennyisegiEgyseg;
    }

    /**
     * Beállítja a mennyiségi egységet.
     *
     * @param mennyisegiEgyseg egysége.
     */
    public void setMennyisegiEgyseg(EMennyisegiEgyseg mennyisegiEgyseg) {
        this.mennyisegiEgyseg = mennyisegiEgyseg;
    }

    /**
     * Az alapanyag neve.
     *
     * @return a név.
     */
    public String getNeve() {
        return neve;
    }

    /**
     * Beállítja az alapanyag nevét.
     *
     * @param neve a név.
     */
    public void setNeve(String neve) {
        if (neve == null) throw new IllegalArgumentException();
        neve = neve.trim();
        if (neve.isEmpty()) {
            throw new IllegalArgumentException("Az alapanyag neve nem lehet üres!");
        }
        this.neve = neve;
    }

    /**
     * A {@link EMennyisegiEgyseg}-re vonatkoztatott egység tömege grammban.
     *
     * @return gramm.
     */
    public double getEgysegeGramm() {
        return egysegeGramm;
    }

    /**
     * Beállítja a {@link EMennyisegiEgyseg}-re vonatkoztatott egység tömeget.
     *
     * @param egysegeGramm grammban.
     */
    public void setEgysegeGramm(double egysegeGramm) {
        if (Double.isNaN(egysegeGramm) || Double.isInfinite(egysegeGramm))
            throw new IllegalArgumentException();
        if (egysegeGramm <= 0) {
            throw new IllegalArgumentException("egysegeGramm <= 0");
        }
        this.egysegeGramm = egysegeGramm;
    }


    /**
     * Felvesz egy vásárolható mennyiséget.
     *
     * @param mennyiseg a mennyiség.
     */
    public void addVasarolhatoMennyiseg(double mennyiseg) {
        if (Double.isNaN(mennyiseg) || Double.isInfinite(mennyiseg))
            throw new IllegalArgumentException();
        if (mennyiseg <= 0) {
            throw new IllegalArgumentException("A vásárolható mennyiség nem lehet nulla vagy negatív!");
        }

        if (!vasarolhatoMennyisegek.contains(mennyiseg)) {
            vasarolhatoMennyisegek.add(mennyiseg);
            Collections.sort(vasarolhatoMennyisegek, new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return -Double.compare(o1, o2);

                }
            });
        }
    }

    /**
     * Töröl egy vásárolható mennyiséget.
     *
     * @param mennyiseg a mennyiség.
     */
    public void remVasarolhatoMennyiseg(double mennyiseg) {
        vasarolhatoMennyisegek.remove(mennyiseg);
    }

    /**
     * Visszaadja az alapanyag jellemzőket.
     *
     * @return a jellemtzők.
     */
    public AlapanyagJellemzok getJellemzok() {
        return jellemzok;
    }

    /**
     * Visszaadja a vásárolható mennyiségek listáját.
     *
     * @return a lista.
     */
    public List<Double> getVasarolhatoMennyisegek() {
        return Collections.unmodifiableList(vasarolhatoMennyisegek);
    }

    /**
     * A vásárlandó mennyiségek függvényében megadja, hogy mennyit vásároljak az alapanyagvból.
     * Ha nincsenek megadva vásárolható mennyiségek {@link #addVasarolhatoMennyiseg(double)}}, akkor
     * 1-nek a többszörösét veszi.
     *
     * @param igeny az igény.
     * @return a vásárlandó mennyiség.
     */
    public double getMennyitVasaroljak(double igeny) {
        if (Double.isInfinite(igeny) || Double.isNaN(igeny)) throw new IllegalArgumentException();
        if (igeny <= 0)
            throw new IllegalArgumentException("igeny<=0");

        igeny = Math.ceil(igeny);

        if (vasarolhatoMennyisegek.isEmpty()) {
            return igeny;
        }


        Set<Double> kosar = new HashSet<>();
        vasarol(0.0, igeny, vasarolhatoMennyisegek, kosar);

        double maxMaradek = Double.MAX_VALUE;
        double maxKosar = 0;

        for (double vegyek : kosar) {
            if(vegyek-igeny<maxMaradek){
                maxMaradek=vegyek-igeny;
                maxKosar=vegyek;
            }
        }
        return maxKosar;
    }


    /**
     * Adott kosártartalomvól kiindulva vásárol a címletmennyiségek szerint.
     *
     * @param megvett     hol tart a vásárlás.
     * @param igeny       mennyi az igény.
     * @param darabszamok milyen darabszámokat lehet vásárolni.
     * @param kosar       milyen kosarak alakultak ki.
     */
    private void vasarol(double megvett, double igeny, List<Double> darabszamok, Set<Double> kosar) {
        if (megvett >= igeny) {
            kosar.add(megvett);
            return;
        }

        for (double c : darabszamok) {
            vasarol(megvett + c, igeny, darabszamok, kosar);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alapanyag alapanyag = (Alapanyag) o;

        if (!jellemzok.equals(alapanyag.jellemzok)) return false;
        return neve.equals(alapanyag.neve);

    }

    @Override
    public int hashCode() {
        return neve.hashCode();
    }
}
