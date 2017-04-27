package hu.hubasky.gastromanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.hubasky.gastromanager.viewmodel.IngredientVM;
import hu.hubasky.gastromanager.viewmodel.ReciepeAdapter;
import hu.hubasky.gastromanager.viewmodel.ReciepeVM;

public class ReciepeManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciepe_manager);

        List<ReciepeVM> reciepesList = new ArrayList<ReciepeVM>();
        Random rand = new Random();
        for(int i = 1; i < 15; i++) {
            reciepesList.add(new ReciepeVM("Fincsi hamcsi recept " + i,
                    "Elkészítési idő: " + rand.nextInt(120) +" perc\nAdagok száma: 4\nFűszeres, illatos pihi puhi csibehusi, gyerekek és felnőttek kedvence.",
                    new ArrayList<IngredientVM>()));
        }

        final ReciepeAdapter reciepeAdapter = new ReciepeAdapter(reciepesList);
        ListView reciepeListView = (ListView) findViewById(R.id.reciepe_list);

        reciepeListView.setAdapter(reciepeAdapter);

    }
}
