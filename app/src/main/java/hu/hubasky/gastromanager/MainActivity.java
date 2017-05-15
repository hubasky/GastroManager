package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.felhasznalo.Felhasznalo;
import hu.hubasky.gastromanager.entity.recept.EReceptStatus;
import hu.hubasky.gastromanager.entity.recept.Hozzavalo;
import hu.hubasky.gastromanager.entity.recept.Recept;

public class MainActivity extends AppCompatActivity {

    private Button reciepe_manager;
    private Button shopping_list;
//    private Button diet_manager;

    private final AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controls.getInstance().getAlapanyagNyilvantarto().keres(null, new ControlResultListener<Alapanyag>() {
            @Override
            public void onSuccess(List<Alapanyag> resultList) {
            }
            @Override
            public void onFailed(Exception ex) {
            }
        });


        reciepe_manager = (Button) findViewById(R.id.reciepe_button);
        shopping_list = (Button) findViewById(R.id.shopping_list_button);

        reciepe_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reciepeManagerIntent = new Intent(self, ReciepeManagerActivity.class);
                startActivity(reciepeManagerIntent);
            }
        });


        //~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~
        //~%~%~%~%~%~ 2. GOMB ~%~%~%~%~%~%~
        //~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~
        shopping_list = (Button) findViewById(R.id.shopping_list_button);
        shopping_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shoppingCartPickerIntent = new Intent(self, ShopItemListPickerActivity.class);
                startActivity(shoppingCartPickerIntent);
            }
        });


//        diet_manager = (Button) findViewById(R.id.diet_manager_buton);
//        diet_manager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

        //~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~
        //~%~%~%~%~% MENU GOMB %~%~%~%~%~%~
        //~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~%~
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.mainmenu, menu);

            // return true so that the menu pop up is opened
    //        return true;
            return super.onCreateOptionsMenu(menu);
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.btn_menu_logout) {
                Toast.makeText(this, "Logout button clicked", Toast.LENGTH_SHORT).show();
            }

            Intent logoutIntent = new Intent(self, LoginActivity.class);
            startActivity(logoutIntent);
            finish();

            return super.onOptionsItemSelected(item);
        }
        @Override
        protected void onResume() {
            super.onResume();
            Controls.getInstance().setActualContext(this);

            try {
                Controls.getInstance().getAlapanyagNyilvantarto().keres(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
