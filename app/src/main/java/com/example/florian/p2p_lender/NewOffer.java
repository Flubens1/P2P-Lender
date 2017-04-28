package com.example.florian.p2p_lender;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewOffer extends AppCompatActivity{

    Button saveButton;
    Button showButton;
    EditText input;
    TextView output;
    String filename = "myfile.txt";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);

        input = (EditText)findViewById(R.id.input);
        saveButton = (Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveFile(filename, input.getText().toString());

            }
        });

        output = (TextView)findViewById(R.id.show);
        showButton = (Button)findViewById(R.id.load);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                output.setText(readFile(filename));

            }
        });

    }

    public void saveFile(String file, String text) {
        try {

            FileOutputStream oS = openFileOutput(file, Context.MODE_PRIVATE);

            Toast.makeText(NewOffer.this, "Success saving file!", Toast.LENGTH_LONG);

            oS.write(text.getBytes());
            oS.close();



        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(NewOffer.this, "Error saving file!", Toast.LENGTH_LONG);
        }
    }

    public String readFile(String file) {

        String text = "";

        try {

            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return text;
    }

}
