package hu.hubasky.gastromanager.entity.bevlist;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Recept;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 01..
 */
public class VasarlandoAlapanyagTest {
    @Test
    public void getStatus() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        VasarlandoAlapanyag v = new VasarlandoAlapanyag(1.0, a);
        assertEquals(EVasaroltStatus.BESZERZENDO, v.getStatus());
    }

    @Test
    public void setStatus() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        VasarlandoAlapanyag v = new VasarlandoAlapanyag(1.0, a);
        v.setStatus(EVasaroltStatus.MEGVETT);
        assertEquals(EVasaroltStatus.MEGVETT, v.getStatus());

    }

    @Test
    public void getReceptKapcsolat() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        VasarlandoAlapanyag v = new VasarlandoAlapanyag(1.0, a);
        assertNull(v.getReceptKapcsolat());
    }

    @Test
    public void setReceptKapcsolat() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        VasarlandoAlapanyag v = new VasarlandoAlapanyag(1.0, a);
        v.setReceptKapcsolat(null);
        assertNull(v.getReceptKapcsolat());

        Recept r = new Recept(new Felhasznalo("1234567", "12345", "xxx"), EReceptStatus.PUBLIKUS, "x", "x", "x", 1.0);
        v.setReceptKapcsolat(r);
        assertSame(r, v.getReceptKapcsolat());

    }

}