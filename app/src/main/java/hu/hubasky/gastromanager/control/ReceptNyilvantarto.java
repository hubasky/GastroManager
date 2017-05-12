package hu.hubasky.gastromanager.control;

import java.util.List;

import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

/**
 * A recept nyilvántartás alapszerkezete.
 * Created by mirso on 2017. 04. 26..
 */

public interface ReceptNyilvantarto extends ControlBase {
    /**
     * Recept keresése.
     *
     * @param jellemzok a keresés jellemzői.
     * @return a találatok.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<Recept> keres(ReceptKeresesiJellemzok jellemzok) throws Exception;

    /**
     * Recept keresése.
     * Aszinkron verzió.
     *
     * @param jellemzok a keresés jellemzői.
     * @param callback  ide hív vissza az eredménnyel.
     * @return a találatok.
     */
    void keres(ReceptKeresesiJellemzok jellemzok, ControlResultListener<Recept> callback);

    /**
     * Kalkulációt készít a vásárlandó alapanyagokról a mennyiség függvényében.
     *
     * @param recept a recept.
     * @param adag   a kívánt adag.
     * @return a vásárlandók listája.
     * @throws Exception Ha kivétel lépett fel.
     */
    List<VasarlandoAlapanyag> vasarlandoKalkulacio(Recept recept, double adag) throws Exception;

    /**
     * Kalkulációt készít a vásárlandó alapanyagokról a mennyiség függvényében.
     *
     * @param recept   a recept.
     * @param adag     a kívánt adag.
     * @param callback ide hív vissza az eredménnyel.
     */
    void vasarlandoKalkulacio(Recept recept, double adag, ControlResultListener<VasarlandoAlapanyag> callback);

    /**
     * Recept tárolása.
     *
     * @param recept a példány.
     * @throws Exception Ha kivétel lépett fel.
     */
    void tarolas(Recept recept) throws Exception;

    /**
     * Recept törlése.
     *
     * @param recept a recept.
     * @throws Exception Ha kivétel lépett fel.
     */
    void torles(Recept recept) throws Exception;
}
