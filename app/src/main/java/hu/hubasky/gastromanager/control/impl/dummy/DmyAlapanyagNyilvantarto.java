package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.List;

import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyAlapanyagNyilvantarto implements AlapanyagNyilvantarto{
    @Override
    public void tarolas(Alapanyag alapanyag) throws Exception {

    }

    @Override
    public void torles(Alapanyag alapanyag) throws Exception {

    }

    @Override
    public List<Alapanyag> keres(AlapanyagKeresesiJellemzok jellemzok) throws Exception {
        return null;
    }
}
