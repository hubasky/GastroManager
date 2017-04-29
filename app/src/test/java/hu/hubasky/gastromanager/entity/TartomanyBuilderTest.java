package hu.hubasky.gastromanager.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class TartomanyBuilderTest {
    @Test
    public void min() throws Exception {
        Tartomany.Builder b = new Tartomany.Builder();
        assertEquals(b, b.min(1));
    }

    @Test
    public void max() throws Exception {
        Tartomany.Builder b = new Tartomany.Builder();
        assertEquals(b, b.max(1));
    }

    @Test
    public void build() throws Exception {
        Tartomany t;

        Tartomany.Builder b = new Tartomany.Builder();
        t = b.build();
        assertNull(t.getMin());
        assertNull(t.getMax());

        b = new Tartomany.Builder();
        b.min(1);
        t = b.build();
        assertEquals(1.0, t.getMin().doubleValue(),0.0);
        assertNull(t.getMax());

        b = new Tartomany.Builder();
        b.max(1);
        t = b.build();
        assertEquals(1.0, t.getMax().doubleValue(),0.0);
        assertNull(t.getMin());

        b = new Tartomany.Builder();
        b.max(10).min(1);
        t = b.build();
        assertEquals(1.0, t.getMin().doubleValue(),0.0);
        assertEquals(10.0, t.getMax().doubleValue(),0.0);

    }

}