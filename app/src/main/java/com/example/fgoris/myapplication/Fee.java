package com.example.fgoris.myapplication;

public class Fee {

    private float totalAmount;
    private String paymentDate;

    public Fee(float totalAmount, String paymentDate) {
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

}
