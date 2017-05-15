package com.example.florian.p2p_lender;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity implements View.OnClickListener{

    Context ctx = this;
    Button ratingButton;
    TextView ratingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String mail = pref.getString("mail", "");
        String name = pref.getString("userName", "");
        String rating = pref.getString("rating", "");

        System.out.println("MyProfile:");
        System.out.println(name);

        TextView email = (TextView)findViewById(R.id.emailView);
        email.setText(mail);

        TextView nameTextView = (TextView)findViewById(R.id.userNameTextView);
        nameTextView.setText(name);

        ratingView = (TextView) findViewById(R.id.ratingView);
        ratingView.setText(rating);
        if(rating.equals("bad")){
            ratingView.setTextColor(Color.RED);
        }else if (rating.equals("neutral")){
            ratingView.setTextColor(Color.DKGRAY);
        } else {
            ratingView.setTextColor(Color.GREEN);
        }
        ratingButton = (Button)findViewById(R.id.newRatingButton);
        ratingButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view){
        if (view == ratingButton){
            PersistUser persistUser = new PersistUser(this);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = pref.edit();
            String mail = pref.getString("mail", "");
            User user = persistUser.getUser(mail);
            FakeRating fakeRating = new FakeRating();
            String newRating = fakeRating.getRating();
            ratingView.setText(newRating);
            user.setRating(newRating);
            persistUser.changeUser(user);
            editor.putString("mail", newRating);
            if(newRating.equals("bad")){
                ratingView.setTextColor(Color.RED);
            }else if (newRating.equals("neutral")){
                ratingView.setTextColor(Color.DKGRAY);
            } else {
                ratingView.setTextColor(Color.GREEN);
            }


        }
    }


}
