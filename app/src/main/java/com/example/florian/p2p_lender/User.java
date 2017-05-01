package com.example.florian.p2p_lender;

import java.util.UUID;

/**
 * Created by aldinbradaric on 01/05/17.
 */

public class User {
    private String mailAddress, password;
    private UUID id;

    public User(String mail, String password) {
        setMailAddress(mail);
        setPassword(password);
        setId();
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
