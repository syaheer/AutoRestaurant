package com.syahiramir.autorestaurant.menuItem;

public class CreamyOatmeal extends MenuItem {

    public CreamyOatmeal(String orderId) {
        name = "Creamy oatmeal";
        itemNumber = 2;
        this.orderId = orderId;

        ingredients.add("½ cup uncooked oatmeal ");
        ingredients.add("1 cup fat-free milk");
        ingredients.add("2 Tbsp raisins");
        ingredients.add("2 tsp brown sugar");
    }
}