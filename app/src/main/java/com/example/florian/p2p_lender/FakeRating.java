package com.example.florian.p2p_lender;


import java.util.Random;

public class FakeRating {


    public int getFakeRating(){
        return 1;
    }



    public String getRating(){
        final double rangeMinMoney = -1000.00;
        final double rangeMinSave = 0;
        final double rangeMaxMoney = 1000.00;
        final int rangeMinAge = 18;
        final int rangeMaxAge = 99;
        final double avgMoneyPerMonth;
        final double avgSavePerMonth;
        int age;

        Random r = new Random();
        avgMoneyPerMonth = rangeMinMoney + (rangeMaxMoney - rangeMinMoney) * r.nextDouble();
        avgSavePerMonth = rangeMinSave + (rangeMaxMoney - rangeMinSave) * r.nextDouble();
        age = r.nextInt(rangeMaxAge - rangeMinAge) + rangeMinAge;

        System.out.println("Money: "+avgMoneyPerMonth);
        System.out.println("Save: "+avgSavePerMonth);
        System.out.println("Age: "+age);

        return "Good";
    }

}
