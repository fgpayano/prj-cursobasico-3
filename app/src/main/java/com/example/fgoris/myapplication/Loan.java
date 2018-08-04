package com.example.fgoris.myapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Loan {

    private float amount;
    private float interest;
    private int frequency;
    private ArrayList<Fee> fees;
    int startNextDateFrom;

    private static final float PERCENT = 100;

    public Loan(float amount, float interest, int frequency) {
        this.amount = amount;
        this.interest = interest;
        this.frequency = frequency;
    }

    private float calc () {

        float i;
        float fee;

        i = (this.interest / Loan.PERCENT);
        fee = (i * this.amount);
        fee = fee / (1 - (float)Math.pow(1 + i, -this.frequency));

        return fee;
    }

    public ArrayList<Fee> renderFromDate(int year, int month, int dayOfMonth)
    {
        float fee = this.calc();
        fees = new ArrayList<>();

        for(int i = 0; i <= this.frequency; i++)
        {
            fees.add(new Fee(fee, Carbon.addDaysFromDate(startNextDateFrom, year, month, dayOfMonth)));

            startNextDateFrom += 15;
        }

        return fees;
    }
}