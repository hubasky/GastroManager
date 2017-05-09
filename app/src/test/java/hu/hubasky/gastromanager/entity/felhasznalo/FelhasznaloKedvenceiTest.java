package hu.hubasky.gastromanager.entity.felhasznalo;

import org.junit.Test;

import java.util.Set;

import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 07..
 */
public class FelhasznaloKedvenceiTest {

    private static final Felhasznalo USER1 = new Felhasznalo("1234567","12345","aa");
    private static final Felhasznalo USER2 = new Felhasznalo("876554321","12345","aa");

    @Test
    public void create() throws Exception{
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = USER2;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "Alma kÖrte BaraCK";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);


        try{
            new FelhasznaloKedvencei(null,null);
            fail();
        }catch (IllegalArgumentException ex){}

        try{
            new FelhasznaloKedvencei(USER1,null);
            fail();
        }catch (IllegalArgumentException ex){}

        try{
            new FelhasznaloKedvencei(null,recept);
            fail();
        }catch (IllegalArgumentException ex){}

        // érvémnyes
        new FelhasznaloKedvencei(USER1,recept);
    }
    @Test
    public void getFelasznalo() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = USER2;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "Alma kÖrte BaraCK";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        FelhasznaloKedvencei fk=new FelhasznaloKedvencei(USER1,recept);
        assertSame(USER1,fk.getFelasznalo());

    }

    @Test
    public void getRecept() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = USER2;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "Alma kÖrte BaraCK";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        FelhasznaloKedvencei fk=new FelhasznaloKedvencei(USER1,recept);
        assertSame(recept,fk.getRecept());
    }

    @Test
    public void isMegfelelo() throws Exception {
        Set<Hozzavalo> hl;
        Felhasznalo tulajdonos = USER2;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "x y";
        String leirasa = "Alma kÖrte BaraCK";
        String fenykepeURL = "7";
        double adag = 1.0;
        boolean exp;
        boolean res;

        Recept recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        FelhasznaloKedvencei fk=new FelhasznaloKedvencei(USER1,recept);
        assertFalse(fk.isMegfelelo(USER2));
        assertTrue(fk.isMegfelelo(USER1));

    }

}