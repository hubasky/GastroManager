package hu.hubasky.gastromanager.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class TartomanyTest {
    @Test
    public void isMegfelel() throws Exception {
        Tartomany.Builder b;
        Tartomany t;

        // nincs se alsó, se felső
        b = new Tartomany.Builder();
        t = b.build();

        assertTrue(t.isMegfelel(1.0));

        // van alsó
        b = new Tartomany.Builder();
        b.min(1);
        t = b.build();
        assertFalse(t.isMegfelel(0.9));
        assertTrue(t.isMegfelel(1.0));
        assertTrue(t.isMegfelel(1.5));

        // van fölső
        b = new Tartomany.Builder();
        b.max(1);
        t = b.build();
        assertTrue(t.isMegfelel(0.9));
        assertTrue(t.isMegfelel(1.0));
        assertFalse(t.isMegfelel(1.5));

        // minkettő van
        b = new Tartomany.Builder();
        b.min(1);
        b.max(2);
        t = b.build();
        assertFalse(t.isMegfelel(0.9));
        assertTrue(t.isMegfelel(1.0));
        assertTrue(t.isMegfelel(1.5));
        assertTrue(t.isMegfelel(2));
        assertFalse(t.isMegfelel(2.1));

    }

    @Test
    public void getMin() throws Exception {
        Tartomany.Builder b;
        Tartomany t;

        b = new Tartomany.Builder();
        t = b.build();
        assertNull(t.getMin());

        b = new Tartomany.Builder();
        b.min(1);
        t = b.build();
        assertEquals(1, t.getMin(), 0.0);


    }

    @Test
    public void getMax() throws Exception {
        Tartomany.Builder b;
        Tartomany t;

        b = new Tartomany.Builder();
        t = b.build();
        assertNull(t.getMax());

        b = new Tartomany.Builder();
        b.max(1);
        t = b.build();
        assertEquals(1, t.getMax(), 0.0);
    }

}