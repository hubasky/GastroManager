package hu.hubasky.gastromanager.entity.felhasznalo;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Recept;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 04. 29..
 */
public class KuldottReceptekTest {
    private final Felhasznalo fkuld = new Felhasznalo("1234567", "1234567", "asws");
    private final Felhasznalo fcel = new Felhasznalo("1234567", "1234567", "asws");
    private final Recept recept = new Recept(fkuld, EReceptStatus.PUBLIKUS, "cica", "valami", "url", 1.5);

    @Test
    public void create() throws Exception {
        KuldottReceptek k;

        // recept null
        try {
            new KuldottReceptek(null, fkuld, fcel);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        // küldő null
        try {
            new KuldottReceptek(recept, null, fcel);
            fail();
        } catch (IllegalArgumentException ex) {
        }
        // cél null
        try {
            new KuldottReceptek(recept, fkuld, null);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        //ok
        new KuldottReceptek(recept, fkuld, fcel);
    }

    @Test
    public void getRecept() throws Exception {
        KuldottReceptek kuldottReceptek = new KuldottReceptek(recept, fkuld, fcel);
        assertSame(recept, kuldottReceptek.getRecept());
    }

    @Test
    public void getCimzett() throws Exception {
        KuldottReceptek kuldottReceptek = new KuldottReceptek(recept, fkuld, fcel);
        assertSame(fcel, kuldottReceptek.getCimzett());

    }

    @Test
    public void getKuldo() throws Exception {
        KuldottReceptek kuldottReceptek = new KuldottReceptek(recept, fkuld, fcel);
        assertSame(fkuld, kuldottReceptek.getKuldo());

    }

}