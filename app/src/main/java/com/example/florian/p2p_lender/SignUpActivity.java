package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
**
* The function of this activity is to allow the user to register an account by providing input data such as
* username, mail address and of course a password.
* The input is being checked on invalid data such as
* no input at all, too short a password (min length is 2) and a valid email (which is compared to a predefined pattern).
* If everything is in order, a new User object is created and persistently saved.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button signUp;
    EditText emailAddress, passwordInput, passwordConfirmation, userName;

    String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mailAddress, password ,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailAddress = (EditText)findViewById(R.id.mailInput);
        passwordInput = (EditText)findViewById(R.id.firstPasswordInput);
        passwordConfirmation = (EditText)findViewById(R.id.secondPasswordInput);
        userName = (EditText)findViewById(R.id.signUpName);

        signUp = (Button) findViewById(R.id.signUpButton);
        signUp.setOnClickListener(this);

    }




    @Override
    public void onClick (View v) {
        if (v == signUp) {

                //check username, email and password input
                if (passwordConfirmation.getText().toString().equals(passwordInput.getText().toString()) &&
                        emailAddress.getText().toString().matches(mailPattern) &&
                        passwordInput.getText().toString().length() > 1 &&
                        passwordConfirmation.getText().toString().length() > 1) {

                    mailAddress = emailAddress.getText().toString();
                    password = passwordInput.getText().toString();
                    username = userName.getText().toString();

                    User user = new User(mailAddress, password, username);
                    Context ctx = this;
                    ctx.getApplicationContext();

                    PersistUser pUser = new PersistUser(ctx);
                    pUser.saveUser(ctx, user);
                    String rating = user.getRating();
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("userName", username);
                    editor.putString("mail", mailAddress);
                    editor.putString("rating", rating);
                    editor.commit();

                    Intent intent = new Intent(this, MyPocket.class);
                    startActivity(intent);
                }
                else {
                    //AlertDialog used to display in case of incomplete input fields
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                    if (userName.getText().toString().length() == 0) {
                        alertDialog.setMessage("Username field must not be empty!");
                        alertDialog.show();
                    }

                    else if (!emailAddress.getText().toString().matches(mailPattern)) {
                        alertDialog.setMessage("Email address does not conform to standard");
                        alertDialog.show();
                    }

                    else if (passwordInput.getText().toString().length() <= 1) {
                        alertDialog.setMessage("Password needs to be at least 2 signs long!");
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
