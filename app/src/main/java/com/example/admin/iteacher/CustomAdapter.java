package com.example.admin.iteacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, String[] items ) {
        super(context, R.layout.custom_row,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lerasInflater = LayoutInflater.from(getContext());
        View customView = lerasInflater.inflate(R.layout.custom_row, parent, false);
        String singleItemItem = getItem(position);
        final TextView leraText = customView.findViewById(R.id.lerasText);
        final ImageView lerasImage = customView.findViewById(R.id.lerasImage);

        leraText.setText(singleItemItem);
        lerasImage.setImageResource(R.mipmap.ic_launcher);
        return customView;
    }
}