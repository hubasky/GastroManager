package hu.hubasky.gastromanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.hubasky.gastromanager.viewmodel.ShopItem;
import hu.hubasky.gastromanager.viewmodel.ShopItemListAdapter;
import hu.hubasky.gastromanager.viewmodel.SwipeDismissListViewTouchListener;

public class ShopItemPickerActivity extends AppCompatActivity implements
        android.widget.CompoundButton.OnCheckedChangeListener {


    private Button addShopItemButton;
    private final AppCompatActivity self = this;

    private static final String TAG = "ShopItemPickerActivity";
    private ListView lv;
    private TextView tv;

    private ArrayList<ShopItem> shopItemList;
    private ShopItemListAdapter siAdapter;

    String passedName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_shopitem_picker);

        lv = (ListView) findViewById(R.id.listview);
        tv = (TextView) findViewById(R.id.textView_shhoppingListLabel);

        passedName = getIntent().getStringExtra(ShopItemListPickerActivity.EXTRA_NAME); //befejezni!


        shopItemList = getIntent().getParcelableArrayListExtra(ShopItemListPickerActivity.EXTRA_CONTENT);

        //kivételesen elkapjuk a nullt, hogy ne kelljen visszanyomozni idáig - úgyis hibára fut
        if (shopItemList == null) {
            throw new NullPointerException("Nullpointer: hiányzik az arraylist a shopitem objektumból!");
        }

        for (int i = 0; i < shopItemList.size(); i++) {
            Log.d(TAG, "onCreate: " +  shopItemList.get(i).getName());

        }

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                shopItemList.remove(position);
                siAdapter.notifyDataSetChanged();
                lv.requestLayout();
                return true;
            }
        });
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
//                                    shopItemList.remove(position);
//                                    siAdapter.notifyDataSetChanged();
                                    deleteShopItem(position);

                                }


                            }
                        });
        lv.setOnTouchListener(touchListener);



        //HOZZÁADÓ GOMB (még nincs implementálva)
        addShopItemButton = (Button) findViewById(R.id.add_shopitem_btn);
        addShopItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(self);

//                final EditText input = new EditText(self);
//                input.setInputType(InputType.TYPE_CLASS_TEXT);

                alertDialog
//                        .setTitle(R.string.new_shclist_prompt_title)
                        .setNegativeButton(R.string.cancel_button_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
//
                        .setPositiveButton(R.string.create_button_text, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
//                                Intent shoppingListIntent = new Intent(self, ShopItemPickerActivity.class);

//                                String selectedName = input.getText().toString();
//                                ShopItemListBundle selectedList = new ShopItemListBundle(selectedName, "SenDeRNaMe", null, new ArrayList<ShopItem>());
//
//                                //parcelable kell, hogy legyen a shoppingcart!
//                                shoppingListIntent.putExtra(EXTRA_CONTENT, selectedList.getSiList());
//                                shoppingListIntent.putExtra(EXTRA_NAME, selectedName);
//                                startActivity(shoppingListIntent);

                            }
                        })
//
//                        .setView(input)

                        .show();

            }
        });




        //this.play (Display)
        tv.setText(passedName);
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

    public int deleteShopItem(int position){

        if(position<=shopItemList.size()) {
            shopItemList.remove(position);
            siAdapter.notifyDataSetChanged();
            return 0;

        }else{
            return -1;
        }
    }

    public void deleteAll(){

        for (int i = 0; i < shopItemList.size(); i++) {
            shopItemList.clear();
        }

        siAdapter.notifyDataSetChanged();
    }

}
