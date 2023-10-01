package com.example.app_.MenuUserOptions;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.app_.R;

public class UserInsert extends AppCompatActivity {

    EditText txt_insertName, txt_insertSurname, txt_insertCity, txt_insertDepartment, txt_insertSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_insert);
        getSupportActionBar().setTitle("Insert");
        txt_insertName = findViewById(R.id.txt_insertName);
        txt_insertSurname = findViewById(R.id.txt_insertSurname);
        txt_insertCity = findViewById(R.id.txt_insertCity);
        txt_insertDepartment = findViewById(R.id.txt_insertDepartment);
        txt_insertSalary = findViewById(R.id.txt_insertSalary);
    }
    public void setBtnInsert(View v){
        ClientsModel clientsModel = new ClientsModel();

        try {
            clientsModel = new ClientsModel(-1,txt_insertName.getText().toString(),txt_insertSurname.getText().toString(),txt_insertCity.getText().toString(),txt_insertDepartment.getText().toString(),Integer.parseInt(txt_insertSalary.getText().toString()));
            Toast.makeText(UserInsert.this, "Succes", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(UserInsert.this, "Error occurred", Toast.LENGTH_SHORT).show();
        }
        DatabaseClients databaseClients = new DatabaseClients(UserInsert.this);
        boolean succes = databaseClients.addClients(clientsModel);
        Toast.makeText(UserInsert.this, "Succes", Toast.LENGTH_SHORT).show();


    }
}