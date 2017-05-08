package com.example.florian.p2p_lender;

import java.util.UUID;

/**
 * Created by aldinbradaric on 01/05/17.
 */

public class User {

    private String userName;
    private String mailAddress, password;
    private UUID id;
    private int rating = 0;

    public User(String mail, String password, String userName) {
        this.userName = userName;
        this.mailAddress = mail;
        this.password = password;
        setId();
        FakeRating fake = new FakeRating();
        rating = fake.getFakeRating();
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }





}
