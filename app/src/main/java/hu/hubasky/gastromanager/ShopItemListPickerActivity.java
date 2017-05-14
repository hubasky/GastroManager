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
import java.util.HashSet;

import hu.hubasky.gastromanager.entity.bevlist.BevasarloLista;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.viewmodel.ShopItem;
import hu.hubasky.gastromanager.viewmodel.ShopItemListBundle;
import hu.hubasky.gastromanager.viewmodel.ShopItemListBundleAdapter;
import hu.hubasky.gastromanager.viewmodel.SwipeDismissListViewTouchListener;

public class ShopItemListPickerActivity extends AppCompatActivity {



    private static final String TAG = "ShoppingCartPickerAct";
    public static final String EXTRA_ID = "hu.hubasky.gastromanager._ID";
//    public static final String EXTRA_CONTENT = "hu.hubasky.gastromanager._CONTENT";
    public static final String EXTRA_NAME = "hu.hubasky.gastromanager._NAME";

    private Button addShopItemListBundleButton;
    private final AppCompatActivity self = this;

    private String loggedinUsrID;
    private final String EXTRA_loggedinUsrID = "loggedinUsrID";

    ListView mListView;
    ShopItemListBundleAdapter adapter;


    private ArrayList<BevasarloLista> shcList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylayout_shopitemlist_picker);

        loggedinUsrID = getIntent().getStringExtra(EXTRA_loggedinUsrID);

        Log.d(TAG, "onCreate: Started.");
        mListView = (ListView) findViewById(R.id.listView_shclist);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent shoppingListIntent = new Intent(self, ShopItemPickerActivity.class);

                BevasarloLista selectedList = shcList.get(position); //lehet, hogy ezt is kell parcellázni!
                String selectedName = selectedList.getBevasarloListaNev();

                //parcelable kell, hogy legyen a shoppingcart!

                shoppingListIntent.putExtra(EXTRA_ID, selectedList.getUniqueKey());
                shoppingListIntent.putExtra(EXTRA_NAME, selectedName);
                shoppingListIntent.putExtra(EXTRA_loggedinUsrID, loggedinUsrID); //session
                startActivity(shoppingListIntent);
            }
        });


        //megosztáshoz long click on item


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(self);

//                final ArrayAdapter<String> adapter = new ArrayAdapter<>(self,android.R.layout.simple_list_item_multiple_choice);
//                adapter.add("DummyUser1");
//                adapter.add("DummyUser2");
//                adapter.add("DummyUser3");
//                adapter.add("DummyUser4");
//                adapter.add("DummyUser5");

                CharSequence[] sq = new CharSequence[5];
                sq[0]=("DummyUser1");
                sq[1]=("DummyUser2");
                sq[2]=("DummyUser3");
                sq[3]=("DummyUser4");
                sq[4]=("DummyUser5");

                int len = adapter.getCount();
                final boolean[] checkArray = new boolean[len];
                for (int i = 0; i < checkArray.length; i++) {
                    checkArray[i] = false;

                }

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

                                shareShopItemList(position); //user arraylist-et is kellene átadni, amit kiválasztott, ha lesz



                            }
                        })


                        .setMultiChoiceItems(sq, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkArray[which]=isChecked;
                            }
                        })


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

                                //TODO: firebase-ből loggedinUsrID alapján a felhasználót lekérni

                                BevasarloLista selectedList = new BevasarloLista(loggedinUsrID, );

                                //parcelable kell, hogy legyen a shoppingcart!
                                shoppingListIntent.putExtra(EXTRA_ID, selectedList.getUniqueKey());
                                shoppingListIntent.putExtra(EXTRA_NAME, selectedName);
                                startActivity(shoppingListIntent);

                            }
                        })

                        .setView(input)

                        .show();

            }
        });



        //TODO: konstruktorba a lekért firebase hashset-et
        shcList = new ArrayList<>();


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

    public int shareShopItemList(int position){ //arraylist<User> user

        //implementálni!

        return 0;
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
//            shcList.get(position).clearItem();
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
//            shcList.get(i).clearItem();
            shcList.remove(i);
        }

        adapter.notifyDataSetChanged();
    }


}
