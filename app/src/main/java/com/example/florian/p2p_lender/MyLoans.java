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
        String mail = pref.getString("mail", "");

        for (int i = offers.size()-1; i >= 0; i--) {
            if (!offers.get(i).getLender().equals(mail)) {
                offers.remove(offers.get(i));
            }
        }

        ListAdapter adapter = new CustomAdapter(this, offers, mail);
        ListView listView = (ListView)findViewById(R.id.myLoansListView);
        listView.setAdapter(adapter);

    }
}
