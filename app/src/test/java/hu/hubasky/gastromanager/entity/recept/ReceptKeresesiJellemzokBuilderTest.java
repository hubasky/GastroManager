package hu.hubasky.gastromanager.entity.recept;

import org.junit.Test;

import java.util.Set;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 03..
 */
public class ReceptKeresesiJellemzokBuilderTest {
    private static final Felhasznalo OKUSER = new Felhasznalo("1234567", "12345", "s y");

    @Test
    public void nevtoredek() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.nevtoredek("alma");
        ReceptKeresesiJellemzok j = b.build(OKUSER);

        assertEquals("ALMA", j.getNevtoredek());
    }

    @Test
    public void etkezes() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.etkezes(EEtkezesek.EBED);
        b.etkezes(EEtkezesek.REGGELI);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.getAjanlottEtkezesek().contains(EEtkezesek.EBED));
        assertTrue(j.getAjanlottEtkezesek().contains(EEtkezesek.REGGELI));
        assertFalse(j.getAjanlottEtkezesek().contains(EEtkezesek.TIZORAI));
        assertFalse(j.getAjanlottEtkezesek().contains(EEtkezesek.UZSONNA));
        assertFalse(j.getAjanlottEtkezesek().contains(EEtkezesek.VACSORA));

    }

    @Test
    public void alapanyag() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        Alapanyag a1 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Alapanyag a2 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "y", 100);
        b.alapanyag(a1);
        b.alapanyag(a2);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.getTartalmazottAlapanyag().contains(a1));
        assertTrue(j.getTartalmazottAlapanyag().contains(a2));
    }

    @Test
    public void kizartAlapanyag() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        Alapanyag a1 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Alapanyag a2 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "y", 100);
        b.kizartAlapanyag(a1);
        b.kizartAlapanyag(a2);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.getKizartAlapanyag().contains(a1));
        assertTrue(j.getKizartAlapanyag().contains(a2));
    }

    @Test
    public void cimke() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        Cimke c1 = new Cimke(ECimkeTipus.ALAPANYAG, "állati", "x");
        Cimke c2 = new Cimke(ECimkeTipus.ALAPANYAG, "novenyi", "x");
        b.cimke(c1);
        b.cimke(c2);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.getTartalmazottCimke().contains(c1));
        assertTrue(j.getTartalmazottCimke().contains(c2));
    }

    @Test
    public void kizartCimke() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        Cimke c1 = new Cimke(ECimkeTipus.ALAPANYAG, "állati", "x");
        Cimke c2 = new Cimke(ECimkeTipus.ALAPANYAG, "novenyi", "x");
        b.kizartCimke(c1);
        b.kizartCimke(c2);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.getKizartCimke().contains(c1));
        assertTrue(j.getKizartCimke().contains(c2));
    }

    @Test
    public void mindenCimketTartalmazzon() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.mindenCimketTartalmazzon(true);
        ReceptKeresesiJellemzok j = b.build(OKUSER);

        assertTrue(j.isMindenCimketTartalmazzon());
    }

    @Test
    public void leirasSzava() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.leirasSzava("alma");
        b.leirasSzava("körte");
        b.leirasSzava("barack");

        ReceptKeresesiJellemzok j = b.build(OKUSER);

        assertTrue(j.getSzavak().contains("ALMA"));
        assertTrue(j.getSzavak().contains("KÖRTE"));
        assertTrue(j.getSzavak().contains("BARACK"));
    }

    @Test
    public void mindenSzotTartalmazzon() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.mindenCimketTartalmazzon(true);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.isMindenCimketTartalmazzon());

    }

    @Test
    public void getAdagSzures() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().min(1).max(10);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertEquals(1, j.getAdagSzures().getMin(), 0.0);
        assertEquals(10, j.getAdagSzures().getMax(), 0.0);

    }

    @Test
    public void darabszam() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();

        b.darabszam(10);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertEquals(10, j.getDarabszam());

    }

    @Test
    public void kezdo() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();

        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        b.kezdo(recept);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertSame(recept, j.getKezdoRecept());

    }

    @Test
    public void kedvenc() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.kedvenc(true);
        b.teljes(false);
        b.felhasznalo(OKUSER);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.isKedvenc());

        // hibák

        try {
            b = new ReceptKeresesiJellemzok.Builder();
            b.kedvenc(true);
            b.teljes(true);

            b.build(OKUSER);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            b = new ReceptKeresesiJellemzok.Builder();
            b.kedvenc(true);

            b.build(OKUSER);
            fail("a teljes-t törölni kell!");
        } catch (IllegalArgumentException ex) {
        }

        try {
            b = new ReceptKeresesiJellemzok.Builder();
            b.kedvenc(true);
            b.build(OKUSER);

            fail("felhasználót nem adtál meg!");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void sajat() throws Exception {
        ReceptKeresesiJellemzok.Builder b = new ReceptKeresesiJellemzok.Builder();
        b.sajat(true);
        b.teljes(false);
        b.felhasznalo(OKUSER);

        ReceptKeresesiJellemzok j = b.build(OKUSER);
        assertTrue(j.isSajat());

        // hibák

        try {
            b = new ReceptKeresesiJellemzok.Builder();
            b.sajat(true);
            b.teljes(true);

            b.build(OKUSER);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            b = new ReceptKeresesiJellemzok.Builder();
            b.sajat(true);

            b.build(OKUSER);
            fail("a teljes-t törölni kell!");
        } catch (IllegalArgumentException ex) {
        }

        try {
            b = new ReceptKeresesiJellemzok.Builder();
            b.sajat(true);
            b.build(OKUSER);

            fail("felhasználót nem adtál meg!");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void teljes() throws Exception {
        boolean exp;
        boolean res;
        ReceptKeresesiJellemzok j;
        ReceptKeresesiJellemzok.Builder b;

        b = new ReceptKeresesiJellemzok.Builder();
        j = b.build(OKUSER);

        //az alapértelmezés
        exp=true;
        res=j.isTeljes();
        assertEquals(exp,res);

        b = new ReceptKeresesiJellemzok.Builder();
        b.teljes(false);
        j = b.build(OKUSER);
        exp=false;
        res=j.isTeljes();
        assertEquals(exp,res);



    }

    @Test
    public void felhasznalo() throws Exception {
        ReceptKeresesiJellemzok j;
        ReceptKeresesiJellemzok.Builder b;

        // nincs felhasználó
        b = new ReceptKeresesiJellemzok.Builder();
        j = b.build(OKUSER);
        assertNull(j.getFelhasznalo());
        assertSame(OKUSER,j.getKeresoFelhasznalo());

        // van felhasználü
        b = new ReceptKeresesiJellemzok.Builder();
        b.felhasznalo(OKUSER);
        j = b.build(OKUSER);
        assertSame(OKUSER,j.getFelhasznalo());
        assertSame(OKUSER,j.getKeresoFelhasznalo());



    }

    @Test
    public void build() throws Exception {
        // már tesztelt.

    }

}