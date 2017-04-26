package hu.hubasky.gastromanager;

/**
 * Építőminta szerkezete
 * Created by mirso on 2017. 04. 26..
 */

public interface IBuilder<T> {
    /**
     * Felépítése.
     * @return a létrehozott példány.
     */
     T build();
}
