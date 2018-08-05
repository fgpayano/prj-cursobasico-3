package com.example.fgoris.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private ListView list;
    private Loan loan;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        list = findViewById(R.id.list_view_component);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        float  loanAmount = bundle.getFloat("loan_amount");
        float loanInterestRate =  bundle.getFloat("loan_interest_rate");
        int loanFrequency = bundle.getInt("loan_frequency");

        loan = new Loan(loanAmount, loanInterestRate, loanFrequency);

        ArrayList<Fee> fees = loan.renderFromDate(bundle.getInt("loan_date_year"), bundle.getInt("loan_date_month"), bundle.getInt("loan_date_day"));

        FeeAdapter adapter = new FeeAdapter(this.getApplicationContext(), R.layout.fee_item, fees);

        list.setAdapter(adapter);
    }
}
