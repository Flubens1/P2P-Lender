package com.example.florian.p2p_lender;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by florian on 26.04.17.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter( Context context, String[] user) {
        super(context, R.layout.custom_row, user);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);

        String item = getItem(position);
        TextView text = (TextView)customView.findViewById(R.id.customRowText);
        ImageView image = (ImageView)customView.findViewById(R.id.customRowImage);

        text.setText(item);
        image.setImageResource(R.drawable.p2p_tasten);
        return customView;

    }
}
