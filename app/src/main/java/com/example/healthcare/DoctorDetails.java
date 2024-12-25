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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {

private String[][] doctor_details_fp =

        {
        {"Doctor Name: Dr.Syed Muhammad Zahid", "Hospital Address: LRH,Peshawar","Exp: 15","Contact#: 00000000","1000"},
        {"Doctor Name: Dr. Jamil Ahmad", "Hospital Address: Sarhad Medical Store ","Exp: 4","Contact# :11111111","1000"},
        {"Doctor Name: Prof. Dr. Shahid Jamil", "Hospital Address: RMI,Hayatabad,Peshawar","Exp: 25","Contact#:2222222","2200"},
        {"Doctor Name: Dr. Seema Noor", "Hospital Address: Khattak Medical Center,Peshawar","Exp: 28","Contact#:3333333","1000"},
        {"Doctor Name: Dr. Wiqar Ahmad", "Hospital Address: Alfalah Medical Center,Peshawar","Exp: 8","Contact#:4444444","1000"}
        };
    private String[][] doctor_details_die =

            {
                    {"Doctor Name: Mr. Zulqarnain Haider", "Hospital Address: KTH,Peshawar","Exp: 7","Contact#: 1111111","5000"},
                    {"Doctor Name: Ms. Ulfat Naz", "Hospital Address: Riaz Hospital,Shadman Town, Faisalabad ","Exp: 1","Contact#: 2222222","500"},
                    {"Doctor Name: Ms. Sundas Khadim Ali", "Hospital Address: Nourish Diet Clinic,University Road, Peshawar","Exp: 3","Contact#:33333333","2000"},
                    {"Doctor Name: Ms. Bibi Sadiqa", "Hospital Address: KTH,Peshawar","Exp: 6","Contact#: 44444444","5000"},
                    {"Doctor Name: Ms. Ayesha Nasir ", "Hospital Address: AL Rehman hospital","Exp: 1","Contact#: 5555555","500"}
            };

    private String[][] doctor_details_den =

            {
                    {"Doctor Name :Dr. Mohammad Shahid Khan Khattak", "Hospital Address :Northwest General Hospital,Peshawar","Exp: 10","Contact# :1111111","3000"},
                    {"Doctor Name :Dr. Salman Khan", "Hospital Address :Misri Khan Dental Clinic","Exp :11","Contact# :22222222","1000"},
                    {"Doctor Name :Dr. Shahid Ahmed", "Hospital Address :Marwat Dental Clinic,Peshawar","Expe :8","Contact# :3333333","800"},
                    {"Doctor Name :Dr. Aisha Farooq", "Hospital Address :Women Dental Clinic,Peshawar","Exp :8","Contact# :4444444","500"},
                    {"Doctor Name :Dr. Faisal Mansoor", "Hospital Address :Pak Medical Center,Peshawar","Exp :4","Contact# :55555555","1000"}
            };

    private String[][] doctor_details_sur =

            {
                    {"Doctor Name :Dr. Gulab Noor Afridi", "Hospital Address :Gulab Noor Afridi Clinic","Exp :13","Contact# :1111111111","1500"},
                    {"Doctor Name :Dr. Rashid Aslam", "Hospital Address :Saeed International Hospital","Exp :24","Contact# :22222222","2500"},
                    {"Doctor Name :Dr. Tariq Jabbar Khan", "Hospital Address :Northwest General Hospital","Exp :35","Contact# :333333333","3000"},
                    {"Doctor Name :Dr. Bahauddin Khan", "Hospital Address :Northwest General Hospital","Exp :16","Contact# :44444444","3000"},
                    {"Doctor Name :Dr. Ramzan Mehsud", "Hospital Address :Zia Medical Complex,Peshawar","Exp :7","Contact# :55555555","1500"}
            };

    private String[][] doctor_details_car =

            {
                    {"Doctor Name :Dr. Zahoor Ahmad Khan", "Hospital Address :Northwest General Hospital","Experience :40","Contact# :111111111","3000"},
                    {"Doctor Name :Assist. Prof. Dr. Muhammad Kashif Iltaf", "Hospital Address :Altaf Cardic Care Clinic","Experience :18","Contact# :22222222","1500"},
                    {"Doctor Name :Dr. Umer Ibrahim Paracha", "Hospital Address :Sardar Khan Memorial Hospital,Swabi","Experience :16","Contact# :3333333333","1000"},
                    {"Doctor Name :Dr. Niaz Ali", "Hospital Address :Northwest General Hospital","Experience :20","Contact# :4444444444","2000"},
                    {"Doctor Name :Dr. Shoaib Subhan", "Hospital Address :MMC General Hospital,Peshawar","Experience :8","Contact# :5555555","1000"}
            };

    String[][] doctor_details = {};
TextView tv;
Button Back;
ArrayList list;
SimpleAdapter sa;
HashMap<String,String> item;
ImageView imgback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
      //  getSupportActionBar().hide();

        tv = findViewById(R.id.textView3);
        imgback = findViewById(R.id.imgback);
        Intent it = getIntent();
        String title = it.getStringExtra("title");

        tv.setText(title);

        if (title.compareTo("Family Physician")==0)
            doctor_details = doctor_details_fp;
        else
        if (title.compareTo("Ditician")==0)
            doctor_details = doctor_details_die;
        else
        if (title.compareTo("Dentist")==0)
            doctor_details = doctor_details_den;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details_sur;
        else
            doctor_details = doctor_details_car;



       // Back = findViewById(R.id.buttonDDBack);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetails.this,FindDoctor.class);
                startActivity(intent);
            }
        });
       list = new ArrayList();
        for(int  i=0; i<doctor_details.length;i++)
        {
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","const fee :"+doctor_details[i][4] + "-/");
            list.add(item);
        }

        sa =  new SimpleAdapter
                (this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView listView = findViewById(R.id.listViewDD);
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetails.this,Appiontment.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}