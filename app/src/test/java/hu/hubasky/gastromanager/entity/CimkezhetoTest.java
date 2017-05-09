
package hu.hubasky.gastromanager.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
        CimkezhetoTestImp ti;

        List<Cimke> tartalmazza;
        List<Cimke> kizarva;
        boolean mindetTartalmazzaKell;
        boolean exp;
        boolean res;

        Cimke allatiAlapanyag = new Cimke(ECimkeTipus.ALAPANYAG, "állati", "x");
        Cimke novenyiAlapanyag = new Cimke(ECimkeTipus.ALAPANYAG, "novenyi", "x");
        Cimke allatiRecept = new Cimke(ECimkeTipus.RECEPT, "állati", "x");
        Cimke novenyiRecept = new Cimke(ECimkeTipus.RECEPT, "novenyi", "x");
        Cimke allatiMinden = new Cimke(ECimkeTipus.MINDEN, "állati", "x");
        Cimke novenyiMinden = new Cimke(ECimkeTipus.MINDEN, "novenyi", "x");

        // semmi cimke, de semmi szűrés
        ti = new CimkezhetoTestImp();
        exp = true;
        res = ti.isMegfelelo(null, true, null);
        assertEquals(exp, res);

        // semmi cimke, de egy van tartalmazandó igény
        ti = new CimkezhetoTestImp();
        exp = false;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)), true, null);
        assertEquals(exp, res);

        // semmi cimke, de van egy kizárandó igény
        ti = new CimkezhetoTestImp();
        exp = true;
        res = ti.isMegfelelo(null, true, new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)));
        assertEquals(exp, res);

        // egy cimke, de az nem jó
        ti = new CimkezhetoTestImp();
        ti.addCimke(novenyiAlapanyag);
        exp = false;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)), true, null);
        assertEquals(exp, res);

        // egy cimke, az jó és típusban is passzol
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiAlapanyag);
        exp = true;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)), true, null);
        assertEquals(exp, res);

        // egy cimke, az jó mert MINDEN típusú
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiMinden);
        exp = true;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)), true, null);
        assertEquals(exp, res);

        // egy cimke, és az nem jó, mert eltérő típusú
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        exp = false;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)), true, null);
        assertEquals(exp, res);

        // több cimke, egyet legalább tartalmaz és kell is
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = true;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiRecept)), true, null);
        assertEquals(exp, res);

        // több cimke, egyet több tartalmazási igény is, de egy elég
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = true;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiRecept, novenyiAlapanyag)), false, null);
        assertEquals(exp, res);

        // több cimke, egyet több tartalmazási igény is, mind kell, de mindet nem tartalmazza
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = false;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiRecept, novenyiAlapanyag)), true, null);
        assertEquals(exp, res);

        // több cimke, egyet több tartalmazási igény is, mind kell, és mindet tartalmazza is
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = true;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(allatiRecept, novenyiRecept)), true, null);
        assertEquals(exp, res);

        // több cimke, nincs tartalmazott, van egy kizárt, de az nincs benne
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = true;
        res = ti.isMegfelelo(null, true, new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)));
        assertEquals(exp, res);

        // több cimke, van egy tartalmazott is, de az nincs benne és van egy kizárt, de az nincs benne
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = false;
        res = ti.isMegfelelo(new ArrayList<Cimke>(Arrays.asList(novenyiAlapanyag)), true, new ArrayList<Cimke>(Arrays.asList(allatiAlapanyag)));
        assertEquals(exp, res);

        // több cimke, nincs tartalmazott, van egy kizárt, és az benne is van
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = false;
        res = ti.isMegfelelo(null, true, new ArrayList<Cimke>(Arrays.asList(allatiRecept)));
        assertEquals(exp, res);

        // több cimke, van egy tartalmazott is,az benne van, de van egy kizárt, ami szintén benne cvan
        ti = new CimkezhetoTestImp();
        ti.addCimke(allatiRecept);
        ti.addCimke(novenyiRecept);
        exp = false;
        res = ti.isMegfelelo(
                new ArrayList<Cimke>(Arrays.asList(allatiRecept)), true,
                new ArrayList<Cimke>(Arrays.asList(novenyiRecept)));
        assertEquals(exp, res);


    }

    @Test
    public void masolasIde() throws Exception {
        CimkezhetoTestImp ti = new CimkezhetoTestImp();

        Cimke c = new Cimke(ECimkeTipus.ALAPANYAG, "x", "y");
        ti.addCimke(c);

        // hibás
        try {
            ti.masolasIde(null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        CimkezhetoTestImp ti1 = new CimkezhetoTestImp();
        ti.masolasIde(ti1);

        Set<Cimke> cimkek = ti1.getCimkek();
        assertTrue(cimkek.contains(c));
    }

    private static class CimkezhetoTestImp extends Cimkezheto {

    }

}