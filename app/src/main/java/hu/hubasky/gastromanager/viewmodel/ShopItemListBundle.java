package hu.hubasky.gastromanager.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Balu on 2017-05-13.
 */

public class ShopItemListBundle {

    private String name;
    private String senderName;
    private String sharedWith;
    private ArrayList<ShopItem> siList;

    private static final String TAG = "ShopItemListBundle";


    public ArrayList<ShopItem> getSiList() {
        return siList;
    }

    public void setSiList(ArrayList<ShopItem> siList) {
        this.siList = siList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(String sharedWith) {
        this.sharedWith = sharedWith;
    }


    public ShopItemListBundle(String name, String senderName, String sharedWith, ArrayList<ShopItem> siList) {

        this.name = name;
        this.senderName = senderName;
        this.sharedWith = sharedWith;
        this.siList = siList;

    }

}