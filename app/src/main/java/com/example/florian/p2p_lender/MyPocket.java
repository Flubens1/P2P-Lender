package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.florian.p2p_lender.R.id.investViewButton;
//import static com.example.florian.p2p_lender.R.id.investmentsButton;
import static com.example.florian.p2p_lender.R.id.start;

public class MyPocket extends AppCompatActivity implements View.OnClickListener{

    Button newOffer;
    Button listViewButton;

    Context ctx = this;
    ConstraintLayout investLayout;
    ConstraintLayout loansLayout;

    TextView totalInvestMents;
    TextView totalAmount;
    TextView totalLoans;
    TextView totalLoansAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pocket);

        newOffer = (Button)findViewById(R.id.offerButton);
        newOffer.setOnClickListener(this);
        listViewButton = (Button)findViewById(R.id.listViewButton);
        listViewButton.setOnClickListener(this);

        investLayout = (ConstraintLayout) findViewById(R.id.investViewButton);
        investLayout.setOnClickListener(this);
        loansLayout = (ConstraintLayout) findViewById(R.id.loansViewButton);
        loansLayout.setOnClickListener(this);



        // InvestmentsButton mit Daten setten
        totalInvestMents = (TextView)findViewById(R.id.count_invests_textView);
        totalAmount = (TextView)findViewById(R.id.amount_invests_textView);
        PersistOffers persistOffers = new PersistOffers(ctx);
        if (persistOffers.getOfferList() != null) {
            ArrayList<Offer> offers = persistOffers.getOfferList();
            int totalInvestments_ = 0;
            int totalamount = 0;
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            String mail = pref.getString("mail", "");
            for (Offer each : offers) {
                if (each.getInvestor()!=null) {
                    if (each.getInvestor().equals(mail)) {
                        totalInvestments_++;
                        totalamount += each.getBetrag();
                    }

                }

            }
            totalInvestMents.setText("Investments: " + String.valueOf(totalInvestments_));
            totalAmount.setText("Amount: " + String.valueOf(totalamount));
        }

        //LoansButton mit Daten setten
        totalLoans = (TextView) findViewById(R.id.count_loans_textView);
        totalLoansAmount = (TextView) findViewById(R.id.amount_loans_textView);
        if (persistOffers.getOfferList() != null) {
            ArrayList<Offer> mOffers = persistOffers.getOfferList();
            int totalLoansInt = 0;
            int totalLoansAmountInt = 0;
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            String mail = pref.getString("mail", "");
            for (Offer each : mOffers) {
                    if (each.getBorrower().equals(mail)) {
                        totalLoansInt++;
                        totalLoansAmountInt += each.getBetrag();
                    }
            }
            totalLoans.setText("Loans: " + String.valueOf(totalLoansInt));
            totalLoansAmount.setText("Amount: " + String.valueOf(totalLoansAmountInt));
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mypocketmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, MyProfile.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                ArrayList<Offer> offers = pOffers.getOfferList();
                for (int i = offers.size()-1; i >= 0; i--) {
                    if (offers.get(i).getInvestor() != null) {
                        offers.remove(offers.get(i));
                    }
                }
                if (offers.size() == 0) {
                    CharSequence text = "There are no open Offers to show";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(this, LoanList.class);
                    startActivity(intent);
                }
            }
        }


        if (v == investLayout){
            PersistOffers pOffers = new PersistOffers(ctx);

            if (pOffers.getOfferList() == null) {

                CharSequence text = "There are no Investments to show";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ctx, text, duration);
                toast.show();

            } else {

                ArrayList<Offer> offers = pOffers.getOfferList();
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                String mail = pref.getString("mail", "");
                boolean found = false;
                for (int i = offers.size()-1; i >= 0; i--) {

                    if (offers.get(i).getInvestor() != null) {

                        if (!offers.get(i).getInvestor().equals(mail)) {
                            offers.remove(offers.get(i));
                        } else {
                            found = true;
                        }
                    }
                    if (offers.get(i).getInvestor() == null) {
                        offers.remove(offers.get(i));
                    }
                }

                if (found) {
                    Intent intent = new Intent(this, MyInvestments.class);
                    startActivity(intent);
                }

                if (offers.size() == 0) {
                    CharSequence text = "You have no Investments";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                } else if (found){
                    Intent intent = new Intent(this, MyInvestments.class);
                    startActivity(intent);

                } else {
                    CharSequence text = "You have no Investments";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                }

            }

        }

        if (v == loansLayout){
            PersistOffers pOffers = new PersistOffers(ctx);
            if (pOffers.getOfferList() == null) {
                CharSequence text = "There are no Offers to show";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ctx, text, duration);
                toast.show();
            } else {
                ArrayList<Offer> offers = pOffers.getOfferList();
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                String mail = pref.getString("mail", "");
                for (int i = offers.size()-1; i >= 0; i--) {
                    if (!offers.get(i).getBorrower().equals(mail)) {
                        offers.remove(offers.get(i));
                    }
                }
                if (offers.size() == 0) {
                    CharSequence text = "You have no Loans";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ctx, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(this, MyLoans.class);
                    startActivity(intent);
                }
            }

        }

    }
}
