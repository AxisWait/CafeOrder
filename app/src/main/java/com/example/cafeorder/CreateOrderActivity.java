package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private String drink;
    private String name;
    private String password;
    private StringBuilder builderAddition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        drink = getString(R.string.tea);

        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.say_hello), name);
        textViewHello.setText(hello);

        textViewAdditions = findViewById(R.id.textViewAdditions);
        String additions = String.format(getString(R.string.additions_drink), drink);
        textViewAdditions.setText(additions);

        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);

        builderAddition = new StringBuilder();
    }


    public void onClickChooseDrink(View view) {
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if(id == R.id.radioButtonTea){
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        }
        else if (id == R.id.radioButtonCoffee){
            drink = getString(R.string.coffee);
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffee.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);

        }
        String additions = String.format(getString(R.string.additions_drink), drink);
        textViewAdditions.setText(additions);


    }

    public void onClickSendOrder(View view) {
        builderAddition.setLength(0);
        if(checkBoxMilk.isChecked()){
            builderAddition.append(getString(R.string.check_box_milk)).append(" ");
        }
        if(checkBoxSugar.isChecked()){
            builderAddition.append(getString(R.string.check_box_sugar)).append(" ");
        }
        if(checkBoxSugar.isChecked() && drink.equals(getString(R.string.tea))){
            builderAddition.append(getString(R.string.check_box_sugar)).append(" ");
        }
        String optionOfDrink ="";

        if(drink.equals(getString(R.string.tea)))
        {
            optionOfDrink = spinnerTea.getSelectedItem().toString();
        }
         else {
        optionOfDrink =spinnerCoffee.getSelectedItem().toString();
        }
        String order = String.format(getString(R.string.order),name,password,drink,optionOfDrink);
         String additions;
         if(builderAddition.length()>0){
            additions = getString(R.string.need_additions) + builderAddition.toString();
         }
         else {
             additions = "";
         }
         String fullOrder = order + additions;
         Intent intent1 = new Intent(this, OrderList.class);
         intent1.putExtra("order", fullOrder);
         startActivity(intent1);
    }
}
