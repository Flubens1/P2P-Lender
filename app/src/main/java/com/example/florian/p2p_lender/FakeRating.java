package com.example.florian.p2p_lender;


/*
*This class fakes the connection to the bank account and uses random generated values to deliver a rating
* Because we are still waiting for an answer from Mr. Möller it is still implementet the way it is now
*/

import java.util.Random;

public class FakeRating {



    public String getRating(){
        final double rangeMinMoney = -100.00;
        final double rangeMinSave = 0;
        final double rangeMaxMoney = 1000.00;
        final int rangeMinAge = 18;
        final int rangeMaxAge = 99;
        double avgMoneyPerMonth;
        double avgSavePerMonth = 0;
        int age;
        String rating = "neutral";

        Random r = new Random();
        avgMoneyPerMonth = rangeMinMoney + (rangeMaxMoney - rangeMinMoney) * r.nextDouble();
        if(avgMoneyPerMonth > 0){
            avgSavePerMonth = rangeMinSave + (avgMoneyPerMonth - rangeMinSave) * r.nextDouble();

        }
        age = r.nextInt(rangeMaxAge - rangeMinAge) + rangeMinAge;

        System.out.println("Money: "+avgMoneyPerMonth);
        System.out.println("Save: "+avgSavePerMonth);
        System.out.println("Age: "+age);

        double sum = avgMoneyPerMonth + avgSavePerMonth;

        if (age > 65){
            rating = "bad";
            return rating;
        }
        if(avgMoneyPerMonth < 0 ){
            rating = "bad";
        } else if (avgMoneyPerMonth < 100){
            rating = "neutral";
        }else {
            rating = "good";
        }


        return rating;
    }

}
