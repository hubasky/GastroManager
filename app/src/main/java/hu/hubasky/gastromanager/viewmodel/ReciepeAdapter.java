package hu.hubasky.gastromanager.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import hu.hubasky.gastromanager.R;
import hu.hubasky.gastromanager.entity.recept.Recept;


public class ReciepeAdapter extends BaseAdapter {

    private List<Recept> reciepes;
    private Context context;
    private RelativeLayout rootView;
    private ReciepeViewHolder holder;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    private String searchString;

    public ReciepeAdapter(List<Recept> reciepes, Context context) {
        this.reciepes = reciepes;
        this.context = context;
    }



    @Override
    public int getCount() {
        return reciepes != null ? reciepes.size() : 0;
    }

    @Override
    public Recept getItem(int position) {
        return reciepes != null ? reciepes.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rootView = (RelativeLayout) convertView;

        holder = new ReciepeViewHolder();

        if (rootView == null) {
            // rootView = (RelativeLayout) View.inflate(parent.getContext(), R.layout.reciepe_list_layout, null);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = (RelativeLayout) inflater.inflate(R.layout.reciepe_list_layout, parent, false);

            holder.titleText = (TextView) rootView.findViewById(R.id.reciepe_title);
            holder.descriptionText = (TextView) rootView.findViewById(R.id.reciepe_abstract);
            rootView.setTag(holder);
        } else {
            holder = (ReciepeViewHolder) rootView.getTag();
        }

        Recept reciepe = getItem(position);

        holder.titleText.setText(reciepe.getNeve());
        holder.descriptionText.setText((String.valueOf((int) reciepe.getAdag()) + " adag"));


        if(searchString != null){
            if(searchString.equals("") || reciepe.getNeve().toLowerCase().contains(searchString.toLowerCase())) {
                rootView.setVisibility(View.VISIBLE);
            } else {
                rootView.setVisibility(View.GONE);
            }
        } else {
                rootView.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    private static class ReciepeViewHolder {
        TextView titleText;
        TextView descriptionText;
    }
}
