package hu.hubasky.gastromanager.entity.alapanyag;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 30..
 */
public class AlapanyagKeresesiJellemzokTest {
    @Test
    public void getNevtoredek() throws Exception {
        // már tesztelt
    }

    @Test
    public void getTartalmazottCimke() throws Exception {
        // már tesztelt
    }

    @Test
    public void isMindetTartalmazza() throws Exception {
        // már tesztelt
    }

    @Test
    public void getKizartCimke() throws Exception {
        // már tesztelt
    }

    @Test
    public void getFeherje100g() throws Exception {
        // már tesztelt
    }

    @Test
    public void getZsir100g() throws Exception {
        // már tesztelt
    }

    @Test
    public void getSzenhidrat100g() throws Exception {
        // már tesztelt
    }

    @Test
    public void getEnergia100g() throws Exception {
        // már tesztelt
    }

    @Test
    public void isMegfelel() throws Exception {
        boolean exp, res;
        AlapanyagKeresesiJellemzok.Builder b;
        AlapanyagKeresesiJellemzok kj;
        double feherjeSzaz = 0.1;
        double zsirSzaz = 0.1;
        double szenhidSzaz = 0.1;
        double energiaKJ = 100;

        double feherjeSzaz1 = 0.2;
        double zsirSzaz1 = 0.2;
        double szenhidSzaz1 = 0.2;
        double energiaKJ1 = 200;


        Cimke allatiAlapanyag = new Cimke(ECimkeTipus.ALAPANYAG, "állati", "x");
        Cimke novenyiAlapanyag = new Cimke(ECimkeTipus.ALAPANYAG, "novenyi", "x");


        AlapanyagJellemzok j1 = new AlapanyagJellemzok(100, feherjeSzaz, zsirSzaz, szenhidSzaz, energiaKJ);
        Alapanyag ajo = new Alapanyag(EMennyisegiEgyseg.GRAMM, j1, "Búzaliszt", 100);
        ajo.addCimke(novenyiAlapanyag);

        AlapanyagJellemzok j2 = new AlapanyagJellemzok(100, feherjeSzaz1, zsirSzaz1, szenhidSzaz1, energiaKJ1);
        Alapanyag arossz = new Alapanyag(EMennyisegiEgyseg.LITER, j2, "Tej", 100);
        arossz.addCimke(allatiAlapanyag);

        // névtöredék - megfelel
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.nevtoredek("bú");
        kj = b.build();
        exp = true;
        res = kj.isMegfelel(ajo);
        assertEquals(exp, res);

        // névtöredék - nem felel meg
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.nevtoredek("bú");
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(arossz);
        assertEquals(exp, res);

        // cimke - tartalmazott kritériummal, de nem felel meg
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.cimke(novenyiAlapanyag);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(arossz);
        assertEquals(exp, res);

        // cimke - tartalmazott kritériummal, és megfelel
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.cimke(novenyiAlapanyag);
        kj = b.build();
        exp = true;
        res = kj.isMegfelel(ajo);
        assertEquals(exp, res);

        // cimke - kizárt kritériummal, de nem felel meg
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.kizartCimke(allatiAlapanyag);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(arossz);
        assertEquals(exp, res);

        // cimke - kizárt kritériummal és megfelel
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.kizartCimke(allatiAlapanyag);
        kj = b.build();
        exp = true;
        res = kj.isMegfelel(ajo);
        assertEquals(exp, res);

        // fehérje tartomány - nem felel meg
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getFeherje100g().min(0.1).max(1.9);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(arossz);
        assertEquals(exp, res);

        // fehérje tartomány - megfelel
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getFeherje100g().min(0.1).max(1.9);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(ajo);
        assertEquals(exp, res);

        // zsír tartomány - nem felel meg
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getZsir100g().min(0.1).max(1.9);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(arossz);
        assertEquals(exp, res);

        // zsír tartomány - megfelel
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getZsir100g().min(0.1).max(1.9);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(ajo);
        assertEquals(exp, res);

        // szénhidrát tartomány - nem felel meg
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getSzenhidrat100g().min(0.1).max(1.9);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(arossz);
        assertEquals(exp, res);

        // szénhidrát tartomány - megfelel
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getSzenhidrat100g().min(0.1).max(1.9);
        kj = b.build();
        exp = false;
        res = kj.isMegfelel(ajo);
        assertEquals(exp, res);




    }

}