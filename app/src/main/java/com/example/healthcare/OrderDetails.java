package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetails extends AppCompatActivity {
    private String[][] order_details ={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    ListView lst;
    ImageView imgback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
       // getSupportActionBar().hide();

        database mydb = new database(getApplicationContext(),"mydb",null,1);

        lst     = findViewById(R.id.listViewod);
        //btnback = findViewById(R.id.btnbackod);
        imgback = findViewById(R.id.imgback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetails.this, Home.class));
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
        String Email = sharedPreferences.getString("Email","").toString();
        ArrayList mydata = mydb.getorderdata(Email);

        order_details = new String[mydata.size()][];
        for (int i = 0;i<order_details.length;i++) {

            order_details[i] = new String[5];
            String arr = mydata.get(i).toString();
            String[] strdata = arr.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strdata[0];
            order_details[i][1] = strdata[1];
            if (strdata[7].compareTo("medicine") == 0) {
                order_details[i][3] = "DEL:" + strdata[4];
            } else {
                order_details[i][3] = "DEL:" + strdata[4] + " " + strdata[5];
            }
            order_details[i][2] = "rs." + strdata[6];
            order_details[i][4] = strdata[7];
        }
            list = new ArrayList();
            for (int i =0;i<order_details.length;i++){
                item = new HashMap<String,String>();
                item.put("line1",order_details[i][0]);
                item.put("line2",order_details[i][1]);
                item.put("line3",order_details[i][2]);
                item.put("line4",order_details[i][3]);
                item.put("line5",order_details[i][4]);
                list.add(item);
            }
        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

    }
}