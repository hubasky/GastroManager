package hu.hubasky.gastromanager.entity.alapanyag;

import org.junit.Test;
import org.w3c.dom.Document;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class AlapanyagJellemzokTest {
    private static final double OKVAL = 0.1;

    @Test
    public void create() {
        AlapanyagJellemzok aj;
        double vonatkoztatasGramm;
        double feherjeSzazalek;
        double zsirSzazalek;
        double szenhidratSzazalek;
        double energiaKJ;

        // vonatkozatási / hiba
        vonatkoztatasGramm = Double.NaN;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // vonatkozatási / hiba
        vonatkoztatasGramm = Double.POSITIVE_INFINITY;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // vonatkozatási / hiba
        vonatkoztatasGramm = -1;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // vonatkozatási / hiba
        vonatkoztatasGramm = 0;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }


        // fehérje / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = Double.NaN;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // fehérje / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = Double.POSITIVE_INFINITY;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // fehérje / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = -0.1;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // fehérje / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = 1.1;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // zsír / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = Double.NaN;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // zsír / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = Double.POSITIVE_INFINITY;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // zsír / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = -0.1;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // zsír / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = 1.1;
        szenhidratSzazalek = OKVAL;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }


        // szénhidrát / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = Double.NaN;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // szénhidrát / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = Double.POSITIVE_INFINITY;
        ;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // szénhidrát / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = -0.1;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // szénhidrát / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = 1.1;
        energiaKJ = OKVAL;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // energia / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = Double.NaN;


        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // energia / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = Double.POSITIVE_INFINITY;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // energia / hiba
        vonatkoztatasGramm = OKVAL;
        feherjeSzazalek = OKVAL;
        zsirSzazalek = OKVAL;
        szenhidratSzazalek = OKVAL;
        energiaKJ = -1;

        try {
            new AlapanyagJellemzok(vonatkoztatasGramm, feherjeSzazalek, zsirSzazalek, szenhidratSzazalek, energiaKJ);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        // több, mint 100%
        try {
            new AlapanyagJellemzok(100, 0.4, 0.4, 0.4, 100);
            fail();
        } catch (IllegalStateException ex) {
        }

        // Jó

        aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        assertEquals(0.1, aj.getVonatkoztatasGramm(), 0.0);
        assertEquals(0.2, aj.getFeherjeSzazalek(), 0.0);
        assertEquals(0.3, aj.getZsirSzazalek(), 0.0);
        assertEquals(0.4, aj.getSzenhidratSzazalek(), 0.0);
        assertEquals(0.5, aj.getEnergiaKJ(), 0.0);


    }

    @Test
    public void getVonatkoztatasGramm() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        assertEquals(0.1, aj.getVonatkoztatasGramm(), 0.0);

    }

    @Test
    public void setVonatkoztatasGramm() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);

        try {
            aj.setVonatkoztatasGramm(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setVonatkoztatasGramm(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setVonatkoztatasGramm(Double.NEGATIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setVonatkoztatasGramm(0);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setVonatkoztatasGramm(-1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        //ok
        aj.setVonatkoztatasGramm(100);
        assertEquals(100, aj.getVonatkoztatasGramm(), 0.0);
    }

    @Test
    public void getFeherjeSzazalek() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        assertEquals(0.2, aj.getFeherjeSzazalek(), 0.0);

    }

    @Test
    public void setFeherjeSzazalek() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);

        try {
            aj.setFeherjeSzazalek(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setFeherjeSzazalek(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setFeherjeSzazalek(Double.NEGATIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setFeherjeSzazalek(1.1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setFeherjeSzazalek(-1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        //ok
        aj.setFeherjeSzazalek(0.11);
        assertEquals(0.11, aj.getFeherjeSzazalek(), 0.0);

    }

    @Test
    public void getZsirSzazalek() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        assertEquals(0.3, aj.getZsirSzazalek(), 0.0);

    }

    @Test
    public void setZsirSzazalek() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);

        try {
            aj.setZsirSzazalek(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setZsirSzazalek(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setZsirSzazalek(Double.NEGATIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setZsirSzazalek(1.1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setZsirSzazalek(-1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        //ok
        aj.setZsirSzazalek(0.11);
        assertEquals(0.11, aj.getZsirSzazalek(), 0.0);

    }

    @Test
    public void getSzenhidratSzazalek() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        assertEquals(0.4, aj.getSzenhidratSzazalek(), 0.0);

    }

    @Test
    public void setSzenhidratSzazalek() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);

        try {
            aj.setSzenhidratSzazalek(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setSzenhidratSzazalek(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setSzenhidratSzazalek(Double.NEGATIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setSzenhidratSzazalek(1.1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setSzenhidratSzazalek(-1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        //ok
        aj.setSzenhidratSzazalek(0.11);
        assertEquals(0.11, aj.getSzenhidratSzazalek(), 0.0);

    }

    @Test
    public void getEnergiaKJ() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        assertEquals(0.5, aj.getEnergiaKJ(), 0.0);

    }

    @Test
    public void setEnergiaKJ() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);

        try {
            aj.setEnergiaKJ(Double.NaN);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setEnergiaKJ(Double.POSITIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        try {
            aj.setEnergiaKJ(Double.NEGATIVE_INFINITY);
            fail();
        } catch (IllegalArgumentException ex) {

        }


        try {
            aj.setEnergiaKJ(-1);
            fail();
        } catch (IllegalArgumentException ex) {

        }

        //ok
        aj.setEnergiaKJ(0.11);
        assertEquals(0.11, aj.getEnergiaKJ(), 0.0);

    }

    @Test
    public void getFeherjeMennyisegGramm() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(200, .1, .1, .1, 100);
        assertEquals(200 * .1, aj.getFeherjeMennyisegGramm(), 0.0);

    }

    @Test
    public void getSzenhidratMennyisegGramm() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(200, .1, .1, .1, 100);
        assertEquals(200 * .1, aj.getSzenhidratMennyisegGramm(), 0.0);
    }

    @Test
    public void getZsirMennyisegGramm() throws Exception {
        AlapanyagJellemzok aj = new AlapanyagJellemzok(200, .1, .1, .1, 100);
        assertEquals(200 * .1, aj.getZsirMennyisegGramm(), 0.0);

    }

    @Test
    public void equals() throws Exception {
        AlapanyagJellemzok aj1 = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);
        AlapanyagJellemzok aj2 = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.5);

        // null
        assertEquals(false,aj1.equals(null));

        // egyezik
        assertEquals(true,aj1.equals(aj2));
        assertEquals(true,aj2.equals(aj2));

        // eltér
        aj2 = new AlapanyagJellemzok(0.11, 0.2, 0.3, 0.4, 0.5);
        assertEquals(true,aj2.equals(aj2));

        // eltér
        aj2 = new AlapanyagJellemzok(0.1, 0.21, 0.3, 0.4, 0.5);
        assertEquals(true,aj2.equals(aj2));

        // eltér
        aj2 = new AlapanyagJellemzok(0.1, 0.2, 0.31, 0.4, 0.5);
        assertEquals(true,aj2.equals(aj2));

        // eltér
        aj2 = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.41, 0.5);
        assertEquals(true,aj2.equals(aj2));

        // eltér
        aj2 = new AlapanyagJellemzok(0.1, 0.2, 0.3, 0.4, 0.51);
        assertEquals(true,aj2.equals(aj2));

    }

}