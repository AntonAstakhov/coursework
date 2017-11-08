package com.example.anton.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Report1Activity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper mDatabaseHelper;
    Report1Adapter report1Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<Department> departments = new ArrayList<>();
        departments.addAll(mDatabaseHelper.getAllDepartments());

        report1Adapter = new Report1Adapter(this, departments);
        listView.setAdapter(report1Adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
