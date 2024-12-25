package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration_page extends AppCompatActivity {

        TextView already_register;
        EditText eduser,edemail,edpass,edconpass;
        Button button_reg;
        DBhelper dBhelper;
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration_page);
           // getSupportActionBar().hide();

            eduser = findViewById(R.id.eduser);
            edemail = findViewById(R.id.edemail);
            edpass = findViewById(R.id.edpass);
            edconpass = findViewById(R.id.edconpass);


            button_reg = findViewById(R.id.button_back);
            button_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    database db = new database(getApplicationContext(),"mydb",null,1);

                    String username = eduser.getText().toString();
                    String Email = edemail.getText().toString();
                    String Password = edpass.getText().toString();
                    String confirm = edconpass.getText().toString();



                    if (username.length() == 0 || Email.length() == 0 || Password.length() == 0 || confirm.length() == 0)
                    {
                        Toast.makeText(Registration_page.this, "please fill all the boxes", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (Password.equals(confirm))
                        {
                            if (isvalid(Password))
                            {
                                db.register(username,Email,Password);
                                Toast.makeText(Registration_page.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Registration_page.this,Home.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Registration_page.this, "Password must be atleast 8 digits containing number,letter and a specail character", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Registration_page.this, "Password and confirm password must be the same", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });



            already_register = findViewById(R.id.tvalr_acc);
            already_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Registration_page.this, LogIn.class);
                    startActivity(intent);
                }
            });


        }
        public static boolean isvalid (String Password){
            int f1 = 0, f2 = 0 , f3 = 0;

            if (Password.length()<8)
            {
                return false;
            }
            else {
                for (int i = 0; i < Password.length(); i++) {
                    if (Character.isLetter(Password.charAt(i)))
                    {
                        f1 = 1;
                    }
                }


                for (int j = 0; j < Password.length(); j++) {
                    if (Character.isDigit(Password.charAt(j))) {

                        f2 = 1;
                    }
                }

                for (int k = 0; k < Password.length(); k++)
                {
                    char c = Password.charAt(k);
                if (c>=33&&c<=46 || c==64)
                {
                   f3=1;
                }
                }

                if (f1==1 && f2==1&& f3 ==1)

                    return true;
                return false;

            }
        }
    }
