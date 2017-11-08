package com.example.anton.coursework;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import static java.sql.Types.NULL;

public class Report1Adapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Department> departments;
    private static LayoutInflater inflater = null;

    public Report1Adapter(Context context, ArrayList<Department> departments) {
        this.context = context;
        this.departments = departments;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return departments.size();
    }

    @Override
    public Object getItem(int position) {
        return departments.get(position);
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
            vi = inflater.inflate(R.layout.report1_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, Report1DetailsActivity.class);
                int i = departments.get(position).getId();
                intent.putExtra("id", i);
                context.startActivity(intent);
            }
        });

        TextView department = (TextView) vi.findViewById(R.id.department);
        TextView manager = (TextView) vi.findViewById(R.id.manager);
        TextView executed = (TextView) vi.findViewById(R.id.executed);
        TextView actual = (TextView) vi.findViewById(R.id.actual);
        TextView future = (TextView) vi.findViewById(R.id.future);
        TextView overdue = (TextView) vi.findViewById(R.id.overdue);


//        ArrayList<Task> tasks = new ArrayList<>();
//        tasks.addAll(mDatabaseHelper.getAllTasks());
//
//        ArrayList<Employee> employees = new ArrayList<>();
//        employees.addAll(mDatabaseHelper.getAllEmployees());
//        for (int i = 0; i < employees.size(); i++) {
//            if (employees.get(i).getP_id() == departments.get(position).getId()) {
//
//            }
//            else {
//                employees.remove(i);
//                i--;
//            }
//        }
//
//        int overdueCount = 0;
//        int executedCount = 0;
//        int actualCount = 0;
//        int futureCount = 0;
//
////        Statuses
////
////        0 - executed
////        1 - actual
////        2 - future
//
//
//        for (int j = 0; j < employees.size(); j++ ) {
//
//            for (int i = 0; i < tasks.size(); i++) {
//
//
//                if (tasks.get(i).getP_id() != employees.get(j).getId()) {
//
//                }
//                else if (tasks.get(i).getStatus() == 0) {
//                    executedCount++;
//                }
//                else if (tasks.get(i).getStatus() == 2) {
//                    futureCount++;
//                }
//                else if (tasks.get(i).getStatus() == 1) {
//                    if (tasks.get(i).getDeadline() > System.currentTimeMillis()) {
//                        overdueCount++;
//                    }
//                    else {
//                        actualCount++;
//                    }
//                }
//            }
//        }


        int overdueCount = 0;
        int executedCount = 0;
        int actualCount = 0;
        int futureCount = 0;


        ArrayList<Task> tasks1 = new ArrayList<>();
//        tasks1.addAll(mDatabaseHelper.getTasks(departments.get(position).getId()));
//        for (int i = 0; i < tasks1.size(); i++) {
//            if (tasks1.get(i).getStatus() == 0) {
//                executedCount++;
//            }
//            else if (tasks1.get(i).getStatus() == 2) {
//                futureCount++;
//            }
//            else if (tasks1.get(i).getStatus() == 1) {
//                if (tasks1.get(i).getDeadline() < System.currentTimeMillis()) {
//                    overdueCount++;
//                }
//                else {
//                    actualCount++;
//                }
//            }
//        }


//        department.setText(departments.get(position).getName());
//        manager.setText(mDatabaseHelper.getManager(departments.get(position).getP_id()).getName());
//
//        executed.setText(String.valueOf(executedCount));
//        overdue.setText(String.valueOf(overdueCount));
//        actual.setText(String.valueOf(actualCount));
//        future.setText(String.valueOf(futureCount));

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
