package com.syahiramir.autorestaurant.menuItem;

public class CreamyOatmeal extends MenuItem {

    public CreamyOatmeal(String orderId, Boolean isPreparing, Boolean isCooking, Boolean isReadyForPickup, Boolean isPickedUp , Boolean isPaid) {
        name = "Creamy oatmeal";
        itemNumber = 2;
        this.orderId = orderId;
        this.isPreparing = isPreparing;
        this.isCooking = isCooking;
        this.isReadyForPickup = isReadyForPickup;
        this. isPickedUp = isPickedUp;
        this.isPaid = isPaid;

        ingredients.add("½ cup uncooked oatmeal ");
        ingredients.add("1 cup fat-free milk");
        ingredients.add("2 Tbsp raisins");
        ingredients.add("2 tsp brown sugar");
    }
}