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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button btn_start_date;
    private Spinner spinner;
    private Calendar calendar;
    private DatePickerDialog start_date;
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

            btn_start_date.setText(dateFormat.format(calendar.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        start_date = new DatePickerDialog(this, mDatePickerListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        findViews();
        setAdapters();
        setListeners();
    }

    public void findViews() {
        button = findViewById(R.id.button);
        btn_start_date = findViewById(R.id.btn_start_date);
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
        btn_start_date.setOnClickListener(view -> start_date.show());

        button.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);

            float cinterest = Float.parseFloat(interestRate.getText().toString());
            float camount = Float.parseFloat(amount.getText().toString());

            intent.putExtra("loan_amount", cinterest);
            intent.putExtra("loan_interest_rate", camount);

            intent.putExtra("loan_frequency", spinnerFrequency);
            intent.putExtra("loan_date_year", calendarYear);
            intent.putExtra("loan_date_month", calendarMonth);
            intent.putExtra("loan_date_day", calendarDay);

            startActivity(intent);
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerFrequency = spinner.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
};
