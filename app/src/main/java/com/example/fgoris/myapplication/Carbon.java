package com.example.fgoris.myapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Carbon {

    public static String addDaysFromDate(int days, int year, int month, int dayOfMonth) {

        SimpleDateFormat date = new SimpleDateFormat("dd MMM yyyy", new Locale("es"));

        GregorianCalendar calendar = new GregorianCalendar(year, month, dayOfMonth);

        calendar.add(Calendar.DAY_OF_MONTH, days);

        return date.format(calendar.getTime());
    }
}