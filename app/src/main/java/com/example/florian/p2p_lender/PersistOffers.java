package com.example.florian.p2p_lender;


import android.content.Context;
import android.util.JsonReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by martin on 05/05/17.
 */
/*
    This class handles everything thats related with loading, saving and updating offers in the persistant files
 */

public class PersistOffers{
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
                        FileInputStream fileInputStream = ctx.openFileInput(filename);
                        int size = fileInputStream.available();
                        byte[] buffer = new byte[size];
                        fileInputStream.read(buffer);
                        fileInputStream.close();
                        text = new String(buffer);
                        offers = gson.fromJson(text, OFFER_TYPE);
                        return offers;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return offers;
            }



            public void saveOffer(Context ctx, Offer offer){
                ArrayList<Offer> offers;
                offers = getOfferList();
                if(offers == null){
                    offers = new ArrayList<Offer>();
                }
                offers.add(offer);

                FileOutputStream fileOutputStream;
                String offerJsonList = gson.toJson(offers);

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


            public void saveOfferList(Context ctx, ArrayList<Offer> offerList) {
                ArrayList<Offer> offers;
                offers = offerList;

                FileOutputStream fileOutputStream;
                String offerJsonList = gson.toJson(offers);

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
