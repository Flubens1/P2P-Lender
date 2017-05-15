package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;
/*
** This particular activity is displayed when a row inside the ListView (mInvestments, mLoans and so on)
* is clicked.
* By making use of SharedPreferences and Intent, the relevant data is fetched and displayed.
* Additionally, a Button named "Invest" is used to change the "Investor" field of the Offer class
* to that of the one who pressed it and save it persistently.
 */
public class DetailedOffer extends AppCompatActivity implements View.OnClickListener{
    TextView offerTitle, offerDescription, actualOfferTitle, actualOfferDescription;
    TextView actualOfferBetrag;
    TextView actualLaufzeit;
    Button investButton;

    ArrayList<Offer> offers = null;
    Offer actualOffer = null;
    String investor = "";
    Context ctx = this;
    int index = 0;
    PersistOffers persistOffers = new PersistOffers(ctx);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_offer);
        System.out.println("inside onCreate of DetailedOffer");

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        //String mailAddress = pref.getString("userMailAddress", "");
        //String userID = pref.getString("UUID", "");
        //String mailSavedInOffer = pref.getString("mail", "");
        //String actualDescription = pref.getString("offerDescription", "");
        investor = pref.getString("mail", "");
        String actualTitle = getIntent().getStringExtra("offerTitle");


        offers = persistOffers.getOfferList();

        for (Offer each : offers) {
            if (each.getOfferName().equals(actualTitle)) {
                actualOffer = each;
                index = offers.indexOf(each);
            }
        }

        offerTitle = (TextView) findViewById(R.id.offerTitle);
        actualOfferTitle = (TextView) findViewById(R.id.actualTitle);
        actualOfferTitle.setText(actualOffer.getOfferName());

        offerDescription = (TextView) findViewById(R.id.offerDescription);
        actualOfferDescription = (TextView) findViewById(R.id.actualDescription);
        actualOfferDescription.setText(actualOffer.getBeschreibung());

        actualLaufzeit = (TextView)findViewById(R.id.detailedOfferActualRunningTime);
        actualLaufzeit.setText(String.valueOf(actualOffer.getLaufzeit()));

        actualOfferBetrag = (TextView)findViewById(R.id.detailedOfferActualBetrag);
        actualOfferBetrag.setText(String.valueOf(actualOffer.getBetrag()));


        investButton = (Button) findViewById(R.id.investInOffer);
        investButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v ) {
        if (v == investButton) {

            offers.get(index).setInvestor(investor);
            persistOffers.saveOfferList(ctx, offers);

            CharSequence text = "Congratulations you invested in this offer";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ctx, text, duration);
            toast.show();

            Intent intent = new Intent(this, MyPocket.class);
            startActivity(intent);

        }
    }

}
