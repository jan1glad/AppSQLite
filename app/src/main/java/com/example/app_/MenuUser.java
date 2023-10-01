package com.example.app_;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.app_.MenuUserOptions.UserDelete;
import com.example.app_.MenuUserOptions.UserInsert;
import com.example.app_.MenuUserOptions.UserSelect;
import com.example.app_.MenuUserOptions.UserUpdate;

public class MenuUser extends AppCompatActivity {

    Button btn_select, btn_insert, btn_update, btn_delete;
    TextView txt_NameAdmin;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);
        getSupportActionBar().setTitle("Menu");
        btn_select = findViewById(R.id.btn_select);
        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        txt_NameAdmin = findViewById(R.id.txt_NameAdmin);
        txt_NameAdmin.setText("Nice to see you again " + name + "!");

    }

    public void setBtn_select(View v) {
        Intent i = new Intent(this, UserSelect.class);
        startActivity(i);

    }

    public void setBtn_insert(View v) {
        Intent i = new Intent(this, UserInsert.class);
        startActivity(i);
    }

    public void setBtn_update(View v) {
        Intent i = new Intent(this, UserUpdate.class);
        startActivity(i);
    }

    public void setBtn_delete(View v) {
        Intent i = new Intent(this, UserDelete.class);
        startActivity(i);
    }

    public void logOut(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}