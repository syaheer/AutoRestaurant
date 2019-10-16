package com.syahiramir.autorestaurant.menuItem;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// The Factory Pattern
public abstract class MenuItem {
    String name;
    int itemNumber;
    String orderId;
    Boolean isPreparing = false;
    Boolean isCooking = false;
    Boolean isReadyForPickup = false;
    Boolean isPickedUp = false;
    Boolean isPaid = false;
    ArrayList<String> ingredients = new ArrayList<>();

    public void order(final Activity activity) {
        Toast.makeText(activity, "Ordered " + name,
                Toast.LENGTH_LONG).show();

        Map<String, Object> food = new HashMap<>();
        food.put("menuId", itemNumber);
        food.put("isPreparing", false);
        food.put("isCooking", false);
        food.put("isReadyForPickup", false);
        food.put("isPickedUp", false);
        food.put("isPaid", false);

        // Post to Firebase, part of the Proxy Pattern.
        FirebaseFirestore.getInstance().collection("orders").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("food")
                .add(food)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                       // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(activity, "Ordered " + name,
                                Toast.LENGTH_LONG).show();
                        activity.finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        Toast.makeText(activity, "Ordering " + name + " failed, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Used by the kitchen. The ingredients are kept secret!
    public void prepareIngredients() {
        // DO nothing
    }

    // used by the kitchen. Why would the user cook?
    public void cook() {
        // DO nothing
    }

    // Used by the kitchen. How would the user know the food is ready for pickup?
    public void readyForPickup() {
        // DO nothing
    }

    public void pickup(final Activity activity) {
        FirebaseFirestore.getInstance().collection("orders").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("food").document(orderId)
                .update("isPickedUp", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, "Picked up " + name,
                                Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Preparing " + name + " failed, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void pay(final Activity activity) {
        FirebaseFirestore.getInstance().collection("orders").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("food").document(orderId)
                .update("isPaid", true)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, "Paid for " + name,
                                Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Preparing " + name + " failed, please try again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public String getOrderId() {
        return orderId;
    }

    public Boolean isPreparing() {
        return isPreparing;
    }

    public Boolean isCooking() {
        return isCooking;
    }

    public Boolean isReadyForPickup() {
        return isReadyForPickup;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void mix() {
        System.out.println("Mixing ingredients.");
    }

    public void bake() {
        System.out.println("Baking " + getName());
    }

    public void box() {
        System.out.println("Place food in official restaurant box");
    }

    public String getName() {
        return name;
    }

    public Boolean isPickedUp() {
        return isPickedUp;
    }
}
