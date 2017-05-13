package hu.hubasky.gastromanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
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


    ListView ingredientsListView;
    EditText descriptionEditText;

    List<Hozzavalo> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reciepe);

        descriptionEditText = (EditText) findViewById(R.id.edit_reciepe_description_text);
        ingredientsListView = (ListView) findViewById(R.id.edit_reciepe_ingredients_list);

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
        ingredientsListView.invalidate();

    }
}
