package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MyPocket extends AppCompatActivity implements View.OnClickListener{

    Button newOffer;
    Button listViewButton;
    Button myProfileButton;
    Button myLoansButton;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pocket);

        newOffer = (Button)findViewById(R.id.offerButton);
        newOffer.setOnClickListener(this);
        listViewButton = (Button)findViewById(R.id.listViewButton);
        listViewButton.setOnClickListener(this);
        myProfileButton = (Button)findViewById(R.id.buttonProfile);
        myProfileButton.setOnClickListener(this);
        myLoansButton = (Button)findViewById(R.id.loansButton);
        myLoansButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (v==newOffer) {
            Intent intent = new Intent(this, NewOffer.class);
            startActivity(intent);
        }

        if (v==listViewButton) {
            PersistOffers pOffers = new PersistOffers(ctx);
            if (pOffers.getOfferList() == null) {
                CharSequence text = "There are no Offers to show";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ctx, text, duration);
                toast.show();
            } else {
                Intent intent = new Intent(this, LoanList.class);
                startActivity(intent);
            }
        }

        if (v==myLoansButton) {
            PersistOffers pOffers = new PersistOffers(ctx);

            if (pOffers.getOfferList() == null) {

                CharSequence text = "There are no Offers to show";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ctx, text, duration);
                toast.show();

            } else {

                /*ArrayList<Offer> offers = pOffers.getOfferList();
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                String name = pref.getString("mail", "");

                for (Offer each : offers) {
                    if (!each.getLender().equals(name)) {
                        offers.remove(each);
                    }
                }

                if (offers == null) {

                    CharSequence text = "You don't have any Loans";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                } else {

                    Intent intent = new Intent(this, MyLoans.class);
                    startActivity(intent);
                }*/


                Intent intent = new Intent(this, MyLoans.class);
                startActivity(intent);

            }
        }

        if (v==myProfileButton) {
            Intent intent = new Intent(this, MyProfile.class);
            startActivity(intent);
        }
    }
}
