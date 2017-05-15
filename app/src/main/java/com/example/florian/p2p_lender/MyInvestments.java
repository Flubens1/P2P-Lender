package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/*
**
* The idea of mInvestments is to display all offers the logged in user has invested in.
* By fetching the persistently saved offers, we compare the "Investor" field-- an email address-- of the respective offer
* to that of the one saved in SharedPreferences. Depending on whether or not the equals() method returns true or false,
* we either keep or remove the respective Offer object.
 */
public class MyInvestments extends AppCompatActivity {
    Context ctx = this;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_investments);

        returnButton = (Button) findViewById(R.id.returnButtonInvestments);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), MyPocket.class);
                v.getContext().startActivity(mIntent);
            }
        });

        PersistOffers pOffers = new PersistOffers(ctx);
        ArrayList<Offer> offers = pOffers.getOfferList();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String mail = pref.getString("mail", "");

        //As an array starts at 0, we take the length of the offers list and subtract 1 to avoid
        //out of bonds problematics.
        for (int i = offers.size()-1; i >= 0; i--) {
            if (offers.get(i).getInvestor() != null) {
                if (!offers.get(i).getInvestor().equals(mail)) {
                    offers.remove(offers.get(i));
                }
            }
            if (offers.get(i).getInvestor() == null) {
                offers.remove(offers.get(i));
            }
        }

        //Once the list has been prepared appropriately, we use the CustomAdapter class to handle
        //the way it's displayed in a ListView.
        ListAdapter adapter = new CustomAdapter(this, offers);
        ListView listView = (ListView)findViewById(R.id.investmentListView);
        listView.setAdapter(adapter);

    }
}
