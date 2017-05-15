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
import java.util.List;


/**
 * Created by martin on 05/05/17.
 */

public class PersistUser {
    String filename = "userList.json";
    Gson gson = new Gson();
    JsonReader reader = null;
    Type OFFER_TYPE = new TypeToken<ArrayList<User>>(){}.getType();
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
            System.out.println("catch FileNotFound => return null");
            return null;
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
        saveUserList(userList);
    }

    public void saveUserList(ArrayList<User> userList){
        FileOutputStream fileOutputStream;
        String offerJsonList = gson.toJson(userList);

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

    public User getUser(String mail){
        ArrayList<User> userList = getUserList();

        for (User u:
             userList) {
            if (u.getMailAddress().equals(mail)){
                return u;
            }
        }


        return null;
    }


    public void changeUser(User user){
        ArrayList<User> userList = getUserList();
        System.out.println("New User ( "+ user.getMailAddress() +") rating: "+user.getRating()+"\n");
        for (User u:
             userList) {
            if (user.getMailAddress().equals(u.getMailAddress())){
                u = user;
                saveUserList(userList);
//                return;
            }
            for(User uU: userList){
                System.out.println("User: " + u.getMailAddress()+ "         Rating: "+u.getRating()+"\n");
            }
        }



    }
}
