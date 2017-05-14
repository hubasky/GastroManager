package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.viewmodel.IngredientVM;
import hu.hubasky.gastromanager.viewmodel.ReciepeAdapter;
import hu.hubasky.gastromanager.viewmodel.ReciepeVM;

public class ReciepeManagerActivity extends AppCompatActivity {

    private static final String TAG = "ReciepeManagerActivity";

    private Button addReciepeButton;
    private final AppCompatActivity self = this;
    private boolean invalidateVisual;
    public static final String EXTRA_RECIPE = "recipe_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciepe_manager);

        invalidateVisual = getIntent().getBooleanExtra(Controls.EXTRA_INVALIDATEVISUAL, true);

        Log.d(TAG, "onCreate: INVALIDATEVISUAL:" + Boolean.toString(invalidateVisual));

        List<ReciepeVM> reciepesList = new ArrayList<ReciepeVM>();
        Random rand = new Random();
        for(int i = 1; i < 15; i++) {
            reciepesList.add(new ReciepeVM("Fincsi hamcsi recept " + i,
                    "Elkészítési idő: " + rand.nextInt(120) +" perc\nAdagok száma: 4\nFűszeres, illatos pihi puhi csibehusi, gyerekek és felnőttek kedvence.",
                    new ArrayList<IngredientVM>()));
        }

        final ReciepeAdapter reciepeAdapter = new ReciepeAdapter(reciepesList, this);
        ListView reciepeListView = (ListView) findViewById(R.id.reciepe_list);

        reciepeListView.setAdapter(reciepeAdapter);

        addReciepeButton = (Button) findViewById(R.id.add_reciepe_btn);
        addReciepeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recept r = new Recept(EReceptStatus.MEGOSZTOTT, "Írja ide a címet", "Leírás", "Kép URL", 4);

                Intent editReciepeIntent = new Intent(self, EditReciepeActivity.class);
                editReciepeIntent.putExtra(EXTRA_RECIPE, r.getUniqueKey());
                startActivity(editReciepeIntent);
            }
        });

        if (invalidateVisual) {
            reciepeAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Controls.getInstance().setActualContext(this);
    }
}
