package hu.hubasky.gastromanager.entity.bevlist;

/**
 * Egy vásárlandó alapanyag státuszát írja le.
 * Created by mirso on 2017. 04. 26..
 */

public enum EVasaroltStatus {
    /**
     * Az alapanyagot még meg kell venni.
     */
    BESZERZENDO("Beszerzendő"),
    /**
     * Az alapanyagot már megvettük.
     */
    MEGVETT("Megvett"),
    /**
     * Az alapanyagot mégsem vesszük meg.
     */
    KIHAGYOTT("Kihagyott");
    /**
     * A státusz megnevezése.
     */
    private final String megnevezes;

    /**
     * Konstruktor.
     * @param megnevezes a megnevezés.
     */
    EVasaroltStatus(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    /**
     * A szöveges megnevezés.
     * @return a szöveg.
     */
    public String getMegnevezes() {
        return megnevezes;
    }
}
