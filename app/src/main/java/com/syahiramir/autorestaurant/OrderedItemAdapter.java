package com.syahiramir.autorestaurant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.syahiramir.autorestaurant.menuItem.MenuItem;

import java.util.List;

public class OrderedItemAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MenuItem> menuItems;

    public OrderedItemAdapter(Activity activity, List<MenuItem> menuItems) {
        this.activity = activity;
        this.menuItems = menuItems;
    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int location) {
        return menuItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.ordered_item, null);

        TextView name = convertView.findViewById(R.id.item_name);
        TextView orderStatus = convertView.findViewById(R.id.orderStatus);
        Button progressButton = convertView.findViewById(R.id.progress_button);

        final MenuItem m = menuItems.get(position);
        name.setText(m.getName());

        if (!m.isPreparing()) {
            progressButton.setVisibility(View.GONE); // The user has no use of this button yet. Food is being prepared
            orderStatus.setText("Order Received");
        } else if (!m.isCooking()) {
            progressButton.setVisibility(View.GONE);
            orderStatus.setText("Ingredients Prepared"); // The user has no use of this button yet. Food is being cooked
        } else if (!m.isReadyForPickup()) {
            progressButton.setText("Serve");
            orderStatus.setText("Food ready to serve"); // The user has no use of this button yet. Cooking is being served
        } else if (!m.isPickedUp()) {
            progressButton.setText("Pick Up");
            progressButton.setVisibility(View.VISIBLE);
            orderStatus.setText("Food ready for pickup");
            progressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m.pickup(activity);
                }
            });
        } else if (!m.isPaid()) {
            progressButton.setText("Pay");
            progressButton.setVisibility(View.VISIBLE);
            orderStatus.setText("Food ready for payment");
            progressButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    m.pay(activity);
                }
            });
        } else {
            // All actions done
            progressButton.setVisibility(View.GONE);
            orderStatus.setText("Food is paid. Thank you!");
        }

        return convertView;
    }

}