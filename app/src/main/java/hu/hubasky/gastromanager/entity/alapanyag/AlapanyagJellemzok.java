package hu.hubasky.gastromanager.entity.alapanyag;

/**
 * Alapanyag jellemzőket leíró osztály.
 * Created by mirso on 2017. 04. 26..
 */

public final class AlapanyagJellemzok {
    /**
     * A jellemző hány gramm tömegű anyagra vonatkozik.
     */
    private double vonatkoztatasGramm;
    /**
     * A fehérje tartolom %-ban.
     */
    private double feherjeSzazalek;
    /**
     * A zsírtartalom %-ban.
     */
    private double zsirSzazalek;
    /**
     * A szénhidrát tartalom %-ban.
     */
    private double szenhidratSzazalek;
    /**
     * Az energiatartalom KJ-ban.
     */
    private double energiaKJ;

    /**
     * Konstruktor.
     *
     * @param vonatkoztatasGramm hány gramm alapanyagra vonatkoznak a jellemzők.
     * @param feherjeSzazalek    a fehérje %-ban.
     * @param zsirSzazalek       a zsír %-ban.
     * @param szenhidratSzazalek a szénhidrát %-ban.
     * @param energiaKJ          az energia KJ-ban.
     */
    public AlapanyagJellemzok(double vonatkoztatasGramm, double feherjeSzazalek, double zsirSzazalek, double szenhidratSzazalek, double energiaKJ) {
        this.setVonatkoztatasGramm(vonatkoztatasGramm);
        this.setFeherjeSzazalek(feherjeSzazalek);
        this.setZsirSzazalek(zsirSzazalek);
        this.setSzenhidratSzazalek(szenhidratSzazalek);
        this.setEnergiaKJ(energiaKJ);
    }


    /**
     * A jellemző hány gramm tömegű anyagra vonatkozik.
     *
     * @return tömeg.
     */
    public double getVonatkoztatasGramm() {
        return vonatkoztatasGramm;
    }

    /**
     * A jellemző hány gramm tömegű anyagra vonatkozik.
     *
     * @param vonatkoztatasGramm a tömeg.
     */
    public void setVonatkoztatasGramm(double vonatkoztatasGramm) {
        if (Double.isNaN(vonatkoztatasGramm) || Double.isInfinite(vonatkoztatasGramm))
            throw new AssertionError();
        if (vonatkoztatasGramm <= 0) {
            throw new IllegalArgumentException("vonatkoztatasGramm <= 0");
        }
        this.vonatkoztatasGramm = vonatkoztatasGramm;
    }

    /**
     * A fehérje tartolom %-ban.
     *
     * @return százalék.
     */
    public double getFeherjeSzazalek() {
        return feherjeSzazalek;
    }

    /**
     * A fehérje tartolom %-ban.
     *
     * @param feherjeSzazalek százalék.
     */
    public void setFeherjeSzazalek(double feherjeSzazalek) {
        if (Double.isNaN(feherjeSzazalek) || Double.isInfinite(feherjeSzazalek))
            throw new AssertionError();

        if (feherjeSzazalek < 0) {
            throw new IllegalArgumentException("feherjeSzazalek < 0");
        }
        this.feherjeSzazalek = feherjeSzazalek;
    }

    /**
     * A zsírtartalom %-ban.
     *
     * @return százalék.
     */
    public double getZsirSzazalek() {
        return zsirSzazalek;
    }

    /**
     * A zsírtartalom %-ban.
     *
     * @param zsirSzazalek a zsírtartalom %-ban.
     */
    public void setZsirSzazalek(double zsirSzazalek) {
        if (Double.isNaN(zsirSzazalek) || Double.isInfinite(zsirSzazalek))
            throw new AssertionError();
        if (zsirSzazalek < 0) {
            throw new IllegalArgumentException("zsirSzazalek < 0");
        }
        this.zsirSzazalek = zsirSzazalek;
    }

    /**
     * A szénhidrát tartalom %-ban.
     *
     * @return százalék.
     */
    public double getSzenhidratSzazalek() {
        return szenhidratSzazalek;
    }

    /**
     * A szénhidrát tartalom %-ban.
     *
     * @param szenhidratSzazalek a százalék.
     */
    public void setSzenhidratSzazalek(double szenhidratSzazalek) {
        if (Double.isNaN(szenhidratSzazalek) || Double.isInfinite(szenhidratSzazalek))
            throw new AssertionError();
        if (szenhidratSzazalek < 0) {
            throw new IllegalArgumentException("szenhidratSzazalek < 0");
        }
        this.szenhidratSzazalek = szenhidratSzazalek;
    }

    /**
     * Az energiatartalom KJ-ban.
     *
     * @return energia KJ.
     */
    public double getEnergiaKJ() {
        return energiaKJ;
    }

    /**
     * Az energiatartalom KJ-ban.
     *
     * @param energiaKJ energia KJ.
     */
    public void setEnergiaKJ(double energiaKJ) {
        if (Double.isNaN(energiaKJ) || Double.isInfinite(energiaKJ))
            throw new AssertionError();
        if (energiaKJ < 0) {
            throw new IllegalArgumentException("energiaKJ < 0");
        }
        this.energiaKJ = energiaKJ;
    }
}
