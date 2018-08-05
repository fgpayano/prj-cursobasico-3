package com.example.fgoris.myapplication;

import java.util.ArrayList;

public class Loan {

    private float amount;
    private float interest;
    private float frequency;
    private int startNextDateFrom;

    private static final float PERCENT = 100;

    Loan(float amount, float interest, float frequency) {
        this.amount = amount;
        this.interest = interest;
        this.frequency = frequency;
    }

    private float calc () {

        float fee;

        fee = this.amount * (this.interest / Loan.PERCENT) * this.frequency;
        fee = (this.amount + fee) / this.frequency;

        return fee;
    }

    public ArrayList<Fee> renderFromDate(int year, int month, int dayOfMonth)
    {
        float fee = this.calc();
        ArrayList<Fee> fees = new ArrayList<>();

        for(int i = 0; i <= this.frequency; i++)
        {
            fees.add(new Fee(fee, Carbon.addDaysFromDate(startNextDateFrom, year, month, dayOfMonth)));

            startNextDateFrom += 15;
        }

        return fees;
    }
}