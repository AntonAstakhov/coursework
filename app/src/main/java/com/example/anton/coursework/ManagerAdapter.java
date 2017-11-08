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

public class ManagerAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Manager> managers;
    private static LayoutInflater inflater = null;

    public ManagerAdapter(Context context, ArrayList<Manager> managers) {
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
            vi = inflater.inflate(R.layout.manager_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, EditManagerActivity.class);
                int i = managers.get(position).getId();
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
        TextView editExperience = (TextView) vi.findViewById(R.id.editExperience);
        TextView editPassport = (TextView) vi.findViewById(R.id.editPassport);
        TextView editSalary = (TextView) vi.findViewById(R.id.editSalary);
        TextView editBankAccount = (TextView) vi.findViewById(R.id.editBankAccount);

        id.setText(String.valueOf(managers.get(position).getId()));
        editName.setText(managers.get(position).getName());
        editAge.setText(String.valueOf(managers.get(position).getAge()));
        editSex.setText(managers.get(position).getSex());
        Date datetime=new Date(managers.get(position).getDob());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editDob.setText(dateString);
        editPhone.setText(managers.get(position).getPhone());
        editEmail.setText(managers.get(position).getEmail());
        editAddress.setText(managers.get(position).getAddress());
        editExperience.setText(String.valueOf(managers.get(position).getExperience()));
        editPassport.setText(managers.get(position).getPassport());
        editSalary.setText(String.valueOf(managers.get(position).getSalary()));
        editBankAccount.setText(managers.get(position).getBankAccount());

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
