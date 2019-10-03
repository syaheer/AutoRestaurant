package com.syahiramir.autorestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.syahiramir.autorestaurant.menuItem.BeefSandwich;
import com.syahiramir.autorestaurant.menuItem.CreamyOatmeal;
import com.syahiramir.autorestaurant.menuItem.MenuItem;
import com.syahiramir.autorestaurant.menuItem.SpinachLasagna;
import com.syahiramir.autorestaurant.menuItem.TacoSalad;


import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    OrderedItemAdapter orderedItemAdapter;
    ArrayList<MenuItem> orderedItemList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Log.d("user", user.getUid());

                            Map<String, Object> userHashmap = new HashMap<>();
                            userHashmap.put("userId", mAuth.getCurrentUser().getUid());

                            // Post to Firebase
                            db.collection("orders").document(mAuth.getCurrentUser().getUid())
                                    .set(userHashmap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Do nothing
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // DO nothing
                                        }
                                    });

                            db.collection("orders").document(mAuth.getCurrentUser().getUid()).collection("food")
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot value,
                                                            @Nullable FirebaseFirestoreException e) {
                                            if (value != null) {
                                                orderedItemList.clear();
                                                for (QueryDocumentSnapshot document : value) {
                                                    long itemId = (long) document.get("menuId");
                                                    if (itemId == 1) {
                                                        orderedItemList.add(new BeefSandwich(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                                    } else if (itemId == 2) {
                                                        orderedItemList.add(new CreamyOatmeal(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                                    } else if (itemId == 3) {
                                                        orderedItemList.add(new SpinachLasagna(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                                    } else if (itemId == 4) {
                                                        orderedItemList.add(new TacoSalad(document.getId(), document.getBoolean("isPreparing"), document.getBoolean("isCooking"), document.getBoolean("isReadyForPickup"), document.getBoolean("isPickedUp"), document.getBoolean("isPaid")));
                                                    }
                                                }
                                                orderedItemAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                        }
                    }
                });

        FloatingActionButton fab = findViewById(R.id.fab);
        ListView orders = findViewById(R.id.orders);

        orderedItemAdapter = new OrderedItemAdapter(this, orderedItemList);
        orders.setAdapter(orderedItemAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });
    }
}
