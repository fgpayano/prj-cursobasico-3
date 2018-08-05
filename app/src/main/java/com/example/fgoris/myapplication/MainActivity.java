package com.example.fgoris.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button btnStartDate;
    private Spinner spinner;
    private Calendar calendar;
    private DatePickerDialog startDate;
    private SimpleDateFormat dateFormat;
    private EditText amount;
    private EditText interestRate;
    private int calendarYear;
    private int calendarMonth;
    private int calendarDay;
    private int spinnerFrequency;

    private DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            calendarDay = day;
            calendarMonth = month;
            calendarYear = year;

            btnStartDate.setText(dateFormat.format(calendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        startDate = new DatePickerDialog(this, mDatePickerListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        findViews();
        setAdapters();
        setListeners();
    }

    public void findViews() {
        button = findViewById(R.id.button);
        btnStartDate = findViewById(R.id.btn_start_date);
        spinner = findViewById(R.id.spinner);
        amount = findViewById(R.id.amount);
        interestRate = findViewById(R.id.interest_rate);
    }

    public void setAdapters()
    {
        ArrayAdapter<CharSequence> frequencies = ArrayAdapter.createFromResource(this, R.array.frequencies, R.layout.support_simple_spinner_dropdown_item);

        frequencies.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(frequencies);
    }

    public void setListeners()
    {
        btnStartDate.setOnClickListener(view -> startDate.show());

        button.setOnClickListener(view -> {

                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);

                String sInterest = interestRate.getText().toString();
                String sAmount = amount.getText().toString();

                if (sInterest == null || sAmount == null || sInterest.isEmpty() || sAmount.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Favor de llenar los campos vacios.", Toast.LENGTH_SHORT).show();
                } else {
                    float cinterest = Float.parseFloat(sInterest);
                    float camount = Float.parseFloat(sAmount);

                    intent.putExtra("loan_amount", camount);
                    intent.putExtra("loan_interest_rate", cinterest);
                    intent.putExtra("loan_frequency", spinnerFrequency);
                    intent.putExtra("loan_date_year", calendarYear);
                    intent.putExtra("loan_date_month", calendarMonth);
                    intent.putExtra("loan_date_day", calendarDay);

                    startActivity(intent);
                }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerFrequency = spinner.getSelectedItemPosition() + 2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
};
