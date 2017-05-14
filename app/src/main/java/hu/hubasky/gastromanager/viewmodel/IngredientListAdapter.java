package hu.hubasky.gastromanager.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import hu.hubasky.gastromanager.R;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;

public class IngredientListAdapter extends BaseAdapter {

    private List<Alapanyag> ingredients;
    private Context context;

    public IngredientListAdapter(List<Alapanyag> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ingredients == null ? 0 : ingredients.size();
    }

    @Override
    public Alapanyag getItem(int position) {
        return ingredients == null ? null : ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout rootView = (LinearLayout) convertView;
        IngredientViewHolder holder = new IngredientViewHolder();

        if (rootView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = (LinearLayout) inflater.inflate(R.layout.single_text_item_layout, parent, false);
            holder.name = (TextView) rootView.findViewById(R.id.single_text_item);
            rootView.setTag(holder);
        } else {
            holder = (IngredientViewHolder) rootView.getTag();
        }

        holder.name.setText(getItem(position).getNeve());

        return rootView;
    }

    private static class IngredientViewHolder {
        TextView name;
    }
}
