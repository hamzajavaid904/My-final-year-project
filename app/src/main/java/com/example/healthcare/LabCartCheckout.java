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

public class LabCartCheckout extends AppCompatActivity {

    EditText edfn,edadd,edpc,edcon;
    Button btnbooking;
    ImageView imgback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_cart_checkout);
       // getSupportActionBar().hide();

        database mydb = new database(getApplicationContext(),"mydb",null,1);

        edfn = findViewById(R.id.edlbcFN);
        edadd = findViewById(R.id.edlbcadd);
        edpc = findViewById(R.id.edlcbpc);
        edcon = findViewById(R.id.edlbcCN);
        btnbooking = findViewById(R.id.btnlcbbook);
        imgback = findViewById(R.id.imgback);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote("$"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabCartCheckout.this,LabCart.class));
            }
        });

        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
                String Email = sharedPreferences.getString("Email","").toString();


                if (edfn.length()==0 || edadd.length()==0 || edpc.length()==0 || edcon.length()==0){
                    Toast.makeText(LabCartCheckout.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    mydb.addorder(Email,edfn.getText().toString(),edadd.getText().toString(),edcon.getText().toString(),Integer.parseInt(edpc.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[0].toString()),"lab");
                    mydb.delete(Email,"lab");

                    Toast.makeText(LabCartCheckout.this, "your booking is successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LabCartCheckout.this,Home.class);
                    startActivity(intent);

                }

            }
        });
    }
}