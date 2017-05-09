package hu.hubasky.gastromanager.entity.recept;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 01..
 */
public class ReceptTest {
    private static final Felhasznalo OKUSER = new Felhasznalo("1234567", "12345", "x y");
    private static final Felhasznalo OKUSER1 = new Felhasznalo("66554433", "66433", "xy y");

    @Test
    public void create() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        // jó
        new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        // hibák
        try {
            new Recept(null, status, neve, leirasa, fenykepeURL, adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, null, leirasa, fenykepeURL, adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, "", leirasa, fenykepeURL, adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, neve, null, fenykepeURL, adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, neve, "", fenykepeURL, adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, neve, leirasa, null, adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, neve, leirasa, "", adag);
            fail();
        } catch (IllegalArgumentException ex) {
        }


        try {
            new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {
        }


        try {
            new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, 0);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, -1);
            fail();
        } catch (IllegalArgumentException ex) {
        }

    }

    @Test
    public void getNeve() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(neve, recept.getNeve());
    }

    @Test
    public void setNeve() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(neve, recept.getNeve());
        recept.setNeve(neve = "alMa  ");
        assertEquals(neve.trim(), recept.getNeve());

        try {
            recept.setNeve(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            recept.setNeve("");
            fail();
        } catch (IllegalArgumentException ex) {
        }

    }

    @Test
    public void getLeirasa() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(recept.getLeirasa(), leirasa);

    }

    @Test
    public void setLeirasa() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(recept.getLeirasa(), leirasa);

        recept.setLeirasa(leirasa = "  fewfkwjjewklfjSKSSSS");
        assertEquals(leirasa.trim(), recept.getLeirasa());
    }

    @Test
    public void getFenykepeURL() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(fenykepeURL, recept.getFenykepeURL());

    }

    @Test
    public void setFenykepeURL() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(fenykepeURL, recept.getFenykepeURL());

        recept.setFenykepeURL(fenykepeURL = "  aa  ");
        assertEquals(fenykepeURL.trim(), recept.getFenykepeURL());

    }

    @Test
    public void getAdag() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(adag, recept.getAdag(), 0.0);
    }

    @Test
    public void setAdag() throws Exception {
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.setAdag(adag = 2.1);
        assertEquals(adag, recept.getAdag(), 0.0);

        // rosszak
        try {
            recept.setAdag(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            recept.setAdag(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            recept.setAdag(0);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        try {
            recept.setAdag(-1);
            fail();
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void addHozzavalo() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        //hibás
        try {
            recept.addHozzavalo(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        assertNotNull(recept.getHozzavalok());
        assertTrue(recept.getHozzavalok().isEmpty());

        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, a);

        recept.addHozzavalo(h);
        hl = recept.getHozzavalok();
        assertTrue(hl.contains(h));
        Hozzavalo h1 = hl.iterator().next();
        assertEquals(1.5, h1.getMennyiseg(), 0.0);

        // ugyanazt az alapanyagot mégegyszer
        h = new Hozzavalo(2.5, a);
        recept.addHozzavalo(h);
        hl = recept.getHozzavalok();
        h1 = hl.iterator().next();
        assertEquals(1.5 + 2.5, h1.getMennyiseg(), 0.0);
    }

    @Test
    public void remHozzavalo() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        //hibás
        try {
            recept.remHozzavalo(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, a);

        Set<Hozzavalo> hozzavalok;
        // benne sem volt
        recept.remHozzavalo(a);
        hozzavalok = recept.getHozzavalok();
        assertTrue(hozzavalok.isEmpty());

        // felvesz
        recept.addHozzavalo(h);
        assertFalse(hozzavalok.isEmpty());

        // rosszat törlünk
        Alapanyag a1 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "xx", 100);

        recept.remHozzavalo(a1);
        assertFalse(hozzavalok.isEmpty());

        // jót törlünk
        recept.remHozzavalo(a);
        assertTrue(hozzavalok.isEmpty());

    }

    @Test
    public void getHozzavalok() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        Set<Hozzavalo> hozzavalok = recept.getHozzavalok();

        try {
            hozzavalok.clear();
            fail();
        } catch (UnsupportedOperationException ex) {
        }

        // a többi tesztelt.
    }

    @Test
    public void masolas() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addEtkezes(EEtkezesek.EBED);
        Cimke c;
        recept.addCimke(c = new Cimke(ECimkeTipus.RECEPT, "xx", "ff"));
        Alapanyag a = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, a);
        recept.addHozzavalo(h);

        Recept recept1 = recept.masolas();

        assertSame(recept.getTulajdonos(), recept1.getTulajdonos());
        assertEquals(recept.getStatus(), recept1.getStatus());
        assertEquals(recept.getNeve(), recept1.getNeve());
        assertEquals(recept.getLeirasa(), recept1.getLeirasa());
        assertEquals(recept.getFenykepeURL(), recept1.getFenykepeURL());
        assertEquals(recept.getAdag(), recept1.getAdag(), 0.0);
        assertTrue(recept1.getAjanlottEtkezesek().contains(EEtkezesek.EBED));
        assertTrue(recept1.getCimkek().contains(c));
        assertTrue(recept1.getHozzavalok().iterator().next().getAlapanyag().equals(a));
    }

    @Test
    public void addEtkezes() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addEtkezes(EEtkezesek.EBED);
        ajanlottEtkezesek = recept.getAjanlottEtkezesek();
        assertTrue(ajanlottEtkezesek.contains(EEtkezesek.EBED));
        recept.addEtkezes(EEtkezesek.REGGELI);
        ajanlottEtkezesek = recept.getAjanlottEtkezesek();
        assertTrue(ajanlottEtkezesek.contains(EEtkezesek.EBED));
        assertTrue(ajanlottEtkezesek.contains(EEtkezesek.REGGELI));
    }

    @Test
    public void remEtkezes() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addEtkezes(EEtkezesek.EBED);
        ajanlottEtkezesek = recept.getAjanlottEtkezesek();
        assertTrue(ajanlottEtkezesek.contains(EEtkezesek.EBED));
        recept.remEtkezes(EEtkezesek.EBED);
        ajanlottEtkezesek = recept.getAjanlottEtkezesek();
        assertFalse(ajanlottEtkezesek.contains(EEtkezesek.EBED));
    }

    @Test
    public void getAjanlottEtkezesek() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        try {
            recept.getAjanlottEtkezesek().clear();
            fail();
        } catch (UnsupportedOperationException ex) {
        }
    }

    @Test
    public void privat() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(EReceptStatus.PUBLIKUS, recept.getStatus());
        assertEquals(OKUSER, recept.getTulajdonos());

        recept.privat(OKUSER1);
        assertEquals(EReceptStatus.PRIVAT, recept.getStatus());
        assertEquals(OKUSER1, recept.getTulajdonos());
    }

    @Test
    public void megosztott() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(EReceptStatus.PUBLIKUS, recept.getStatus());
        assertEquals(OKUSER, recept.getTulajdonos());

        recept.megosztott(OKUSER1);
        assertEquals(EReceptStatus.MEGOSZTOTT, recept.getStatus());
        assertEquals(OKUSER1, recept.getTulajdonos());
    }

    @Test
    public void publikus() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;


        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(EReceptStatus.PUBLIKUS, recept.getStatus());
        assertEquals(OKUSER, recept.getTulajdonos());

        recept.megosztott(OKUSER1);
        assertEquals(EReceptStatus.MEGOSZTOTT, recept.getStatus());
        assertEquals(OKUSER1, recept.getTulajdonos());

        recept.publikus();
        assertEquals(EReceptStatus.PUBLIKUS, recept.getStatus());
    }

    @Test
    public void isMegfelelo() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Cimke cimkeRecept = new Cimke(ECimkeTipus.RECEPT, "finom", "ff");
        Cimke cimkeAlapNovenyi = new Cimke(ECimkeTipus.ALAPANYAG, "növényi", "00");

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        // nincs rajta cimke, de nem is szűrünk rá
        exp = true;
        res = recept.isMegfelelo(null, true, null);
        assertEquals(exp, res);

        // nincs rajta cimke, de szűrűnk rá
        exp = false;
        res = recept.isMegfelelo(Arrays.asList(cimkeRecept), true, null);
        assertEquals(exp, res);

        // nincs cimke, de kizárunk
        exp = true;
        res = recept.isMegfelelo(null, true, Arrays.asList(cimkeRecept));
        assertEquals(exp, res);

        Alapanyag a1 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "liszt", 100);
        recept.addHozzavalo(new Hozzavalo(1, a1));

        // van recept cimke és ezt akarjuk
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addCimke(cimkeRecept);
        exp = true;
        res = recept.isMegfelelo(Arrays.asList(cimkeRecept), true, null);
        assertEquals(exp, res);

        // van recept cimke de ezt nem akarjuk
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addCimke(cimkeRecept);
        exp = false;
        res = recept.isMegfelelo(null, true, Arrays.asList(cimkeRecept));
        assertEquals(exp, res);

        // van hozzávalócimke és ezt akarjuk is
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        a1 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "liszt", 100);
        recept.addHozzavalo(new Hozzavalo(1, a1));
        a1.addCimke(cimkeAlapNovenyi);
        exp = true;
        res = recept.isMegfelelo(Arrays.asList(cimkeAlapNovenyi), true, null);
        assertEquals(exp, res);

        // van hozzávalócimke és de ezt nem akarjuk
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        a1 = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "liszt", 100);
        recept.addHozzavalo(new Hozzavalo(1, a1));
        a1.addCimke(cimkeAlapNovenyi);
        exp = false;
        res = recept.isMegfelelo(null, true, Arrays.asList(cimkeAlapNovenyi));
        assertEquals(exp, res);
    }

    @Test
    public void setTulajdonos() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.setTulajdonos(OKUSER1);
        assertSame(OKUSER1, recept.getTulajdonos());
    }

    @Test
    public void getTulajdonos() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertSame(OKUSER, recept.getTulajdonos());
    }

    @Test
    public void getStatus() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(EReceptStatus.PUBLIKUS, recept.getStatus());

    }

    @Test
    public void setStatus() throws Exception {
        Set<EEtkezesek> ajanlottEtkezesek;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        assertEquals(EReceptStatus.PUBLIKUS, recept.getStatus());
        recept.setStatus(EReceptStatus.PRIVAT);
        assertEquals(EReceptStatus.PRIVAT, recept.getStatus());
    }

    @Test
    public void isTartalmazottAlapanyag() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        // hiba
        try {
            recept.isTartalmazottAlapanyag(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }


        recept.addEtkezes(EEtkezesek.EBED);
        Alapanyag alapVan = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "x", 100);
        Hozzavalo h = new Hozzavalo(1.5, alapVan);
        recept.addHozzavalo(h);

        Alapanyag alapNincs = new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100, .1, .1, .1, 100), "xx", 100);

        // benne van
        assertTrue(recept.isTartalmazottAlapanyag(new HashSet<Alapanyag>(Arrays.asList(alapVan))));

        // nincs benne
        assertFalse(recept.isTartalmazottAlapanyag(new HashSet<Alapanyag>(Arrays.asList(alapNincs))));
    }

    @Test
    public void isTartalmazottSzavak() {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "Alma kÖrte BaraCK";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        try {
            recept.isTartalmazottSzavak(null, true);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        Set<String> keresett = new HashSet<>();

        // semmit sem keresünk, az mind megvan
        exp = true;
        res = recept.isTartalmazottSzavak(keresett, false);
        assertEquals(exp, res);

        // semmit sem keresünk, az mind megvan
        exp = true;
        res = recept.isTartalmazottSzavak(keresett, true);
        assertEquals(exp, res);

        // találunk benne (egyet), de nem mindet keressük
        keresett.clear();
        keresett.add("KÖRTE");
        exp = true;
        res = recept.isTartalmazottSzavak(keresett, false);
        assertEquals(exp, res);

        // találunk benne (egyet), mindet keressük
        keresett.clear();
        keresett.add("KÖRTE");
        exp = true;
        res = recept.isTartalmazottSzavak(keresett, true);
        assertEquals(exp, res);

        // találunk benne (többet), de nem mindet keressük
        keresett.clear();
        keresett.add("ALMA");
        keresett.add("BARACK");
        keresett.add("ÉÉÉÉ");
        exp = true;
        res = recept.isTartalmazottSzavak(keresett, false);
        assertEquals(exp, res);

        // találunk benne (többet), de és mindet keresnénk
        keresett.clear();
        keresett.add("ALMA");
        keresett.add("BARACK");
        keresett.add("ÉÉÉÉ");
        exp = false;
        res = recept.isTartalmazottSzavak(keresett, true);
        assertEquals(exp, res);


    }

    @Test
    public void vasarlandoKalkulacio() throws Exception {
        fail("Nem írtam meg!");
    }

}