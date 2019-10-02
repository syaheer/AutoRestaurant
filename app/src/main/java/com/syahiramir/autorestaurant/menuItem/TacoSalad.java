package com.syahiramir.autorestaurant.menuItem;

public class TacoSalad extends MenuItem {

    public TacoSalad(String orderId, Boolean isPreparing, Boolean isCooking, Boolean isReadyForPickup, Boolean isPickedUp , Boolean isPaid) {
        name = "Taco salad";
        itemNumber = 4;
        this.orderId = orderId;
        this.isPreparing = isPreparing;
        this.isCooking = isCooking;
        this.isReadyForPickup = isReadyForPickup;
        this. isPickedUp = isPickedUp;
        this.isPaid = isPaid;

        ingredients.add("2 ounces tortilla chips");
        ingredients.add("2 ounces cooked ground turkey");
        ingredients.add("2 tsp corn/canola oil (to cook turkey)");
        ingredients.add("¼ cup kidney beans");
        ingredients.add("½ ounce low-fat cheddar cheese");
        ingredients.add("2 tsp corn/canola oil (to cook turkey)");
        ingredients.add("½ cup chopped lettuce");
        ingredients.add("½ cup avocado");
    }
}