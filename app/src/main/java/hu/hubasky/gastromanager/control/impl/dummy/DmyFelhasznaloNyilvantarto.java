package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.List;

import hu.hubasky.gastromanager.control.FelhasznaloNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyFelhasznaloNyilvantarto implements FelhasznaloNyilvantarto {
    @Override
    public List<Felhasznalo> keres(String nevtoredek, Felhasznalo kizart) throws Exception {
        return null;
    }

    @Override
    public Felhasznalo login(String usernev, String jelszo) throws Exception {
        return null;
    }

    @Override
    public Felhasznalo regiszter(String usernev, String jelszo, String nev) throws Exception {
        return null;
    }

    @Override
    public List<Recept> getKapottReceptek(Felhasznalo felhasznalo) throws Exception {
        return null;
    }

    @Override
    public void remKapottRecept(Felhasznalo felhasznalo, Recept recept) throws Exception {

    }

    @Override
    public List<BevasarloLista> getBevasarloListak(Felhasznalo felhasznalo) throws Exception {
        return null;
    }

    @Override
    public void addBevasarloLista(Felhasznalo felhasznalo, BevasarloLista bevasarloLista) throws Exception {

    }

    @Override
    public void kuldes(Felhasznalo felhasznalo, Recept recept) throws Exception {

    }
}
