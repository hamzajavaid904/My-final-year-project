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

public class LabTestDetails extends AppCompatActivity {
    TextView textn,textc;
    EditText eddetails;
    Button btnaddcart,btnldback;
    ImageView imgback;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        //getSupportActionBar().hide();

        database mydb = new database(getApplicationContext(),"mydb",null,1);
        textn = findViewById(R.id.textviewld2);
        textc = findViewById(R.id.textviewld3);
        eddetails = findViewById(R.id.editTextld);
        btnaddcart = findViewById(R.id.buttonld1);
       // btnldback = findViewById(R.id.buttonld2);
        imgback = findViewById(R.id.imgback);

        eddetails.setKeyListener(null);

        Intent intent = getIntent();
        textn.setText(intent.getStringExtra("text1"));
        eddetails.setText(intent.getStringExtra("text2"));
        textc.setText("Total amount :"+intent.getStringExtra("text3"));

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(LabTestDetails.this,LabTestActivity.class);
                startActivity(intent1);
            }
        });
        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
                String Email = sharedPreferences.getString("Email","").toString();
                String product = textn.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());



                if (mydb.checkcart(Email,product)!=0) {
                    Toast.makeText(LabTestDetails.this, "product already inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                        mydb.cart(Email,product,price,"lab");
                   Toast.makeText(LabTestDetails.this, "record inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LabTestDetails.this,LabTestActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
}