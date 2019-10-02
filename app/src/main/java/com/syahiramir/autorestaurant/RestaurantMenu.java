package com.syahiramir.autorestaurant;

import android.view.Menu;

import com.syahiramir.autorestaurant.menuItem.BeefSandwich;
import com.syahiramir.autorestaurant.menuItem.CreamyOatmeal;
import com.syahiramir.autorestaurant.menuItem.Iterator;
import com.syahiramir.autorestaurant.menuItem.MenuItem;
import com.syahiramir.autorestaurant.menuItem.MenuItemIterator;
import com.syahiramir.autorestaurant.menuItem.SpinachLasagna;
import com.syahiramir.autorestaurant.menuItem.TacoSalad;

public class RestaurantMenu {
    static final int MAX_ITEMS = 6;
    private static MenuItem[] menuItems;
    static int numberOfItems = 0;
    private static RestaurantMenu uniqueInstance;

    // The Singleton Pattern!
    public static RestaurantMenu getInstance() {
        if (uniqueInstance == null) {
            System.out.println("Creating unique instance of RestaurantMenu");
            uniqueInstance = new RestaurantMenu();
        }
        System.out.println("Returning instance of RestaurantMenu");
        return uniqueInstance;
    }


    private RestaurantMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        // Menu does not need extra identifiers
        addItem(new BeefSandwich(null, null, null, null, null, null));
        addItem(new CreamyOatmeal(null, null, null, null, null, null));
        addItem(new SpinachLasagna(null, null, null, null, null, null));
        addItem(new TacoSalad(null, null, null, null, null, null));
    }

    private static void addItem(MenuItem menuItem) {
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full! Can’t add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems = numberOfItems + 1;
        }
    }

    public Iterator createIterator() {
        return new MenuItemIterator(menuItems);
    }
}
