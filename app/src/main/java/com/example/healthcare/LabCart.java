package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class LabCart extends AppCompatActivity {
    Button  btncheckout, btndate, btntime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    float totalamount = 0;
    String[][] packages = {};
    TextView total;

    ListView lst;
    ImageView imgback;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_cart);
       // getSupportActionBar().hide();

        database mydb = new database(getApplicationContext(),"mydb",null,1);
        btndate = findViewById(R.id.buttonlcdate);
        btntime = findViewById(R.id.buttonlctime);
        btncheckout = findViewById(R.id.buttonlccheckout);
        total = findViewById(R.id.textViewlc3);
        lst = findViewById(R.id.listViewlc);
        imgback = findViewById(R.id.imgback);

        SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
        String Email = sharedPreferences.getString("Email","").toString();


        ArrayList cartdata = mydb.getdata(Email,"lab");
        Toast.makeText(this, ""+cartdata, Toast.LENGTH_SHORT).show();

        packages = new String[cartdata.size()][];
        for (int i = 0;i<packages.length;i++){
            packages[i]= new String[5];
        }

        for (int i = 0;i<cartdata.size();i++){
         String  arrdata = cartdata.get(i).toString();
          String[] strdata = arrdata.split(java.util.regex.Pattern.quote("$"));
          packages [i][0] = strdata[0];
          packages[i][4] = "cost :"+strdata[1]+"/-";
         totalamount = totalamount + Float.parseFloat(strdata[1]);
        }

        total.setText(String.valueOf(totalamount));

       ArrayList list = new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            HashMap items = new HashMap<String,String>();
            items.put("line1",packages[i][0]);
            items.put("line2",packages[i][1]);
            items.put("line3",packages[i][2]);
            items.put("line4",packages[i][3]);
            items.put("line5",packages[i][4]);
            list.add(items);
        }
        SimpleAdapter sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);


        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabCart.this,LabCartCheckout.class);
                intent.putExtra("price",total.getText());
                intent.putExtra("date",btndate.getText());
                intent.putExtra("time",btntime.getText());
                startActivity(intent);
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
                Intent intent = new Intent(LabCart.this, LabTestActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btndate.setText(year + "/" + month + "/" + dayOfMonth);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void iniTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                btntime.setText(hourOfDay + ":" + minute);

            }

        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;

        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, min, true);

    }
}