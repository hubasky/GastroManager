package hu.hubasky.gastromanager.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import hu.hubasky.gastromanager.R;


public class ReciepeAdapter extends BaseAdapter {

    private List<ReciepeVM> reciepes;
    private Context context;
    private RelativeLayout rootView;
    private ReciepeViewHolder holder;

    public CharSequence getSearchString() {
        return searchString;
    }

    public void setSearchString(CharSequence searchString) {
        this.searchString = searchString;
    }

    private CharSequence searchString;

    public ReciepeAdapter(List<ReciepeVM> reciepes, Context context) {
        this.reciepes = reciepes;
        this.context = context;
    }



    @Override
    public int getCount() {
        return reciepes != null ? reciepes.size() : 0;
    }

    @Override
    public ReciepeVM getItem(int position) {
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

        ReciepeVM reciepe = getItem(position);

        holder.titleText.setText(reciepe.getName());
        holder.descriptionText.setText(reciepe.getDescription());


        if(searchString != null){
            if(searchString.toString() == "" || reciepe.getName().contains(searchString.toString())) {
                rootView.setVisibility(View.VISIBLE);
            } else {
                rootView.setVisibility(View.INVISIBLE);
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
