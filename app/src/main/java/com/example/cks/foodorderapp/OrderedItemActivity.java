package com.example.cks.foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class OrderedItemActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_item);

        recycleList = (RecyclerView) findViewById(R.id.orderedList);
        recycleList.setHasFixedSize(true);
        recycleList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter <Order, OrderViewHolder> FRBA = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(
                Order.class,
                R.layout.vieworderedlist,
                OrderViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Order model, int position) {
                viewHolder.setItemname(model.getItemname());
                viewHolder.setUsername(model.getUsername());
            }
        };
        recycleList.setAdapter(FRBA);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder
    {
        View orderView;
        public OrderViewHolder(View itemView) {
            super(itemView);
            orderView = itemView;
        }

        public void setItemname(String itemname)
        {
            TextView text_item = (TextView) orderView.findViewById(R.id.OrderedItem);
            text_item.setText(itemname);
        }
        public void setUsername(String username)
        {
            TextView text_User = (TextView) orderView.findViewById(R.id.OrderedName);
            text_User.setText(username);
        }
    }
}
