package hu.hubasky.gastromanager.entity;

/**
 * A mennyiségi egységek fajtái.
 * Created by mirso on 2017. 04. 26..
 */

public enum EMennyisegiEgyseg {
    /**
     * Grammban.
     */
    GRAMM("gramm"),
    /**
     * Literben.
     */
    LITER("liter"),
    /**
     * Darabban.
     */
    DARAB("darab");

    /**
     * A szöveges megnevezés.
     */
    private final String megnevezes;

    /**
     * Konstruktor.
     * @param megnevezes a szöveges megnevezés.
     */
    EMennyisegiEgyseg(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    /**
     * Visszaadja az egység szöveges megnevezését.
     * @return a név.
     */
    public String getMegnevezes() {
        return megnevezes;
    }
}
