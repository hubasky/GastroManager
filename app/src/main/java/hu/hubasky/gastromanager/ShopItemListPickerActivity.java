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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import hu.hubasky.gastromanager.viewmodel.ShopItem;
import hu.hubasky.gastromanager.viewmodel.ShopItemListBundle;
import hu.hubasky.gastromanager.viewmodel.ShopItemListBundleAdapter;
import hu.hubasky.gastromanager.viewmodel.SwipeDismissListViewTouchListener;

public class ShopItemListPickerActivity extends AppCompatActivity {

    private static final String TAG = "ShoppingCartPickerAct";
    public static final String EXTRA_ID = "hu.hubasky.gastromanager._ID";
    public static final String EXTRA_CONTENT = "hu.hubasky.gastromanager._CONTENT";
    public static final String EXTRA_NAME = "hu.hubasky.gastromanager._NAME";

    private Button addShopItemListBundleButton;
    private final AppCompatActivity self = this;

    ListView mListView;
    ShopItemListBundleAdapter adapter;

    private ArrayList<ShopItemListBundle> shcList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_shopitemlist_picker);

        Log.d(TAG, "onCreate: Started.");
        mListView = (ListView) findViewById(R.id.listView_shclist);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent shoppingListIntent = new Intent(self, ShopItemPickerActivity.class);

                ShopItemListBundle selectedList = shcList.get(position); //lehet, hogy ezt is kell parcellázni!
                String selectedName = selectedList.getName();

                //parcelable kell, hogy legyen a shoppingcart!
                shoppingListIntent.putExtra(EXTRA_CONTENT, selectedList.getSiList());
                shoppingListIntent.putExtra(EXTRA_NAME, selectedName);
                startActivity(shoppingListIntent);
            }
        });


        //megosztáshoz long click on item
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(self);

                final EditText input = new EditText(self);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                alertDialog
                        .setTitle(R.string.share_shclist_prompt_title)
                        .setNegativeButton(R.string.cancel_button_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })

                        .setPositiveButton(R.string.create_button_text, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent shoppingListIntent = new Intent(self, ShopItemPickerActivity.class);

                                String selectedName = input.getText().toString();
                                ShopItemListBundle selectedList = new ShopItemListBundle(selectedName, "SenDeRNaMe", null, new ArrayList<ShopItem>());

                                //parcelable kell, hogy legyen a shoppingcart!
                                shoppingListIntent.putExtra(EXTRA_CONTENT, selectedList.getSiList());
                                shoppingListIntent.putExtra(EXTRA_NAME, selectedName);
                                startActivity(shoppingListIntent);

                            }
                        })


                        .setView(input)

                        .show();

                //telling the framework that the touch event is consumed and no further event handling is required

                return true;
            }
        });


        //új kosár hozzáadásához
        addShopItemListBundleButton = (Button) findViewById(R.id.add_shopitembundle_btn);
        addShopItemListBundleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(self);

                final EditText input = new EditText(self);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                alertDialog
                        .setTitle(R.string.new_shclist_prompt_title)
                        .setNegativeButton(R.string.cancel_button_text, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })

                        .setPositiveButton(R.string.create_button_text, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent shoppingListIntent = new Intent(self, ShopItemPickerActivity.class);

                                String selectedName = input.getText().toString();
                                ShopItemListBundle selectedList = new ShopItemListBundle(selectedName, "SenDeRNaMe", null, new ArrayList<ShopItem>());

                                //parcelable kell, hogy legyen a shoppingcart!
                                shoppingListIntent.putExtra(EXTRA_CONTENT, selectedList.getSiList());
                                shoppingListIntent.putExtra(EXTRA_NAME, selectedName);
                                startActivity(shoppingListIntent);

                            }
                        })

                        .setView(input)

                        .show();

            }
        });

        ArrayList<ShopItem> shopItemList1 = new ArrayList<>(32);
        ArrayList<ShopItem> shopItemList2 = new ArrayList<>(32);
        ArrayList<ShopItem> shopItemList3 = new ArrayList<>(32);
        shopItemList1.add(new ShopItem("Répa", 3, "csokor"));
        shopItemList1.add(new ShopItem("Retek", 2, "csokor"));
        shopItemList1.add(new ShopItem("Mogyoró", 1, "kiló"));
        shopItemList1.add(new ShopItem("Korán", 30, "perc"));
        shopItemList1.add(new ShopItem("Rigó", 7, "darab"));
        shopItemList2.add(new ShopItem("Répa", 3, "csokor"));
        shopItemList2.add(new ShopItem("Rigó", 2, "csokor"));
        shopItemList2.add(new ShopItem("Rigó", 1, "kiló"));
        shopItemList2.add(new ShopItem("Mogyoró", 30, "perc"));
        shopItemList2.add(new ShopItem("Rigó", 7, "darab"));

        ShopItemListBundle a = new ShopItemListBundle("Tejszínes csirke", "Béla", "Gizike",shopItemList3);
        ShopItemListBundle b = new ShopItemListBundle("Majorannás torta", "Béla", "Terka", shopItemList1);
        ShopItemListBundle c = new ShopItemListBundle("Uzsonnás bütyök kékvér-lekvárral, ahogy a nagyanyám 10 éves kora előtt csinálta hajnalban mielőtt megetette a tyukokat a lánya helyett", "Béla", "Gabriella, Etelka", shopItemList2);
        ShopItemListBundle d = new ShopItemListBundle("Hagymás torma", "Béla", "Gizike", shopItemList1);
        ShopItemListBundle e = new ShopItemListBundle("Porhanyós porhamu", "Béla", "Gizike", shopItemList2);
        ShopItemListBundle f = new ShopItemListBundle("Szentséges kenyér", "Gyulus", "Gizike", shopItemList2);
        ShopItemListBundle g = new ShopItemListBundle("Kinyíró kenyér", "Gyulus", "Gizike", shopItemList1);
        ShopItemListBundle h = new ShopItemListBundle("Kanyarós lapos kalács", "Béla", "Gizike", shopItemList2);
        ShopItemListBundle i = new ShopItemListBundle("Kakaós tevepata", "Istvánus", "Gabriella, Etelka, jÁn0s1lel, nemszabolcs_bazsi123, szekptikussPolitikuss, dejóhosszúezafelhasználónév", shopItemList2);
        ShopItemListBundle j = new ShopItemListBundle("Tekercses vakarcs", "Gyulus", "Gizike", shopItemList1);
        ShopItemListBundle k = new ShopItemListBundle("Vakarós vakaró", "Béla", "Gizike", shopItemList1);
        ShopItemListBundle l = new ShopItemListBundle("Huszonéves pipi", "Gyulus", "Gizike", shopItemList2);
        ShopItemListBundle m = new ShopItemListBundle("Borban tartott bourbon", "Béla", "Etelka", shopItemList1);
        ShopItemListBundle n = new ShopItemListBundle("Borleves", "Istvánus", "Gizike", shopItemList1);
        ShopItemListBundle o = new ShopItemListBundle("Korhelyes korhelyleves", "Pistabá", "LoremIpsum", shopItemList1);

        shcList = new ArrayList<>();

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


        //TÖRLÉSHEZ
        adapter = new ShopItemListBundleAdapter(this, R.layout.layout_shopitemlist_bundle, shcList);
        mListView.setAdapter(adapter);

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        mListView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    if (true){//user == loggedinUser) {
                                        deleteShListItem(position);
                                    }else{
                                        unsubscribeShListItem(position);
                                    }
                                }


                            }
                        });
        mListView.setOnTouchListener(touchListener);

    }


    public int unsubscribeShListItem(int position){

        //
        if(position<=shcList.size()) {
            shcList.remove(position);
            adapter.notifyDataSetChanged();
            return 0;

        }else{
            return -1;
        }
    }

    //TÉNYLEGESEN TÖRLI AZ ELEMET
    public int deleteShListItem(int position){

        //
        if(position<=shcList.size()) {
            shcList.get(position).clearItem();
            shcList.remove(position);
            adapter.notifyDataSetChanged();
            return 0;

        }else{
            return -1;
        }
    }

    //TÉNYLEGESEN TÖRLI AZ ÖSSZES ELEMET
    public void deleteAll(){

        for (int i = 0; i < shcList.size(); i++) {
            shcList.get(i).clearItem();
            shcList.remove(i);
        }

        adapter.notifyDataSetChanged();
    }


}
