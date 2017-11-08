package com.example.anton.coursework;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.sql.Types.NULL;

public class EmployeeAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Employee> employees;
    private static LayoutInflater inflater = null;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
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
            vi = inflater.inflate(R.layout.employee_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, EditEmployeeActivity.class);
                int i = employees.get(position).getId();
                intent.putExtra("id", i);
                context.startActivity(intent);
            }
        });

        TextView id = (TextView) vi.findViewById(R.id.id);
        TextView editName = (TextView) vi.findViewById(R.id.editName);
        TextView editAge = (TextView) vi.findViewById(R.id.editAge);
        TextView editSex = (TextView) vi.findViewById(R.id.editSex);
        TextView editDob = (TextView) vi.findViewById(R.id.editDob);
        TextView editPhone = (TextView) vi.findViewById(R.id.editPhone);
        TextView editEmail = (TextView) vi.findViewById(R.id.editEmail);
        TextView editAddress = (TextView) vi.findViewById(R.id.editAddress);
        TextView editPosition = (TextView) vi.findViewById(R.id.editPosition);
        TextView editExperience = (TextView) vi.findViewById(R.id.editExperience);
        TextView editPassport = (TextView) vi.findViewById(R.id.editPassport);
        TextView editSalary = (TextView) vi.findViewById(R.id.editSalary);
        TextView editBankAccount = (TextView) vi.findViewById(R.id.editBankAccount);
        TextView editStatus = (TextView) vi.findViewById(R.id.editStatus);
        TextView p_id = (TextView) vi.findViewById(R.id.p_id);

        id.setText(String.valueOf(employees.get(position).getId()));
        editName.setText(employees.get(position).getName());
        editAge.setText(String.valueOf(employees.get(position).getAge()));
        editSex.setText(employees.get(position).getSex());
        Date datetime=new Date(employees.get(position).getDob());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editDob.setText(dateString);
        editPhone.setText(employees.get(position).getPhone());
        editEmail.setText(employees.get(position).getEmail());
        editAddress.setText(employees.get(position).getAddress());
        editPosition.setText(employees.get(position).getPosition());
        editExperience.setText(String.valueOf(employees.get(position).getExperience()));
        editPassport.setText(employees.get(position).getPassport());
        editSalary.setText(String.valueOf(employees.get(position).getSalary()));
        editBankAccount.setText(employees.get(position).getBankAccount());
        editStatus.setText(String.valueOf(employees.get(position).getStatus()));
        p_id.setText(String.valueOf(employees.get(position).getP_id()));

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
