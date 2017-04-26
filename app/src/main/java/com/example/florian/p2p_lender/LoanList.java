package com.example.florian.p2p_lender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class LoanList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);


        String[] user = {"Martin", "Aldin", "Flo", "Candy", "Andy", "Minty"};
        ListAdapter adapter = new CustomAdapter(this, user);
        ListView listView = (ListView)findViewById(R.id.loanListView);
        listView.setAdapter(adapter);

    }
}
