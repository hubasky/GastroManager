package hu.hubasky.gastromanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Balu on 2017-05-09.
 */
class CartItem {

    //public volt
    CartItem(String name, int quantity, String quantityType) {
        this.name = name;
        this.quantity = quantity;
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

    public String getQuantityType() {
        return quantityType;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    String name;
    int quantity;
    String quantityType;
    boolean selected = false;
}


public class ShoppingAdapter extends ArrayAdapter<CartItem> {

    private List<CartItem> cartItemList;
    private Context context;


    public ShoppingAdapter(List<CartItem> cartItemList, Context context) {
        super(context, R.layout.shopping_list_layout, cartItemList);
        this.cartItemList = cartItemList;
        this.context = context;
    }

    @Override
    public String toString() {
//        return super.toString();
        return "I exist!";
    }

    private static class CartItemHolder{
        public TextView nameView;
        public TextView quantityView;
        public TextView quantityTypeView;
        public CheckBox checkBoxView;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        CartItemHolder holder = new CartItemHolder();

        if (convertView == null) {

            //nem pontosan értem, hogy ez mi, csak, hogy mit csinál
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.shopping_list_layout, null);
            holder.nameView = (TextView) v.findViewById(R.id.cartItem);
            holder.quantityView = (TextView) v.findViewById(R.id.cartItemQty);
            holder.quantityTypeView = (TextView) v.findViewById(R.id.cartItemQtyType);
            holder.checkBoxView = (CheckBox) v.findViewById(R.id.chk_box);

            holder.checkBoxView.setOnCheckedChangeListener((ShoppingListActivity) context);

        } else {
            holder = (CartItemHolder) v.getTag();
        }

        CartItem ci = cartItemList.get(position);
        holder.nameView.setText(ci.getName());
        holder.quantityView.setText("" + ci.getQuantity());
        holder.quantityTypeView.setText(ci.getQuantityType());
        holder.checkBoxView.setChecked(ci.isSelected());
        holder.checkBoxView.setTag(ci);

        return v;
    }
}
