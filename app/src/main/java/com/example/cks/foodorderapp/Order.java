package com.example.cks.foodorderapp;

/**
 * Created by cks on 1/20/2018.
 */

public class Order {
    String username, itemname;

    public Order()
    {

    }

    public Order(String username, String  itemname) {
        this.username = username;
        this.itemname = itemname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getUsername() {
        return username;
    }

    public String getItemname() {
        return itemname;
    }
}
