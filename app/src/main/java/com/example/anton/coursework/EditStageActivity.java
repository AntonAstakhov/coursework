package com.example.anton.coursework;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.isSameDay;

public class EditStageActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Spinner dropdown;

    DatabaseHelper mDatabaseHelper;
    EditText editName, editProgress,editWorkingHours;
    TextView editBeginDate, editDeadline;
    Spinner editPriority;
    Button btnDel;

    private static int selectedListId;

    private static int selectedPriority;


    Calendar today;

    int btnID;

    long beginDate = 0;
    long deadline = 0;

    int  day_x, month_x, year_x; // deadline
    int day_y, month_y, year_y; // begin date



    private static int position;
    private static int positionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stage);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);


        Intent intent = getIntent();
        position = intent.getIntExtra("id", -1);
        positionList = 0;


        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(mDatabaseHelper.getAllTasks());
        ArrayList<String> items = new ArrayList<>();
        int temp = mDatabaseHelper.getStage(position).getP_id();
        for (int i = 0; i < tasks.size(); i++) {
            if (temp == tasks.get(i).getId()) positionList = i;
            items.add(tasks.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);

        dropdown.setSelection(positionList);


        selectedListId = 0;

        editName = (EditText) findViewById(R.id.editName);
        editBeginDate = (TextView) findViewById(R.id.editBeginDate);
        editDeadline = (TextView) findViewById(R.id.editDeadline);
        editPriority = (Spinner) findViewById(R.id.spinner2);
        editProgress = (EditText) findViewById(R.id.editProgress);
        editWorkingHours = (EditText) findViewById(R.id.editWorkingHours);
        btnDel = (Button) findViewById(R.id.btnDel);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedListId = (int) dropdown.getSelectedItemId();
                for (int i = 0; i < tasks.size(); i++) {
                    if (selectedListId == i) {
                        selectedListId = tasks.get(i).getId();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        ArrayList<String> items2 = new ArrayList<>();
        items2.add("Найвищий");
        items2.add("Високий");
        items2.add("Середній");
        items2.add("Низький");
        items2.add("Дуже низький");
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items2);
        editPriority.setAdapter(adapter2);

        editPriority.setSelection(mDatabaseHelper.getStage(position).getPriority() - 1);


        selectedPriority = 1;

        editPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedPriority = (int) editPriority.getSelectedItemId() + 1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditStageActivity.this)
                        .setTitle("Видалити дані про етап")
                        .setMessage("Ви впевнені, що хочете видалити дані про етап?\n" +
                                "При видаленні також будуть втрачені дані зв'язаних записів!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabaseHelper.deleteStage(position);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(R.drawable.ic_warning_black_24dp)
                        .show();
            }
        });




        today = Calendar.getInstance();

        day_x = today.get(Calendar.DAY_OF_MONTH);
        month_x = today.get(Calendar.MONTH);
        year_x = today.get(Calendar.YEAR);

        day_y = today.get(Calendar.DAY_OF_MONTH);
        month_y = today.get(Calendar.MONTH);
        year_y = today.get(Calendar.YEAR);


        editBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                btnID = 1;
                DatePickerDialog dpickerListner = new DatePickerDialog(EditStageActivity.this, EditStageActivity.this, year_y, month_y, day_y);
                dpickerListner.show();

            }
        });

        editDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                btnID = 2;
                DatePickerDialog dpickerListner = new DatePickerDialog(EditStageActivity.this, EditStageActivity.this, year_x, month_x, day_x);
                dpickerListner.show();

            }
        });


        addItemsRunTime();

        editName.setSelection(editName.getText().length());

    }


    public void addItemsRunTime() {
        editName.setText(mDatabaseHelper.getStage(position).getName());

        Date datetime=new Date(mDatabaseHelper.getStage(position).getBeginDate());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editBeginDate.setText(String.valueOf(dateString));
        beginDate = mDatabaseHelper.getStage(position).getBeginDate();

        datetime=new Date(mDatabaseHelper.getStage(position).getDeadline());
        sdf1=new SimpleDateFormat("dd.MM.yyyy");
        dateString = sdf1.format(datetime);
        editDeadline.setText(String.valueOf(dateString));
        deadline = mDatabaseHelper.getStage(position).getDeadline();

        editProgress.setText(String.valueOf(mDatabaseHelper.getStage(position).getProgress()));
        editWorkingHours.setText(String.valueOf(mDatabaseHelper.getStage(position).getWorkingHours()));

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (btnID == 1) {
            year_y = year;
            month_y = month;
            day_y = dayOfMonth;

            today.set(year_y, month_y, day_y);
            beginDate = today.getTimeInMillis();

            Date datetime=new Date(beginDate);
            SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
            String dateString = sdf1.format(datetime);
            editBeginDate.setText(dateString);
        }

        if (btnID == 2) {
            year_x = year;
            month_x = month;
            day_x = dayOfMonth;

            today.set(year_x, month_x, day_x);
            deadline = today.getTimeInMillis();

            Date datetime=new Date(deadline);
            SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
            String dateString = sdf1.format(datetime);
            editDeadline.setText(dateString);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }


    public int AddData() {
        Stage stage = new Stage();

        stage.setId(position);

        if (editName.length() == 0) {
            toastMessage("No name!");
            return 1;
        }
        stage.setName(editName.getText().toString());

        if (editBeginDate.length() == 0) {
            toastMessage("Введіть дату початку!");
            return 1;
        }
        stage.setBeginDate(beginDate);

        if (editDeadline.length() == 0) {
            toastMessage("Введіть дату завершення!");
            return 1;
        }
        stage.setDeadline(deadline);

        if (new Date(beginDate).after(new Date(mDatabaseHelper.getTask(selectedListId).getDeadline()))) {
            if (isSameDay(new Date(beginDate), new Date(mDatabaseHelper.getTask(selectedListId).getDeadline()))) {

            }
            else {
                toastMessage("Дата початку повинна передувати даті завершення!");
                return 1;
            }
        }

        if (new Date(beginDate).before(new Date(mDatabaseHelper.getTask(selectedListId).getBeginDate()))) {
            if (isSameDay(new Date(beginDate), new Date(mDatabaseHelper.getTask(selectedListId).getBeginDate()))) {

            }
            else {
                toastMessage("Етап не може розпочатись раніше завдання, якому він належить!");
                return 1;
            }
        }

        if (new Date(deadline).after(new Date(mDatabaseHelper.getTask(selectedListId).getDeadline()))) {
            if (isSameDay(new Date(deadline), new Date(mDatabaseHelper.getTask(selectedListId).getDeadline()))) {

            }
            else {
                toastMessage("Етап не може закінчитись пізніше завдання, якому він належить!");
                return 1;
            }
        }

        stage.setPriority(selectedPriority);
        Log.e("PRIORITY: ", String.valueOf(selectedPriority));

        if (editProgress.length() == 0) {
            toastMessage("Введіть ступінь готовності!");
            return 1;
        }
        int progress = Integer.parseInt(editProgress.getText().toString());
        if (progress >= 0 && progress <=100) stage.setProgress(progress);
        else {
            toastMessage("Ступінь готовності має лежати в діапазоні (0-100)!");
            return 1;
        }

        if (editWorkingHours.length() == 0) {
            toastMessage("Введіть обсяг годин на виконання!");
            return 1;
        }
        double workingHours = Double.parseDouble(editWorkingHours.getText().toString());

        if (workingHours > 0) stage.setWorkingHours(workingHours);
        else {
            toastMessage("Обсяг годин на виконання має бути більше 0!");
            return 1;
        }


        if (selectedListId == 0) {
            toastMessage("Wrong task!");
            return 1;
        }
        stage.setP_id(selectedListId);

        mDatabaseHelper.updateStage(stage);

        return 0;
    }


    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_check) {
            if (AddData() == 0) finish();
            return true;
        }

        if (id == android.R.id.home) {
//            AddData();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        AddData();
        finish();
        super.onBackPressed();
    }
}
