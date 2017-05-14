package hu.hubasky.gastromanager.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hu.hubasky.gastromanager.R;
import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;


public class ShopItemListBundleAdapter extends ArrayAdapter<BevasarloLista> {
    private static final String TAG = "ShopItemListBundleAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    public ShopItemListBundleAdapter(Context context, int resource, ArrayList<BevasarloLista> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    private static class ViewHolder{

        TextView name;
        TextView senderName;
        TextView sharedWith;

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getBevasarloListaNev();
//        String senderName = getItem(position).getTulajdonos().getNev();

//        ArrayList<Felhasznalo> tmp = new ArrayList<>(getItem(position).getSharedWith());
//        String sharedWith = "";
//        for (int i = 0; i < tmp.size(); i++) {
//            sharedWith = tmp.get(i).getNev() + " ";
//
//        }


        final View result;
        ViewHolder holder = new ViewHolder();

        if (convertView == null) {


            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.senderName = (TextView) convertView.findViewById(R.id.textView2_dyn);
            holder.sharedWith = (TextView) convertView.findViewById(R.id.textView3_dyn);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

//        result = convertView;


        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        //result.startAnimation(animation);


        lastPosition = position;

        holder.name.setText(name);
//        holder.senderName.setText(senderName);
//        holder.sharedWith.setText(sharedWith);


        return convertView;
    }

}

