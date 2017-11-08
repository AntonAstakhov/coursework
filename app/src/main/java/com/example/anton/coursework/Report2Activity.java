package com.example.anton.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Report2Activity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper mDatabaseHelper;
    Report2Adapter report2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report2);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<Manager> managers = new ArrayList<>();
        managers.addAll(mDatabaseHelper.getAllManagers());

        report2Adapter = new Report2Adapter(this, managers);
        listView.setAdapter(report2Adapter);
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
