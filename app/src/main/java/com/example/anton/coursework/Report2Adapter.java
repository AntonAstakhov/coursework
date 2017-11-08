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


public class Report2Adapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Manager> managers;
    private static LayoutInflater inflater = null;

    public Report2Adapter(Context context, ArrayList<Manager> managers) {
        this.context = context;
        this.managers = managers;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return managers.size();
    }

    @Override
    public Object getItem(int position) {
        return managers.get(position);
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
            vi = inflater.inflate(R.layout.report2_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

//        vi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(context, Report2DetailsActivity.class);
//                int i = managers.get(position).getId();
//                intent.putExtra("id", i);
//                context.startActivity(intent);
//            }
//        });

        TextView employee = (TextView) vi.findViewById(R.id.employee);
        TextView manager = (TextView) vi.findViewById(R.id.manager);
        TextView busy = (TextView) vi.findViewById(R.id.busy);
        TextView free = (TextView) vi.findViewById(R.id.free);
        TextView overdue = (TextView) vi.findViewById(R.id.overdue);


        int overdueCount = 0;
        int busyCount = 0;
        int freeCount = 0;
        int employeeCount = 0;

        employeeCount = mDatabaseHelper.getEmployeesCount(managers.get(position).getId());

//        Statuses
//        0 - busy
//        1 - free

        String departmentName = "не призначено";
        ArrayList<Department> departments = new ArrayList<>();
        departments.addAll(mDatabaseHelper.getAllDepartments());
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getP_id() == managers.get(position).getId()) {
                departmentName = departments.get(i).getName();
            }
            else {
                departments.remove(i);
                i--;
            }
        }

        ArrayList<Employee> employees = new ArrayList<>();
        employees.addAll(mDatabaseHelper.getAllEmployees());

        for (int j = 0; j < departments.size(); j++ ) {

            for (int i = 0; i < employees.size(); i++) {

                if (employees.get(i).getP_id() != departments.get(j).getId())  {

                }

                else if (employees.get(i).getStatus() == 0) {
                    busyCount++;
                }
                else if (employees.get(i).getStatus() == 1) {
                    freeCount++;
                }
            }
        }


//        ArrayList<Task> tasks = new ArrayList<>();
//        tasks.addAll(mDatabaseHelper.getAllTasks());



        employee.setText(String.valueOf(employeeCount));
        manager.setText(managers.get(position).getName());
        overdue.setText(String.valueOf(overdueCount));
        busy.setText(String.valueOf(busyCount));
        free.setText(String.valueOf(freeCount));

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}

