package com.example.locationremindersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;


public class Date_Time extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    EditText mtitle,mmessage;
    Button mbtndate,mcancle;
    TextView mresult;
    Calendar now = Calendar.getInstance();
   int day,month,year,hour,minute;
   int mday,mmonth,myear,mhour,mminute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__time);
        mbtndate = findViewById(R.id.btnDate);
        mresult  = findViewById(R.id.Result);

        mbtndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                year =c.get(Calendar.YEAR);
                month =c.get(Calendar.MONTH);
                day =c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(Date_Time.this,Date_Time.this,year,month,day);
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker,int i,int i1,int i2) {
        myear = i;
        mmonth=i1+i;
        mday  =i2;
        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR);
        minute=c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(Date_Time.this,Date_Time.this,hour,minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int i, int i1) {
        mhour   =i;
        mminute =i1;

        Toast.makeText(Date_Time.this,
                "Year "+myear+
                " Month "+mmonth +
                " day "+mday+
                " Hours "+mhour+
                " Minute "+mminute,Toast.LENGTH_LONG).show();
    }
}
