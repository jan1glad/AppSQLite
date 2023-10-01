package com.example.app_;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseAdmin databaseAdmin;
    Button btn_log;

    EditText txt_login, txt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Log In");
        txt_login = findViewById(R.id.txt_login);
        txt_pass = findViewById(R.id.txt_pass);

        btn_log = findViewById(R.id.btn_log);
        databaseAdmin = new DatabaseAdmin(MainActivity.this);
    }

    public void goAdmin(View v){
        Intent i = new Intent(this, AdminAdd.class);

        startActivity(i);
    }

    public void logIn(View v) {

        String name = txt_login.getText().toString();
        String password = txt_pass.getText().toString();

        if (name.equals("") || password.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter both name and password!", Toast.LENGTH_SHORT).show();
        } else {
            Boolean check = databaseAdmin.check(name,password);
            //Boolean checkPassword = databaseAdmin.checkPassword(password);

            if (check) {
                // Successful login
                Toast.makeText(MainActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MenuUser.class);
                i.putExtra("Name",name);
                startActivity(i);
            }else{
                    // User not found
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
