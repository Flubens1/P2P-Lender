package com.example.florian.p2p_lender;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class DetailedOffer extends AppCompatActivity implements View.OnClickListener{
    TextView offerTitle, offerDescription, actualOfferTitle, actualOfferDescription;
    Button investButton;

    boolean offerWasInvestedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);
        System.out.println("inside onCreate of DetailedOffer");

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        /*String mailAddress = pref.getString("userMailAddress", "");
        String userID = pref.getString("UUID", "");
        String mailSavedInOffer = pref.getString("mail", "");*/
        String actualTitle = pref.getString("offerTitle", "");
        String actualDescription = pref.getString("offerDescription", "");

        offerTitle = (TextView) findViewById(R.id.offerTitle);
        offerDescription = (TextView) findViewById(R.id.offerDescription);
        actualOfferTitle = (TextView) findViewById(R.id.actualTitle);
        actualOfferTitle.setText(actualTitle);
        actualOfferDescription = (TextView) findViewById(R.id.actualDescription);
        actualOfferDescription.setText(actualDescription);
        investButton = (Button) findViewById(R.id.investInOffer);
        investButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v ) {
        if (v == investButton) {
            //mailAddress is being saved when a user logs in, thus it's his mail address string.
            //mailSavedInOffer however is saved when a new offer is created. Upon creation
            //it immediately sets the 'lender' variable in the offer object to that of the one that created said offer
            //this is clearly a workaround method until I figure how to access the clicked on Offer object
            offerWasInvestedIn = true;
            System.out.println(offerWasInvestedIn);
        }
    }

}
