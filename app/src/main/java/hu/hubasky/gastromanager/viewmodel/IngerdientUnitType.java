package hu.hubasky.gastromanager.viewmodel;

/**
 * Created by Balu on 2017-05-11.
 */


public enum IngerdientUnitType{
    weight("kg"),
    volume("liter"),
    piece("piece"),
    smallamount("kis"),
    unspecified("unspec")
    ;

    private final String type;
    public String getType() {
        return type;
    }

    IngerdientUnitType(String type) { this.type = type; }

}