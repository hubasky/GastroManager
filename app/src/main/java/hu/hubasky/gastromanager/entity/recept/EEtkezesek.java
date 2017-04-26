package hu.hubasky.gastromanager.entity.recept;

/**
 * AZ étkezések
 * Created by mirso on 2017. 04. 26..
 */

public enum EEtkezesek {
    REGGELI("Reggeli"),
    TIZORAI("Tízórai"),
    EBED("Ebéd"),
    UZSONNA("Uzsonna"),
    VACSORA("Vacsora");
    /**
     * A szöveges megnevezés.
     */
    private final String megnevezes;

    /**
     * Konstuktor.
     * @param megnevezes a megnevezés.
     */
    EEtkezesek(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    /**
     * Visszaadja a szöveges megnevezést.
     * @return a megnevezés.
     */
    public String getMegnevezes() {
        return megnevezes;
    }
}
