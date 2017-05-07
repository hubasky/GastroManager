package hu.hubasky.gastromanager.entity.recept;

import org.junit.Test;

import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
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

        boolean exp, res;
        ReceptKeresesiJellemzok jellemzok;
        ReceptKeresesiJellemzok.Builder b;

        Recept recept,recept1;
        Felhasznalo tulajdonos = OKUSER;
        EReceptStatus status = EReceptStatus.PUBLIKUS;
        String neve = "alma, barack";
        String leirasa = "xy";
        String fenykepeURL = "7";
        double adag = 1.0;
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);


        ////////////////////////////////////////////////////////////
        // névtöredék
        ////////////////////////////////////////////////////////////

        b = new ReceptKeresesiJellemzok.Builder();
        b.nevtoredek("alma");
        jellemzok = b.build(OKUSER);
        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        b = new ReceptKeresesiJellemzok.Builder();
        b.nevtoredek("BARA");
        jellemzok = b.build(OKUSER);
        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        b = new ReceptKeresesiJellemzok.Builder();
        b.nevtoredek("X");
        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        ////////////////////////////////////////////////////////////
        // ajánlott étkezések
        ////////////////////////////////////////////////////////////

        // nincs ajánlott
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.etkezes(EEtkezesek.REGGELI);
        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // nem egyezik meg.
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addEtkezes(EEtkezesek.EBED);
        b = new ReceptKeresesiJellemzok.Builder();
        b.etkezes(EEtkezesek.REGGELI);
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // egy megegyezik.
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addEtkezes(EEtkezesek.EBED);
        recept.addEtkezes(EEtkezesek.REGGELI);
        b = new ReceptKeresesiJellemzok.Builder();
        b.etkezes(EEtkezesek.REGGELI);
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        ////////////////////////////////////////////////////////////
        // tartalmazott alapanyag
        ////////////////////////////////////////////////////////////

        // a recept üres
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.alapanyag(new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100));
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // nem egyezik meg
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addHozzavalo(new Hozzavalo(5.0,
                new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "cukor", 100)));
        b = new ReceptKeresesiJellemzok.Builder();
        b.alapanyag(new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100));
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // egy megegyezik
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addHozzavalo(new Hozzavalo(5.0,
                new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100)));
        recept.addHozzavalo(new Hozzavalo(5.0,
                new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "cukor", 100)));
        b = new ReceptKeresesiJellemzok.Builder();
        b.alapanyag(new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100));
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);


        ////////////////////////////////////////////////////////////
        // kizárt alapanyag
        ////////////////////////////////////////////////////////////

        // a recept üres
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.kizartAlapanyag(new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100));
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // nem üres, de nem az van kizárva
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addHozzavalo(new Hozzavalo(5.0,
                new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "cukor", 100)));
        b = new ReceptKeresesiJellemzok.Builder();
        b.kizartAlapanyag(new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100));
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // az egyik ki van zárva.
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addHozzavalo(new Hozzavalo(5.0,
                new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "cukor", 100)));
        recept.addHozzavalo(new Hozzavalo(5.0,
                new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100)));
        b = new ReceptKeresesiJellemzok.Builder();
        b.kizartAlapanyag(new Alapanyag(EMennyisegiEgyseg.GRAMM, new AlapanyagJellemzok(100.0, .1, .1, .1, 100), "liszt", 100));
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        ////////////////////////////////////////////////////////////
        // tartalmazott cimke
        ////////////////////////////////////////////////////////////

        // a nincs rajta cimke, de kellene
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // van rajta cimke, de nem megfelelő
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "növényi", "ff"));
        b = new ReceptKeresesiJellemzok.Builder();
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // van rajta cimke, és megfelelő /egy van és egyet keresünk
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "növényi", "ff"));
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        b = new ReceptKeresesiJellemzok.Builder();
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // van rajta cimke, több van, mindet keresünk, de csak egy megfelelő
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "növényi", "ff"));
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        b = new ReceptKeresesiJellemzok.Builder();
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "finom", "ff"));
        b.mindenCimketTartalmazzon(true);
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // van rajta cimke, több van, mindet keresünk, és mind megfeleő
        recept = new Recept(tulajdonos, status, neve, leirasa, fenykepeURL, adag);
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "növényi", "ff"));
        recept.addCimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        b = new ReceptKeresesiJellemzok.Builder();
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "állati", "ff"));
        b.cimke(new Cimke(ECimkeTipus.RECEPT, "növényi", "ff"));
        b.mindenCimketTartalmazzon(true);
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        ////////////////////////////////////////////////////////////
        // szavak
        ////////////////////////////////////////////////////////////

        // nincs olyan szó
        recept = new Recept(tulajdonos, status, neve, "alma körte barack", fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.leirasSzava("cica");
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // egy szót keresünk és van olyan szó
        recept = new Recept(tulajdonos, status, neve, "alma körte barack", fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.leirasSzava("BARACK");
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // több szót keresünk és van olyan szó, az már elég
        recept = new Recept(tulajdonos, status, neve, "alma körte barack", fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.leirasSzava("BARACK");
        b.leirasSzava("ZIZI");
        b.mindenSzotTartalmazzon(false);
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);


        // több szót keresünk és mindegyiket akarjuk, de nincs
        recept = new Recept(tulajdonos, status, neve, "alma körte barack", fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.leirasSzava("BARACK");
        b.leirasSzava("ZIZI");
        b.mindenSzotTartalmazzon(true);
        jellemzok = b.build(OKUSER);

        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // több szót keresünk és mindegyiket akarjuk, de mind megvan
        recept = new Recept(tulajdonos, status, neve, "alma körte barack", fenykepeURL, adag);
        b = new ReceptKeresesiJellemzok.Builder();
        b.leirasSzava("BARACK");
        b.leirasSzava("KÖRTE");
        b.mindenSzotTartalmazzon(true);
        jellemzok = b.build(OKUSER);

        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        ////////////////////////////////////////////////////////////
        // szavak
        ////////////////////////////////////////////////////////////

        // adag szűrés / ez túl sok
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().max(4.0);
        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // adag szűrés / ez túl evés
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().min(6);
        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // adag szűrés / csak alsó határra szűrünk, de jó
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().min(4);
        jellemzok = b.build(OKUSER);
        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // adag szűrés / csak felső határra szűrünk, de jó
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().max(6);
        jellemzok = b.build(OKUSER);
        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // adag szűrés /tartományra szűrűnk, és nem jó
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().min(5.5);
        b.getAdagSzures().max(6);
        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // adag szűrés /tartományra szűrűnk, és nem jó
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().min(4);
        b.getAdagSzures().max(4.9);
        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // adag szűrés /tartományra szűrűnk, és  jó
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        b = new ReceptKeresesiJellemzok.Builder();
        b.getAdagSzures().min(4);
        b.getAdagSzures().max(6);
        jellemzok = b.build(OKUSER);
        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // ha van kezdő recept megadva, és azután való
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        recept.setUniqueKey("1");
        recept1 = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        recept1.setUniqueKey("0");
        b = new ReceptKeresesiJellemzok.Builder();
        b.kezdo(recept1);

        jellemzok = b.build(OKUSER);
        exp = true;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);

        // ha van kezdő recept megadva, de azelőtti
        recept = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        recept.setUniqueKey("0");
        recept1 = new Recept(tulajdonos, status, neve,leirasa, fenykepeURL, 5);
        recept1.setUniqueKey("1");
        b = new ReceptKeresesiJellemzok.Builder();
        b.kezdo(recept1);

        jellemzok = b.build(OKUSER);
        exp = false;
        res = jellemzok.isMegfelelo(recept);
        assertEquals(exp, res);



    }

}