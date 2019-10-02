package com.syahiramir.autorestaurant.menuItem;

public class SpinachLasagna extends MenuItem {

    public SpinachLasagna(String orderId, Boolean isPreparing, Boolean isCooking, Boolean isReadyForPickup, Boolean isPickedUp , Boolean isPaid) {
        name = "Spinach lasagna roll-ups";
        itemNumber = 3;
        this.orderId = orderId;
        this.isPreparing = isPreparing;
        this.isCooking = isCooking;
        this.isReadyForPickup = isReadyForPickup;
        this. isPickedUp = isPickedUp;
        this.isPaid = isPaid;

        ingredients.add("1 cup lasagna noodles(2 oz dry)");
        ingredients.add("½ cup cooked spinach");
        ingredients.add("½ cup ricotta cheese");
        ingredients.add("1 ounce part-skim mozzarella ");
        ingredients.add("½ cup tomato sauce*");
    }
}