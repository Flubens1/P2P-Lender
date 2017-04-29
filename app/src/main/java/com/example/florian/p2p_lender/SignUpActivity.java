package com.example.florian.p2p_lender;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    Button signUp;
    EditText emailAddress, passwordInput, passwordConfirmation;
    String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = (Button) findViewById(R.id.signUpButton);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        if (v == signUp) {

                emailAddress = (EditText) findViewById(R.id.mailInput);
                passwordInput = (EditText) findViewById(R.id.firstPasswordInput);
                passwordConfirmation = (EditText) findViewById(R.id.secondPasswordInput);

                if (passwordConfirmation.getText().toString().equals(passwordInput.getText().toString()) &&
                        emailAddress.getText().toString().matches(mailPattern) &&
                        passwordInput.getText().toString().length() != 0 &&
                        passwordConfirmation.getText().toString().length() != 0) {

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                    if (!emailAddress.getText().toString().matches(mailPattern)) {
                        alertDialog.setMessage("Email address does not conform to standard");
                        alertDialog.show();
                    }

                    else if (passwordInput.getText().toString().length() == 0 ||
                            passwordConfirmation.getText().toString().length() == 0) {
                        alertDialog.setMessage("Password field is empty!");
                        alertDialog.show();
                    }

                    else if (!passwordConfirmation.getText().toString().equals(passwordInput.getText().toString())) {
                        alertDialog.setMessage("Password inputs didn't match up!");
                        alertDialog.show();
                    }
                }
        }
    }
}
