package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.List;

import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyCimkeNyilvantarto implements CimkeNyilvantarto {
    @Override
    public List<Cimke> keres(ECimkeTipus tipus, String nevtoredek) throws Exception {
        return null;
    }

    @Override
    public void tarolas(Cimke cimke) throws Exception {

    }
}
