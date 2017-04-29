package hu.hubasky.gastromanager.entity;

import hu.hubasky.gastromanager.common.Helper;

/**
 * Egy keresési cimkét leíró osztály.
 * Created by mirso on 2017. 04. 26..
 */

public final class Cimke {
    /**
     * Mihez használható fel a cimke.
     */
    private final ECimkeTipus tipus;
    /**
     * A cimke szövege.
     */
    private final String szoveg;
    /**
     * A cimke színkódja.
     */
    private final String szinKodja;

    /**
     * Konstruktor.
     *
     * @param tipus     a cimke típusa.
     * @param szoveg    a szövege.
     * @param szinKodja a színkód, megjelenítéshez.
     */
    public Cimke(ECimkeTipus tipus, String szoveg, String szinKodja) {
        if (szoveg == null) throw new IllegalArgumentException();
        if (szinKodja == null) throw new IllegalArgumentException();

        szoveg = szoveg.trim();
        if (szoveg.isEmpty()) {
            throw new IllegalArgumentException("A cimke szövege nem lehet üres!");
        }
//        if (szoveg.contains(" ")) {
//            throw new IllegalArgumentException(String.format("A cimke szövege nem állhat több szóból: %s", szoveg));
//        }

        szinKodja = szinKodja.trim();
        if (szinKodja.isEmpty()) {
            throw new IllegalArgumentException("A színkód nem lehet üres!");
        }

        this.tipus = tipus;
        this.szoveg = szoveg.toUpperCase();
        this.szinKodja = szinKodja;
    }

    /**
     * A cimke szövege.
     *
     * @return a szöveg.
     */
    public String getSzoveg() {
        return szoveg;
    }

    /**
     * A hozzátartozó színkód (hexa).
     *
     * @return a színkód.
     */
    public String getSzinKodja() {
        return szinKodja;
    }

    /**
     * Visszaadja a cimke típusát.
     *
     * @return a típus.
     */
    public ECimkeTipus getTipus() {
        return tipus;
    }

    /**
     * Visszaadja, hogy a cimke típusa alapján megfelel-e a paraméterben átadott
     * vizsgált típusnak.
     *
     * @param vizsgalt a vizsgált.
     * @return true, ha igen.
     */
    public boolean isMegfelelo(ECimkeTipus vizsgalt) {
        if (vizsgalt == ECimkeTipus.MINDEN || tipus == ECimkeTipus.MINDEN) {
            return true;
        }

        return tipus == vizsgalt;

    }

    /**
     * Visszaadja, hogy a cimke megfelel-e a felételeknek.
     *
     * @param vcimke     a vizsgált cimke.
     * @param nevtoredek a névtöredés. Lehet {@code null} is.
     * @return true, ha igen.
     */
    public boolean isMegfelelo(ECimkeTipus vcimke, String nevtoredek) {
        nevtoredek = Helper.trim(nevtoredek);
        if (nevtoredek.isEmpty()) {
            return isMegfelelo(vcimke);
        }

        return Helper.contains(szoveg, nevtoredek) && isMegfelelo(vcimke);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cimke cimke = (Cimke) o;

        if (tipus != cimke.tipus) return false;
        if (!szoveg.equals(cimke.szoveg)) return false;
        return szinKodja.equals(cimke.szinKodja);

    }

    @Override
    public int hashCode() {
        return tipus.hashCode();
    }

}
