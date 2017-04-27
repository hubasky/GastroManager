package hu.hubasky.gastromanager.viewmodel;

/**
 * Created by Peet on 2017. 04. 27..
 */

public enum IngerdientUnit {
    KG("kg"),
    DKG("dkg"),
    G("g"),
    L("l"),
    DECILITER("dl"),
    MILILITER("ml"),
    PIECE("db"),
    TABLESPOON("ek"),
    TEASPOON("tk"),
    PINCH("csipet"),
    UNDEFINED("")
    ;

    private final String unit;

    private IngerdientUnit(final String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}
