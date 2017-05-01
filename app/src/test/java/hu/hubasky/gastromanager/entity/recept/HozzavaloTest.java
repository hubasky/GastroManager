package hu.hubasky.gastromanager.entity.recept;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 01..
 */
public class HozzavaloTest {

    @Test
    public void create() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);

        try {
            new Hozzavalo(Double.NaN, a);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Hozzavalo(Double.POSITIVE_INFINITY, a);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Hozzavalo(1.0, null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        new Hozzavalo(1.0, a);
    }

    @Test
    public void getMennyiseg() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, a);

        assertEquals(1.5, h.getMennyiseg(), 0.0);
    }

    @Test
    public void getAlapanyag() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, a);

        assertSame(a, h.getAlapanyag());
    }

    @Test
    public void addMennyiseg() throws Exception {
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, a);

        try {
            h.addMennyiseg(0);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        try {
            h.addMennyiseg(-1);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        try {
            h.addMennyiseg(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        try {
            h.addMennyiseg(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        h.addMennyiseg(2.4);
        assertEquals(1.5 + 2.4, h.getMennyiseg(), 0.0);

    }

}