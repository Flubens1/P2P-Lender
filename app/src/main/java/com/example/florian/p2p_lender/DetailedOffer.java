package com.example.florian.p2p_lender;

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
    TextView offerTitle, offerDescription;
    Button investButton;

    boolean offerWasInvestedIn = false;

    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
    String mailAddress = pref.getString("userMailAddress", "");
    String userID = pref.getString("UUID", null);
    String mailSavedInOffer = pref.getString("mail", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);

        offerTitle = (TextView) findViewById(R.id.offerTitle);
        offerDescription = (TextView) findViewById(R.id.offerDescription);
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
            if (!mailAddress.equals(mailSavedInOffer)) {
                offerWasInvestedIn = true;
            }
        }
    }

}
