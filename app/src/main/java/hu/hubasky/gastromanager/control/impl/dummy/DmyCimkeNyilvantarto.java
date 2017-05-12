package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.AsyncControlBase;
import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyCimkeNyilvantarto extends AsyncControlBase implements CimkeNyilvantarto {
    /**
     * Adatok.
     */
    private final List<Cimke> adatok = new ArrayList<>();


    @Override
    public boolean init(Controls controls) {
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
                throw new RuntimeException(e);
            }
        }
        return true;
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
    public void keres(final ECimkeTipus tipus, final String nevtoredek, final ControlResultListener<Cimke> callback) {
        if (callback == null) throw new IllegalArgumentException("A callback nem lehet null!");

        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Cimke> keres = keres(tipus, nevtoredek);
                    // itt hívunk vissza az eredménnyel, de már a UI szálon
                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(keres);
                        }
                    });
                } catch (final Exception e) {
                    // itt hívunk vissza a hibával, de már a UI szálon
                    callbackUI(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailed(e);
                        }
                    });
                }
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
