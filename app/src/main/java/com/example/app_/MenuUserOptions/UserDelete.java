package com.example.app_.MenuUserOptions;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.app_.R;

import java.util.List;

public class UserDelete extends AppCompatActivity {
    Spinner spinner_delete;
    Button btn_deleteRow;
    EditText txt_delete;

    DatabaseClients databaseClients;
    ArrayAdapter<ClientsModel> clientsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete);
        getSupportActionBar().setTitle("Delete");
        spinner_delete = findViewById(R.id.spinner_delete);
        btn_deleteRow = findViewById(R.id.btn_deleteRow);
        txt_delete = findViewById(R.id.txt_delete);

        databaseClients = new DatabaseClients(UserDelete.this);


        String[] groupOptions = {"ID", "Name", "Surname", "City", "Department", "Salary"};
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groupOptions);
        spinner_delete.setAdapter(groupAdapter);
    }

    public void setBtn_deleteRow(View v) {
        String column = spinner_delete.getSelectedItem().toString();
        String text = txt_delete.getText().toString();

        boolean deleted = databaseClients.deleteOne(column, text);

        if (deleted) {
            // Handle the case where rows were deleted (e.g., show a message)
            Toast.makeText(this, "No matching found", Toast.LENGTH_SHORT).show();
        } else {
            // Handle the case where no rows were deleted (e.g., show a message)
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}