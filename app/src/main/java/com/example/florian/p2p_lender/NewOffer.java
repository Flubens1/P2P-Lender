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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewOffer extends AppCompatActivity{

    Button saveButton;
    EditText input;
    EditText name;
    EditText laufzeit;
    EditText betrag;

    String mail = "";

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);

        name = (EditText)findViewById(R.id.editTextName);
        input = (EditText)findViewById(R.id.inputText);
        laufzeit = (EditText)findViewById(R.id.newOfferLaufzeit);
        betrag = (EditText)findViewById(R.id.newOfferBetrag);

        //final String filename = name.getText().toString() + ".txt";

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        mail = pref.getString("mail", "");

        System.out.println("mail = " + mail);


        saveButton = (Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Offer o = new Offer(name.getText().toString(), input.getText().toString(), mail, Integer.parseInt(laufzeit.getText().toString()), Integer.parseInt(betrag.getText().toString()));
                o.setBorrower(mail);
                //todo pass offerTitle to sharedPreference
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("offerTitle", o.getOfferName());
                editor.apply();

                ctx.getApplicationContext();
                PersistOffers persistOffers = new PersistOffers(ctx);
                persistOffers.saveOffer(ctx, o);
//                saveFile(filename, input.getText().toString());
                CharSequence text = "Congratulations you created a new offer";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(ctx, text, duration);
                toast.show();
                Intent intent = new Intent(v.getContext(), MyPocket.class);
                startActivity(intent);

            }
        });




//        output = (TextView)findViewById(R.id.show);
//        showButton = (Button)findViewById(R.id.load);
//        showButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                output.setText(readFile(filename));
//
//            }
//        });


//        goToLoanList = (Button)findViewById(R.id.goToLoanList);
//        goToLoanList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), LoanList.class);
//                intent.putExtra("filename", filename);
//                startActivity(intent);
//            }
//        });
    }

//    public void saveFile(String file, String text) {
//        try {
//
//            FileOutputStream oS = openFileOutput(file, Context.MODE_PRIVATE);
//
//            Toast.makeText(NewOffer.this, "Success saving file!", Toast.LENGTH_LONG);
//
//            oS.write(text.getBytes());
//            oS.close();
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(NewOffer.this, "Error saving file!", Toast.LENGTH_LONG);
//        }
//    }
//
//    public String readFile(String file) {
//
//        String text = "";
//
//        try {
//
//            FileInputStream fis = openFileInput(file);
//            int size = fis.available();
//            byte[] buffer = new byte[size];
//            fis.read(buffer);
//            fis.close();
//            text = new String(buffer);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return text;
//    }

}
