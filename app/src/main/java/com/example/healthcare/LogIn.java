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
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity
{
    Button login_btn;
    TextView register;
    EditText edEmail,edpass;

    DBhelper dBhelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // getSupportActionBar().hide();

        database db = new database(getApplicationContext(),"mydb",null,1);

        register = findViewById(R.id.textView2);
        login_btn = findViewById(R.id.buttonlogin);
        edEmail= findViewById(R.id.edemail);
        edpass = findViewById(R.id.edpass);



        login_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Email = edEmail.getText().toString();
                String Password = edpass.getText().toString();

                if (Email.length()==0 || Password.length()==0)
                {
                    Toast.makeText(LogIn.this, "Please fill all the boxes", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (db.login(Email,Password)==1){
                        Toast.makeText(LogIn.this, "login successfull", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email",Email);
                        editor.apply();
                            Intent intent = new Intent(LogIn.this,Home.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LogIn.this, "invalid Email or password", Toast.LENGTH_SHORT).show();
                    }
                    
                   
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LogIn.this,Registration_page.class);
                startActivity(intent);
            }
        });
    }
}