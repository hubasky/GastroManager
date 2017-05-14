package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private Button diet_manager;

    private final AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        // ********************************************************

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference("reciepes");
//        DatabaseReference item = dbRef.push();
//        item.setValue("test");

        Felhasznalo peet = new Felhasznalo("peet3d", "12341234", "Peet");
        peet.setUniqueKey("test_user");

        Recept reciepe = new Recept(
                peet,
                EReceptStatus.PUBLIKUS,
                "Egészben, són sült csirke",
                "Az egész csirkét egy kevés vajjal kívül belül átkenjük és egy egész almát tegyünk a belsejébe. Az egy kilógram sót öntsünk egy tepszi aljába, egyengessük el, majd helyezzük rá a bevajazott csirkét. A csirke szárnyait alufóliával beburkolhatjuk, hogy megvédjük a megégéstől, de a sütés utolsó 15 percében távolítsuk el, hogy jó ropogósra süljön. 180 fokra előmelegített sütőbe helyezve 50-60 perc alatt ropogós aranybarnára süssük.",
                "http://",
                4
        );
        Hozzavalo csirke = new Hozzavalo(1500,
                new Alapanyag(
                        EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 0.15, 0.15, 0.15, 100),
                        "csirke",
                        1
                ));
        Hozzavalo alma = new Hozzavalo(1500,
                new Alapanyag(
                        EMennyisegiEgyseg.DARAB,
                        new AlapanyagJellemzok(1, 0.1, 0.1, 0.1, 20),
                        "alma",
                        1
                ));
        Hozzavalo vaj = new Hozzavalo(1500,
                new Alapanyag(
                        EMennyisegiEgyseg.GRAMM,
                        new AlapanyagJellemzok(100, 0.1, 0.4, 0.015, 100),
                        "vaj",
                        1
                ));
        csirke.getAlapanyag().setUniqueKey("csirke_hozzavalo");
        alma.getAlapanyag().setUniqueKey("alma_hozzavalo");
        vaj.getAlapanyag().setUniqueKey("vaj_hozzavalao");
        reciepe.addHozzavalo(csirke);
        reciepe.addHozzavalo(alma);
        reciepe.addHozzavalo(vaj);

        try {
            Controls.getInstance().getReceptNyilvantarto().tarolas(reciepe);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ********************************************************

*/

        reciepe_manager = (Button) findViewById(R.id.reciepe_button);
        shopping_list = (Button) findViewById(R.id.shopping_list_button);
        diet_manager = (Button) findViewById(R.id.diet_manager_buton);

        reciepe_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reciepeManagerIntent = new Intent(self, ReciepeManagerActivity.class);
                startActivity(reciepeManagerIntent);
            }
        });


        shopping_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shoppingCartPickerIntent = new Intent(self, ShopItemListPickerActivity.class);
                startActivity(shoppingCartPickerIntent);
            }
        });

        diet_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
