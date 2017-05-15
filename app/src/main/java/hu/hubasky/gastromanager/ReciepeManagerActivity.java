package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Recept;
import hu.hubasky.gastromanager.viewmodel.IngredientVM;
import hu.hubasky.gastromanager.viewmodel.ReciepeAdapter;
import hu.hubasky.gastromanager.viewmodel.ReciepeVM;

public class ReciepeManagerActivity extends AppCompatActivity {

    private static final String TAG = "ReciepeManagerActivity";

    private Button addReciepeButton;
    private EditText searchField;
    ListView reciepeListView;
    private final AppCompatActivity self = this;
    public static final String EXTRA_RECIPE = "recipe_extra";

    List<Recept> reciepesList = new ArrayList<Recept>();
    ReciepeAdapter reciepeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reciepe_manager);

        reciepeListView = (ListView) findViewById(R.id.reciepe_list);
        reciepeAdapter = new ReciepeAdapter(reciepesList, this);
        reciepeListView.setAdapter(reciepeAdapter);

        Controls.getInstance().getReceptNyilvantarto().keres(null, new ControlResultListener<Recept>() {
            @Override
            public void onSuccess(List<Recept> resultList) {
                reciepesList = resultList;
                reciepeAdapter = new ReciepeAdapter(resultList, self);
                reciepeListView.setAdapter(reciepeAdapter);
            }

            @Override
            public void onFailed(Exception ex) {

            }
        });

        reciepeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recept r = reciepesList.get(position);

                Intent editReciepeIntent = new Intent(self, EditReciepeActivity.class);
                editReciepeIntent.putExtra(EXTRA_RECIPE, r.getUniqueKey());
                editReciepeIntent.putExtra("newReciepe", false);
                startActivity(editReciepeIntent);
            }
        });

        addReciepeButton = (Button) findViewById(R.id.add_reciepe_btn);
        addReciepeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recept r = new Recept(EReceptStatus.PUBLIKUS,
                        "",
                        "",
                        "http://",
                        4);

                try {
                    Controls.getInstance().getReceptNyilvantarto().tarolas(r);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent editReciepeIntent = new Intent(self, EditReciepeActivity.class);
                editReciepeIntent.putExtra(EXTRA_RECIPE, r.getUniqueKey());
                editReciepeIntent.putExtra("newReciepe", true);
                startActivity(editReciepeIntent);
            }
        });

        searchField = (EditText) findViewById(R.id.reciepe_searchfield);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = searchField.getText().toString();
                reciepeAdapter.setSearchString(result);
                reciepeAdapter.notifyDataSetChanged();
            }
        });
    }
}
