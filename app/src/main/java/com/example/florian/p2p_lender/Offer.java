package com.example.florian.p2p_lender;

/**
 * Created by florian on 01.05.17.
 */

public class Offer {

    private String offerName;
    private String beschreibung;
    private String lender;
    private String investor;


    public Offer(String offerName, String beschreibung, String userMail) {
        this.offerName = offerName;
        this.beschreibung = beschreibung;
        lender = userMail;

    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
