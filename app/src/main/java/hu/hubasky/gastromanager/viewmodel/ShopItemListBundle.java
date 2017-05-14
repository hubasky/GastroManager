package hu.hubasky.gastromanager.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;


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

    String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    String getSharedWith() {
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

    public void clearItem(){
        this.name = null;
        this.senderName = null;
        this.sharedWith = null;
        this.siList.clear();
        this.siList = null;
    }

}