package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.FelhasznaloNyilvantarto;
import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyFelhasznaloNyilvantarto implements FelhasznaloNyilvantarto {

    /**
     * Felhasználók.
     */
    private final List<Felhasznalo> felhasznalok = new ArrayList<>();
    /**
     * Kapott receptek.
     */
    private final Map<Felhasznalo, List<Recept>> kapottReceptek = new HashMap<>();
    /**
     * Bevásárlólisták.
     */
    private final Map<Felhasznalo, List<BevasarloLista>> bevlistak = new HashMap<>();

    /**
     * Felhasználó kedvencei.
     */
    private final Map<Felhasznalo, List<Recept>> kedvencek = new HashMap<>();


    @Override
    public void init(Controls controls) {

    }

    @Override
    public List<Felhasznalo> keres(final String nevtoredek, final Felhasznalo kizart) throws Exception {
        if (nevtoredek == null) throw new IllegalArgumentException("névtöredék nem lehet null!");

        return Helper.filter(felhasznalok, new Helper.Checker<Felhasznalo>() {
            @Override
            public boolean check(Felhasznalo param) {
                return ((kizart == null) || !kizart.equals(param)) &&
                        param.isMegfelelo(nevtoredek);
            }
        });
    }

    @Override
    public Felhasznalo login(final String usernev, final String jelszo) throws Exception {
        List<Felhasznalo> fnd = Helper.filter(felhasznalok, new Helper.Checker<Felhasznalo>() {
            @Override
            public boolean check(Felhasznalo param) {
                return param.isAzonos(usernev) && param.jelszoEllenorzes(jelszo);
            }
        });
        return fnd.isEmpty() ? null : fnd.get(0);
    }

    @Override
    public Felhasznalo regiszter(String usernev, String jelszo, String nev) throws Exception {
        if (login(usernev, jelszo) != null) {
            throw new Exception("A felhasználó már létezik!");
        }
        Felhasznalo ret = new Felhasznalo(usernev, jelszo, nev);
        ret.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
        felhasznalok.add(ret);
        return ret;
    }

    @Override
    public List<Recept> getKapottReceptek(Felhasznalo felhasznalo) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");

        List<Recept> ret = kapottReceptek.get(felhasznalo);
        return ret == null ? Collections.<Recept>emptyList() : Collections.unmodifiableList(ret);

    }

    @Override
    public void remKapottRecept(Felhasznalo felhasznalo, Recept recept) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");
        if (recept == null) throw new IllegalArgumentException("recept nem lehet null!");

        List<Recept> ret = kapottReceptek.get(felhasznalo);
        if (ret != null) {
            ret.remove(recept);
        }
    }

    @Override
    public List<BevasarloLista> getBevasarloListak(Felhasznalo felhasznalo) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");

        List<BevasarloLista> bl = bevlistak.get(felhasznalo);
        return bl == null ? Collections.<BevasarloLista>emptyList() : Collections.unmodifiableList(bl);
    }

    @Override
    public void addBevasarloLista(Felhasznalo felhasznalo, BevasarloLista bevasarloLista) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");
        if (bevasarloLista == null)
            throw new IllegalArgumentException("bevasarloLista nem lehet null!");

        List<BevasarloLista> bl = bevlistak.get(felhasznalo);
        if (bl == null) {
            bevlistak.put(felhasznalo, bl = new ArrayList<BevasarloLista>());
        }
        if (!bevasarloLista.isUnuiqueKey()) {
            bevasarloLista.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
        }
        bl.add(bevasarloLista);

    }

    @Override
    public void kuldes(Felhasznalo felhasznalo, Recept recept) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");
        if (recept == null) throw new IllegalArgumentException("recept nem lehet null!");

        List<Recept> recepts = kapottReceptek.get(felhasznalo);
        if (recepts == null) {
            kapottReceptek.put(felhasznalo, recepts = new ArrayList<Recept>());
        }
        if (!recepts.contains(recept))
            recepts.add(recept);
    }

    @Override
    public void addKedvenc(Felhasznalo felhasznalo, Recept recept) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");
        if (recept == null) throw new IllegalArgumentException("recept nem lehet null!");

        List<Recept> recepts = kedvencek.get(felhasznalo);
        if (recept == null) {
            kedvencek.put(felhasznalo, recepts = new ArrayList<Recept>());
        }
        if (!recepts.contains(recept)) {
            recepts.add(recept);
        }

    }

    @Override
    public void remKedvenc(Felhasznalo felhasznalo, Recept recept) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");
        if (recept == null) throw new IllegalArgumentException("recept nem lehet null!");

        List<Recept> recepts = kedvencek.get(felhasznalo);
        if (recepts != null) {
            recepts.remove(recept);
        }
    }

    @Override
    public List<Recept> getKedvencek(Felhasznalo felhasznalo) throws Exception {
        if (felhasznalo == null) throw new IllegalArgumentException("felhasznalo nem lehet null!");

        List<Recept> recepts = kedvencek.get(felhasznalo);
        return recepts == null ? Collections.<Recept>emptyList() : Collections.unmodifiableList(recepts);

    }
}
