package com.example.florian.p2p_lender;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button logIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn = (Button)findViewById(R.id.logInButton);
        logIn.setOnClickListener(this);

        signUp = (Button)findViewById(R.id.signUpButton);
        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == logIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        else if (v == signUp) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}



