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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.sql.Types.NULL;

public class DepartmentAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Department> departments;
    private static LayoutInflater inflater = null;

    public DepartmentAdapter(Context context, ArrayList<Department> departments) {
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
            vi = inflater.inflate(R.layout.department_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, EditDepartmentActivity.class);
                int i = departments.get(position).getId();
                intent.putExtra("id", i);
                context.startActivity(intent);
            }
        });

        TextView id = (TextView) vi.findViewById(R.id.id);
        TextView editName = (TextView) vi.findViewById(R.id.editName);
        TextView editPhone = (TextView) vi.findViewById(R.id.editPhone);
        TextView editEmail = (TextView) vi.findViewById(R.id.editEmail);
        TextView p_id = (TextView) vi.findViewById(R.id.p_id);

        id.setText(String.valueOf(departments.get(position).getId()));
        editName.setText(departments.get(position).getName());
        editPhone.setText(departments.get(position).getPhone());
        editEmail.setText(departments.get(position).getEmail());
        p_id.setText(String.valueOf(departments.get(position).getP_id()));

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
