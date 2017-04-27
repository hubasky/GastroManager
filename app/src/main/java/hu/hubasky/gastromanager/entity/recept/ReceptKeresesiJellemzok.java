package hu.hubasky.gastromanager.entity.recept;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import hu.hubasky.gastromanager.IBuilder;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.Tartomany;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;

/**
 * Recept keresési jellemzők
 * Created by mirso on 2017. 04. 26..
 */

public final class ReceptKeresesiJellemzok {

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
    private final boolean mindetTartalmazza;
    /**
     * A kizért cimkék.
     */
    private final List<Cimke> kizartCimke;
    /**
     * Szavak a szöveges leírásban.
     */
    private final Set<String> szavak;
    /**
     * Adagra vonatkozó feltétel.
     */
    private final Tartomany adagSzures;


    /**
     * Az építő.
     */
    public final static class Builder implements IBuilder<ReceptKeresesiJellemzok> {
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
        private boolean mindetTartalmazza;
        /**
         * A kizért cimkék.
         */
        private final List<Cimke> kizartCimke = new LinkedList<>();
        /**
         * Szavak a szöveges leírásban.
         */
        private final Set<String> szavak = new HashSet<>();
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
        public Builder mindetTartalmazza(boolean val) {
            mindetTartalmazza = val;
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
         * @param val a maximális daraszam.
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


        @Override
        public ReceptKeresesiJellemzok build() {
            return new ReceptKeresesiJellemzok(
                    nevtoredek,
                    ajanlottEtkezesek.isEmpty() ? null : ajanlottEtkezesek,
                    tartalmazottAlapanyag.isEmpty() ? null : tartalmazottAlapanyag,
                    kizartAlapanyag.isEmpty() ? null : kizartAlapanyag,
                    tartalmazottCimke.isEmpty() ? null : tartalmazottCimke,
                    mindetTartalmazza,
                    kizartCimke.isEmpty() ? null : kizartCimke,
                    szavak.isEmpty() ? null : szavak,
                    adagSzures.build());
        }
    }

    /**
     * Konstuktor.
     *
     * @param nevtoredek            keresési névtöredék.
     * @param ajanlottEtkezesek     ajánlott étkezések.
     * @param tartalmazottAlapanyag tartalmazott alapanyag.
     * @param kizartAlapanyag       kizárt alapanyag.
     * @param tartalmazottCimke     tartalmazott cimke.
     * @param mindetTartalmazza     mindent tartalmazott cimkét tartalmazzon?
     * @param kizartCimke           kizárt cimke.
     * @param szavak                keresendő szavak a leírásban.
     * @param adagSzures            adagra vonatkozó szűrés.
     */
    private ReceptKeresesiJellemzok(String nevtoredek, Set<EEtkezesek> ajanlottEtkezesek,
                                    Set<Alapanyag> tartalmazottAlapanyag, Set<Alapanyag> kizartAlapanyag,
                                    List<Cimke> tartalmazottCimke, boolean mindetTartalmazza,
                                    List<Cimke> kizartCimke, Set<String> szavak, Tartomany adagSzures) {
        this.nevtoredek = nevtoredek;
        this.ajanlottEtkezesek = ajanlottEtkezesek;
        this.tartalmazottAlapanyag = tartalmazottAlapanyag;
        this.kizartAlapanyag = kizartAlapanyag;
        this.tartalmazottCimke = tartalmazottCimke;
        this.mindetTartalmazza = mindetTartalmazza;
        this.kizartCimke = kizartCimke;
        this.szavak = szavak;
        this.adagSzures = adagSzures;
    }

    /**
     * Visszaadja, hogy a recept megfelel-e a keresési feltételeknek.
     *
     * @param recept a recept.
     * @return true, ha igen.
     */
    public boolean isMegfelelo(Recept recept) {
        if (nevtoredek != null && !recept.getNeve().toUpperCase().contains(nevtoredek)) {
            // névtöredék nem oké.
            return false;
        }
        throw new UnsupportedOperationException("Nem implementált!");
    }
}
