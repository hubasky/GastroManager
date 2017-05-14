package hu.hubasky.gastromanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.List;

import hu.hubasky.gastromanager.control.ControlResultListener;
import hu.hubasky.gastromanager.control.Controls;
import hu.hubasky.gastromanager.entity.EMennyisegiEgyseg;
import hu.hubasky.gastromanager.entity.alapanyag.Alapanyag;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagJellemzok;
import hu.hubasky.gastromanager.entity.alapanyag.AlapanyagKeresesiJellemzok;

import static java.lang.Double.parseDouble;

public class EditIngredientActivity extends AppCompatActivity {

    private EditIngredientActivity self = this;

    private EditText nameEditText;
    private Spinner unitTypeSpinner;
    private EditText unitInGEditText;

    private EditText proteinPercentEditText;
    private EditText fatPercentEditText;
    private EditText carboPercentEditText;
    private EditText energyContentEditText;

    private Button cancelButton;
    private Button saveButton;

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);

        initializeViews();

        AlapanyagKeresesiJellemzok.Builder apkj = new AlapanyagKeresesiJellemzok.Builder();
        apkj.mindetTartalmazza(true);

        Controls.getInstance().getAlapanyagNyilvantarto().keres(apkj.build(), new ControlResultListener<Alapanyag>() {
            @Override
            public void onSuccess(List<Alapanyag> resultList) {
                for (Alapanyag a : resultList) {
                    if (a.getUniqueKey().equals(id)) {
                        self.updateFields(a);
                        break;
                    }
                }
            }

            @Override
            public void onFailed(Exception ex) {

            }
        });



        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.setResult(RESULT_OK);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EMennyisegiEgyseg unitType = null;
                double proteinP = 0.0,
                        fatP = 0.0,
                        carboP = 0.0,
                        energyC = 0.0,
                        unitInG = 0.0;
                String name = "";

                String unitTypeString = (String) unitTypeSpinner.getSelectedItem();
                if (unitTypeString != null) {
                    if (unitTypeString.equals(getString(R.string.weight))) {
                        unitType = EMennyisegiEgyseg.GRAMM;
                    } else if (unitTypeString.equals(getString(R.string.liquid))) {
                        unitType = EMennyisegiEgyseg.LITER;
                    } else {
                        unitType = EMennyisegiEgyseg.DARAB;
                    }
                }

                proteinP = parseDouble(proteinPercentEditText.getText().toString().replace(',', '.')) / 100;
                fatP = parseDouble(fatPercentEditText.getText().toString().replace(',', '.')) / 100;
                carboP = parseDouble(carboPercentEditText.getText().toString().replace(',', '.')) / 100;
                energyC = parseDouble(energyContentEditText.getText().toString().replace(',', '.'));
                unitInG = parseDouble(unitInGEditText.getText().toString().replace(',', '.'));

                name = nameEditText.getText().toString();

                Alapanyag result = new Alapanyag(
                        unitType,
                        new AlapanyagJellemzok(100, proteinP, fatP, carboP, energyC),
                        name,
                        unitInG
                );

                if (id != null) {
                    result.setUniqueKey(id);
                }

                try {
                    Controls.getInstance().getAlapanyagNyilvantarto().tarolas(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        });



    }

    private void initializeViews() {
        nameEditText = (EditText) findViewById(R.id.edit_ingredient_name);
        unitTypeSpinner = (Spinner) findViewById(R.id.edit_ingredient_unit_type_spinner);
        unitInGEditText = (EditText) findViewById(R.id.edit_ingredient_reference_g);

        proteinPercentEditText = (EditText) findViewById(R.id.edit_ingredient_protein_percent);
        fatPercentEditText = (EditText) findViewById(R.id.edit_ingredient_fat_percent);
        carboPercentEditText = (EditText) findViewById(R.id.edit_ingredient_carbo_percent);
        energyContentEditText = (EditText) findViewById(R.id.edit_ingredient_energy_content);

        cancelButton = (Button) findViewById(R.id.edit_ingredient_cancel_button);
        saveButton = (Button) findViewById(R.id.edit_ingredient_ok_button);

        id = getIntent().getStringExtra("ingredient");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }

    private void updateFields(Alapanyag ingredient) {
        initializeViews();
        nameEditText.setText(ingredient.getNeve());

        int selection = 0;
        switch (ingredient.getMennyisegiEgyseg()) {
            case GRAMM: selection = 0;
                break;
            case LITER: selection = 1;
                break;
            case DARAB: selection = 2;
                break;
        }
        unitTypeSpinner.setSelection(selection);
        unitInGEditText.setText(String.valueOf(ingredient.getEgysegeGramm()));

        DecimalFormat df = new DecimalFormat("#.00");

        AlapanyagJellemzok ingredientDetails = ingredient.getJellemzok();
        proteinPercentEditText.setText(df.format(ingredientDetails.getFeherjeSzazalek() * 100));
        fatPercentEditText.setText(df.format(ingredientDetails.getZsirSzazalek() * 100));
        carboPercentEditText.setText(df.format(ingredientDetails.getSzenhidratSzazalek() * 100));
        energyContentEditText.setText(String.valueOf((int)ingredientDetails.getEnergiaKJ()));
    }
}
