package com.example.florian.p2p_lender;

/**
 * Created by florian on 01.05.17.
 */

public class Offer {

    private String offerName;
    private String beschreibung;


    public Offer(String offerName, String beschreibung) {
        this.offerName = offerName;
        this.beschreibung = beschreibung;
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
