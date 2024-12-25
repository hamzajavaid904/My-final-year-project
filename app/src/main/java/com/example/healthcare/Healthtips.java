package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Healthtips extends AppCompatActivity {

        String [][] healtharticles=
                {
                {"Walking Daily","","","","CLick for more details"},
                {"Home care for covid 19","","","","Click for more details"},
                {"Stop Smoking","","","","Click for more details"},
                {"manestural cramps","","","","click for more details"},
                {"Healthy gut","","","","click here for more details"}
        };

    int[] images =
            {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
            };
    ListView lst;
    Button btn_back;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ImageView imgback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthtips);
       // getSupportActionBar().hide();

        lst = findViewById(R.id.listViewht);
        imgback = findViewById(R.id.imgback);


        list = new ArrayList();
        for (int i = 0; i < healtharticles.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("line1",healtharticles[i][0]);
            item.put("line2",healtharticles[i][1]);
            item.put("line3",healtharticles[i][2]);
            item.put("line4",healtharticles[i][3]);
            item.put("line5",healtharticles[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(Healthtips.this,Healtharticledetails.class);
                it.putExtra("text1",healtharticles[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });



        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Healthtips.this,Home.class));
            }
        });
    }
}