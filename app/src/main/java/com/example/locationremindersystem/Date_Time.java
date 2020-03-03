package com.example.locationremindersystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.allyants.notifyme.NotifyMe;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;


import java.util.Calendar;


public class Date_Time extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;
    EditText title,message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__time);
        Button btndate = findViewById(R.id.btnDate);
        Button cancel  = findViewById(R.id.cancle);
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);

        dpd = DatePickerDialog.newInstance(
                Date_Time.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        tpd = TimePickerDialog.newInstance(
                Date_Time.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false
        );

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                NotifyMe.cancel(getApplicationContext(),"test");
            }
        });
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(),"DatePickerDialog");
            }
        });
    }

    @Override
    public void onDateSet (DatePickerDialog view,int year, int monthOfYear, int dayOfMonth){
        now .set(Calendar.YEAR,year);
        now.set(Calendar.MONTH,monthOfYear);
        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        tpd.show(getFragmentManager(),"TimePickerDialog");
    }

    public void onTimeSet (TimePickerDialog view,int hourOfDay, int minite, int second){
        now.set(Calendar.HOUR_OF_DAY,hourOfDay);
        now.set(Calendar.MINUTE,minite);
        now.set(Calendar.SECOND,second);

        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(title.getText().toString())
                .content(message.getText().toString())
                .color(25,0,0,255)
                .led_color (255,255,255,255)
                .time(now)
                .addAction(new Intent(),"Snooze",false)
                .key("test")
                .addAction(new Intent(),"Dismiss",true,false)
                .addAction(new Intent(),"Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();
    }
}
