package hu.hubasky.gastromanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.viewmodel.EditIngredientListAdapter;

public class EditReciepeActivity extends AppCompatActivity {

    private final AppCompatActivity self = this;
    private final int ADD_INGREDIENT_REQUEST = 1000;

    ListView ingredientsListView;
    EditText descriptionEditText;
    Button addIngredient;

    List<Hozzavalo> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reciepe);

        final Context self = this;

        descriptionEditText = (EditText) findViewById(R.id.edit_reciepe_description_text);
        ingredientsListView = (ListView) findViewById(R.id.edit_reciepe_ingredients_list);
        addIngredient = (Button) findViewById(R.id.edit_reciepe_add_ingredient);

        ingredients = new ArrayList<>();
        ingredients.add(new Hozzavalo(1020,
                new Alapanyag(EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 0.1, 0.123, 0.56, 0.01),
                        "liszt",
                        100)));
        ingredients.add(new Hozzavalo(250,
                new Alapanyag(EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 0.01, 0.23, 0.16, 0.81),
                        "vaj",
                        100)));

        EditIngredientListAdapter ingredientAdapter = new EditIngredientListAdapter(ingredients);
        ingredientsListView.setAdapter(ingredientAdapter);


        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingredientEditIntent = new Intent(self, AddIngredientActivity.class);
                startActivityForResult(ingredientEditIntent, ADD_INGREDIENT_REQUEST);
            }
        });

    }
}
