package com.syahiramir.autorestaurant;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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
        Button orderButton = convertView.findViewById(R.id.order_button);

        final MenuItem m = menuItems.get(position);
        name.setText(m.getName());
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.order(activity);
            }
        });

        return convertView;
    }

}