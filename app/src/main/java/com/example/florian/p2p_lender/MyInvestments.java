package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class MyInvestments extends AppCompatActivity {
    Context ctx = this;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
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
        //listView.setAdapter(adapter);*/

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
        //System.out.println(mail);
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

        /*Iterator<Offer> iterator = offers.iterator();
        Offer temp;
        while (iterator.hasNext()) {

            temp = iterator.next();
            System.out.println(temp.getInvestor());
            if (temp.getInvestor() == null) {  // magst du den nicht eher removen? warum soll ein null drinnen bleiben?
                offers.remove(temp);          // damit funkt es eher, aber ich will dir ja nicht drein reden
                break;
            }
            if (!temp.getInvestor().equals(mail)) {
                offers.remove(temp);
            }
        }*/

        // aus irgendeinem grund muss man sooft return drücken um wieder von der activity weg zu kommmen, wie die anzahl der investments ist,
        // was bei 3 investments schon nervig ist, ich würd ne andere schleife machen

        ListAdapter adapter = new CustomAdapter(this, offers, mail);
        ListView listView = (ListView)findViewById(R.id.investmentListView);
        listView.setAdapter(adapter);

    }
}
