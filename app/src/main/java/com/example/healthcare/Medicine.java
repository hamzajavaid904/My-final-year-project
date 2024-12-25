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

public class Medicine extends AppCompatActivity {

    private String [][] Package =
            {
                    {"Amoxicillin","","","","1000"},
                    {"Alprazolam","","","","300"},
                    {"Paracetamol","","","","900"},
                    {"dopamine","","","","500"},
                    {"morphine","","","","700"}
            };

    private String [] Package_details =
            {
                    "                   Amoxicillin\n\n"+"Upper Respiratory Tract Infections of the Ear, Nose, and Throat \n\n"+"Infections of the Genitourinary Tract\n\n"+"Infections of the Skin and Skin Structure\n\n"+"Infections of the Lower Respiratory Tract\n\n",
                    "                   Alprazolam\n\n"+"Generalized Anxiety Disorder\n\n"+"Recommended starting oral dosage is 0.25 mg to 0.5 mg three times daily.\n\n"+"Panic Disorder:Recommended starting oral dosage is 0.5 mg three times daily. The dosage may be increased at intervals of every 3 to 4 days in increments of no more than 1 mg per day.\n\n",
                    "                   Paracetamol\n\n"+"Headache\n"+"Tension headache\n" + "Migraine\n" + "Backache\n" + "Rheumatic and muscle pain\n" + "Mild arthritis/osteoarthritis\n" + "Toothache\n" + "Period pain (dysmenorrhea)\n" + "Colds and flu symptoms\n" + "Sore throat\n" + "Sinus pain\n" + "Post-operative pain\n" + "Fever (pyrexia)",
                    "                   dopamine\n\n"+"a heart rhythm disorder\n" +"coronary artery disease (clogged arteries)\n" +"a blood clot\n" +"an allergy to any foods or drugs\n" + "asthma or sulfite allergy\n" + "metabolic acidosis\n" + "diabetes\n" + "circulation problems such as Raynaud's syndrome\n" + "frostbite\n" + "Buerger's disease",
                    "                   morphine\n\n"+"Morphine is used to treat moderate to severe pain when alternative pain relief medicines are not effective or not tolerated. Morphine is an opioid pain-relieving medication that usually provides significant pain relief for short-term or chronic pain."

            };
    ListView listViewmed;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnback,btngotocart;
    ImageView imgback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
       // getSupportActionBar().hide();


        listViewmed = findViewById(R.id.listViewmed);
        btngotocart = findViewById(R.id.btngotocartmed);
      //  btnback = findViewById(R.id.buttonbackmed);
        imgback = findViewById(R.id.imgback);



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
        listViewmed.setAdapter(sa);

        listViewmed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(Medicine.this,MedDetails.class);
                it.putExtra("text1",Package[i][0]);
                it.putExtra("text2",Package_details[i]);
                it.putExtra("text3",Package[i][4]);
                startActivity(it);
            }
        });

        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(Medicine.this, MedGotocart.class);
                startActivity(it);
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bck = new Intent(Medicine.this,Home.class);
                startActivity(bck);
            }
        });

    }
}