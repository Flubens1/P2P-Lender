package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class MyInvestments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_investments);
        Context ctx = MyInvestments.this;

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String mailAddressInvestor = pref.getString("userMailAddress", "");

        //fetch list from serialized class
        PersistOffers pOffers = new PersistOffers(ctx);
        ArrayList<Offer> offerList = pOffers.getOfferList();

        //determine which elements are to be shown (ie. those where the user has invested money in
        Iterator<Offer> iterator = offerList.iterator();
        Offer temp;

        while (iterator.hasNext()) {
            temp = iterator.next();
            //need some more variables here before I try anything
            if (!temp.getInvestor().equals(mailAddressInvestor)) {
                System.out.println(temp.getInvestor());
                System.out.println(temp.getBorrower());
                offerList.remove(temp);
            }
        }



        //use only after final list has been created
        //ListAdapter adapter = new CustomAdapter(this, offerList);
        ListView listView = (ListView)findViewById(R.id.investmentListView);
        //listView.setAdapter(adapter);
    }
}
