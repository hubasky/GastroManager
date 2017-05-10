package hu.hubasky.gastromanager;

import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "ShoppingListActivity";

    ListView lv;
    ArrayList<CartItem> cartItemsList;
    ShoppingAdapter shoppingAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_manager);



        lv = (ListView) findViewById(R.id.listview_cartItem);
        //itt miért nem jön létre a ojjektum?
        Log.d(TAG, "onCreate: listview is created with tag: " + lv.getTag());

        displayShoppingList();

    }

    private void displayShoppingList() {

        cartItemsList = new ArrayList<CartItem>();
        cartItemsList.add(new CartItem("Répa", 3, "csokor"));
        cartItemsList.add(new CartItem("Retek", 2, "csokor"));
        cartItemsList.add(new CartItem("Mogyoró", 100, "g"));
        cartItemsList.add(new CartItem("Korán", 10, "perc"));
        cartItemsList.add(new CartItem("Rigó", 1, "db"));

        shoppingAdapter = new ShoppingAdapter(cartItemsList, this);
        Log.d(TAG, "displayShoppingList: listview tag = " + lv.getTag());
        lv.setAdapter(shoppingAdapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION){
            CartItem ci = cartItemsList.get(pos);
            ci.setSelected(isChecked);

            Toast.makeText(this, "Clicked on item:" + ci.getName() + ". Status: " + isChecked, Toast.LENGTH_SHORT).show();

        }
    }
}
