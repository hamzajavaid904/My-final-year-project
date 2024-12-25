package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Healtharticledetails extends AppCompatActivity {

    ImageView img,imgback;
    TextView txt1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healtharticledetails);

        img = findViewById(R.id.imageViewhtd);
        txt1 = findViewById(R.id.textViewhtd2);
        imgback = findViewById(R.id.imgback);
       // btn = findViewById(R.id.buttonhtd);

        Intent intent = getIntent();
        txt1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int resid = bundle.getInt("text2");
            img.setImageResource(resid);
        }
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Healtharticledetails.this,Healthtips.class));
            }
        });

    }
}