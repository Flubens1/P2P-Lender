package com.example.florian.p2p_lender;

import java.util.UUID;

/**
 * POJO class
 */

public class User {

    private String userName;
    private String mailAddress, password;
    private UUID id;
    private String rating = "";

    public User(String mail, String password, String userName) {
        setUserName(userName);
        setMailAddress(mail);
        setPassword(password);
        setId();
        FakeRating rate = new FakeRating();
        rating = rate.getRating();
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
