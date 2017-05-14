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

import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
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

    public static final String EXTRA_INVALIDATEVISUAL = "";
    ListView ingredientsListView;
    EditText descriptionEditText;
    Button btnaddIngredient;
    Button btncancelEdit;
    Button btnsaveEdit;

    List<Hozzavalo> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reciepe);

        final Context self = this;

        descriptionEditText = (EditText) findViewById(R.id.edit_reciepe_description_text);
        ingredientsListView = (ListView) findViewById(R.id.edit_reciepe_ingredients_list);
        btnaddIngredient = (Button) findViewById(R.id.edit_reciepe_add_ingredient);
        btncancelEdit = (Button) findViewById(R.id.edit_reciepe_cancel_button);
        btnsaveEdit = (Button) findViewById(R.id.edit_reciepe_save_button);

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

        final EditIngredientListAdapter ingredientAdapter = new EditIngredientListAdapter(ingredients);
        ingredientsListView.setAdapter(ingredientAdapter);



//
//        ReceptKeresesiJellemzok.Builder apkj = new ReceptKeresesiJellemzok.Builder();
//        apkj.
//
//        Controls.getInstance().getAlapanyagNyilvantarto().keres(apkj.build(), new ControlResultListener<Alapanyag>() {
//            @Override
//            public void onSuccess(List<Alapanyag> resultList) {
//                for (Alapanyag a : resultList) {
//                    if (a.getUniqueKey().equals(id)) {
//                        self.updateFields(a);
//                        break;
//                    }
//                }
//            }




        btnaddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingredientEditIntent = new Intent(self, AddIngredientActivity.class);
                startActivityForResult(ingredientEditIntent, ADD_INGREDIENT_REQUEST);
            }
        });


        btncancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnsaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Firebase-be pusholni a szarokat

//                Recept r = new Recept()


                String id = getIntent().getStringExtra(ReciepeManagerActivity.EXTRA_RECIPE);


//                if (id != null) {
//                    result.setUniqueKey(id);
//                }
//
//                try {
//                    Controls.getInstance().getAlapanyagNyilvantarto().tarolas(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }




                Intent backToRecipeManagerIntent = new Intent(self, ReciepeManagerActivity.class);
                backToRecipeManagerIntent.putExtra(Controls.EXTRA_INVALIDATEVISUAL, true);
                startActivity(backToRecipeManagerIntent);
                finish();
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


//                                    lv.requestLayout();
                                    ingredients.remove(position);
                                    ingredientAdapter.notifyDataSetChanged();


                                }


                            }
                        });
        ingredientsListView.setOnTouchListener(touchListener);

    }
}
