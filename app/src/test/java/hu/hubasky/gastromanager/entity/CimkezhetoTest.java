
package hu.hubasky.gastromanager.entity;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class CimkezhetoTest {
    @Test
    public void addCimke() throws Exception {
        CimkezhetoTestImp ti = new CimkezhetoTestImp();
        try {
            ti.addCimke(null);
            fail();
        } catch (Exception ex) {

        }

        Cimke c = new Cimke(ECimkeTipus.ALAPANYAG, "x", "y");
        ti.addCimke(c);
        assertEquals(1, ti.getCimkek().size());

        //ugyanazt mégegyszer nem lehet
        c = new Cimke(ECimkeTipus.ALAPANYAG, "x", "y");
        ti.addCimke(c);
        assertEquals(1, ti.getCimkek().size());

        // de mást igen.
        c = new Cimke(ECimkeTipus.RECEPT, "x", "y");
        ti.addCimke(c);
        assertEquals(2, ti.getCimkek().size());

        // de mást igen.
        c = new Cimke(ECimkeTipus.ALAPANYAG, "x1", "y");
        ti.addCimke(c);
        assertEquals(3, ti.getCimkek().size());

        // de mást igen.
        c = new Cimke(ECimkeTipus.ALAPANYAG, "x", "y1");
        ti.addCimke(c);
        assertEquals(4, ti.getCimkek().size());


    }

    @Test
    public void remCimke() throws Exception {
        CimkezhetoTestImp ti = new CimkezhetoTestImp();
        try {
            ti.remCimke(null);
            fail();
        } catch (Exception ex) {

        }

        Cimke c = new Cimke(ECimkeTipus.ALAPANYAG, "x", "y");
        ti.addCimke(c);
        assertEquals(1, ti.getCimkek().size());

        // ez nincs benne
        c = new Cimke(ECimkeTipus.ALAPANYAG, "x1", "y");
        ti.remCimke(c);
        assertEquals(1, ti.getCimkek().size());

        // de ez benne van
        c = new Cimke(ECimkeTipus.ALAPANYAG, "x", "y");
        ti.remCimke(c);
        assertEquals(0, ti.getCimkek().size());
    }

    @Test
    public void isMegfelelo() throws Exception {
        CimkezhetoTestImp ti = new CimkezhetoTestImp();
        ti.addCimke(new Cimke(ECimkeTipus.ALAPANYAG, "állati", "x"));
        ti.addCimke(new Cimke(ECimkeTipus.ALAPANYAG, "növényi", "x"));
        ti.addCimke(new Cimke(ECimkeTipus.RECEPT, "állati", "x"));
        ti.addCimke(new Cimke(ECimkeTipus.RECEPT, "növényi", "x"));
        ti.addCimke(new Cimke(ECimkeTipus.MINDEN, "finom", "x"));


    }

    private static class CimkezhetoTestImp extends Cimkezheto {

    }

}