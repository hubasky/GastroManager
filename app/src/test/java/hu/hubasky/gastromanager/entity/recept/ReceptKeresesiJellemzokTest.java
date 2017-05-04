package hu.hubasky.gastromanager.entity.recept;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

import static org.junit.Assert.*;

/**
 * Created by mirso on 2017. 05. 01..
 */
public class ReceptKeresesiJellemzokTest {

    private static final Felhasznalo OKUSER = new Felhasznalo("1234567", "12345", "x y");
    private static final Felhasznalo OKUSER1 = new Felhasznalo("66554433", "66433", "xy y");

    @Test
    public void isMegfelelo() throws Exception {

        Recept recept;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "alma, barack";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);

        ReceptKeresesiJellemzok.Builder b=new ReceptKeresesiJellemzok.Builder();

        // névtöredék


    }

}