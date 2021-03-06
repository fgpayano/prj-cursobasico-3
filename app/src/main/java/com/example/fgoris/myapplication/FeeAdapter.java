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

    FeeAdapter(@NonNull Context context, int resource, @NonNull List<Fee> objects) {
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
        DecimalFormat format = new DecimalFormat("RD$#.00");
        textAmount.setText(format.format(amount));

        return convertView;
    }
}