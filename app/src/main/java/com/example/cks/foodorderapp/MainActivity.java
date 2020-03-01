package com.example.cks.foodorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void add_food_itemsOnClick(View view) {
        Intent addFoodIntent = new Intent(MainActivity.this, AddFood.class);
        startActivity(addFoodIntent);
    }

    public void ViewOrderedItemOnclick(View view)
    {
        startActivity(new Intent(MainActivity.this, OrderedItemActivity.class));
    }
}
