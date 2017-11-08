package com.example.anton.coursework;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import static java.sql.Types.NULL;

public class Report1DetailsInnerAdapter extends BaseAdapter {
    DatabaseHelper mDatabaseHelper;

    Context context;
    ArrayList<Stage> stages;
    private static LayoutInflater inflater = null;

    public Report1DetailsInnerAdapter(Context context, ArrayList<Stage> stages) {
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
            vi = inflater.inflate(R.layout.report1details_inner_row, parent,false);

        mDatabaseHelper = mDatabaseHelper.getInstance(context);

        TextView stage = (TextView) vi.findViewById(R.id.stage);
        TextView percentage = (TextView) vi.findViewById(R.id.percentage);

        String p = stages.get(position).getProgress() + " %";
        stage.setText(stages.get(position).getName());
        percentage.setText(p);

        return vi;
    }


    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}

