package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    CardView findDoctor,cardlabtest,exit,cardmedicine,cardordedetails,cardhealthtips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      //  getSupportActionBar().hide();

        cardlabtest = findViewById(R.id.cardlabtest);
        cardmedicine = findViewById(R.id.cardbuymedicine);
        findDoctor = findViewById(R.id.cardfinddoctor);
        cardhealthtips = findViewById(R.id.HealthArticle);
        cardordedetails = findViewById(R.id.cardOrderdetails);
        exit = findViewById(R.id.cardExit);

       SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email","").toString();
        Toast.makeText(this, "welcome"+email, Toast.LENGTH_SHORT).show();


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Home.this, LogIn.class);
                startActivity(intent);
            }
        });


        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FindDoctor.class);
                startActivity(intent);
            }
        });


        cardlabtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,LabTestActivity.class);
                startActivity(intent);
            }
        });

        cardmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Medicine.class);
                startActivity(intent);
            }
        });

        cardhealthtips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Healthtips.class);
                startActivity(intent);
            }
        });

        cardordedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,OrderDetails.class);
                startActivity(intent);
            }
        });
    }
}