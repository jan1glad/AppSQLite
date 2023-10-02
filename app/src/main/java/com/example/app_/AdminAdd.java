package com.example.app_;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminAdd extends AppCompatActivity {


    EditText admin_Name, admin_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);
        getSupportActionBar().setTitle("Add yourself as an Admin");
        admin_Name = findViewById(R.id.admin_Name);
        admin_Password = findViewById(R.id.admin_Password);

    }

    public void addAdmin(View v) {
        AdminModel adminModel = new AdminModel();


        try {
            adminModel = new AdminModel(-1, admin_Name.getText().toString(), admin_Password.getText().toString());
        } catch (Exception e) {
            Toast.makeText(AdminAdd.this, "Error occurred", Toast.LENGTH_SHORT).show();
        }
        DatabaseAdmin databaseAdmin = new DatabaseAdmin(AdminAdd.this);
        boolean succes = databaseAdmin.addOne(adminModel);
        if (succes) {
            Toast.makeText(AdminAdd.this, "Succes", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(AdminAdd.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
