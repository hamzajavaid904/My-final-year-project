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
import android.widget.Toast;

public class MedCheckout extends AppCompatActivity {
    EditText edfn,edadd,edpc,edcon;
    Button btnbooking;
    ImageView imgback;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_med);
        //getSupportActionBar().hide();

        database mydb = new database(getApplicationContext(),"mydb",null,1);

        edfn = findViewById(R.id.edmdcFN);
        edadd = findViewById(R.id.edmdcadd);
        edpc = findViewById(R.id.edmdcpc);
        edcon = findViewById(R.id.edmdcCN);
        btnbooking = findViewById(R.id.btnmdcbook);
        imgback = findViewById(R.id.imgback);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote("$"));
        String date = intent.getStringExtra("date");
      //  String time = intent.getStringExtra("time");
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedCheckout.this,MedGotocart.class));
            }
        });
        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
                String Email = sharedPreferences.getString("Email","").toString();


                if (edfn.length()==0 || edadd.length()==0 || edpc.length()==0 || edcon.length()==0){
                    Toast.makeText(MedCheckout.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    mydb.addorder(Email,edfn.getText().toString(),edadd.getText().toString(),edcon.getText().toString(),Integer.parseInt(edpc.getText().toString()),date.toString(),"",Float.parseFloat(price[0].toString()),"medicine");
                    mydb.delete(Email,"medicine");

                    Toast.makeText(MedCheckout.this, "your booking is successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MedCheckout.this,Home.class);
                    startActivity(intent);
                }

            }
        });

    }
}