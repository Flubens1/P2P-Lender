package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LoanList extends AppCompatActivity {

    Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);


        PersistOffers pOffers = new PersistOffers(ctx);
        ArrayList<Offer> offers = pOffers.getOfferList();


        for (int i = offers.size()-1; i >= 0; i--) {

            if (offers.get(i).getInvestor() != null) {
                offers.remove(offers.get(i));
            }
        }

        ListAdapter adapter = new CustomAdapter(this, offers);
        ListView listView = (ListView)findViewById(R.id.loanListView);
        listView.setAdapter(adapter);


    }


}
