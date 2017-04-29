package hu.hubasky.gastromanager.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;

/**
 * Created by mirso on 2017. 04. 26..
 */

public final class Helper {

    /**
     * Egy elem vizsgálatának függvénye.
     *
     * @param <T> a vizsgálandó típus.
     */
    public interface Checker<T> {
        /**
         * Megvizsgálja az átadott paraméter.
         *
         * @return a vizsgálat logikai eredmény.
         */
        boolean check(T param);
    }

    /**
     * Felkeresi a megadott listában a kívánt alapanyagot.
     *
     * @param search    a lista, amiben keresünk.
     * @param alapanyag a keresett alapanyag.
     * @param <T>       a lista típus.
     * @return a találat vagy {@code null} ha nincs benne.
     */
    public static <T extends Hozzavalo> T find(Set<T> search, Alapanyag alapanyag) {
        for (T each : search) {
            if (each.getAlapanyag().equals(alapanyag)) {
                return each;
            }
        }
        return null;
    }

    /**
     * Az {@code osszes} halmaz cimkéiből kiválasztja a megfelelő típusúakat.
     *
     * @param osszes az összes.
     * @param tipus  a kívánt típus.
     * @return a cimkék listája.
     */
    public static Set<Cimke> filter(Set<Cimke> osszes, final ECimkeTipus tipus) {
        if (tipus == ECimkeTipus.MINDEN) {
            return Collections.unmodifiableSet(osszes);
        }
        return filter(osszes, new Checker<Cimke>() {
            @Override
            public boolean check(Cimke param) {
                return param.isMegfelelo(tipus);
            }
        });
    }

    /**
     * Az {@code osszes} halmaz cimkéiből kiválasztja a megfelelő típusúakat.
     *
     * @param osszes az összes.
     * @param tipus  a kívánt típus.
     * @return a cimkék listája.
     */
    public static List<Cimke> filter(List<Cimke> osszes, final ECimkeTipus tipus) {
        if (tipus == ECimkeTipus.MINDEN) {
            return Collections.unmodifiableList(osszes);
        }
        return filter(osszes, new Checker<Cimke>() {
            @Override
            public boolean check(Cimke param) {
                return param.isMegfelelo(tipus);
            }
        });
    }


    /**
     * Visszaadja, hogy létezik-e metszete a két halmaznak.
     *
     * @param t1  egyik halmaz.
     * @param t2  másik halmaz.
     * @param <T> elemtípus.
     * @return true, ha igen.
     */
    public static <T> boolean isExistsIntersect(Set<T> t1, Set<T> t2) {
        Set<T> tmp = new HashSet<>(t1.size() > t2.size() ? t1.size() : t2.size());
        tmp.addAll(t1);
        tmp.retainAll(t2);
        return !tmp.isEmpty();
    }

    /**
     * {@code null}-tűrő trimm.
     *
     * @param str a string.
     * @return a trimmelt string. Ha a string {@code} null, akkor üres stringet ad vissza.
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * {@code null}-tűrő tartalmazás vizsgálat, ami kis- és nagybetű között nem tesz különbséget.
     *
     * @param instr a string, amiben keresünk.
     * @param part  a szövegdarab, amiben keresünk.
     * @return true, ha benne van.
     */
    public static boolean contains(String instr, String part) {
        if (instr == null || part == null) {
            return false;
        }
        return instr.toUpperCase().contains(part.toUpperCase());
    }

    /**
     * Egy gyűjtemény szűrését végzi el.
     *
     * @param items a vizsgált elemek.
     * @param func  a vizsgálatot végző funckió.
     * @param <T>   az elemípus.
     * @return a feltételnek megfelelő elemek.
     */
    public static <T> List<T> filter(List<T> items, Checker<T> func) {
        if (func == null) {
            throw new IllegalArgumentException("A func paraméter nem lehet null!");
        }
        if (items == null || items.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> ret = null;
        for (T each : items) {
            if (func.check(each)) {
                if (ret == null) {
                    ret = new LinkedList<>();
                }
                ret.add(each);
            }
        }
        return ret == null ? Collections.<T>emptyList() : ret;

    }

    /**
     * Egy gyűjtemény szűrését végzi el.
     *
     * @param items a vizsgált elemek.
     * @param func  a vizsgálatot végző funckió.
     * @param <T>   az elemípus.
     * @return a feltételnek megfelelő elemek.
     */
    public static <T> Set<T> filter(Set<T> items, Checker<T> func) {
        if (func == null) {
            throw new IllegalArgumentException("A func paraméter nem lehet null!");
        }
        if (items == null || items.isEmpty()) {
            return Collections.emptySet();
        }
        Set<T> ret = null;
        for (T each : items) {
            if (func.check(each)) {
                if (ret == null) {
                    ret = new HashSet<>();
                }
                ret.add(each);
            }
        }
        return ret == null ? Collections.<T>emptySet() : ret;

    }

    /**
     * {@code null}-tűrő összehasonlítás (ignore case).
     * @param s1 egyik string.
     * @param s2 másik string.
     * @return true, ha egyeznek.
     */
    public static boolean isEqualsIgnoreCase(String s1,String s2){
        if(s1==null || s2==null){
            return false;
        }
        return s1.trim().equalsIgnoreCase(s2.trim());
    }


    /**
     * Nem példányosítható.
     */
    private Helper() {
        throw new AssertionError();
    }
}
