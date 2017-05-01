package hu.hubasky.gastromanager.entity.recept;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.IBuilder;
import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.Tartomany;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

/**
 * Recept keresési jellemzők
 * Created by mirso on 2017. 04. 26..
 */

public final class ReceptKeresesiJellemzok {

    /**
     * Aki a keresést végzi.
     */
    private final Felhasznalo keresoFelhasznalo;
    /**
     * Keresési névtöredés.
     */
    private final String nevtoredek;
    /**
     * Ajánlott étkezések szerint.
     */
    private final Set<EEtkezesek> ajanlottEtkezesek;
    /**
     * Tartalmazott alapanyag szerint.
     */
    private final Set<Alapanyag> tartalmazottAlapanyag;
    /**
     * Kizárt alapanyag szerint.
     */
    private final Set<Alapanyag> kizartAlapanyag;
    /**
     * A tartalmazott cimkék.
     */
    private final List<Cimke> tartalmazottCimke;
    /**
     * Az összes cimkét tartalmazza.
     */
    private final boolean mindenCimketTartalmazzon;
    /**
     * A kizért cimkék.
     */
    private final List<Cimke> kizartCimke;
    /**
     * Szavak a szöveges leírásban.
     */
    private final Set<String> szavak;
    /**
     * Minden szót tartalmazzon.
     */
    private final boolean mindenSzotTartalmazzon;
    /**
     * Adagra vonatkozó feltétel.
     */
    private final Tartomany adagSzures;
    /**
     * A találatok maximális száma.
     */
    private final int darabszam;
    /**
     * Melyik recept utántól keressen.
     */
    private final Recept kezdoRecept;
    /**
     * Kedvenc recept is kell.
     * A kiinduló halmaz a kedvencek listája (szűrés).
     */
    private final boolean kedvenc;
    /**
     * A kiinduló halmaz a sajátok listája (szűrés).
     */
    private final boolean sajat;
    /**
     * A kiindulo halmaz a teljes adatbázis.
     */
    private final boolean teljes;
    /**
     * A felhasználó, akire szűrni kell.
     */
    private final Felhasznalo felhasznalo;


    /**
     * Az építő.
     */
    public final static class Builder {
        /**
         * Keresési névtöredés.
         */
        private String nevtoredek;
        /**
         * Ajánlott étkezések szerint.
         */
        private final Set<EEtkezesek> ajanlottEtkezesek = EnumSet.noneOf(EEtkezesek.class);
        /**
         * Tartalmazott alapanyag szerint.
         */
        private final Set<Alapanyag> tartalmazottAlapanyag = new HashSet<>();
        /**
         * Kizárt alapanyag szerint.
         */
        private final Set<Alapanyag> kizartAlapanyag = new HashSet<>();
        /**
         * A tartalmazott cimkék.
         */
        private final List<Cimke> tartalmazottCimke = new LinkedList<>();
        /**
         * Az összes cimkét tartalmazza.
         */
        private boolean mindenCimketTartalmazzon;
        /**
         * A kizért cimkék.
         */
        private final List<Cimke> kizartCimke = new LinkedList<>();
        /**
         * Szavak a szöveges leírásban.
         */
        private final Set<String> szavak = new HashSet<>();
        /**
         * Minden szót tartalmazzon.
         */
        private boolean mindenSzotTartalmazzon;
        /**
         * Adagra vonatkozó feltétel.
         */
        private Tartomany.Builder adagSzures = new Tartomany.Builder();
        /**
         * A találatok maximális száma.
         */
        private int darabszam;
        /**
         * Melyik recept utántól keressen.
         */
        private Recept kezdoRecept;
        /**
         * Kedvenc recept is kell.
         * A kiinduló halmaz a kedvencek listája (szűrés).
         */
        private boolean kedvenc;
        /**
         * A kiinduló halmaz a sajátok listája (szűrés).
         */
        private boolean sajat;
        /**
         * A kiindulo halmaz a teljes adatbázis.
         */
        private boolean teljes = true;
        /**
         * A felhasználó, akire szűrni kell.
         */
        private Felhasznalo felhasznalo;


        /**
         * Névtöredék szerint.
         *
         * @param val a töredék.
         * @return az építő.
         */
        public Builder nevtoredek(String val) {
            nevtoredek = val == null ? null : val.trim().toUpperCase();
            if (nevtoredek.length() == 0) {
                nevtoredek = null;
            }
            return this;
        }

        /**
         * Ajánlott étkezés.
         *
         * @param val étkezés.
         * @return építő.
         */
        public Builder etkezes(EEtkezesek val) {
            ajanlottEtkezesek.add(val);
            return this;
        }

        /**
         * Tartalmazott alapanyag.
         *
         * @param alapanyag alapanyag.
         * @return építő.
         */
        public Builder alapanyag(Alapanyag alapanyag) {
            tartalmazottAlapanyag.add(alapanyag);
            return this;
        }

        /**
         * Kizárt alapanyag.
         *
         * @param alapanyag alapanyag.
         * @return építő.
         */
        public Builder kizartAlapanyag(Alapanyag alapanyag) {
            kizartAlapanyag.add(alapanyag);
            return this;
        }

        /**
         * Keresési cimke (tartalmazott).
         *
         * @param val a cimke.
         * @return az építő.
         */
        public Builder cimke(Cimke val) {
            tartalmazottCimke.add(val);
            return this;
        }

        /**
         * Kizárt cimke (tartalmazott).
         *
         * @param val a cimke.
         * @return az építő.
         */
        public Builder kizartCimke(Cimke val) {
            kizartCimke.add(val);
            return this;
        }

        /**
         * Minden cimkét tartalmazzon-e.
         *
         * @param val az érték.
         * @return az építő.
         */
        public Builder mindenCimketTartalmazzon(boolean val) {
            mindenCimketTartalmazzon = val;
            return this;
        }

        /**
         * Leírásban szereplő szóra.
         *
         * @param val a szó.
         * @return az építő.
         */
        public Builder leirasSzava(String val) {
            if (val != null) {
                val = val.trim().toUpperCase();
                if (val.length() > 0) {
                    szavak.add(val);
                }
            }
            return this;
        }

        /**
         * Minden szót tartlmazzon.
         *
         * @param val true, ha igen.
         * @return az építő
         */
        public Builder mindenSzotTartalmazzon(boolean val) {
            mindenSzotTartalmazzon = val;
            return this;
        }

        /**
         * Az adagra vonatkozó szűrő építője.
         *
         * @return a példány.
         */
        public Tartomany.Builder getAdagSzures() {
            return adagSzures;
        }

        /**
         * Maximális találati darabszám megadása.
         *
         * @param val a maximális daraszamm vagy 0 esetén nincs korlát.
         * @return az építő.
         */
        public Builder darabszam(int val) {
            darabszam = val;
            return this;
        }

        /**
         * Melyik recept utántól kezdje a keresést.
         *
         * @param val a kezdő.
         * @return az építő.
         */
        public Builder kezdo(Recept val) {
            kezdoRecept = val;
            return this;
        }

        /**
         * A keresés kiinduló halmazában a kedvencek benne vannak.
         *
         * @param val true, ha igen.
         * @return építő.
         * @see #felhasznalo(Felhasznalo)
         */
        public Builder kedvenc(boolean val) {
            kedvenc = val;
            return this;
        }

        /**
         * A keresés kiinduló halmazában a saját receptek benne vannak.
         *
         * @param val true, ha igen.
         * @return építő.
         * @see #felhasznalo(Felhasznalo)
         */
        public Builder sajat(boolean val) {
            sajat = val;
            return this;
        }

        /**
         * A keresés kiinduló halmazában a teljes adatbázis receptjei benne vannak.
         *
         * @param val true, ha igen.
         * @return építő.
         * @see #felhasznalo(Felhasznalo)
         */
        public Builder teljes(boolean val) {
            teljes = val;
            return this;
        }

        /**
         * Beállítja a felhasználót, mint feltételt.
         *
         * @param felhasznalo a felhasználó.
         * @return az építő
         * @see #sajat(boolean) Saját szűrésnél kötelező.
         * @see #kedvenc(boolean) Kedvenc szűrésnél kötelező.
         * @see #teljes(boolean) Megadható. (Ha idegen felhasználó, akkor csak a publikus receptekben keres,
         * ha a saját, bejelentkezett user, akkor a publikus és privát is)
         */
        public Builder felhasznalo(Felhasznalo felhasznalo) {
            felhasznalo = felhasznalo;
            return this;
        }

        /**
         * Felépíti a keresési jellemzőket.
         *
         * @param keresoFelhasznalo a keresést kezdeményező felhasználó
         * @return a jellemzők.
         */
        public ReceptKeresesiJellemzok build(Felhasznalo keresoFelhasznalo) {
            if (keresoFelhasznalo == null) {
                throw new IllegalArgumentException();
            }
            if (teljes && sajat) {
                throw new IllegalArgumentException("Ha teljes halmazra keresel, akkor a sajátot ne add meg!");
            }
            if (teljes && kedvenc) {
                throw new IllegalArgumentException("Ha teljes halmazra keresel, akkor a kedvencet ne add meg!");
            }
            if (felhasznalo == null && (sajat || kedvenc)) {
                throw new IllegalArgumentException("Ha saját vagy kedvenc halmazra keresel, akkor felhasználót is meg kell adni!");
            }
            return new ReceptKeresesiJellemzok(
                    keresoFelhasznalo,
                    nevtoredek,
                    ajanlottEtkezesek.isEmpty() ? null : ajanlottEtkezesek,
                    tartalmazottAlapanyag.isEmpty() ? null : tartalmazottAlapanyag,
                    kizartAlapanyag.isEmpty() ? null : kizartAlapanyag,
                    tartalmazottCimke.isEmpty() ? null : tartalmazottCimke,
                    mindenCimketTartalmazzon,
                    kizartCimke.isEmpty() ? null : kizartCimke,
                    szavak.isEmpty() ? null : szavak,
                    mindenSzotTartalmazzon,
                    adagSzures.build(), darabszam, kezdoRecept, kedvenc, sajat, teljes, felhasznalo);
        }
    }


    /**
     * Konstuktor.
     *
     * @param keresoFelhasznalo        a keresést végző felhasználó.
     * @param nevtoredek               keresési névtöredék.
     * @param ajanlottEtkezesek        ajánlott étkezések.
     * @param tartalmazottAlapanyag    tartalmazott alapanyag.
     * @param kizartAlapanyag          kizárt alapanyag.
     * @param tartalmazottCimke        tartalmazott cimke.
     * @param mindenCimketTartalmazzon mindent tartalmazott cimkét tartalmazzon?
     * @param kizartCimke              kizárt cimke.
     * @param szavak                   keresendő szavak a leírásban.
     * @param mindenSzotTartalmazzon   minden szót tartalmazzon.
     * @param adagSzures               adagra vonatkozó szűrés.
     * @param darabszam                a maximális találati darabszám vagy 0 esetén nincs korlát.
     * @param felhasznalo              a tulajdonos felhasználó.
     * @param kedvenc                  kedvenc receptek benne legyenek-e a kezdő halmazban.
     * @param kezdoRecept              melyik receptől kezdje a keresést. {@code null} esetén az első megtalálttól.
     * @param sajat                    true esetén a saját receptek benne vannak a keresőhalmazban.
     * @param teljes                   a teljes recepthalmazban keresünk.
     */
    public ReceptKeresesiJellemzok(
            Felhasznalo keresoFelhasznalo,
            String nevtoredek, Set<EEtkezesek> ajanlottEtkezesek, Set<Alapanyag> tartalmazottAlapanyag,
            Set<Alapanyag> kizartAlapanyag, List<Cimke> tartalmazottCimke, boolean mindenCimketTartalmazzon,
            List<Cimke> kizartCimke, Set<String> szavak, boolean mindenSzotTartalmazzon,
            Tartomany adagSzures, int darabszam,
            Recept kezdoRecept, boolean kedvenc, boolean sajat, boolean teljes, Felhasznalo felhasznalo) {
        this.keresoFelhasznalo = keresoFelhasznalo;
        this.nevtoredek = nevtoredek;
        this.ajanlottEtkezesek = ajanlottEtkezesek;
        this.tartalmazottAlapanyag = tartalmazottAlapanyag;
        this.kizartAlapanyag = kizartAlapanyag;
        this.tartalmazottCimke = tartalmazottCimke;
        this.mindenCimketTartalmazzon = mindenCimketTartalmazzon;
        this.kizartCimke = kizartCimke;
        this.szavak = szavak;
        this.mindenSzotTartalmazzon = mindenSzotTartalmazzon;
        this.adagSzures = adagSzures;
        this.darabszam = darabszam;
        this.kezdoRecept = kezdoRecept;
        this.kedvenc = kedvenc;
        this.sajat = sajat;
        this.teljes = teljes;
        this.felhasznalo = felhasznalo;
    }

    /**
     * Visszaadja, hogy a recept megfelel-e a keresési feltételeknek.
     *
     * @param recept a recept.
     * @return true, ha igen.
     */
    public boolean isMegfelelo(Recept recept) {
        if (nevtoredek != null && !Helper.contains(recept.getNeve(), nevtoredek)) {
            // névtöredék nem oké.
            return false;
        }
        if (ajanlottEtkezesek != null && !Helper.isExistsIntersect(ajanlottEtkezesek, recept.getAjanlottEtkezesek())) {
            // ajánlott étkezések szerint nem oké.
            return false;
        }

        if (tartalmazottAlapanyag != null && !recept.isTartalmazottAlapanyag(tartalmazottAlapanyag)) {
            // tartalmazott alapanyag szerint nem jó.
            return false;
        }

        if (kizartAlapanyag != null && recept.isTartalmazottAlapanyag(kizartAlapanyag)) {
            // kizárt alapanyag szerint nem jó.
            return false;
        }

        if (!recept.isMegfelelo(tartalmazottCimke, mindenCimketTartalmazzon, kizartCimke)) {
            // cimke szerint nem jó
            return false;
        }

        if (szavak != null && !recept.isTartalmazottSzavak(szavak, mindenSzotTartalmazzon)) {
            // a szavak szerint nem jó
            return false;
        }
        if (!adagSzures.isMegfelel(recept.getAdag())) {
            // adag szerint nem jó
            return false;
        }
        if (keresoFelhasznalo.equals(recept.getTulajdonos())) {
            // a keresést végző felhasználó nem tulajdonosa a receptnek.
            if (recept.getStatus() != EReceptStatus.PUBLIKUS) {
                return false;
            }
        }


        return true;
    }
}
