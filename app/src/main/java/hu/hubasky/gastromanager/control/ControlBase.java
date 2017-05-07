package hu.hubasky.gastromanager.control;

/**
 * A vezérlőosztályok alapszerkezete.
 * Created by mirso on 2017. 04. 29..
 */

public interface ControlBase {
    /**
     * Inicializálás.
     *
     * @param controls a vezérlők tárolója.
     * @return megtörtént-e az inicializálás.
     */
    boolean init(Controls controls);
}
