package hu.hubasky.gastromanager.control;

import java.util.List;

/**
 * Aszinkron control-eredmény visszajelzésre használható interfész.
 * Created by mirso on 2017. 05. 12..
 */

public interface ControlResultListener<T> {
    /**
     * A kérés sikeres volt feldolgozása.
     * Ez már UI szál.
     *
     * @param resultList az eredménylista.
     */
    void onSuccess(List<T> resultList);

    /**
     * A kérés nem sikerült, kivétel lépett fel.
     * Ez már UI szál.
     *
     * @param ex a kivétel.
     */
    void onFailed(Exception ex);

}
