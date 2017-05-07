package hu.hubasky.gastromanager.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 07..
 */
public class EgyediKulcsTest {
    @Test
    public void getUniqueKey() throws Exception {
        EgyediKulcs e = new EgyediKulcs();
        e.setUniqueKey("ALMA");
        assertEquals("ALMA", e.getUniqueKey());

    }

    @Test
    public void setUniqueKey() throws Exception {
        EgyediKulcs e = new EgyediKulcs();
        e.setUniqueKey("ALMA");
        assertEquals("ALMA", e.getUniqueKey());

    }

    @Test
    public void isUnuiqueKey() throws Exception {
        EgyediKulcs e = new EgyediKulcs();
        assertFalse(e.isUnuiqueKey());
        e.setUniqueKey("ALMA");
        assertTrue(e.isUnuiqueKey());

    }

}