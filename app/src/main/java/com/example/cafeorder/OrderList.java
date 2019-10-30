package com.example.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderList extends AppCompatActivity {

    private TextView textViewFullOrder;
    private  String order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        textViewFullOrder = findViewById(R.id.textViewFullOrder);
        Intent intent = getIntent();
        if(intent.hasExtra("order")){
            order = intent.getStringExtra("order");
        }
        else {
            order = getString(R.string.emptyOrder);
        }
        textViewFullOrder.setText(order);

    }
}
