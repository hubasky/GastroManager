package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.ReceptNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyReceptNyilvantarto implements ReceptNyilvantarto {

    /**
     * Receptek.
     */
    private final List<Recept> receptek = new ArrayList<>();

    @Override
    public void init(Controls controls) {

    }

    /**
     * A keresési jellemzők alapján létrehozza a kezdő halmazt.
     *
     * @param jellemzok a jellemzők.
     * @return a halmaz.
     * @throws Exception Ha kivétel lépett fel.
     */
    private List<Recept> keresesHalmazSzerint(final ReceptKeresesiJellemzok jellemzok) throws Exception {
        Integer darabszam = jellemzok.getDarabszam() == 0 ? null : jellemzok.getDarabszam();

        if (jellemzok.isTeljes()) {
            return Helper.filter(receptek, new Helper.Checker<Recept>() {
                @Override
                public boolean check(Recept param) {
                    // itt kezeli le a publikus/privát jogosultságot.
                    return jellemzok.isMegfelelo(param);
                }
            }, darabszam);
        }

        // saját vagy kedvenc
        List<Recept> ret = new ArrayList<>(32);

        if (jellemzok.isSajat()) {
            // sajátok kellenek.
            ret.addAll(Helper.filter(receptek, new Helper.Checker<Recept>() {
                @Override
                public boolean check(Recept param) {
                    return param.getTulajdonos().isAzonos(jellemzok.getFelhasznalo().getUsernev());
                }
            }));
        }

        if (jellemzok.isKedvenc()) {
            // kedvencek kellenek.
            List<Recept> kedvencek = Controls.getInstance()
                    .getFelhasznaloNyilvantarto()
                    .getKedvencek(jellemzok.getFelhasznalo());
            ret.addAll(kedvencek);
        }


        // a ret-ben lenne a teljes halmaz
        Collections.sort(ret, new Comparator<Recept>() {
            @Override
            public int compare(Recept o1, Recept o2) {
                return o1.compareTo(o2);
            }
        });

        return Helper.filter(ret, new Helper.Checker<Recept>() {
            @Override
            public boolean check(Recept param) {
                return jellemzok.isMegfelelo(param);
            }
        }, darabszam);

    }

    @Override
    public List<Recept> keres(final ReceptKeresesiJellemzok jellemzok) throws Exception {
        if (jellemzok == null) throw new IllegalArgumentException("jellemzok nem lehet null!");
        return keresesHalmazSzerint(jellemzok);
    }

    @Override
    public List<VasarlandoAlapanyag> vasarlandoKalkulacio(Recept recept, double adag) throws Exception {
        if (recept == null) throw new IllegalArgumentException("recept nem lehet null!");
        return recept.vasarlandoKalkulacio(adag);

    }

    @Override
    public synchronized void tarolas(Recept recept) throws Exception {
        if (!recept.isUnuiqueKey()) {
            recept.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
        }

        int pos = Collections.binarySearch(receptek, recept);
        if (pos >= 0) {
            return;
        }
        receptek.add(-pos, recept);

    }

    @Override
    public void torles(Recept recept) throws Exception {
        if (!recept.isUnuiqueKey()) {
            return;
        }
        int pos = Collections.binarySearch(receptek, recept);

        if (pos >= 0) {
            receptek.remove(-pos);
        }
    }
}
