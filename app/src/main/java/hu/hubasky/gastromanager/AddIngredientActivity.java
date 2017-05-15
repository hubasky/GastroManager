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
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.viewmodel.IngredientListAdapter;
import hu.hubasky.gastromanager.viewmodel.SingleTextAdapter;
import hu.hubasky.gastromanager.viewmodel.SwipeDismissListViewTouchListener;

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
    private String passedUID;

    private IngredientListAdapter ingredientListAdapter;

    boolean newIngredient = false;
    Recept reciepe;
    Hozzavalo ingredient;

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

        //UID (most név) elkapása - ehhez a listához adjuk majd hozzá az összetevőt
        passedUID = Intent.EXTRA_UID;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        ingredientsListView = (ListView) findViewById(R.id.edit_ingredient_ingredients_list);
        searchFieldEditText = (EditText) findViewById(R.id.edit_ingredient_search_edit_text);
        cancelButton = (Button) findViewById(R.id.edit_ingredient_cancel_button);
        newButton = (Button) findViewById(R.id.edit_ingredient_new_button);
        okButton = (Button) findViewById(R.id.edit_ingredient_ok_button);
        quantityEditText = (EditText) findViewById(R.id.edit_ingredient_quantity_edit_text);
        unitTypeSpinner = (Spinner) findViewById(R.id.edit_ingredient_unit_type_spinner);


        newIngredient = this.getIntent().getBooleanExtra("new_ingredient", false);
        final String reciepeUniqueKey = this.getIntent().getStringExtra("reciepe_uniqueKey");
        final String ingredientUniqueKey = this.getIntent().getStringExtra("ingredient_uniqueKey");

        if (!newIngredient) {
            ingredientsListView.setEnabled(false);
        }

        Controls.getInstance().getReceptNyilvantarto().keres(null, new ControlResultListener<Recept>() {
            @Override
            public void onSuccess(List<Recept> resultList) {
                for (Recept r : resultList) {
                    if (r.getUniqueKey().equals(reciepeUniqueKey)) {
                        reciepe = r;
                        break;
                    }
                }

                if (!newIngredient) {
                    for (Hozzavalo i : reciepe.getHozzavalok()) {
                        if (ingredientUniqueKey.equals(i.getAlapanyag().getUniqueKey())) {
                            ingredient = i;
                            break;
                        }
                    }

                    quantityEditText.setText(String.valueOf((int) ingredient.getMennyiseg()));
                    selectIngredient(ingredients.indexOf(ingredient.getAlapanyag()));
                }
            }

            @Override
            public void onFailed(Exception ex) {

            }
        });

        Controls.getInstance().getAlapanyagNyilvantarto().keres(null, new ControlResultListener<Alapanyag>() {
            @Override
            public void onSuccess(List<Alapanyag> resultList) {
                ingredients = resultList;
                ingredientListAdapter = new IngredientListAdapter(ingredients, self);
                ingredientsListView.setAdapter(ingredientListAdapter);
            }

            @Override
            public void onFailed(Exception ex) {

            }
        });

        spinnerUnits = new ArrayList<>();
        ingredients = new ArrayList<>();

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




        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
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

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newIngredient) {
                    ingredient = new Hozzavalo(
                            getQuantity(),
                            selectedIngredient
                    );
                    reciepe.addHozzavalo(ingredient);
                } else {
                    ingredient.setMennyiseg(getQuantity());
                }
                try {
                    Controls.getInstance().getReceptNyilvantarto().tarolas(reciepe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
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
                ((BaseAdapter)ingredientsListView.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception ex) {
                Toast.makeText(self, "@string/db_error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double getQuantity() {
        double result = Double.parseDouble(quantityEditText.getText().toString());
        String selectedUnitType = (String) unitTypeSpinner.getSelectedItem();

        if (selectedUnitType != null) {
            switch (selectedUnitType) {
                case "dkg": result = result * 10;
                    break;
                case "kg": result = result * 1000;
                    break;
                case "ml": result = result / 1000;
                    break;
                case "dl": result = result / 100;
            }
        }
        return result;
    }
}
