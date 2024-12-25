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

public class LabTestActivity extends AppCompatActivity {
    private String [][] Package =
    {
            {"Package 1 ","","","","1000"},
            {"Package 2 ","","","","300"},
            {"Package 3 ","","","","900"},
            {"Package 4 ","","","","500"},
            {"Package 5 ","","","","700"}
    };

    private String [] Package_details =
            {
                    "                    Full Body Checkup \n\n"+"Blood Glucose Fasting \n" + "Complete Hemogram \n" + "HbA1c \n" + "Iron Studies \n" + "Kidney function test \n" + "LDH Lactate Dehydrogenase, Serum" + "Lipid Profile \n" + "liver function test" ,
                    "                    Blood Glucose Fasting \n\n"+"Blood Glucose Fasting ",
                    "                    COVID-19 IgG \n\n"+ "COVID-19 Antibody - IgG",
                    "                    Thyriod check \n\n"+"Thyriod Profile-Total (T3, T4 & TSH Ultra-sensitive)",
                    "                    Immunity check \n\n"+"Complete Hemogram \n" + "CRP( C Reactive Protien) Quantitative , serum \n" + "Iron studies \n" + "Kidney Function Test \n" + "Vitamin - D Total-25 Hydroxy \n" + "Liver Funtion test \n" + "Lipid Profile"

            };
    ListView listViewLT;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnback,btngotocart;
    ImageView imgback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
      //  getSupportActionBar().hide();

        listViewLT = findViewById(R.id.listViewLT);
        btngotocart = findViewById(R.id.btnltgotocart);
        //btnback = findViewById(R.id.button);
        imgback = findViewById(R.id.imgback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bck = new Intent(LabTestActivity.this,Home.class);
                startActivity(bck);
            }
        });

        list = new ArrayList();
        for (int i = 0; i < Package.length;i++)
        {
         item = new HashMap<String,String>();
         item.put("line1",Package[i][0]);
         item.put("line2",Package[i][1]);
         item.put("line3", Package[i][2]);
         item.put("line4",Package[i][3]);
         item.put("line5","Rs."+Package[i][4]+"/-");
         list.add(item);
        }

        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listViewLT.setAdapter(sa);

        listViewLT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetails.class);
                it.putExtra("text1",Package[i][0]);
                it.putExtra("text2",Package_details[i]);
                it.putExtra("text3",Package[i][4]);
                startActivity(it);
            }
        });

        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(LabTestActivity.this,LabCart.class);
                startActivity(it);
            }
        });



    }
}