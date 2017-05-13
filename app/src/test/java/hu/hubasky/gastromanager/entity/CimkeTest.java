package hu.hubasky.gastromanager.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class CimkeTest {

    @Test
    public void create() throws Exception {
        Cimke cimke;

        try {
            cimke = new Cimke(ECimkeTipus.ALAPANYAG, "szoveg", null);
            fail();
        } catch (Exception ex) {

        }
        try {
            cimke = new Cimke(ECimkeTipus.ALAPANYAG, null, "szin");
            fail();
        } catch (Exception ex) {

        }

        cimke = new Cimke(ECimkeTipus.ALAPANYAG, "szoveg", "szin");
    }

    @Test
    public void getSzoveg() throws Exception {
        Cimke cimke = new Cimke(ECimkeTipus.ALAPANYAG, "szoveg", "szin");
        assertEquals("SZOVEG", cimke.getSzoveg());
    }

    @Test
    public void getSzinKodja() throws Exception {
        Cimke cimke = new Cimke(ECimkeTipus.ALAPANYAG, "szoveg", "FFFFFF");
        assertEquals("FFFFFF", cimke.getSzinKodja());
    }

    @Test
    public void getTipus() throws Exception {
        Cimke cimke = new Cimke(ECimkeTipus.ALAPANYAG, "szoveg", "FFFFFF");
        assertEquals(ECimkeTipus.ALAPANYAG, cimke.getTipus());
    }

    @Test
    public void isMegfelelo() throws Exception {
        Cimke cimke;

        cimke = new Cimke(ECimkeTipus.ALAPANYAG, "szoveg", "FFFFFF");
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.ALAPANYAG));
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.MINDEN));
        assertEquals(false, cimke.isMegfelelo(ECimkeTipus.RECEPT));

        cimke = new Cimke(ECimkeTipus.RECEPT, "szoveg", "FFFFFF");
        assertEquals(false, cimke.isMegfelelo(ECimkeTipus.ALAPANYAG));
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.MINDEN));
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.RECEPT));

        cimke = new Cimke(ECimkeTipus.MINDEN, "szoveg", "FFFFFF");
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.ALAPANYAG));
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.MINDEN));
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.RECEPT));
    }

    @Test
    public void isMegfelelo1() throws Exception {
        Cimke cimke;

        cimke = new Cimke(ECimkeTipus.ALAPANYAG, "állati eredetű", "FFFFFF");
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.ALAPANYAG, "ÁLLATI"));
        assertEquals(true, cimke.isMegfelelo(ECimkeTipus.ALAPANYAG, "EREDET"));
        assertEquals(false, cimke.isMegfelelo(ECimkeTipus.ALAPANYAG, "CICA"));
        assertEquals(false, cimke.isMegfelelo(ECimkeTipus.RECEPT, "EREDET"));
    }

    @Test
    public void equals() throws Exception {
        Cimke c1,c2;
        c1 = new Cimke(ECimkeTipus.ALAPANYAG,"x","y");
        c2 = new Cimke(ECimkeTipus.ALAPANYAG,"x","y");
        assertTrue(c1.equals(c2));

        c2 = new Cimke(ECimkeTipus.MINDEN,"x","y");
        assertFalse(c1.equals(c2));

        c2 = new Cimke(ECimkeTipus.ALAPANYAG,"x1","y");
        assertFalse(c1.equals(c2));

        c2 = new Cimke(ECimkeTipus.ALAPANYAG,"x","y1");
        assertFalse(c1.equals(c2));
    }


}