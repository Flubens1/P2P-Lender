package com.example.florian.p2p_lender;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by aldinbradaric on 01/05/17.
 */

/**
 * For now this class isn't being used. Will most likely be deleted soon
 */
public class SerializedUser {

    private File data;                  /* this variable contains the path to the stored files */
    private FileOutputStream fileOuS;     /* same as above, just with output */
    private ObjectOutputStream objectOuS; /* same as above, just with output */

    public SerializedUser() {
        /* Generally speaking, this constructor allows us to create a file in the root directory
         * of the project, having it stay there for good thus
          * creating a so called absolute path*/

        try {
            String absolutePathString = new File("").getAbsolutePath();
            System.out.println(absolutePathString);
            absolutePathString = absolutePathString.concat("\\account.ser");
            data = new File(absolutePathString);
            //System.out.println(data);

            if(!data.exists()) {
                System.out.println("file not found. creating.");
                data.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getObjectByID(UUID ID) {
        ArrayList<User> objectList = getObjectList();
        User temp;

        if (objectList == null) {
            return null;
        }


        /* equals needs to be used to compared UUID objects*/
        Iterator<User> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp.getId().equals(ID)) {
                return temp;
            }
        }
        return null;
    }




    public void saveObject(User entry) {

        try {
            //TODO need to implement saving test code here

        }   catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<User> getObjectList() {
        /* create new ArrayList of type Object */
        //ArrayList<Account> objectList = new ArrayList<Account>();
        /* create new file with input path */
        //File file = new File(path);

        /* Writing the declaration fo the streams inside the brackets makes sure that
         * the possible exception is thrown in Java 8 */
        try (FileInputStream fileInS = new FileInputStream(data);
             ObjectInputStream objectInS = new ObjectInputStream(fileInS)){


            /* the inputStream mechanism allows us to read streams of bytes from a file.
            * FileReader class might be an option here (used for streams of chars)*/

            /* here you read all the objects inside the file inserted earlier*/
            ArrayList<User> objectList = (ArrayList<User>) objectInS.readObject();
            /* Here I might need to close the streams; possibly with finally statement */
            System.out.println("Accounts found " + objectList.size());

            return objectList;

        }   catch (EOFException e){
            e.printStackTrace();
            return null;
        }   catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
