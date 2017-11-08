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

public class TaskAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Task> tasks;
    private static LayoutInflater inflater = null;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
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
            vi = inflater.inflate(R.layout.task_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, EditTaskActivity.class);
                int i = tasks.get(position).getId();
                intent.putExtra("id", i);
                context.startActivity(intent);
            }
        });

        TextView id = (TextView) vi.findViewById(R.id.id);
        TextView editName = (TextView) vi.findViewById(R.id.editName);
        TextView editBeginDate = (TextView) vi.findViewById(R.id.editBeginDate);
        TextView editDeadline = (TextView) vi.findViewById(R.id.editDeadline);
        TextView editStatus = (TextView) vi.findViewById(R.id.editStatus);
        TextView p_id = (TextView) vi.findViewById(R.id.p_id);

        id.setText(String.valueOf(tasks.get(position).getId()));
        editName.setText(tasks.get(position).getName());
        Date datetime=new Date(tasks.get(position).getBeginDate());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editBeginDate.setText(dateString);
        datetime=new Date(tasks.get(position).getDeadline());
        sdf1=new SimpleDateFormat("dd.MM.yyyy");
        dateString = sdf1.format(datetime);
        editDeadline.setText(dateString);
        editStatus.setText(String.valueOf(tasks.get(position).getStatus()));
        p_id.setText(String.valueOf(tasks.get(position).getP_id()));

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
