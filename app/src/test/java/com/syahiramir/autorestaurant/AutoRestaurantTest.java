package com.syahiramir.autorestaurant;

import com.syahiramir.autorestaurant.menuItem.BeefSandwich;

import org.junit.Test;

import static org.junit.Assert.*;

public class AutoRestaurantTest {

    //check if all menu items are added. should be 4 total: Beef sandwich, creamy oatmeal, spinach lasagna, taco salad.
    @Test
    public void initiateMenu_checkAllMenuItemAdded() {
        RestaurantMenu restaurantMenu = RestaurantMenu.getInstance();
        assertEquals(restaurantMenu.menuItems.length, 4);
    }

    //test restaurant menu singleton pattern
    @Test
    public void getRestaurantMenuInstance_checkIfSameInstance() {
        assertEquals(RestaurantMenu.getInstance(), RestaurantMenu.getInstance());
    }

    // Test factory pattern
    @Test
    public void getMenuItem_checkIfInitiatedProperly() {
        BeefSandwich beefSandwich = new BeefSandwich("",false,false,false,false,false);
        assertEquals(beefSandwich.getName(), "Beef sandwich");
        assertEquals(beefSandwich.getOrderId(), "");
        assertEquals(beefSandwich.isPreparing(), false);
        assertEquals(beefSandwich.isCooking(), false);
        assertEquals(beefSandwich.isReadyForPickup(), false);
        assertEquals(beefSandwich.isPickedUp(), false);
        assertEquals(beefSandwich.isPaid(), false);
    }
}