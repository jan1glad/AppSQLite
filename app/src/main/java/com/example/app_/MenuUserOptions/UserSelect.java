package com.example.app_.MenuUserOptions;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_.R;

import java.util.List;

public class UserSelect extends AppCompatActivity {

    Spinner sr_groupBy, sr_orderBy, spinner_ascDesc;
    ListView lv_clients;
    DatabaseClients databaseClients;
    ArrayAdapter<ClientsModel> clientsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);
        getSupportActionBar().setTitle("Select");
        sr_groupBy = findViewById(R.id.sr_groupBy);
        sr_orderBy = findViewById(R.id.sr_orderBy);
        spinner_ascDesc = findViewById(R.id.spinner_ascDesc);
        lv_clients = findViewById(R.id.lv_clients);

        databaseClients = new DatabaseClients(UserSelect.this);

        String[] groupOptions = {"Name", "Surname", "City", "Department", "Salary"};
        String[] descAsc = {"ASC", "DESC"};
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, groupOptions);
        ArrayAdapter<String> options = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, descAsc);
        sr_groupBy.setAdapter(groupAdapter);
        sr_orderBy.setAdapter(groupAdapter);
        spinner_ascDesc.setAdapter(options);

        ShowCustomersOnListView(databaseClients);
    }

    private void ShowCustomersOnListView(DatabaseClients databaseClients) {
        clientsArrayAdapter = new ArrayAdapter<>(UserSelect.this, android.R.layout.simple_list_item_1, databaseClients.getEveryone());

        lv_clients.setAdapter(clientsArrayAdapter);
    }

    public void setBtn_groupBy(View v) {
        String selectedGroupBy = sr_groupBy.getSelectedItem().toString();

        // Call the groupBy method in the DatabaseClients class with the selected grouping option
        List<ClientsModel> groupedClients = databaseClients.groupBy(selectedGroupBy);

        // Update the ListView adapter with the grouped data
        clientsArrayAdapter.clear();
        clientsArrayAdapter.addAll(groupedClients);
        clientsArrayAdapter.notifyDataSetChanged();
    }
    public void setBtn_orderBy(View v) {
        String selectedGroupBy = sr_orderBy.getSelectedItem().toString();
        String ascDesc = spinner_ascDesc.getSelectedItem().toString();
        // Call the groupBy method in the DatabaseClients class with the selected grouping option
        List<ClientsModel> groupedClients = databaseClients.orderBy(selectedGroupBy, ascDesc);

        // Update the ListView adapter with the grouped data
        clientsArrayAdapter.clear();
        clientsArrayAdapter.addAll(groupedClients);
        clientsArrayAdapter.notifyDataSetChanged();
    }
}