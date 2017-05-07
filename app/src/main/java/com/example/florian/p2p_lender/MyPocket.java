package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyPocket extends AppCompatActivity implements View.OnClickListener{

    Button newOffer;
    Button listViewButton;
    Button myProfileButton;
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

        if (v==myProfileButton) {
            Intent intent = new Intent(this, MyProfile.class);
            startActivity(intent);
        }
    }
}
