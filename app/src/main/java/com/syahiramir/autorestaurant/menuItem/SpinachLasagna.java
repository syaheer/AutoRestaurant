package com.syahiramir.autorestaurant.menuItem;

public class SpinachLasagna extends MenuItem {

    public SpinachLasagna(String orderId) {
        name = "Spinach lasagna roll-ups";
        itemNumber = 3;
        this.orderId = orderId;

        ingredients.add("1 cup lasagna noodles(2 oz dry)");
        ingredients.add("½ cup cooked spinach");
        ingredients.add("½ cup ricotta cheese");
        ingredients.add("1 ounce part-skim mozzarella ");
        ingredients.add("½ cup tomato sauce*");
    }
}