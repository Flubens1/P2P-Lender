package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by florian on 26.04.17.
 */

public class CustomAdapter extends ArrayAdapter<Offer> {

    Offer item;
    Context mContext;

    public CustomAdapter(Context context, ArrayList<Offer> offers) {
        super(context, R.layout.custom_row, offers);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);

        item = getItem(position);
        TextView offerTextBeschreibung = (TextView)customView.findViewById(R.id.offerTextBeschreibung);
        TextView offerTextName = (TextView)customView.findViewById(R.id.offerNameText);
        ImageView image = (ImageView)customView.findViewById(R.id.customRowImage);
        TextView bewertung = (TextView)customView.findViewById(R.id.bewertung);

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Offer offer = getItem(position);
                Intent mIntent = new Intent(v.getContext(), DetailedOffer.class);
                mIntent.putExtra("offerTitle", offer.getOfferName());
                v.getContext().startActivity(mIntent);

            }
        });


        offerTextBeschreibung.setText(item.getBeschreibung());
        bewertung.setText("Bewertung: " + item.getOfferBewertung());
        offerTextName.setText(item.getOfferName());
        image.setImageResource(R.drawable.p2p_tasten);
        return customView;

    }
}
