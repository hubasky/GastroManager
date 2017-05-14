package hu.hubasky.gastromanager.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import hu.hubasky.gastromanager.R;

public class SingleTextAdapter extends BaseAdapter {

    List<String> data;

    public SingleTextAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public String getItem(int position) {
        return data != null ? data.get(position) : "";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout rootView = (LinearLayout) convertView;
        SingleTextViewHolder holder = new SingleTextViewHolder();

        if (rootView == null) {
            rootView = (LinearLayout) View.inflate(parent.getContext(), R.layout.single_text_item_layout, null);

            holder.text = (TextView) rootView.findViewById(R.id.single_text_item);
            rootView.setTag(holder);

        } else {
            holder = (SingleTextViewHolder) rootView.getTag();
        }

        holder.text.setText(getItem(position));
        return rootView;
    }

    private static class SingleTextViewHolder {
        TextView text;
    }
}
