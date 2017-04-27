package hu.hubasky.gastromanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hu.hubasky.gastromanager.viewmodel.IngredientVM;
import hu.hubasky.gastromanager.viewmodel.ReciepeAdapter;
import hu.hubasky.gastromanager.viewmodel.ReciepeVM;

public class ReciepeManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciepe_manager);

        List<ReciepeVM> reciepesList = new ArrayList<ReciepeVM>();
        reciepesList.add(new ReciepeVM("Recept 1", "Leírás 1", new ArrayList<IngredientVM>()));
        reciepesList.add(new ReciepeVM("Recept 2", "Leírás 2", new ArrayList<IngredientVM>()));
        reciepesList.add(new ReciepeVM("Recept 3", "Leírás 3", new ArrayList<IngredientVM>()));
        reciepesList.add(new ReciepeVM("Recept 4", "Leírás 4", new ArrayList<IngredientVM>()));
        reciepesList.add(new ReciepeVM("Recept 5", "Leírás 5", new ArrayList<IngredientVM>()));

        final ReciepeAdapter reciepeAdapter = new ReciepeAdapter(reciepesList);
        ListView reciepeListView = (ListView) findViewById(R.id.reciepe_list);

//        reciepeListView.setAdapter(reciepeAdapter);

    }
}
