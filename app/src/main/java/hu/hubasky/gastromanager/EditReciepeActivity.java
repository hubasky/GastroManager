package hu.hubasky.gastromanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.control.impl.firebase.FirebaseAccess;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.entity.recept.ReceptKeresesiJellemzok;
import hu.hubasky.gastromanager.viewmodel.EditIngredientListAdapter;
import hu.hubasky.gastromanager.viewmodel.ReciepeAdapter;
import hu.hubasky.gastromanager.viewmodel.SwipeDismissListViewTouchListener;

import static android.content.Intent.*;

public class EditReciepeActivity extends AppCompatActivity {

    private final AppCompatActivity self = this;
    private final int ADD_INGREDIENT_REQUEST = 1000;

    ListView ingredientsListView;
    EditText descriptionEditText;
    EditText titleEditText;
    EditText portionEditText;
    Button btnaddIngredient;
    Button btncancelEdit;
    Button btnsaveEdit;


    boolean newReciepe;
    final List<Hozzavalo> ingredients = new ArrayList<Hozzavalo>();

    Recept reciepe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reciepe);

        final Context self = this;

        initializeFields();

        EditIngredientListAdapter ingredientAdapter = new EditIngredientListAdapter(ingredients);
        ingredientsListView.setAdapter(ingredientAdapter);

        final String uniqueKey = this.getIntent().getStringExtra("recipe_extra");
        newReciepe = this.getIntent().getBooleanExtra("newReciepe", false);

        Controls.getInstance().getReceptNyilvantarto().keres(null, new ControlResultListener<Recept>() {
            @Override
            public void onSuccess(List<Recept> resultList) {
                for (Recept r : resultList) {
                    if (r.getUniqueKey().equals(uniqueKey)) {
                        reciepe = r;
                        updateReciepeFields();
                        break;
                    }
                }
            }

            @Override
            public void onFailed(Exception ex) {
                Log.d("EditReciepeActivity", getString(R.string.db_error));
            }
        });

        ingredientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ingredientEditIntent = new Intent(self, AddIngredientActivity.class);
                ingredientEditIntent.putExtra("reciepe_uniqueKey", reciepe.getUniqueKey());
                ingredientEditIntent.putExtra("new_ingredient", false);
                ingredientEditIntent.putExtra("ingredient_uniqueKey", ingredients.get(position).getAlapanyag().getUniqueKey());
                startActivity(ingredientEditIntent);
            }
        });

        btnaddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingredientEditIntent = new Intent(self, AddIngredientActivity.class);
                ingredientEditIntent.putExtra("reciepe_uniqueKey", reciepe.getUniqueKey());
                ingredientEditIntent.putExtra("new_ingredient", true);
                startActivity(ingredientEditIntent);
            }
        });

        btncancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newReciepe)
                    try {
                        Controls.getInstance().getReceptNyilvantarto().torles(reciepe);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                finish();
            }
        });

        btnsaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reciepe.setNeve(titleEditText.getText().toString());
                reciepe.setLeirasa(descriptionEditText.getText().toString());
                reciepe.setAdag(Double.parseDouble(portionEditText.getText().toString()));

                try {
                    Controls.getInstance().getReceptNyilvantarto().tarolas(reciepe);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        ingredientsListView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    reciepe.remHozzavalo(ingredients.get(position).getAlapanyag());
                                    ingredients.remove(position);
                                    ((BaseAdapter)ingredientsListView.getAdapter()).notifyDataSetChanged();
                                }
                            }
                        });
        ingredientsListView.setOnTouchListener(touchListener);
    }

    private void initializeFields() {
        descriptionEditText = (EditText) findViewById(R.id.edit_reciepe_description_text);
        titleEditText = (EditText) findViewById(R.id.edit_reciepe_title_text);
        ingredientsListView = (ListView) findViewById(R.id.edit_reciepe_ingredients_list);
        btnaddIngredient = (Button) findViewById(R.id.edit_reciepe_add_ingredient);
        btncancelEdit = (Button) findViewById(R.id.edit_reciepe_cancel_button);
        btnsaveEdit = (Button) findViewById(R.id.edit_reciepe_save_button);
        portionEditText = (EditText) findViewById(R.id.edit_reciepe_portion_edit_text);
    }

    private void updateReciepeFields() {
        if (reciepe != null) {
            initializeFields();
            titleEditText.setText(reciepe.getNeve());
            descriptionEditText.setText(reciepe.getLeirasa());
            portionEditText.setText(String.valueOf((int) reciepe.getAdag()));
            ingredients.clear();
            for (Hozzavalo i : reciepe.getHozzavalok()) {
                ingredients.add(i);
            }
            ((BaseAdapter)ingredientsListView.getAdapter()).notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (newReciepe)
            try {
                Controls.getInstance().getReceptNyilvantarto().torles(reciepe);
            } catch (Exception e) {
                e.printStackTrace();
            }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateReciepeFields();
    }
}
