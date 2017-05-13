package hu.hubasky.gastromanager.viewmodel;

/**
 * Created by Balu on 2017-05-13.
 */

public class ShoppingCart {

    private String name;
    private String senderName;
    private String sharedWith;



    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getSenderName() {
        return senderName;
    }

    void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    String getSharedWith() {
        return sharedWith;
    }

    void setSharedWith(String sharedWith) {
        this.sharedWith = sharedWith;
    }

    public ShoppingCart(String name, String senderName, String sharedWith) {

        this.name = name;
        this.senderName = senderName;
        this.sharedWith = sharedWith;
    }
}
