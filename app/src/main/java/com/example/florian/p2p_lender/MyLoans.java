package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyLoans extends AppCompatActivity {

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loans);


        PersistOffers pOffers = new PersistOffers(ctx);
        ArrayList<Offer> offers = pOffers.getOfferList();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String name = pref.getString("mail", "");

        for (Offer each : offers) {
            if (!each.getLender().equals(name)) {
                offers.remove(each);
            }
        }


        ListAdapter adapter = new CustomAdapter(this, offers);
        ListView listView = (ListView)findViewById(R.id.myLoansListView);
        listView.setAdapter(adapter);


    }
}
