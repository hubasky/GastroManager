package hu.hubasky.gastromanager.control.impl.dummy;

import org.junit.Test;

import java.util.List;

import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.control.ControlBase;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.FelhasznaloNyilvantarto;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

/**
 * Deszka modell
 * Created by mirso on 2017. 05. 07..
 */

public class DummyTest {
    /**
     * Példa inicializációra.
     */
    @Test
    public void init() {
        // inicializáció
        Controls controls = Controls.getInstance();
    }

    /**
     * Példák cimkekezelésre.
     *
     * @throws Exception
     */
    @Test
    public void cimkekezeloPeldak() throws Exception {
        Controls controls = Controls.getInstance();
        CimkeNyilvantarto cimkeNyilvantarto = controls.getCimkeNyilvantarto();

        List<Cimke> talalatok;


        // keresünt szótöredékre
        String keresoszo;
        talalatok = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, keresoszo = "növény");
        System.out.printf("%s : %s%n", keresoszo, talalatok.isEmpty() ? "Nincs ilyen" : String.format("%s db találat van.", talalatok.size()));

        talalatok = cimkeNyilvantarto.keres(ECimkeTipus.RECEPT, keresoszo = "NAGYI");
        System.out.printf("%s : %s%n", keresoszo, talalatok.isEmpty() ? "Nincs ilyen" : String.format("%s db találat van.", talalatok.size()));

        // felveszünk egy új cimkét
        cimkeNyilvantarto.tarolas(new Cimke(ECimkeTipus.RECEPT, "vacak", "0ffff"));
        talalatok = cimkeNyilvantarto.keres(ECimkeTipus.RECEPT, keresoszo = "vacak");
        System.out.printf("%s : %s%n", keresoszo, talalatok.isEmpty() ? "Nincs ilyen" : String.format("%s db találat van.", talalatok.size()));
    }

    /**
     * Példák felhasználó kezelésére.
     */
    @Test
    public void felhasznaloKezeloPeldak() throws Exception {
        Controls controls = Controls.getInstance();
        FelhasznaloNyilvantarto felhasznaloNyilvantarto = controls.getFelhasznaloNyilvantarto();
        Felhasznalo loggedIn;

        // sikeres bejelentkezés
        loggedIn = felhasznaloNyilvantarto.login(
                DmyFelhasznaloNyilvantarto.MAKRAAT_USR,
                DmyFelhasznaloNyilvantarto.ALL_PSW);
        System.out.printf("%s bejelentkezés%n",loggedIn==null?"Sikertelen":"Sikeres");

        // sikertelen bejelentkezés
        loggedIn = felhasznaloNyilvantarto.login(
                DmyFelhasznaloNyilvantarto.MAKRAAT_USR,
                "xxx");
        System.out.printf("%s bejelentkezés%n",loggedIn==null?"Sikertelen":"Sikeres");
    }

    /**
     * Példák alapanyagok kezelésére.
     */
    @Test
    public void alapanyagKezeloPeldak() throws Exception {
        Controls controls = Controls.getInstance();
        AlapanyagNyilvantarto alapanyagNyilvantarto = controls.getAlapanyagNyilvantarto();
        CimkeNyilvantarto cimkeNyilvantarto = controls.getCimkeNyilvantarto();

        List<Cimke> cimkeTalalatok;
        List<Alapanyag> talalatok;
        AlapanyagKeresesiJellemzok.Builder b;
        AlapanyagKeresesiJellemzok keresesiJellemzok;

        /////////////////////////////////////////////////////////////////
        // keresés
        /////////////////////////////////////////////////////////////////


        // csinálunk egy építőt
        b = new AlapanyagKeresesiJellemzok.Builder();
        // beállítjuk a feltételeket
        b.nevtoredek("tojás");
        // utána létrehozatjuk vele a keresési jellemzőket
        keresesiJellemzok = b.build();
        // aztán ezzel keresünk
        talalatok = alapanyagNyilvantarto.keres(keresesiJellemzok);
        // majd kiírjuk
        System.out.println(talalatok.isEmpty() ? "Nincs találat" : String.format("%d találat van.", talalatok.size()));

        // picit összetettebb keresésénél:
        // csinálunk egy építőt
        b = new AlapanyagKeresesiJellemzok.Builder();
        // beállítjuk a keresési feltételeket

        // legyen rajta növényi címke
        cimkeTalalatok = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény");
        if (cimkeTalalatok.isEmpty()) {
            throw new Exception("Ilyennek kéne lennie!");
        }
        b.cimke(cimkeTalalatok.get(0));
        // de ne legyen rajta "magas" feliratú cimke
        cimkeTalalatok = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "magas");
        if (cimkeTalalatok.isEmpty()) {
            throw new Exception("Ilyennek kéne lennie!");
        }
        b.kizartCimke(cimkeTalalatok.get(0));
        // utána létrehozatjuk vele a keresési jellemzőket
        keresesiJellemzok = b.build();
        // aztán ezzel keresünk
        talalatok = alapanyagNyilvantarto.keres(keresesiJellemzok);
        // majd kiírjuk
        System.out.println(talalatok.isEmpty() ? "Nincs találat" : String.format("%d találat van.", talalatok.size()));


        /////////////////////////////////////////////////////////////////
        // új alapanyag felvitele
        /////////////////////////////////////////////////////////////////
        // Létre kell hozni a jellemzőket először
        AlapanyagJellemzok alapanyagJellemzok = new AlapanyagJellemzok(
                100.0, // hány grammra vonatkoznak az adatok,
                0.1, // fehérje %
                0.2, // zsír %
                0.1, // szénhidrát %
                100 //energia KJ

        );
        Alapanyag a = new Alapanyag(
                EMennyisegiEgyseg.DARAB,
                alapanyagJellemzok,
                "Grízgaluska",
                30.0 /* egy darabnyi 30 gramm*/);
        // hozzáadhatunk cimkéket is
        cimkeTalalatok = cimkeNyilvantarto.keres(ECimkeTipus.ALAPANYAG, "növény");
        a.addCimke(cimkeTalalatok.get(0));
        // milyen egységekben (itt hány darabos kiszerelésben vásárolható)
        a.addVasarolhatoMennyiseg(5);
        a.addVasarolhatoMennyiseg(10);
        a.addVasarolhatoMennyiseg(20);
        // végül lementem
        alapanyagNyilvantarto.tarolas(a);

        // vajon, vissza tudom keresni?
        b = new AlapanyagKeresesiJellemzok.Builder();
        b.nevtoredek("gríz");
        keresesiJellemzok = b.build();
        // aztán ezzel keresünk
        talalatok = alapanyagNyilvantarto.keres(keresesiJellemzok);
        // majd kiírjuk
        System.out.println(talalatok.isEmpty() ? "Nincs találat" : String.format("%d találat van.", talalatok.size()));
    }


}
