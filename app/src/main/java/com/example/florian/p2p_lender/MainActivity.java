package com.example.florian.p2p_lender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn = (Button)findViewById(R.id.logInButton);
        logIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v==logIn) {
            Intent intent = new Intent(this, MyPocket.class);
            startActivity(intent);
        }

    }
}
