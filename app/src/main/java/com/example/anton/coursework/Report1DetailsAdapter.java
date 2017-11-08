package com.example.anton.coursework;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import static java.sql.Types.NULL;

public class Report1DetailsAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Task> tasks;
    private static LayoutInflater inflater = null;

    public Report1DetailsAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Intent intent;
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.report1details_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        TextView task = (TextView) vi.findViewById(R.id.task);
        TextView employee = (TextView) vi.findViewById(R.id.employee);
//        ListView listView = (ListView) vi.findViewById(R.id.listView);

        task.setText(tasks.get(position).getName());
        employee.setText(mDatabaseHelper.getEmployee(tasks.get(position).getP_id()).getName());

        ArrayList<Stage> stages = new ArrayList<>();
        stages.addAll(mDatabaseHelper.getStages(tasks.get(position).getId()));

//        Report1DetailsInnerAdapter report1DetailsInnerAdapter = new Report1DetailsInnerAdapter(context, stages);
//        listView.setAdapter(report1DetailsInnerAdapter);


        LinearLayout myRoot = (LinearLayout) vi.findViewById(R.id.linear);
        LinearLayout a = new LinearLayout(context);
        a.setOrientation(LinearLayout.VERTICAL);
        Log.e("TASK: ", "HEY!!!!!");
        for (int i = 0; i < stages.size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(stages.get(i).getName());
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(10, 10, 10, 10);
            a.addView(textView);
            ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setProgress(stages.get(i).getProgress());
            progressBar.setPadding(0, 10, 0, 10);
            a.addView(progressBar);
        }

        myRoot.addView(a);

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}

