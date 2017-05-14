package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.hubasky.gastromanager.entity.bevlist.VasarlandoAlapanyag;
import hu.hubasky.gastromanager.viewmodel.ShopItemListAdapter;
import hu.hubasky.gastromanager.viewmodel.SwipeDismissListViewTouchListener;

public class ShopItemPickerActivity extends AppCompatActivity implements
        android.widget.CompoundButton.OnCheckedChangeListener {


    private Button addShopItemButton;
    private final AppCompatActivity self = this;

    private static final String TAG = "ShopItemPickerActivity";
    private ListView lv;
    private TextView tv;

    private ArrayList<VasarlandoAlapanyag> kosar;
    private ShopItemListAdapter siAdapter;
    public static final String EXTRA_ID = "hu.hubasky.gastromanager._ID";

    private String passedID;

    String passedName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_shopitem_picker);

        lv = (ListView) findViewById(R.id.listview);
        tv = (TextView) findViewById(R.id.textView_shhoppingListLabel);

        passedName = getIntent().getStringExtra(ShopItemListPickerActivity.EXTRA_NAME);
        passedID = getIntent().getStringExtra(ShopItemListPickerActivity.EXTRA_ID);
        //loggedinUsrID = getIntent().getStringExtra(Controls.EXTRA_loggedinUsrID);

        //TODO: Firebase-ből lekérni, különben null lesz!

        //kivételesen elkapjuk a nullt, hogy ne kelljen visszanyomozni idáig - úgyis hibára fut
        if (kosar == null) {

            kosar = new ArrayList<>(32);

//            throw new NullPointerException("Nullpointer: hiányzik az arraylist a shopitem objektumból!");
        }

        for (int i = 0; i < kosar.size(); i++) {
            Log.d(TAG, "onCreate: " +  kosar.get(i).getAlapanyag().getNeve());

        }

//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                kosar.remove(position);
//                siAdapter.notifyDataSetChanged();
//                lv.requestLayout();
//                return true;
//            }
//        });
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        lv,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {


//                                    lv.requestLayout();
//                                    kosar.remove(position);
//                                    siAdapter.notifyDataSetChanged();
                                    deleteShopItem(position);

                                }


                            }
                        });
        lv.setOnTouchListener(touchListener);



        //HOZZÁADÓ GOMB
        addShopItemButton = (Button) findViewById(R.id.add_shopitem_btn);
        addShopItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addShopItem = new Intent(self, AddIngredientActivity.class);

                addShopItem.putExtra(EXTRA_ID, passedID);
                startActivity(addShopItem);

            }
        });




        //this.play (Display)
        tv.setText(passedName);
        siAdapter = new ShopItemListAdapter(kosar, this);
        lv.setAdapter(siAdapter);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int pos = lv.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION) {
            VasarlandoAlapanyag p = kosar.get(pos);
            p.setStatus(isChecked);

            Toast.makeText(
                    this,
                    "Clicked on shopitem: " + p.getAlapanyag().getNeve() + ". State: is "
                            + isChecked, Toast.LENGTH_SHORT).show();
        }
    }

    public int deleteShopItem(int position){

        //TODO: firebase implementáció
        if(position<= kosar.size()) {
            kosar.remove(position);
            siAdapter.notifyDataSetChanged();
            return 0;

        }else{
            return -1;
        }
    }

    public void deleteAll(){
        //TODO: firebase implementáció
        for (int i = 0; i < kosar.size(); i++) {
            kosar.clear();
        }

        siAdapter.notifyDataSetChanged();
    }

}
