package com.syahiramir.autorestaurant.menuItem;

public class BeefSandwich extends MenuItem {

    public BeefSandwich(String orderId, Boolean isPreparing, Boolean isCooking, Boolean isReadyForPickup, Boolean isPickedUp , Boolean isPaid) {
        name = "Beef sandwich";
        itemNumber = 1;
        this.orderId = orderId;
        this.isPreparing = isPreparing;
        this.isCooking = isCooking;
        this.isReadyForPickup = isReadyForPickup;
        this.isPickedUp = isPickedUp;
        this.isPaid = isPaid;

        ingredients.add("1 small whole grain hoagie bun");
        ingredients.add("2 ounces lean roast beef");
        ingredients.add("1 slice part-skim mozzarella");
        ingredients.add("¼ cup mushrooms");
        ingredients.add("1 tsp corn/canola oil (to cook mushrooms)");
        ingredients.add("2 tsp corn/canola oil (to cook turkey)");
        ingredients.add("½ cup chopped lettuce");
        ingredients.add("½ cup avocado");
    }
}