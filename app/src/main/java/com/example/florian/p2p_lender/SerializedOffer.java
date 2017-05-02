package com.example.florian.p2p_lender;

/**
 * Created by florian on 02.05.17.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florian
 */

public class SerializedOffer implements Serializable{

    private String path = "/home/florian/NetBeansProjects/OfferSerializeTest/src/offerserializetest/offer.ser";

    //private String path = null;

    private FileInputStream fileIS;

    private ObjectInputStream objectIS;

    private FileOutputStream fileOS;

    private ObjectOutputStream objectOS;

    public SerializedOffer() {

    }

    public ArrayList<Offer> getOfferList() {

        ArrayList<Offer> offerList = null;

        try {

            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            fileIS = new FileInputStream(file);
            objectIS = new ObjectInputStream(fileIS);
            offerList = (ArrayList<Offer>)objectIS.readObject();

            objectIS.close();
            fileIS.close();
            return offerList;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerializedOffer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(SerializedOffer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializedOffer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        finally{
            if(objectIS!=null || fileIS!=null){
                try {
                    objectIS.close();
                    fileIS.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    public void speichereOffer(Offer offer) {

        try {

            ArrayList<Offer> offerList = getOfferList();

            if (offerList == null) {
                offerList = new ArrayList<Offer>();
            }

            //ArrayList<Offer> offerList = new ArrayList<Offer>();

            offerList.add(offer);


            File file = new File(path);
            fileOS = new FileOutputStream(file);
            objectOS = new ObjectOutputStream(fileOS);

            objectOS.writeObject(offerList);

            objectOS.close();
            fileOS.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerializedOffer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializedOffer.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


}