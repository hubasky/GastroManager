package hu.hubasky.gastromanager;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;
import hu.hubasky.gastromanager.viewmodel.IngredientListAdapter;
import hu.hubasky.gastromanager.viewmodel.SingleTextAdapter;

import static android.R.attr.data;

public class AddIngredientActivity extends AppCompatActivity {

    private final int EDIT_INGREDIENT_REQUEST = 1001;

    Context self = this;

    private ListView ingredientsListView;
    private EditText searchFieldEditText;
    private Button cancelButton;
    private Button newButton;
    private Button okButton;
    private EditText quantityEditText;
    private Spinner unitTypeSpinner;

    private List<Alapanyag> ingredients;
    private List<String> spinnerUnits;

    private final List<String> weightUnits = new ArrayList<String>() {{
        add("g");
        add("dkg");
        add("kg");}};

    private final List<String> liquidUnits = new ArrayList<String>() {{
        add("ml");
        add("dl");
        add("l");}};

    private final List<String> pieceUnits = new ArrayList<String>() {{
        add("db");}};

    Alapanyag selectedIngredient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        ingredientsListView = (ListView) findViewById(R.id.edit_ingredient_ingredients_list);
        searchFieldEditText = (EditText) findViewById(R.id.edit_ingredient_search_edit_text);
        cancelButton = (Button) findViewById(R.id.edit_ingredient_cancel_button);
        newButton = (Button) findViewById(R.id.edit_ingredient_new_button);
        okButton = (Button) findViewById(R.id.edit_ingredient_ok_button);
        quantityEditText = (EditText) findViewById(R.id.edit_ingredient_quantity_edit_text);
        unitTypeSpinner = (Spinner) findViewById(R.id.edit_ingredient_unit_type_spinner);

        ingredients = new ArrayList<>();
        spinnerUnits = new ArrayList<>();

        final IngredientListAdapter ingredientAdapter = new IngredientListAdapter(ingredients, self);
        ingredientsListView.setAdapter(ingredientAdapter);

        ingredientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                selectIngredient(position);
            }
        });

        ingredientsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                Alapanyag ingredient = selectIngredient(position);

                Intent editIngredient = new Intent(self, EditIngredientActivity.class);
                editIngredient.putExtra("ingredient", ingredient.getUniqueKey());
                startActivityForResult(editIngredient, 1001);

                return true;
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIngredient = new Intent(self, EditIngredientActivity.class);
                startActivityForResult(editIngredient, EDIT_INGREDIENT_REQUEST);

                refreshIngredients();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshIngredients();
        // TODO: Szerkesztesbol visszateresnel a spinner nem updatelodik.
    }

    private Alapanyag selectIngredient(int position) {
        selectedIngredient = (Alapanyag) ingredientsListView.getItemAtPosition(position);

        if (selectedIngredient != null) {
            switch (selectedIngredient.getMennyisegiEgyseg()) {
                case GRAMM:
                    spinnerUnits = weightUnits;
                    break;
                case LITER:
                    spinnerUnits = liquidUnits;
                    break;
                case DARAB:
                    spinnerUnits = pieceUnits;
                    break;
            }
        } else {
            spinnerUnits = new ArrayList<String>();
        }

        SingleTextAdapter spinnerAdapter = new SingleTextAdapter(spinnerUnits);
        unitTypeSpinner.setAdapter(spinnerAdapter);
        unitTypeSpinner.invalidate();
        return selectedIngredient;
    }

    private void refreshIngredients() {
        AlapanyagKeresesiJellemzok.Builder akjb = new AlapanyagKeresesiJellemzok.Builder();
        akjb.mindetTartalmazza(true);

        Controls.getInstance().getAlapanyagNyilvantarto().keres(akjb.build(), new ControlResultListener<Alapanyag>() {
            @Override
            public void onSuccess(List<Alapanyag> resultList) {
                /*
                ingredients = resultList;
                IngredientListAdapter adapter = new IngredientListAdapter(resultList, self);
                ingredientsListView.setAdapter(adapter);
                ingredientsListView.invalidate();
                */
                if (resultList != null && resultList.size() == 1) {
                    Alapanyag ingredient = resultList.get(0);
                    boolean found = false;
                    int index = 0;
                    for (Alapanyag i : ingredients) {
                        if (i.getUniqueKey().equals(ingredient.getUniqueKey())) {
                            found = true;
                            break;
                        }
                        index++;
                    }
                    if (found) {
                        ingredients.set(index, ingredient);
                    } else {
                        ingredients.add(ingredient);
                    }
                    ((BaseAdapter)ingredientsListView.getAdapter()).notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Exception ex) {
                Toast.makeText(self, "@string/db_error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
