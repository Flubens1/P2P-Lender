package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String name = pref.getString("mail", "");
        System.out.println("MyProfile:");
        System.out.println(name);

        TextView email = (TextView)findViewById(R.id.emailView);
        email.setText(name);



    }
}
