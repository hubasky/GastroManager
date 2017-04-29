package hu.hubasky.gastromanager.control;

import java.util.List;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;

/**
 * A cimkék nyilvántartója.
 * Created by mirso on 2017. 04. 26..
 */

public interface CimkeNyilvantarto extends ControlBase{
    /**
     * Cimkék keresése.
     * @param tipus a cimke típus.
     * @param nevtoredek a cimkében szereplő névtöredék.
     * @return a találatok.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<Cimke> keres(ECimkeTipus tipus, String nevtoredek) throws Exception;

    /**
     * Cimke tárolása.
     * @param cimke a cimke példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    void tarolas(Cimke cimke) throws Exception;
}
