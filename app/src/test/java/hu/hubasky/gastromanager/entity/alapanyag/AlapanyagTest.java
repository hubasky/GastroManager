package hu.hubasky.gastromanager.entity.alapanyag;

import org.junit.Test;

import java.util.List;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class AlapanyagTest {

    @Test
    public void create() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);

        // null jellemzők
        jellemzok = null;
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // null név
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = null;
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        //üres név
        neve = "";
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        //space név
        neve = " ";
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        neve = "abc";
        // nulla egység
        egysegeGramm = 0;
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // negatív egység
        egysegeGramm = -1;
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // nan egység
        egysegeGramm = Double.NaN;
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // infinite egység
        egysegeGramm = Double.POSITIVE_INFINITY;
        try {
            new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void getMennyisegiEgyseg() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        assertEquals(mennyisegiEgyseg, a.getMennyisegiEgyseg());
    }

    @Test
    public void setMennyisegiEgyseg() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        a.setMennyisegiEgyseg(mennyisegiEgyseg = EMennyisegiEgyseg.LITER);

        assertEquals(mennyisegiEgyseg, a.getMennyisegiEgyseg());

    }

    @Test
    public void getNeve() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        assertEquals(neve, a.getNeve());

    }

    @Test
    public void setNeve() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        a.setNeve(neve = "cica");
        assertEquals(neve, a.getNeve());

        // null neve
        try {
            a.setNeve(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // üres neve
        try {
            a.setNeve("");
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // space neve
        try {
            a.setNeve("  ");
            fail();
        } catch (IllegalArgumentException ex) {
        }

    }

    @Test
    public void getEgysegeGramm() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        assertEquals(egysegeGramm, a.getEgysegeGramm(), 0.0);

    }

    @Test
    public void setEgysegeGramm() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        a.setEgysegeGramm(egysegeGramm = 200);
        assertEquals(egysegeGramm, a.getEgysegeGramm(), 0.0);

        // nulla egység
        try {
            a.setEgysegeGramm(0);
        } catch (IllegalArgumentException ex) {
        }

        // negatív egység
        try {
            a.setEgysegeGramm(-1);
        } catch (IllegalArgumentException ex) {
        }

        // nan egység
        try {
            a.setEgysegeGramm(Double.NaN);
        } catch (IllegalArgumentException ex) {
        }

        // inf. egység
        try {
            a.setEgysegeGramm(Double.POSITIVE_INFINITY);
        } catch (IllegalArgumentException ex) {
        }


    }

    @Test
    public void addVasarolhatoMennyiseg() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);


        // nan
        try {
            a.addVasarolhatoMennyiseg(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // inf
        try {
            a.addVasarolhatoMennyiseg(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // nulla
        try {
            a.addVasarolhatoMennyiseg(0);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // negatív
        try {
            a.addVasarolhatoMennyiseg(-1);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // oké
        a.addVasarolhatoMennyiseg(1);
        assertEquals(1, a.getVasarolhatoMennyisegek().size());
        assertTrue(a.getVasarolhatoMennyisegek().contains(1.0));

        // duplán
        a.addVasarolhatoMennyiseg(1);
        assertEquals(1, a.getVasarolhatoMennyisegek().size());
        assertTrue(a.getVasarolhatoMennyisegek().contains(1.0));

        // más
        a.addVasarolhatoMennyiseg(3);
        assertEquals(2, a.getVasarolhatoMennyisegek().size());
        assertTrue(a.getVasarolhatoMennyisegek().contains(1.0));
        assertTrue(a.getVasarolhatoMennyisegek().contains(3.0));
    }

    @Test
    public void remVasarolhatoMennyiseg() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        a.addVasarolhatoMennyiseg(1);

        //nem töröl
        a.addVasarolhatoMennyiseg(1);
        a.remVasarolhatoMennyiseg(0);
        assertEquals(1, a.getVasarolhatoMennyisegek().size());
        assertTrue(a.getVasarolhatoMennyisegek().contains(1.0));

        // töröl
        a.remVasarolhatoMennyiseg(1);
        assertEquals(0, a.getVasarolhatoMennyisegek().size());
        assertFalse(a.getVasarolhatoMennyisegek().contains(1.0));

    }

    @Test
    public void getJellemzok() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        assertSame(jellemzok, a.getJellemzok());

    }

    @Test
    public void getVasarolhatoMennyisegek() throws Exception {
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;
        List<Double> vasarolhatoMennyisegek;

        // jó
        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        Alapanyag a = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        vasarolhatoMennyisegek = a.getVasarolhatoMennyisegek();
        assertNotNull(vasarolhatoMennyisegek);
        assertTrue(vasarolhatoMennyisegek.isEmpty());

        vasarolhatoMennyisegek = a.getVasarolhatoMennyisegek();
        a.addVasarolhatoMennyiseg(1);
        a.addVasarolhatoMennyiseg(2);
        assertEquals(2, vasarolhatoMennyisegek.size());
        assertTrue(vasarolhatoMennyisegek.contains(1.0));
        assertTrue(vasarolhatoMennyisegek.contains(2.0));
        assertFalse(vasarolhatoMennyisegek.contains(3.0));

        // nem módosítható
        try {
            vasarolhatoMennyisegek.add(1.0);
            fail();
        } catch (UnsupportedOperationException ex) {
        }


    }

    @Test
    public void equals() throws Exception {
        Alapanyag a1;
        Alapanyag a2;
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;
        boolean exp;
        boolean res;

        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .1, .1, 111);
        neve = "abc";
        egysegeGramm = 140;
        a1 = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);

        //null
        exp = false;
        res = a1.equals(null);
        assertEquals(exp, res);

        // azonos
        a2 = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);
        exp = true;
        res = a1.equals(a2);
        assertEquals(exp, res);

        // ha a mennyiségi egység más, még ugyanaz
        a2 = new Alapanyag(EMennyisegiEgyseg.LITER, jellemzok, neve, egysegeGramm);
        exp = true;
        res = a1.equals(a2);
        assertEquals(exp, res);

        // ha a egységegramm egység más, még ugyanaz
        a2 = new Alapanyag(EMennyisegiEgyseg.LITER, jellemzok, neve, egysegeGramm * 2);
        exp = true;
        res = a1.equals(a2);
        assertEquals(exp, res);

        // ha a neve más, akkor más
        a2 = new Alapanyag(EMennyisegiEgyseg.LITER, jellemzok, neve+"s", egysegeGramm );
        exp = false;
        res = a1.equals(a2);
        assertEquals(exp, res);

        // ha a jellemző más, akkor más
        jellemzok = new AlapanyagJellemzok(100, .2, .1, .1, 111);
        a2 = new Alapanyag(EMennyisegiEgyseg.LITER, jellemzok, neve, egysegeGramm );
        exp = false;
        res = a1.equals(a2);
        assertEquals(exp, res);


    }

}