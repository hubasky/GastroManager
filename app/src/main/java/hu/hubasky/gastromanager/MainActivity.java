package hu.hubasky.gastromanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button reciepe_manager;
    private Button shopping_list;
    private Button diet_manager;

    private final AppCompatActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

//        shopping_list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        shopping_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shoppingListIntent = new Intent(self, ShoppingListActivity.class);
                startActivity(shoppingListIntent);
            }
        });

        diet_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
