package hu.hubasky.gastromanager.entity.felhasznalo;

import hu.hubasky.gastromanager.common.Helper;

/**
 * Egy felhasználót reprezentáló osztály.
 * Created by mirso on 2017. 04. 26..
 */

public final class Felhasznalo {
    /**
     * A felhasználói név min hossza.
     */
    private static final int MIN_USERNEV_LENGTH = 6;
    /**
     * A jelszó  min hossza.
     */
    private static final int MIN_JELSZO_LENGTH = 5;
    /**
     * A felhasználói (bejelentkező) név.
     */
    private String usernev;
    /**
     * A felhasználó jelszava.
     */
    private String jelszo;
    /**
     * A felhasználó természetes személy neve.
     */
    private String nev;

    /**
     * Felhasználó létrehozása.
     *
     * @param usernev a felhasz
     * @param jelszo
     * @param nev
     */
    public Felhasznalo(String usernev, String jelszo, String nev) {
        this.setUsernev(usernev);
        this.setJelszo(jelszo);
        this.setNev(nev);
    }


    /**
     * A felhasználói (bejelentkező) név.
     *
     * @return a bejelentkezési név.
     */
    public String getUsernev() {
        return usernev;
    }

    /**
     * Beállítja a felhasználói nevet.
     *
     * @param usernev a név.
     */
    private void setUsernev(String usernev) {
        if (usernev == null) throw new IllegalArgumentException();
        usernev = usernev.trim();

        if (usernev.length() < MIN_USERNEV_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("A felhasználói név nem lehet rövidebb, mint %d karakter!", MIN_USERNEV_LENGTH));
        }

        this.usernev = usernev.toUpperCase();
    }

    /**
     * A felhasználó jelszava.
     *
     * @return a jelszó.
     */
    public String getJelszo() {
        return jelszo;
    }

    /**
     * Beállítja a jelszót.
     *
     * @param jelszo a jelszó.
     */
    public void setJelszo(String jelszo) {
        if (jelszo == null) throw new IllegalArgumentException();
        jelszo = jelszo.trim();

        if (jelszo.length() < MIN_JELSZO_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("A jelszó nem lehet rövidebb, mint %d karakter!", MIN_JELSZO_LENGTH));
        }


        this.jelszo = jelszo;
    }

    /**
     * A felhasználó természetes személy neve.
     *
     * @return a név.
     */
    public String getNev() {
        return nev;
    }

    /**
     * Beállítja a nevet.
     *
     * @param nev a név.
     */
    public void setNev(String nev) {
        if (nev == null) throw new IllegalArgumentException();
        nev = nev.trim();

        if (nev.isEmpty()) {
            throw new IllegalArgumentException("A név nem lehet üres!");
        }

        this.nev = nev;
    }

    /**
     * Visszaadja, hogy a paraméterben átadott felhasználói név ehhez a felhasználóhoz tarozik-e.
     *
     * @param pusernev a vizsgált felhasználói név.
     * @return true, ha igen.
     */
    public boolean isAzonos(String pusernev) {
        if (pusernev == null) {
            return false;
        }
        return Helper.isEqualsIgnoreCase(usernev, pusernev);
    }

    /**
     * Megfelelő-e a jelszó.
     *
     * @param probaJelszo a próbált jelszó.
     * @return true, ha igen.
     */
    public boolean jelszoEllenorzes(String probaJelszo) {
        return jelszo.equals(probaJelszo == null ? "" : probaJelszo);
    }

    /**
     * A névtöredék alapján megfelelő-e a felhasználó.
     *
     * @param nevtoredek a töredék.
     * @return true, ha igen.
     */
    public boolean isMegfelelo(String nevtoredek) {
        nevtoredek = Helper.trim(nevtoredek);
        if (nevtoredek.isEmpty()) {
            return true;
        }
        return Helper.contains(nev, nevtoredek);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Felhasznalo that = (Felhasznalo) o;

        if (!usernev.equalsIgnoreCase(that.usernev)) return false;
        if (jelszo != null ? !jelszo.equals(that.jelszo) : that.jelszo != null) return false;
        return nev != null ? nev.equals(that.nev) : that.nev == null;

    }

    @Override
    public int hashCode() {
        int result = usernev.hashCode();
        result = 31 * result + (jelszo != null ? jelszo.hashCode() : 0);
        result = 31 * result + (nev != null ? nev.hashCode() : 0);
        return result;
    }
}
