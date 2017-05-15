package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class LoanList extends AppCompatActivity {

    Context ctx = this;
    Button goodButton;
    Button neutralButton;
    Button badButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);


        PersistOffers pOffers = new PersistOffers(ctx);
        final ArrayList<Offer> offers = pOffers.getOfferList();


        for (int i = offers.size()-1; i >= 0; i--) {

            if (offers.get(i).getInvestor() != null) {
                offers.remove(offers.get(i));
            }
        }


        goodButton = (Button)findViewById(R.id.goodButton);
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("good click");
                ArrayList<Offer> goodList = new ArrayList<Offer>();

                for (Offer each : offers) {
                    if (each.getOfferBewertung().equals("good")) {
                        goodList.add(each);
                        System.out.println("each = " + each.getOfferName());
                        System.out.println("each Bewertung = " + each.getOfferBewertung());
                        System.out.println("size = " + goodList.size());
                    }
                }

                ListAdapter adapter = new CustomAdapter(ctx, goodList);
                ListView listView = (ListView)findViewById(R.id.loanListView);
                listView.setAdapter(adapter);

            }
        });
        neutralButton = (Button)findViewById(R.id.neutralButton);
        neutralButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("neutral click");
            }
        });
        badButton = (Button)findViewById(R.id.badButton);
        badButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("bad click");
            }
        });



        ListAdapter adapter = new CustomAdapter(this, offers);
        ListView listView = (ListView)findViewById(R.id.loanListView);
        listView.setAdapter(adapter);


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.returnmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.returnButton:
                Intent intent = new Intent(this, MyPocket.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
