package com.example.app_.MenuUserOptions;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.app_.R;

public class UserUpdate extends AppCompatActivity {

    EditText txt_updateValue, txt_currently;
    Spinner spinner_currentUP, spinner_updateRow;
    Button btn_updateValues;
    DatabaseClients databaseClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);
        getSupportActionBar().setTitle("Update");
        databaseClients = new DatabaseClients(this);

        spinner_currentUP = findViewById(R.id.spinner_currentUP);
        spinner_updateRow = findViewById(R.id.spinner_updateRow);
        txt_currently = findViewById(R.id.txt_currently);
        txt_updateValue = findViewById(R.id.txt_updateValue);
        btn_updateValues = findViewById(R.id.btn_updateValues);

        String[] groupOptions = {"ID", "Name", "Surname", "City", "Department", "Salary"};
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groupOptions);
        spinner_currentUP.setAdapter(groupAdapter);
        spinner_updateRow.setAdapter(groupAdapter);
    }
    public void setBtn_updateValues(View v){
        String currentColumn = spinner_currentUP.getSelectedItem().toString();
        String spinnerUpdate = spinner_updateRow.getSelectedItem().toString();
        String currently = txt_currently.getText().toString();
        String update = txt_updateValue.getText().toString();

        int affectedRows = databaseClients.updateValues(currentColumn, currently, spinnerUpdate, update);
        //System.out.println("Affected Rows: " + affectedRows);

        if (affectedRows > 0) {
            Toast.makeText(this, "Updated " + affectedRows + " rows", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No rows updated", Toast.LENGTH_SHORT).show();
        }

    }
}