package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MedDetails extends AppCompatActivity {
    TextView textn,textc;
    EditText eddetails;
    Button btnaddcart,btnbackmed;
    ImageView imgbck;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_details);
        //getSupportActionBar().hide();

        database mydb = new database(getApplicationContext(),"mydb",null,1);

        textn = findViewById(R.id.textviewmed2);
        textc = findViewById(R.id.textviewmed3);
        eddetails = findViewById(R.id.editTextmed);
        btnaddcart = findViewById(R.id.buttonmed1);
       // btnbackmed = findViewById(R.id.buttonmed2);
        imgbck = findViewById(R.id.imgback);

        eddetails.setKeyListener(null);

        Intent intent = getIntent();
        textn.setText(intent.getStringExtra("text1"));
        eddetails.setText(intent.getStringExtra("text2"));
        textc.setText("Total amount: "+intent.getStringExtra("text3"));

        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
                String Email = sharedPreferences.getString("Email","").toString();
                String product = textn.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());


                if (mydb.checkcart(Email,product)!=0) {
                    Toast.makeText(MedDetails.this, "product already inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    mydb.cart(Email,product,price,"medicine");
                    Toast.makeText(MedDetails.this, "record inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MedDetails.this,Medicine.class);
                    startActivity(intent);
                }
            }
        });

        imgbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(MedDetails.this,Medicine.class);
                startActivity(intent1);
            }
        });
    }
}