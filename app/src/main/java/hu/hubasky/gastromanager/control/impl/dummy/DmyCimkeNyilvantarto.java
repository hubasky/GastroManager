package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyCimkeNyilvantarto implements CimkeNyilvantarto {
    /**
     * Adatok.
     */
    private final List<Cimke> adatok = new ArrayList<>();


    @Override
    public void init(Controls controls) {
        for (Cimke c : Arrays.asList(
                new Cimke(ECimkeTipus.ALAPANYAG, "Állati eredetű", "FF0000"),
                new Cimke(ECimkeTipus.ALAPANYAG, "Növényi eredetű", "088A29"),
                new Cimke(ECimkeTipus.ALAPANYAG, "Tejcukor", "0101DF"),
                new Cimke(ECimkeTipus.RECEPT, "Nagyi receptje", "FF8000"),
                new Cimke(ECimkeTipus.MINDEN, "Nagyon finom!", "#FF00FF"),
                new Cimke(ECimkeTipus.ALAPANYAG, "Magas energia!", "FF0000"))) {
            try {
                tarolas(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Cimke> keres(final ECimkeTipus tipus, final String nevtoredek) throws Exception {
        return Helper.filter(adatok, new Helper.Checker<Cimke>() {
            @Override
            public boolean check(Cimke param) {
                return param.isMegfelelo(tipus, nevtoredek);
            }
        });
    }

    @Override
    public void tarolas(Cimke cimke) throws Exception {
        if (cimke == null) throw new IllegalArgumentException("cimke nem lehet null!");
        if (!adatok.contains(cimke)) {
            if (!cimke.isUnuiqueKey()) {
                cimke.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
            }
            adatok.add(cimke);
        }
    }
}
