package hu.hubasky.gastromanager.control.impl.dummy;

/**
 * Dummy implementációhoz egyedi kulcs kezelője
 * Created by mirso on 2017. 05. 07..
 */

public final class DmyEgyediKulcsKezelo {
    /**
     * A Singleton tároló.
     */
    private static class Holder {
        private static final DmyEgyediKulcsKezelo INSTANCE = new DmyEgyediKulcsKezelo();
    }

    /**
     * Visszaadja a példányt.
     *
     * @return a példány.
     */
    public static DmyEgyediKulcsKezelo getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Számláló.
     */
    private long counter = 100000000;


    /**
     * Nem példányosítható.
     */
    private DmyEgyediKulcsKezelo() {
    }

    /**
     * Visszaadja a következő egyedi kulcsot.
     *
     * @return a kulcs.
     */
    public synchronized String getNext() {
        try {
            return "" + counter;
        } finally {
            counter++;
        }
    }
}
