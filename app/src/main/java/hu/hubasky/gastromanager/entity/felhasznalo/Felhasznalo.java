package hu.hubasky.gastromanager.entity.felhasznalo;

/**
 * Egy felhasználót reprezentáló osztály.
 * Created by mirso on 2017. 04. 26..
 */

public class Felhasznalo {
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
     * @param usernev a név.
     */
    private void setUsernev(String usernev) {
        if (usernev == null) throw new AssertionError();
        usernev = usernev.trim();

        if (usernev.length() < MIN_USERNEV_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("A felhasználói név nem lehet rövidebb, mint %d karakter!", MIN_USERNEV_LENGTH));
        }

        this.usernev = usernev.toUpperCase();
    }

    /**
     * A felhasználó jelszava.
     * @return a jelszó.
     */
    public String getJelszo() {
        return jelszo;
    }

    /**
     * Beállítja a jelszót.
     * @param jelszo a jelszó.
     */
    public void setJelszo(String jelszo) {
        if (jelszo == null) throw new AssertionError();
        jelszo = jelszo.trim();

        if (jelszo.length() < MIN_JELSZO_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("A jelszó nem lehet rövidebb, mint %d karakter!", MIN_JELSZO_LENGTH));
        }


        this.jelszo = jelszo;
    }

    /**
     * A felhasználó természetes személy neve.
     * @return a név.
     */
    public String getNev() {
        return nev;
    }

    /**
     * Beállítja a nevet.
     * @param nev a név.
     */
    public void setNev(String nev) {
        if (nev == null) throw new AssertionError();
        nev = nev.trim();

        if(nev.isEmpty()){
            throw new IllegalArgumentException("A név nem lehet üres!");
        }

        this.nev = nev;
    }

    /**
     * Megfelelő-e a jelszó.
     * @param probaJelszo a próbált jelszó.
     * @return true, ha igen.
     */
    public boolean jelszoEllenorzes(String probaJelszo){
        return jelszo.equals(probaJelszo==null?"":probaJelszo);
    }
}
