package hu.hubasky.gastromanager.entity;

import java.util.Comparator;

/**
 * Egyedi kulcsot implementáló osztály.
 * Elvárás: a kulcs kronológai sorrendet is adjon.
 * Created by mirso on 2017. 05. 07..
 */

public class EgyediKulcs implements Comparable<EgyediKulcs> {
    /**
     * Az egyedi kulcs.
     */
    private String uniqueKey;

    /**
     * Visszaadja az egyedi kulcsot.
     *
     * @return a kulcs.
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     * Beállítja az egyedi kulcsot.
     *
     * @param uniqueKey a kulcs.
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * Van-e egyedi kulcs.
     *
     * @return a kulcs.
     */
    public boolean isUnuiqueKey() {
        return uniqueKey != null;
    }

    @Override
    public int compareTo(EgyediKulcs o) {
        return (uniqueKey == null ? "" : uniqueKey).compareTo(o.uniqueKey == null ? "" : o.uniqueKey);
    }
}
