package hu.hubasky.gastromanager.entity.alapanyag;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 30..
 */
public class AlapanyagKeresesiJellemzokBuilderTest {
    @Test
    public void nevtoredek() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertSame(b, b.nevtoredek("x"));
    }

    @Test
    public void cimke() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertSame(b, b.cimke(new Cimke(ECimkeTipus.ALAPANYAG, "x", "y")));
    }

    @Test
    public void kizartCimke() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertSame(b, b.kizartCimke(new Cimke(ECimkeTipus.ALAPANYAG, "x", "y")));
    }

    @Test
    public void mindetTartalmazza() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertSame(b, b.mindetTartalmazza(true));
    }

    @Test
    public void getFeherje100g() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertNotNull(b.getFeherje100g());
    }

    @Test
    public void getZsir100g() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertNotNull(b.getZsir100g());
    }

    @Test
    public void getSzenhidrat100g() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertNotNull(b.getSzenhidrat100g());
    }

    @Test
    public void getEnergia100g() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b = new AlapanyagKeresesiJellemzok.Builder();
        assertNotNull(b.getEnergia100g());
    }

    @Test
    public void build() throws Exception {
        AlapanyagKeresesiJellemzok.Builder b;
        AlapanyagKeresesiJellemzok kj;
        Cimke c1 = new Cimke(ECimkeTipus.ALAPANYAG, "növényi", "0ff");
        Cimke c2 = new Cimke(ECimkeTipus.ALAPANYAG, "finom", "0ff");
        Cimke c3 = new Cimke(ECimkeTipus.ALAPANYAG, "állati", "0ff");


        //üres
        b = new AlapanyagKeresesiJellemzok.Builder();
        kj = b.build();
        assertNull(kj.getNevtoredek());
        assertNull(kj.getTartalmazottCimke());
        assertFalse(kj.isMindetTartalmazza());
        assertNull(kj.getKizartCimke());
        assertNotNull(kj.getFeherje100g());
        assertNull(kj.getFeherje100g().getMin());
        assertNull(kj.getFeherje100g().getMax());
        assertNull(kj.getZsir100g().getMin());
        assertNull(kj.getZsir100g().getMax());
        assertNull(kj.getSzenhidrat100g().getMin());
        assertNull(kj.getSzenhidrat100g().getMax());
        assertNull(kj.getEnergia100g().getMin());
        assertNull(kj.getEnergia100g().getMax());

        // névtöredék
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.nevtoredek("cica");
        kj = b.build();
        assertEquals("CICA", kj.getNevtoredek());

        // tartalmazott cimke
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.cimke(c1);
        b.cimke(c2);
        kj = b.build();
        assertTrue(kj.getTartalmazottCimke().contains(c1));
        assertTrue(kj.getTartalmazottCimke().contains(c2));
        assertFalse(kj.getTartalmazottCimke().contains(c3));

        // mindet tartalmazza.
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.mindetTartalmazza(true);
        kj = b.build();
        assertTrue(kj.isMindetTartalmazza());

        // kizárt cimke
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.kizartCimke(c1);
        b.kizartCimke(c2);
        kj = b.build();
        assertTrue(kj.getKizartCimke().contains(c1));
        assertTrue(kj.getKizartCimke().contains(c2));
        assertFalse(kj.getKizartCimke().contains(c3));

        // fehérje
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getFeherje100g().min(1).max(100);
        kj = b.build();
        assertEquals(1, kj.getFeherje100g().getMin(), 1.0);
        assertEquals(100, kj.getFeherje100g().getMax(), 1.0);

        // zsír
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getZsir100g().min(1).max(100);
        kj = b.build();
        assertEquals(1, kj.getZsir100g().getMin(), 1.0);
        assertEquals(100, kj.getZsir100g().getMax(), 1.0);

        // szénhidrát
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getSzenhidrat100g().min(1).max(100);
        kj = b.build();
        assertEquals(1, kj.getSzenhidrat100g().getMin(), 1.0);
        assertEquals(100, kj.getSzenhidrat100g().getMax(), 1.0);

        // energia
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.getEnergia100g().min(1).max(100);
        kj = b.build();
        assertEquals(1, kj.getEnergia100g().getMin(), 1.0);
        assertEquals(100, kj.getEnergia100g().getMax(), 1.0);

    }

}