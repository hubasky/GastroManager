package hu.hubasky.gastromanager.entity.alapanyag;

/**
 * Alapanyag százalékos fehérje, zsír, szénhidrát jellemzők, valamint a vonatkoztatási és
 * vizsgált mennyiségnek megfelelően kalkulálja a tömeg értékeket.
 * Created by mirso on 2017. 04. 26..
 */

public final class KalkulaltOsszetevok {

    /**
     * Kiszámolja az összetevők értékeit az alapanyagból a vizsgált mennyiség alapján.
     *
     * @param alapanyag              az alapanyag.
     * @param vizsgaltMennyisegGramm a vizsgált mennyiség.
     * @return a példány.
     */
    public static KalkulaltOsszetevok kalkulal(Alapanyag alapanyag, double vizsgaltMennyisegGramm) {
        if (alapanyag == null) throw new AssertionError();
        if (!(!Double.isNaN(vizsgaltMennyisegGramm) && !Double.isInfinite(vizsgaltMennyisegGramm)))
            throw new AssertionError();

        AlapanyagJellemzok jellemzok = alapanyag.getJellemzok();
        double arany = vizsgaltMennyisegGramm / jellemzok.getVonatkoztatasGramm();

        return new KalkulaltOsszetevok(
                vizsgaltMennyisegGramm,
                jellemzok.getFeherjeMennyisegGramm() * arany,
                jellemzok.getZsirMennyisegGramm() * arany,
                jellemzok.getSzenhidratMennyisegGramm() * arany,
                jellemzok.getEnergiaKJ() * arany);

    }

    /**
     * A vizsgált mennyiség grammban.
     */
    private final double vizsgaltMennyisegGramm;
    /**
     * A fehérjetartalom grammban.
     */
    private final double feherjeGramm;
    /**
     * A zsírtarrtalom grammban.
     */
    private final double zsirGramm;
    /**
     * A szénhidrát-tartalom grammban.
     */
    private final double szenhidratGramm;
    /**
     * Az energiatartalom grammban.
     */
    private final double energiaKJ;

    /**
     * Konstruktor.
     *
     * @param vizsgaltMennyisegGramm vizsgált mennyiség grammban.
     * @param feherjeGramm           fehérjetartalom grammban.
     * @param zsirGramm              zsírtarrtalom grammban.
     * @param szenhidratGramm        szénhidrát-tartalom grammban.
     * @param energiaKJ              energiatartalom grammban.
     */
    private KalkulaltOsszetevok(double vizsgaltMennyisegGramm, double feherjeGramm, double zsirGramm,
                                double szenhidratGramm, double energiaKJ) {
        this.vizsgaltMennyisegGramm = vizsgaltMennyisegGramm;
        this.feherjeGramm = feherjeGramm;
        this.zsirGramm = zsirGramm;
        this.szenhidratGramm = szenhidratGramm;
        this.energiaKJ = energiaKJ;
    }


    /**
     * A vizsgált mennyiség grammban.
     *
     * @return a mennyiség.
     */
    public double getVizsgaltMennyisegGramm() {
        return vizsgaltMennyisegGramm;
    }

    /**
     * A fehérjetartalom grammban.
     *
     * @return a mennyiség.
     */
    public double getFeherjeGramm() {
        return feherjeGramm;
    }

    /**
     * A zsírtarrtalom grammban.
     *
     * @return a mennyiség.
     */
    public double getZsirGramm() {
        return zsirGramm;
    }

    /**
     * A szénhidrát-tartalom grammban.
     *
     * @return a mennyiség.
     */
    public double getSzenhidratGramm() {
        return szenhidratGramm;
    }

    /**
     * Az energiatartalom grammban.
     *
     * @return a mennyiség.
     */
    public double getEnergiaKJ() {
        return energiaKJ;
    }
}
