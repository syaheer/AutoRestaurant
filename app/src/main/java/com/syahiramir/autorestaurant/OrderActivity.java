package com.syahiramir.autorestaurant;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.syahiramir.autorestaurant.menuItem.Iterator;
import com.syahiramir.autorestaurant.menuItem.MenuItem;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    OrderListAdapter orderListAdapter;
    ArrayList<MenuItem> menuItemList = new ArrayList<>();
    RestaurantMenu restaurantMenu = RestaurantMenu.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ListView menuList = findViewById(R.id.menu);

        orderListAdapter = new OrderListAdapter(this, menuItemList);
        menuList.setAdapter(orderListAdapter);
        listMenu();
    }

    public void listMenu() {
        Iterator menuIterator = restaurantMenu.createIterator();
        listMenu(menuIterator);
    }

    private void listMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            menuItemList.add(menuItem);
        }
        orderListAdapter.notifyDataSetChanged();
    }
}
