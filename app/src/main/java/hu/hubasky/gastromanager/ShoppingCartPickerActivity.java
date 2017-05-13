package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hu.hubasky.gastromanager.viewmodel.ShoppingCart;
import hu.hubasky.gastromanager.viewmodel.ShoppingCartListAdapter;

public class ShoppingCartPickerActivity extends AppCompatActivity {

    private static final String TAG = "ShoppingCartPickerAct";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shclist);

        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView_shclist);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent shoppingListIntent = new Intent(ShoppingCartPickerActivity.this, ShoppingListActivity.class);
                //shoppingListIntent.putExtra(ID_EXTRA, String.valueOf(id));
                startActivity(shoppingListIntent);
            }
        });



        ShoppingCart a = new ShoppingCart("Tejszínes csirke", "Béla", "Gizike");
        ShoppingCart b = new ShoppingCart("Majorannás torta", "Béla", "Terka");
        ShoppingCart c = new ShoppingCart("Uzsonnás bütyök kékvér-lekvárral, ahogy a nagyanyám 10 éves kora előtt csinálta hajnalban mielőtt megetette a tyukokat a lánya helyett", "Béla", "Gabriella, Etelka");
        ShoppingCart d = new ShoppingCart("Hagymás torma", "Béla", "Gizike");
        ShoppingCart e = new ShoppingCart("Porhanyós porhamu", "Béla", "Gizike");
        ShoppingCart f = new ShoppingCart("Szentséges kenyér", "Gyulus", "Gizike");
        ShoppingCart g = new ShoppingCart("Kinyíró kenyér", "Gyulus", "Gizike");
        ShoppingCart h = new ShoppingCart("Kanyarós lapos kalács", "Béla", "Gizike");
        ShoppingCart i = new ShoppingCart("Kakaós tevepata", "Istvánus", "Gabriella, Etelka, jÁn0s1lel, nemszabolcs_bazsi123, szekptikussPolitikuss, dejóhosszúezafelhasználónév");
        ShoppingCart j = new ShoppingCart("Tekercses vakarcs", "Gyulus", "Gizike");
        ShoppingCart k = new ShoppingCart("Vakarós vakaró", "Béla", "Gizike");
        ShoppingCart l = new ShoppingCart("Huszonéves pipi", "Gyulus", "Gizike");
        ShoppingCart m = new ShoppingCart("Borban tartott bourbon", "Béla", "Etelka");
        ShoppingCart n = new ShoppingCart("Borleves", "Istvánus", "Gizike");
        ShoppingCart o = new ShoppingCart("Korhelyes korhelyleves", "Pistabá", "LoremIpsum");

        ArrayList<ShoppingCart> shcList = new ArrayList<>();

        shcList.add(a);
        shcList.add(b);
        shcList.add(c);
        shcList.add(d);
        shcList.add(e);
        shcList.add(f);
        shcList.add(g);
        shcList.add(h);
        shcList.add(i);
        shcList.add(j);
        shcList.add(k);
        shcList.add(l);
        shcList.add(m);
        shcList.add(n);
        shcList.add(o);



        ShoppingCartListAdapter adapter = new ShoppingCartListAdapter(this, R.layout.layout_shclist_details, shcList);
        mListView.setAdapter(adapter);


    }
}
