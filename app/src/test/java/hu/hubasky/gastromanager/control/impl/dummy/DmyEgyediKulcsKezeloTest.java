package hu.hubasky.gastromanager.control.impl.dummy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 07..
 */
public class DmyEgyediKulcsKezeloTest {
    @Test
    public void getInstance() throws Exception {
        DmyEgyediKulcsKezelo instance = DmyEgyediKulcsKezelo.getInstance();
        DmyEgyediKulcsKezelo instance1 = DmyEgyediKulcsKezelo.getInstance();
        assertSame(instance, instance1);
    }

    @Test
    public void getNext() throws Exception {
        String key1=DmyEgyediKulcsKezelo.getInstance().getNext();
        String key2=DmyEgyediKulcsKezelo.getInstance().getNext();
        assertTrue(key1.compareTo(key2)<0);
    }

}