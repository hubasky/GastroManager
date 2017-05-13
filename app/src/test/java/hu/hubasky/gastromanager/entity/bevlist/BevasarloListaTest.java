package hu.hubasky.gastromanager.entity.bevlist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 01..
 */
public class BevasarloListaTest {

    private static final Felhasznalo OKUSER = new Felhasznalo("1234567", "123456", "y");

    @Test
    public void create() throws Exception {
        try {
            new BevasarloLista(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        new BevasarloLista(OKUSER);
    }

    @Test
    public void addVasarlandok() throws Exception {
        BevasarloLista b = new BevasarloLista(OKUSER);
        Set<VasarlandoAlapanyag> vasarlandok;

        try {
            b.addVasarlandok(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Alapanyag a1 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Liszt", 100);
        VasarlandoAlapanyag v1 = new VasarlandoAlapanyag(1.0, a1);

        Alapanyag a2 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor", 100);
        VasarlandoAlapanyag v2 = new VasarlandoAlapanyag(1.0, a2);

        b.addVasarlandok(Arrays.asList(v1, v2));
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // újabb mennyiség ugyanabból 2.0 lesz a mennyiség
        b.addVasarlandok(Arrays.asList(v1));
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // kikeressük a lisztet
        Set<VasarlandoAlapanyag> fnd = Helper.filter(vasarlandok, new Helper.Checker<VasarlandoAlapanyag>() {
            @Override
            public boolean check(VasarlandoAlapanyag param) {
                return param.getAlapanyag().getNeve().equalsIgnoreCase("Liszt");
            }
        });
        assertTrue(!fnd.isEmpty());
        VasarlandoAlapanyag fndAlapanyagLiszt = fnd.iterator().next();
        assertEquals(1.0 + 1.0, fndAlapanyagLiszt.getMennyiseg(), 0.0);
    }

    @Test
    public void addVasarlando() throws Exception {
        BevasarloLista b = new BevasarloLista(OKUSER);
        Set<VasarlandoAlapanyag> vasarlandok;

        try {
            b.addVasarlando(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Alapanyag a1 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Liszt", 100);
        VasarlandoAlapanyag v1 = new VasarlandoAlapanyag(1.0, a1);

        Alapanyag a2 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor", 100);
        VasarlandoAlapanyag v2 = new VasarlandoAlapanyag(1.0, a2);

        b.addVasarlando(v1);
        b.addVasarlando(v2);

        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // újabb mennyiség ugyanabból 2.0 lesz a mennyiség
        b.addVasarlando(v1);
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // kikeressük a lisztet
        Set<VasarlandoAlapanyag> fnd = Helper.filter(vasarlandok, new Helper.Checker<VasarlandoAlapanyag>() {
            @Override
            public boolean check(VasarlandoAlapanyag param) {
                return param.getAlapanyag().getNeve().equalsIgnoreCase("Liszt");
            }
        });
        assertTrue(!fnd.isEmpty());
        VasarlandoAlapanyag fndAlapanyagLiszt = fnd.iterator().next();
        assertEquals(1.0 + 1.0, fndAlapanyagLiszt.getMennyiseg(), 0.0);
    }

    @Test
    public void getVasarlandok() throws Exception {
        BevasarloLista b = new BevasarloLista(OKUSER);
        Set<VasarlandoAlapanyag> set = b.getVasarlandok();
        assertNotNull(set);
        assertTrue(set.isEmpty());

        try {
            set.clear();
            fail();
        } catch (UnsupportedOperationException ex) {
        }

        // a többi resztelve
    }

    @Test
    public void torol() throws Exception {
        BevasarloLista b = new BevasarloLista(OKUSER);
        Set<VasarlandoAlapanyag> vasarlandok;

        Alapanyag a1 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Liszt", 100);
        VasarlandoAlapanyag v1 = new VasarlandoAlapanyag(1.0, a1);

        Alapanyag a2 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor", 100);
        VasarlandoAlapanyag v2 = new VasarlandoAlapanyag(1.0, a2);

        Alapanyag anincs = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor1", 100);

        b.addVasarlandok(Arrays.asList(v1, v2));
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // olyat törlünk, ami nincs
        b.torol(anincs);
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // ami van
        b.torol(a1);
        vasarlandok = b.getVasarlandok();
        assertFalse(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));
    }

    @Test
    public void status() throws Exception {
        BevasarloLista b = new BevasarloLista(OKUSER);

        try {
            b.status(EVasaroltStatus.MEGVETT, null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Set<VasarlandoAlapanyag> vasarlandok;

        Alapanyag a1 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Liszt", 100);
        VasarlandoAlapanyag v1 = new VasarlandoAlapanyag(1.0, a1);

        Alapanyag a2 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor", 100);
        VasarlandoAlapanyag v2 = new VasarlandoAlapanyag(1.0, a2);

        Alapanyag anincs = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor1", 100);

        b.addVasarlandok(Arrays.asList(v1, v2));
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));

        // alapból beszerzendő
        assertEquals(EVasaroltStatus.BESZERZENDO, v1.getStatus());
        assertEquals(EVasaroltStatus.BESZERZENDO, v2.getStatus());

        // a1 megvett
        b.status(EVasaroltStatus.MEGVETT, a1);
        assertEquals(EVasaroltStatus.MEGVETT, v1.getStatus());
        assertEquals(EVasaroltStatus.BESZERZENDO, v2.getStatus());
    }

    @Test
    public void lezaras() throws Exception {
        BevasarloLista b = new BevasarloLista(OKUSER);

        try {
            b.status(EVasaroltStatus.MEGVETT, null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Set<VasarlandoAlapanyag> vasarlandok;

        Alapanyag a1 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Liszt", 100);
        VasarlandoAlapanyag v1 = new VasarlandoAlapanyag(1.0, a1);

        Alapanyag a2 = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor", 100);
        VasarlandoAlapanyag v2 = new VasarlandoAlapanyag(1.0, a2);

        Alapanyag anincs = new Alapanyag(
                EMennyisegiEgyseg.GRAMM,
                new AlapanyagJellemzok(100, .1, .1, .1, 100), "Cukor1", 100);

        b.addVasarlandok(Arrays.asList(v1, v2));
        vasarlandok = b.getVasarlandok();
        assertTrue(vasarlandok.contains(v1));
        assertTrue(vasarlandok.contains(v2));
        // alapból beszerzendő
        assertEquals(EVasaroltStatus.BESZERZENDO, v1.getStatus());
        assertEquals(EVasaroltStatus.BESZERZENDO, v2.getStatus());

        // a1 megvett
        b.status(EVasaroltStatus.MEGVETT, a1);

        // lezárás
        b.lezaras();

        // minden meg nem vett kihagyott
        assertEquals(EVasaroltStatus.MEGVETT, v1.getStatus());
        assertEquals(EVasaroltStatus.KIHAGYOTT, v2.getStatus());

    }

}