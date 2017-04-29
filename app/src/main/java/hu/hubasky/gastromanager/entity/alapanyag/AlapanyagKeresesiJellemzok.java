package hu.hubasky.gastromanager.entity.alapanyag;

import java.util.LinkedList;
import java.util.List;

import hu.hubasky.gastromanager.common.Helper;
import hu.hubasky.gastromanager.entity.Cimke;
import hu.hubasky.gastromanager.entity.Tartomany;

/**
 * Alapanyag keresési jellemzők.
 * Created by mirso on 2017. 04. 26..
 */

public final class AlapanyagKeresesiJellemzok {


    /**
     * Névtöredék.
     */
    private final String nevtoredek;
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
     * A fehérjetartalom 100g-ban.
     */
    private final Tartomany feherje100g;
    /**
     * A zsírtartalom 100g-ban.
     */
    private final Tartomany zsir100g;
    /**
     * A szénhidráttartalom 100g-ban.
     */
    private final Tartomany szenhidrat100g;
    /**
     * Az energiatartalom (kj) 100g-ban
     */
    private final Tartomany energia100g;

    /**
     * Építő.
     */
    public static class Builder {

        /**
         * Névtöredék.
         */
        private String nevtoredek;
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
         * A fehérjetartalom 100g-ban.
         */
        private final Tartomany.Builder feherje100g = new Tartomany.Builder();
        /**
         * A zsírtartalom 100g-ban.
         */
        private final Tartomany.Builder zsir100g = new Tartomany.Builder();
        /**
         * A szénhidráttartalom 100g-ban.
         */
        private final Tartomany.Builder szenhidrat100g = new Tartomany.Builder();
        /**
         * Az energiatartalom (kj) 100g-ban
         */
        private final Tartomany.Builder energia100g = new Tartomany.Builder();

        /**
         * Keresési névtöredék.
         *
         * @param val az érték.
         * @return építő.
         */
        public Builder nevtoredek(String val) {
            nevtoredek = val == null ? null : val.trim().toUpperCase();
            if (nevtoredek.length() == 0) {
                nevtoredek = null;
            }
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
         * A fehérje keresési tartomány.
         *
         * @return az építő.
         */
        public Tartomany.Builder getFeherje100g() {
            return feherje100g;
        }

        /**
         * A zsír keresési tartomány.
         *
         * @return az építő.
         */
        public Tartomany.Builder getZsir100g() {
            return zsir100g;
        }

        /**
         * A szénhirdát keresési tartomány.
         *
         * @return az építő.
         */
        public Tartomany.Builder getSzenhidrat100g() {
            return szenhidrat100g;
        }


        /**
         * Az energia keresési tartomány.
         *
         * @return az építő.
         */
        public Tartomany.Builder getEnergia100g() {
            return energia100g;
        }


        public AlapanyagKeresesiJellemzok build() {
            Tartomany feherje = feherje100g.build();
            Tartomany szenhidrat = szenhidrat100g.build();
            Tartomany zsir = zsir100g.build();

            double min = 0, max = 0;
            min += feherje.getMin() == null ? 0 : feherje.getMin();
            min += szenhidrat.getMin() == null ? 0 : szenhidrat.getMin();
            min += zsir.getMin() == null ? 0 : zsir.getMin();

            if (min > 100) {
                throw new IllegalArgumentException(String.format("A 100g-ra jutó minimális összetevők mértéke (%.3f)g > 100g", min));
            }

            max += feherje.getMax() == null ? 0 : feherje.getMax();
            max += szenhidrat.getMax() == null ? 0 : szenhidrat.getMax();
            max += zsir.getMax() == null ? 0 : zsir.getMax();

            if (max > 100) {
                throw new IllegalArgumentException(String.format("A 100g-ra jutó maximális összetevők mértéke (%.3f)g > 100g", max));
            }

            return new AlapanyagKeresesiJellemzok(
                    nevtoredek,
                    tartalmazottCimke.isEmpty() ? null : tartalmazottCimke,
                    mindetTartalmazza,
                    kizartCimke.isEmpty() ? null : kizartCimke,
                    feherje,
                    szenhidrat,
                    zsir,
                    energia100g.build());

        }
    }

    /**
     * Konstruktor.
     *
     * @param nevtoredek        a keresett névtöredék.
     * @param tartalmazottCimke a tartalmazott cimkék.
     * @param mindetTartalmazza minden tartalmazott cimkének meg kell-e lennie.
     * @param kizartCimke       a kizárt cimkék.
     * @param feherje100g       a fehérje tartomány.
     * @param zsir100g          a zsír tartomány.
     * @param szenhidrat100g    a szénhidrát tartomány.
     * @param energia100g       az energia tartomány.
     */
    private AlapanyagKeresesiJellemzok(String nevtoredek, List<Cimke> tartalmazottCimke, boolean mindetTartalmazza,
                                       List<Cimke> kizartCimke, Tartomany feherje100g, Tartomany zsir100g,
                                       Tartomany szenhidrat100g, Tartomany energia100g) {
        this.nevtoredek = nevtoredek;
        this.tartalmazottCimke = tartalmazottCimke;
        this.mindetTartalmazza = mindetTartalmazza;
        this.kizartCimke = kizartCimke;
        this.feherje100g = feherje100g;
        this.zsir100g = zsir100g;
        this.szenhidrat100g = szenhidrat100g;
        this.energia100g = energia100g;
    }


    /**
     * Visszaadja, hogy az alapanyag megfelel-e a keresési feltételeknek.
     *
     * @param alapanyag a vizsgált alapanyag.
     * @return true, ha igen.
     */
    public boolean isMegfelel(Alapanyag alapanyag) {
        if (nevtoredek != null && !Helper.contains(alapanyag.getNeve(),nevtoredek)) {
            // névtöredék szerint nem oké.
            return false;
        }

        if (!alapanyag.isMegfelelo(tartalmazottCimke, mindetTartalmazza, kizartCimke)) {
            // cimkék szerint nem oké.
            return false;
        }

        KalkulaltOsszetevok kalkulal = KalkulaltOsszetevok.kalkulal(alapanyag, 100);

        if (!feherje100g.isMegfelel(kalkulal.getFeherjeGramm())) {
            return false;
        }
        if (!zsir100g.isMegfelel(kalkulal.getZsirGramm())) {
            return false;
        }
        if (!szenhidrat100g.isMegfelel(kalkulal.getSzenhidratGramm())) {
            return false;
        }
        if (!energia100g.isMegfelel(kalkulal.getEnergiaKJ())) {
            return false;
        }
        return true;
    }
}
