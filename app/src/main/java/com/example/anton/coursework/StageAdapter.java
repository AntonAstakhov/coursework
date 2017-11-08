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

public class StageAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Stage> stages;
    private static LayoutInflater inflater = null;

    public StageAdapter(Context context, ArrayList<Stage> stages) {
        this.context = context;
        this.stages = stages;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stages.size();
    }

    @Override
    public Object getItem(int position) {
        return stages.get(position);
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
            vi = inflater.inflate(R.layout.stage_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, EditStageActivity.class);
                int i = stages.get(position).getId();
                intent.putExtra("id", i);
                context.startActivity(intent);
            }
        });

        TextView id = (TextView) vi.findViewById(R.id.id);
        TextView editName = (TextView) vi.findViewById(R.id.editName);
        TextView editBeginDate = (TextView) vi.findViewById(R.id.editBeginDate);
        TextView editDeadline = (TextView) vi.findViewById(R.id.editDeadline);
        TextView editPriority = (TextView) vi.findViewById(R.id.editPriority);
        TextView editProgress = (TextView) vi.findViewById(R.id.editProgress);
        TextView editWorkingHours = (TextView) vi.findViewById(R.id.editWorkingHours);
        TextView p_id = (TextView) vi.findViewById(R.id.p_id);

        id.setText(String.valueOf(stages.get(position).getId()));
        editName.setText(stages.get(position).getName());
        Date datetime=new Date(stages.get(position).getBeginDate());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editBeginDate.setText(dateString);
        datetime=new Date(stages.get(position).getDeadline());
        sdf1=new SimpleDateFormat("dd.MM.yyyy");
        dateString = sdf1.format(datetime);
        editDeadline.setText(dateString);
        editPriority.setText(String.valueOf(stages.get(position).getPriority()));
        editProgress.setText(String.valueOf(stages.get(position).getProgress()));
        editWorkingHours.setText(String.valueOf(stages.get(position).getWorkingHours()));
        p_id.setText(String.valueOf(stages.get(position).getP_id()));

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
