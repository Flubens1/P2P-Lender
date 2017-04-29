package com.example.florian.p2p_lender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoanList extends AppCompatActivity {


    String filename = "myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);


        String[] user = {"Martin" + readFile(filename), "Aldin", "Flo", "Candy", "Andy", "Minty"};
        ListAdapter adapter = new CustomAdapter(this, user);
        ListView listView = (ListView)findViewById(R.id.loanListView);
        listView.setAdapter(adapter);

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
