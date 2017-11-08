package com.example.anton.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Report1DetailsActivity extends AppCompatActivity {

    TextView report;
    DatabaseHelper mDatabaseHelper;

    ListView listView;
    Report1DetailsAdapter report1DetailsAdapter;

    private static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report1_details);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);
        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();
        position = intent.getIntExtra("id", -1);

        report = (TextView) findViewById(R.id.report);

        String s = "Детальний звіт про виконання завдань відділом \"" + mDatabaseHelper.getDepartment(position).getName() + "\"";
        report.setText(s);

//        ArrayList<Task> tasks = new ArrayList<>();
//        tasks.addAll(mDatabaseHelper.getTasks(position));
//
//        report1DetailsAdapter = new Report1DetailsAdapter(this, tasks);
//        listView.setAdapter(report1DetailsAdapter);

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
