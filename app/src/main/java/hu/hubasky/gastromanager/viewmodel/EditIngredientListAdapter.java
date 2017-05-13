package hu.hubasky.gastromanager.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hu.hubasky.gastromanager.R;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;


public class EditIngredientListAdapter extends BaseAdapter {
    private List<Hozzavalo> ingredientsList;

    public EditIngredientListAdapter(List<Hozzavalo> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @Override
    public int getCount() {
        return ingredientsList != null ? ingredientsList.size() : 0;
    }

    @Override
    public Hozzavalo getItem(int position) {
        return ingredientsList != null ? ingredientsList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;

        if (rootView == null) {
            rootView = View.inflate(parent.getContext(), R.layout.ingredient_listitem_layout, null);
        }

        Hozzavalo ingredient = getItem(position);

        TextView quantity = (TextView) rootView.findViewById(R.id.edit_reciepe_ingredient_quantity);
        TextView name = (TextView) rootView.findViewById(R.id.edit_reciepe_ingredient_name);

        quantity.setText(ingredient.displayQuantity());
        name.setText(ingredient.getAlapanyag().getNeve());

        return rootView;
    }
}
