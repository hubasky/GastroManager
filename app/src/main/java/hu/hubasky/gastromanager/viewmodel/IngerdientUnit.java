package hu.hubasky.gastromanager.viewmodel;

/**
 * Created by Peet on 2017. 04. 27..
 */


public enum IngerdientUnit {

    //szorzó az UI grammba történő letároláshoz kell
    KG("kg", 1000, IngerdientUnitType.weight),
    DKG("dkg", 10, IngerdientUnitType.weight),
    G("g", 1, IngerdientUnitType.weight),
    L("l", 1000, IngerdientUnitType.volume),
    DECILITER("dl", 100, IngerdientUnitType.volume),
    MILILITER("ml", 1, IngerdientUnitType.volume),
    PIECE("db", 1, IngerdientUnitType.piece),
    TABLESPOON("ek", 1, IngerdientUnitType.smallamount),
    TEASPOON("tk", 1, IngerdientUnitType.smallamount),
    PINCH("csipet", 1, IngerdientUnitType.smallamount),
    UNDEFINED("némi", 1, IngerdientUnitType.unspecified)
    ;


    private final String unit;
    private final int unitConvertMultiplier;
    private final IngerdientUnitType type;

    public IngerdientUnitType getIngerdientUnitType() {
        return this.type;
    }

    //pl. összeadásnál IngerdientUnit.valueOf(UIrólbekasztoltmértékegység).getUnitConvertMultiplier();
    public int getUnitConvertMultiplier() {
        //UI-hoz kelleni fog szerintem konvertálni nagyobb mértékegységre a bevásárlólistát
        return unitConvertMultiplier;
    }

    private IngerdientUnit(final String unit, final int unitConvertMultiplier, final IngerdientUnitType type) {
        this.unit = unit;
        this.unitConvertMultiplier = unitConvertMultiplier;
        this.type = type;
    }



    @Override
    public String toString() {
        return unit;
    }
}
