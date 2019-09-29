package com.syahiramir.autorestaurant.menuItem;

import com.syahiramir.autorestaurant.menuItem.Iterator;

public class MenuItemIterator implements Iterator {
    MenuItem[] items;
    int position = 0;

    public MenuItemIterator(MenuItem[] items) {
        this.items = items;
    }

    public Object next() {
        MenuItem menuItem = items[position];
        position = position + 1;
        return menuItem;
    }

    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }
}
