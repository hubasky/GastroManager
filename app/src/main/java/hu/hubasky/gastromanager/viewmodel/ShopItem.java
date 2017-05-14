package hu.hubasky.gastromanager.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import hu.hubasky.gastromanager.entity.Cimkezheto;
import hu.hubasky.gastromanager.viewmodel.IngerdientUnit;

import static android.R.attr.id;

public class ShopItem extends Cimkezheto implements Parcelable {

    private String name;
    private int quantity;
    private String quantityType;
    private boolean selected;// = false;
    private static final String TAG = "ShopItem";

    public ShopItem(String name, int quantity, String quantityType) {
        super();
        this.name =  name;
        this.quantity = quantity;
        this.quantityType = quantityType;
    }



    public String getQuantityType() {
        return quantityType;
    }
    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // ~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~
    // ~%~%~%~%~%~%~%~%~%~%~% INTERFÉSZ IMPLEMENTÁCIÓ %~%~%~%~%~%~%~%~%~%~%~%~%~%~%~
    // ~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.v(TAG, "writeToParcel..." + flags);

        dest.writeString(name);
        dest.writeInt(quantity);
        dest.writeString(quantityType);
        dest.writeByte((byte) (selected ? 1 : 0));
    }

    protected ShopItem(Parcel in) {
        name = in.readString();
        quantity = in.readInt();
        quantityType = in.readString();
        selected = in.readByte() != 0;
    }

    public static final Creator<ShopItem> CREATOR = new Creator<ShopItem>() {
        @Override
        public ShopItem createFromParcel(Parcel in) {
            return new ShopItem(in);
        }

        @Override
        public ShopItem[] newArray(int size) {
            return new ShopItem[size];
        }
    };
}
