package hu.hubasky.gastromanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.hubasky.gastromanager.viewmodel.ShopItem;
import hu.hubasky.gastromanager.viewmodel.ShopItemListAdapter;

public class ShopItemPickerActivity extends AppCompatActivity implements
        android.widget.CompoundButton.OnCheckedChangeListener {


    private static final String TAG = "ShopItemPickerActivity";
    ListView lv;
    TextView tv;
    ArrayList<ShopItem> shopItemList; //ezt kell firebase-ről lekapni ID alapján!
    ShopItemListAdapter siAdapter;

    String passedName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_manager);

        lv = (ListView) findViewById(R.id.listview);
        tv = (TextView) findViewById(R.id.textView_shhoppingListLabel);

        passedName = getIntent().getStringExtra(ShopItemListPickerActivity.EXTRA_NAME); //befejezni!
        shopItemList = getIntent().getParcelableArrayListExtra(ShopItemListPickerActivity.EXTRA_CONTENT);

//        for (int i = 0; i < shopItemList.size(); i++) {
//            Log.d(TAG, "onCreate - " + i + ". item in list: " + shopItemList.get(i).getName());
//            Log.d(TAG, "other info: " + shopItemList.get(i).getQuantity() + ", " +shopItemList.get(i).getQuantityType() );
//            Log.d(TAG, "Selection info: " +  shopItemList.get(i).isSelected());
//
//        }


        displayShopItemList();
    }

    private void displayShopItemList() {

        tv.setText(passedName);
//        shopItemList = new ArrayList<>(32);
//        //ID extra alapján lekérni a cuccokat a listába, meg a labelt
//        Log.d(TAG, "displayShopItemList: Innen jövünk!");
//        shopItemList.add(new ShopItem("Répa", 3, "csokor"));
//        shopItemList.add(new ShopItem("Retek", 2, "csokor"));
//        shopItemList.add(new ShopItem("Mogyoró", 1, "kiló"));
//        shopItemList.add(new ShopItem("Korán", 30, "perc"));
//        shopItemList.add(new ShopItem("Rigó", 7, "darab"));
//        Log.d(TAG, "displayShopItemList: Hozzávalók hozzáadva!");

        siAdapter = new ShopItemListAdapter(shopItemList, this);
        lv.setAdapter(siAdapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int pos = lv.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION) {
            ShopItem p = shopItemList.get(pos);
            p.setSelected(isChecked);

            Toast.makeText(
                    this,
                    "Clicked on shopitem: " + p.getName() + ". State: is "
                            + isChecked, Toast.LENGTH_SHORT).show();
        }
    }
}
