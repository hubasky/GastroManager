package hu.hubasky.gastromanager.entity.alapanyag;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 30..
 */
public class KalkulaltOsszetevokTest {
    @Test
    public void kalkulal() throws Exception {
        Alapanyag a1;
        EMennyisegiEgyseg mennyisegiEgyseg;
        AlapanyagJellemzok jellemzok;
        String neve;
        double egysegeGramm;

        mennyisegiEgyseg = EMennyisegiEgyseg.DARAB;
        jellemzok = new AlapanyagJellemzok(100, .1, .2, .3, 500);
        neve = "abc";
        egysegeGramm = 100;
        a1 = new Alapanyag(mennyisegiEgyseg, jellemzok, neve, egysegeGramm);

        KalkulaltOsszetevok kalkulal = KalkulaltOsszetevok.kalkulal(a1, 200);
        assertEquals(200, kalkulal.getVizsgaltMennyisegGramm(), 0.0);
        assertEquals(2 * 500, kalkulal.getEnergiaKJ(), 0.0);
        assertEquals(2 * 0.1 * 100, kalkulal.getFeherjeGramm(), 0.0);
        assertEquals(2 * 0.2 * 100, kalkulal.getZsirGramm(), 0.0);
        assertEquals(2 * 0.3 * 100, kalkulal.getSzenhidratGramm(), 0.0);

    }

    @Test
    public void getVizsgaltMennyisegGramm() throws Exception {
        // már tesztelt.
    }

    @Test
    public void getFeherjeGramm() throws Exception {
        // már tesztelt.

    }

    @Test
    public void getZsirGramm() throws Exception {
        // már tesztelt.

    }

    @Test
    public void getSzenhidratGramm() throws Exception {
        // már tesztelt.

    }

    @Test
    public void getEnergiaKJ() throws Exception {
        // már tesztelt.

    }

}