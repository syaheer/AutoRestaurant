package com.syahiramir.autorestaurant.menuItem;

import android.app.Activity;
import android.util.Log;
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
public class MenuItem {
    String name;
    int itemNumber;
    String orderId;
    ArrayList<String> ingredients = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void order(final Activity activity) {
        Toast.makeText(activity, "Ordered " + name,
                Toast.LENGTH_LONG).show();

        Map<String, Object> food = new HashMap<>();
        food.put("menuId", itemNumber);
        food.put("preparing", false);

        // Post to Firebase
        db.collection("orders").document(mAuth.getCurrentUser().getUid()).collection("food")
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

    public void collectIngredients() {
        System.out.println("Collecting ingredients:");
        for (String ingredient : ingredients) {
            System.out.println("\t" + ingredient);
        }
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
}
