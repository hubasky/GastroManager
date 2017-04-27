package hu.hubasky.gastromanager.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import hu.hubasky.gastromanager.R;

/**
 * Created by Peet on 2017. 04. 27..
 */

public class ReciepeAdapter extends BaseAdapter {

    private List<ReciepeVM> reciepes;

    public ReciepeAdapter(List<ReciepeVM> reciepes) {
        this.reciepes = reciepes;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout rootView = (RelativeLayout) convertView;

        if (rootView == null) {
            rootView = (RelativeLayout) View.inflate(parent.getContext(), R.layout.reciepe_list_layout, null);
        }

        ReciepeVM reciepe = getItem(position);

        TextView titleText = (TextView) rootView.findViewById(R.id.reciepe_title);
        TextView descriptionText = (TextView) rootView.findViewById(R.id.reciepe_abstract);


        return null;
    }
}
