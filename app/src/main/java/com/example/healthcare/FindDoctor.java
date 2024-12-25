package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctor extends AppCompatActivity {

    CardView Familyphysician,Ditician,Dentist,Surgeon,Cardiologist,Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
      //  getSupportActionBar().hide();

        Familyphysician = findViewById(R.id.CardFDfamilyPhysician);
        Familyphysician.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Family Physician");
                startActivity(it);

            }
        });

        Ditician = findViewById(R.id.CardFDDitician);
        Ditician.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Ditician");
                startActivity(it);
            }
        });

        Dentist = findViewById(R.id.CardFDDentist);
        Dentist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });

        Surgeon = findViewById(R.id.CardFDSurgeon);
        Surgeon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Surgeon");
                startActivity(it);

            }
        });

        Cardiologist = findViewById(R.id.CardFDCardioligist);
        Cardiologist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(FindDoctor.this,DoctorDetails.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });

        Exit = findViewById(R.id.CardFDBack);
        Exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FindDoctor.this, Home.class);
                startActivity(intent);
            }
        });
    }
}