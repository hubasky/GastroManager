package hu.hubasky.gastromanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.hubasky.gastromanager.viewmodel.ShopItem;
import hu.hubasky.gastromanager.viewmodel.ShoppingAdapter;

public class ShoppingListActivity extends AppCompatActivity implements
        android.widget.CompoundButton.OnCheckedChangeListener {

    ListView lv;
    ArrayList<ShopItem> shopItemList;
    ShoppingAdapter siAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_manager);

        lv = (ListView) findViewById(R.id.listview);
        displayShopItemList();
    }

    private void displayShopItemList() {

        shopItemList = new ArrayList<>(32);
        shopItemList.add(new ShopItem("Répa", 3, "csokor"));
        shopItemList.add(new ShopItem("Retek", 2, "csokor"));
        shopItemList.add(new ShopItem("Mogyoró", 1, "kiló"));
        shopItemList.add(new ShopItem("Korán", 30, "perc"));
        shopItemList.add(new ShopItem("Rigó", 7, "darab"));

        siAdapter = new ShoppingAdapter(shopItemList, this);
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
