package hu.hubasky.gastromanager.control.impl.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.control.AlapanyagNyilvantarto;
import hu.hubasky.gastromanager.control.CimkeNyilvantarto;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.FelhasznaloNyilvantarto;
import hu.hubasky.gastromanager.control.ReceptNyilvantarto;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.ECimkeTipus;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.EEtkezesek;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;

/**
 * Created by hallgato on 2017-04-27.
 */

public final class DmyReceptNyilvantarto implements ReceptNyilvantarto {

    /**
     * Receptek.
     */
    private final List<Recept> receptek = new ArrayList<>();

    @Override
    public boolean init(Controls controls) {
        FelhasznaloNyilvantarto fn = controls.getFelhasznaloNyilvantarto();
        CimkeNyilvantarto cimkeNyilvantarto = controls.getCimkeNyilvantarto();
        AlapanyagNyilvantarto alapanyagNyilvantarto = controls.getAlapanyagNyilvantarto();

        try {
            List<Felhasznalo> usr;
            // tulajdonost keresünk.

            usr = fn.keres("makra", null);
            if (usr.isEmpty()) {
                return false;
            }
            Recept r = new Recept(
                    usr.get(0),
                    EReceptStatus.PUBLIKUS,
                    "Makra recept",
                    "Ez egy finom kis étel",
                    "https://www.google.hu/imgres?imgurl=http%3A%2F%2Fbudapestcity.org%2F11-egyeb%2Fhungaricum%2Fetelek%2Fimages%2Fkep-nagy-gulyasleves.jpg&imgrefurl=http%3A%2F%2Fbudapestcity.org%2F11-egyeb%2Fhungaricum%2Fetelek%2Findex-hu.htm&docid=eIT_iFGFSe2eZM&tbnid=NtpzFqzwJ7DPjM%3A&vet=10ahUKEwjM-5iJlN7TAhUMKpoKHVK2B-YQMwg0KAAwAA..i&w=630&h=300&bih=837&biw=1920&q=magyaros%20%C3%A9telek&ved=0ahUKEwjM-5iJlN7TAhUMKpoKHVK2B-YQMwg0KAAwAA&iact=mrc&uact=8",
                    4.0);

            // aztán cimkét.
            Cimke c;
            List<Cimke> cimkeTalalat = cimkeNyilvantarto.keres(ECimkeTipus.MINDEN, "finom");
            if (cimkeTalalat.isEmpty()) return false;
            c = cimkeTalalat.get(0);
            r.addCimke(c);

            // majd alapanyagot
            AlapanyagKeresesiJellemzok.Builder b;
            AlapanyagKeresesiJellemzok akj;
            List<Alapanyag> alapanyagTalalatok;

            b = new AlapanyagKeresesiJellemzok.Builder();
            b.nevtoredek("tojás");
            akj = b.build();
            alapanyagTalalatok = alapanyagNyilvantarto.keres(akj);
            if (alapanyagTalalatok.isEmpty()) {
                return false;
            }
            r.addHozzavalo(new Hozzavalo(6, alapanyagTalalatok.get(0)));
            // milyen étkezésre
            r.addEtkezes(EEtkezesek.EBED);

            tarolas(r);

            return true;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * A keresési jellemzők alapján létrehozza a kezdő halmazt.
     *
     * @param jellemzok a jellemzők.
     * @return a halmaz.
     * @throws Exception Ha kivétel lépett fel.
     */
    private List<Recept> keresesHalmazSzerint(final ReceptKeresesiJellemzok jellemzok) throws Exception {
        Integer darabszam = jellemzok.getDarabszam() == 0 ? null : jellemzok.getDarabszam();

        if (jellemzok.isTeljes()) {
            return Helper.filter(receptek, new Helper.Checker<Recept>() {
                @Override
                public boolean check(Recept param) {
                    // itt kezeli le a publikus/privát jogosultságot.
                    return jellemzok.isMegfelelo(param);
                }
            }, darabszam);
        }

        // saját vagy kedvenc
        List<Recept> ret = new ArrayList<>(32);

        if (jellemzok.isSajat()) {
            // sajátok kellenek.
            ret.addAll(Helper.filter(receptek, new Helper.Checker<Recept>() {
                @Override
                public boolean check(Recept param) {
                    return param.getTulajdonos().isAzonos(jellemzok.getFelhasznalo().getUsernev());
                }
            }));
        }

        if (jellemzok.isKedvenc()) {
            // kedvencek kellenek.
            List<Recept> kedvencek = Controls.getInstance()
                    .getFelhasznaloNyilvantarto()
                    .getKedvencek(jellemzok.getFelhasznalo());
            ret.addAll(kedvencek);
        }


        // a ret-ben lenne a teljes halmaz
        Collections.sort(ret, new Comparator<Recept>() {
            @Override
            public int compare(Recept o1, Recept o2) {
                return o1.compareTo(o2);
            }
        });

        return Helper.filter(ret, new Helper.Checker<Recept>() {
            @Override
            public boolean check(Recept param) {
                return jellemzok.isMegfelelo(param);
            }
        }, darabszam);

    }

    @Override
    public List<Recept> keres(final ReceptKeresesiJellemzok jellemzok) throws Exception {
        if (jellemzok == null) throw new IllegalArgumentException("jellemzok nem lehet null!");
        return keresesHalmazSzerint(jellemzok);
    }

    @Override
    public List<VasarlandoAlapanyag> vasarlandoKalkulacio(Recept recept, double adag) throws Exception {
        if (recept == null) throw new IllegalArgumentException("recept nem lehet null!");
        return recept.vasarlandoKalkulacio(adag);

    }

    @Override
    public synchronized void tarolas(Recept recept) throws Exception {
        if (!recept.isUnuiqueKey()) {
            recept.setUniqueKey(DmyEgyediKulcsKezelo.getInstance().getNext());
        }

        int pos = Collections.binarySearch(receptek, recept);
        if (pos >= 0) {
            return;
        }
        receptek.add(-(pos + 1), recept);

    }

    @Override
    public void torles(Recept recept) throws Exception {
        if (!recept.isUnuiqueKey()) {
            return;
        }
        int pos = Collections.binarySearch(receptek, recept);

        if (pos >= 0) {
            receptek.remove(-pos);
        }
    }
}
