package com.example.florian.p2p_lender;

/**
 * Created by flo.
 */

/**
 * POJO class
 */

public class Offer {

    private String offerName;
    private String beschreibung;
    private String borrower;
    private String investor = null;
    private int laufzeit;
    private int betrag;
    private String offerBewertung;



    private int interestRate;

    public Offer(String offerName, String beschreibung, String userMail, int laufzeit, int betrag, String userRating) {
        setOfferName(offerName);
        setBeschreibung(beschreibung);
        setBorrower(userMail);
        setLaufzeit(laufzeit);
        setBetrag(betrag);
        setOfferBewertung(userRating);
        setInterestRate(userRating);
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String userRating) {
        if (userRating.equals("bad")){
            interestRate = 9;
        }else if (userRating.equals("neutral")){
            interestRate = 5;
        }else{
            interestRate = 3;
        }
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
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

    public int getLaufzeit() {
        return laufzeit;
    }

    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }

    public int getBetrag() {
        return betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }

    public String getOfferBewertung() {
        return offerBewertung;
    }

    public void setOfferBewertung(String offerBewertung) {
        this.offerBewertung = offerBewertung;
    }
}
