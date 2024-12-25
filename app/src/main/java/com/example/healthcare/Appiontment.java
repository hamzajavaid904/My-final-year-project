package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Appiontment extends AppCompatActivity {
TextView tv;
EditText ed1,ed2,ed3,ed4;
Button btndate,btntime,btnback,btnapp;

private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;

ImageView imgback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appiontment);
      //  getSupportActionBar().hide();
        tv = findViewById(R.id.textViewApp);
        ed1 = findViewById(R.id.edAppFN);
        ed2 = findViewById(R.id.edAppadd);
        ed3 = findViewById(R.id.edAppCN);
        ed4 = findViewById(R.id.edAppfee);
        btndate = findViewById(R.id.buttonAppD);
        btntime = findViewById(R.id.buttonAppT);
        btnback = findViewById(R.id.button_back);
        btnapp = findViewById(R.id.button_app);
        imgback = findViewById(R.id.imgback);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it =  getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText(fees);

        btnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database db = new database(getApplicationContext(),"mydb",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
                String Email = sharedPreferences.getString("Email","").toString();

                if (db.checkappionmetexist(Email,title+" "+fullname,address,contact,btndate.getText().toString(),btntime.getText().toString())!=0){
                    Toast.makeText(Appiontment.this, "Appiontment is already booked", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addorder(Email,title+" "+fullname,address,contact,0,btndate.getText().toString(),btntime.getText().toString(),Float.parseFloat(fees),"appiontment");
                    Toast.makeText(Appiontment.this, "your appiontment is done ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Appiontment.this,Home.class));
                 }
            }
        });

        initDatePicker();
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        iniTimePicker();
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Appiontment.this,FindDoctor.class);
                startActivity(intent);
            }
        });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btndate.setText(year+"/"+month+"/"+dayOfMonth);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void iniTimePicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                btntime.setText(hourOfDay +":"+ minute);

            }

        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;

        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,min,true);
    }

}