package hu.hubasky.gastromanager.entity.felhasznalo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class FelhasznaloTest {

    private static final String OKUSER = "123456";
    private static final String OKPASS = "q12345Q";
    private static final String OKNEV = "abcd abcd";

    @Test
    public void create() throws Exception {

        // null felhasználói név
        try {

            new Felhasznalo(null, OKPASS, OKNEV);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        // rövid felhasználói név
        try {

            new Felhasznalo("12", OKPASS, OKNEV);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        // null jelszó
        try {

            new Felhasznalo(OKUSER, null, OKNEV);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        // rövid jelszó
        try {

            new Felhasznalo(OKUSER, "12", OKNEV);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        // null név
        try {

            new Felhasznalo(OKUSER, OKPASS, null);
            fail();
        } catch (IllegalArgumentException ex) {

        }
        // üres név
        try {

            new Felhasznalo(OKUSER, OKPASS, "");
            fail();
        } catch (IllegalArgumentException ex) {

        }

        // oké
        new Felhasznalo(OKUSER, OKPASS, OKNEV);

    }

    @Test
    public void getUsernev() throws Exception {
        Felhasznalo f = new Felhasznalo("aaaaaa", OKPASS, OKNEV);
        String exp = "AAAAAA";
        String res = f.getUsernev();
        assertEquals(exp, res);
    }

    @Test
    public void getJelszo() throws Exception {
        String exp = "abcdef";
        Felhasznalo f = new Felhasznalo(OKUSER, exp, OKNEV);
        String res = f.getJelszo();
        assertEquals(exp, res);
    }

    @Test
    public void setJelszo() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, OKNEV);
        // null jelszó
        try {
            f.setJelszo(null);
        } catch (IllegalArgumentException ex) {

        }
        // rövid jelszó
        try {
            f.setJelszo("1");
        } catch (IllegalArgumentException ex) {

        }
        // oké
        String psw = "abcdefgh";
        f.setJelszo(psw);
        assertEquals(psw, f.getJelszo());
    }

    @Test
    public void getNev() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, OKNEV);
        assertEquals(OKNEV, f.getNev());
    }

    @Test
    public void setNev() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, OKNEV);
        // null név
        try {
            f.setNev(null);
        } catch (IllegalArgumentException ex) {
        }
        // üres név
        try {
            f.setNev("");
        } catch (IllegalArgumentException ex) {
        }

        String nev = "alalal alala";
        f.setNev(nev);
        assertEquals(nev, f.getNev());

    }

    @Test
    public void isAzonos() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, OKNEV);

        // null
        assertFalse(f.isAzonos(null));

        assertTrue(f.isAzonos(OKUSER.toLowerCase()));
        assertTrue(f.isAzonos(OKUSER.toUpperCase()));
        assertTrue(f.isAzonos(OKUSER));

        assertFalse(f.isAzonos(OKNEV));
    }

    @Test
    public void jelszoEllenorzes() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, OKNEV);

        //null
        assertFalse(f.jelszoEllenorzes(null));
        assertTrue(f.jelszoEllenorzes(OKPASS));
        assertFalse(f.jelszoEllenorzes(OKNEV));
        assertFalse(f.jelszoEllenorzes(OKPASS.toLowerCase()));
        assertFalse(f.jelszoEllenorzes(OKPASS.toUpperCase()));
    }

    @Test
    public void isMegfelelo() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, "Nagy Pista");

        //null
        assertTrue(f.isMegfelelo(null));
        // üres
        assertTrue(f.isMegfelelo(""));


        assertTrue(f.isMegfelelo("nagy"));
        assertTrue(f.isMegfelelo("Ist"));
        assertFalse(f.isMegfelelo("x"));

    }

    @Test
    public void equals() throws Exception {
        Felhasznalo f = new Felhasznalo(OKUSER, OKPASS, OKNEV);
        Felhasznalo f1;
        f1 = new Felhasznalo(OKUSER, OKPASS, OKNEV);

        //null
        assertFalse(f.equals(null));

        //oké
        assertEquals(f, f1);
        f1 = new Felhasznalo(OKUSER.toLowerCase(), OKPASS, OKNEV);
        assertEquals(f, f1);
        f1 = new Felhasznalo(OKUSER.toLowerCase(), OKPASS, OKNEV);
        assertEquals(f, f1);

        //eltérnek
        f1 = new Felhasznalo(OKUSER + "x", OKPASS, OKNEV);
        assertNotEquals(f, f1);
        f1 = new Felhasznalo(OKUSER, OKPASS + "x", OKNEV);
        assertNotEquals(f, f1);
        f1 = new Felhasznalo(OKUSER, OKPASS, OKNEV + "x");
        assertNotEquals(f, f1);

    }

}