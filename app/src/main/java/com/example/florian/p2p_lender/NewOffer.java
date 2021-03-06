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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by flo.
 */


/*
**
* This activity is accessed by pressing the "New Offer" button inside MyPocket.
* Input is taken from the user (and SharedPreferences) and used to create an Offer object,
* which in turn is then persistently saved in PersistOffers.
* Afterwards, the user is being forwarded to the MyPocket activity.
 */
public class NewOffer extends AppCompatActivity{

    Button saveButton;
    EditText input;
    EditText name;
    EditText laufzeit;
    EditText betrag;



    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);

        name = (EditText)findViewById(R.id.editTextName);
        input = (EditText)findViewById(R.id.inputText);
        laufzeit = (EditText)findViewById(R.id.newOfferLaufzeit);
        betrag = (EditText)findViewById(R.id.newOfferBetrag);

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String mail = pref.getString("mail", "");
        final String offerBewertung = pref.getString("rating", "");

        saveButton = (Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Offer o = new Offer(name.getText().toString(), input.getText().toString(), mail, Integer.parseInt(laufzeit.getText().toString()), Integer.parseInt(betrag.getText().toString()), offerBewertung);
                o.setBorrower(mail);

                ctx.getApplicationContext();
                PersistOffers persistOffers = new PersistOffers(ctx);
                persistOffers.saveOffer(ctx, o);
                CharSequence text = "Congratulations you created a new offer";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ctx, text, duration);
                toast.show();
                Intent intent = new Intent(v.getContext(), MyPocket.class);
                startActivity(intent);

            }
        });


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
