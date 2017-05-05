package com.example.florian.p2p_lender;

import android.content.Context;
import android.util.JsonReader;

import com.example.florian.p2p_lender.Offer;
import com.example.florian.p2p_lender.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by martin on 05/05/17.
 */

public class PersistUser {
    String filename = "userList.json";
    Gson gson = new Gson();
    JsonReader reader = null;
    Type OFFER_TYPE = new TypeToken<ArrayList<Offer>>(){}.getType();
    Context ctx;

    public PersistUser(Context ctx) {
        this.ctx = ctx;
    }

    public ArrayList<User> getUserList(){
        String text = "";
        ArrayList<User> userList = null;


        try {

            System.out.println("try to fetch json");
            FileInputStream fileInputStream = ctx.openFileInput(filename);
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            text = new String(buffer);
            userList = gson.fromJson(text, OFFER_TYPE);
            System.out.println(userList.size());
            for (User u: userList){
                System.out.println(u.toString());
            }
            return userList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }



    public void saveUser(Context ctx, User user){
        ArrayList<User> userList;
        userList = getUserList();
        if(userList == null){
            userList = new ArrayList<User>();
        }
        userList.add(user);
        System.out.println("loaded User list");

        FileOutputStream fileOutputStream;
        String offerJsonList = gson.toJson(userList);
        System.out.println(offerJsonList);
        for (User u: userList){
            System.out.println(u.toString());
        }

        try {
            fileOutputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            fileOutputStream.write(offerJsonList.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
