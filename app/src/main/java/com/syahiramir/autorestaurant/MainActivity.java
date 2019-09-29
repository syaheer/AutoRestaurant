package com.syahiramir.autorestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.syahiramir.autorestaurant.menuItem.BeefSandwich;
import com.syahiramir.autorestaurant.menuItem.CreamyOatmeal;
import com.syahiramir.autorestaurant.menuItem.MenuItem;
import com.syahiramir.autorestaurant.menuItem.SpinachLasagna;
import com.syahiramir.autorestaurant.menuItem.TacoSalad;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    OrderedItemAdapter otderedItemAdapter;
    ArrayList<MenuItem> menuItemList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        }
                    }
                });

        FloatingActionButton fab = findViewById(R.id.fab);
        ListView orders = findViewById(R.id.orders);

        otderedItemAdapter = new OrderedItemAdapter(this, menuItemList);
        orders.setAdapter(otderedItemAdapter);

        db.collection("orders").document(mAuth.getCurrentUser().getUid()).collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                long itemId = (long) document.get("menuId");
                                if (itemId == 1) {
                                    menuItemList.add(new BeefSandwich(document.getId()));
                                } else if (itemId == 2) {
                                    menuItemList.add(new CreamyOatmeal(document.getId()));
                                } else if (itemId == 3) {
                                    menuItemList.add(new SpinachLasagna(document.getId()));
                                } else if (itemId == 4) {
                                    menuItemList.add(new TacoSalad(document.getId()));
                                }
                            }
                            otderedItemAdapter.notifyDataSetChanged();
                        }
                    }
                });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });
    }
}
