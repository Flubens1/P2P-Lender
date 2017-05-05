package com.example.florian.p2p_lender;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PersistOffers extends AppCompatActivity{
    String filename = "offers.json";
    Gson gson = new Gson();
    JsonReader reader = null;
    Type OFFER_TYPE = new TypeToken<ArrayList<Offer>>(){}.getType();
    Context ctx;

    public PersistOffers(Context ctx) {
        this.ctx = ctx;
    }

    public ArrayList<Offer> getOfferList(){
                String text = "";
                ArrayList<Offer> offers = null;


                try {
                    File file = new File(filename);
                    if (file.exists()){
                        System.out.println("try to fetch json");
                        FileInputStream fileInputStream = ctx.openFileInput(filename);
                        int size = fileInputStream.available();
                        byte[] buffer = new byte[size];
                        fileInputStream.read(buffer);
                        fileInputStream.close();
                        text = new String(buffer);
                        offers = gson.fromJson(text, OFFER_TYPE);
                        System.out.println(offers.size());
                        for (Offer o: offers){
                            System.out.println(o.toString());
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return offers;
            }



            public void saveOffer(Offer offer){
                ArrayList<Offer> offers = null;
                offers = getOfferList();
                if(offers == null){
                    offers = new ArrayList<Offer>();
                }
                offers.add(offer);
                System.out.println("loaded offer list");

                FileOutputStream fileOutputStream;
                String offerJsonList = gson.toJson(offers);
                System.out.println(offerJsonList);
                for (Offer o: offers){
                    System.out.println(o.toString());
                }

                try {
//                    System.out.println("Test1");
//                    System.out.println(filename);
                    fileOutputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
//                    System.out.println("Test2");
                    fileOutputStream.write(offerJsonList.getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }


}
