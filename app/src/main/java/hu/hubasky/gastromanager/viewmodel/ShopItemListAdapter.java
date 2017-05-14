package hu.hubasky.gastromanager.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.R;
import hu.hubasky.gastromanager.ShopItemPickerActivity;
import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;

public class ShopItemListAdapter extends ArrayAdapter<VasarlandoAlapanyag>{

    private List<VasarlandoAlapanyag> shopItemtList;
    private Context context;
    private static final String TAG = "ShopItemListAdapter";

    public ShopItemListAdapter(List<VasarlandoAlapanyag> shopItemtList, Context context) {
        super(context, R.layout.layout_shopitem, shopItemtList);
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
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {



        View v = convertView;

        ShopItemHolder holder = new ShopItemHolder();

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.layout_shopitem, parent,false);

            holder.itemName = (TextView) v.findViewById(R.id.name);
            holder.qtyView = (TextView) v.findViewById(R.id.quantity);
            holder.qtyTypeView = (TextView) v.findViewById(R.id.quantityType);
            holder.chkBox = (CheckBox) v.findViewById(R.id.chk_box);

            holder.chkBox.setOnCheckedChangeListener((ShopItemPickerActivity) context);

            v.setTag(holder);

        } else {
            holder = (ShopItemHolder) v.getTag();
        }


        VasarlandoAlapanyag si = shopItemtList.get(position);
        holder.itemName.setText(si.getAlapanyag().getNeve());
        holder.qtyView.setText(Double.toString(si.getMennyiseg()));
        holder.qtyTypeView.setText(si.displayQuantity());
        holder.chkBox.setChecked(si.getStatus());
        holder.chkBox.setTag(si);

        return v;
    }
}
