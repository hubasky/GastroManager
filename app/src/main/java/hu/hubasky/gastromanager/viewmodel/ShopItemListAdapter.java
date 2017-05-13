package hu.hubasky.gastromanager.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import hu.hubasky.gastromanager.R;
import hu.hubasky.gastromanager.ShopItemPickerActivity;

public class ShopItemListAdapter extends ArrayAdapter<ShopItem>{

    private List<ShopItem> shopItemtList;
    private Context context;

    public ShopItemListAdapter(List<ShopItem> shopItemtList, Context context) {
        super(context, R.layout.shopping_list_layout, shopItemtList);
        this.shopItemtList = shopItemtList;
        this.context = context;
    }

    private static class ShopItemHolder {
        TextView itemName;
        TextView qtyView;
        TextView qtyTypeView;
        CheckBox chkBox;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        ShopItemHolder holder = new ShopItemHolder();

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.shopping_list_layout, parent,false);

            holder.itemName = (TextView) v.findViewById(R.id.name);
            holder.qtyView = (TextView) v.findViewById(R.id.quantity);
            holder.qtyTypeView = (TextView) v.findViewById(R.id.quantityType);
            holder.chkBox = (CheckBox) v.findViewById(R.id.chk_box);

            holder.chkBox.setOnCheckedChangeListener((ShopItemPickerActivity) context);

        } else {
            holder = (ShopItemHolder) v.getTag();
        }

        ShopItem si = shopItemtList.get(position);
        holder.itemName.setText(si.getName());
        holder.qtyView.setText(si.getQuantity());
        holder.qtyTypeView.setText(si.getQuantityType());
        holder.chkBox.setChecked(si.isSelected());
        holder.chkBox.setTag(si);

        return v;
    }
}
