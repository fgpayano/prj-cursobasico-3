package com.example.fgoris.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class FeeAdapter extends ArrayAdapter<Fee> {

    private Context mContext;
    private int mResource;

    public FeeAdapter(@NonNull Context context, int resource, @NonNull List<Fee> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String date = getItem(position).getPaymentDate();
        float amount = getItem(position).getTotalAmount();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView textDate = convertView.findViewById(R.id.fee_payment_date);
        TextView textAmount = convertView.findViewById(R.id.fee_amount);

        textDate.setText(date);
        textAmount.setText(Float.toString(amount));

       // DecimalFormat format = new DecimalFormat("RD$#.00");

        //textAmount.setText(format.format(amount));

        return convertView;
    }
}





























/*

public class FeeAdapter extends ArrayAdapter<Fee> {

    private Context feeContext;
    private List<Fee> fees = new ArrayList<>();

    public FeeAdapter(@NonNull Context context, ArrayList<Fee> list) {
        super(context, 0, list);
        feeContext = context;
        fees = list;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View list = convertView;
        Fee currentFee = fees.get(position);

        if(list==null) {
            list = LayoutInflater.from(feeContext).inflate(R.layout.fee_item, parent, false);
        }

        TextView amount = list.findViewById(R.id.fee_amount);
        amount.setText(Float.toString(currentFee.getTotalAmount()));

        TextView payment_date = list.findViewById(R.id.fee_payment_date);
        payment_date.setText(payment_date.toString());

        return list;
    }

}

 */