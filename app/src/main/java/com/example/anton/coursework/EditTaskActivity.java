package com.example.anton.coursework;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class EditTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Spinner dropdown;

    DatabaseHelper mDatabaseHelper;
    EditText editName, editStatus;
    TextView editBeginDate, editDeadline;
    Button btnDel;

    private static int selectedListId;


    int  day_x, month_x, year_x; // deadline
    int day_y, month_y, year_y; // begin date

    Calendar today;

    int btnID;

    long beginDate = 0;
    long deadline = 0;

    private static int position;
    private static int positionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        mDatabaseHelper = mDatabaseHelper.getInstance(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("id", -1);
        positionList = 0;

        final ArrayList<Employee> employees = new ArrayList<>();
        employees.addAll(mDatabaseHelper.getAllEmployees());
        ArrayList<String> items = new ArrayList<>();
        int temp = mDatabaseHelper.getTask(position).getP_id();
        for (int i = 0; i < employees.size(); i++) {
            if (temp == employees.get(i).getId()) positionList = i;
            items.add(employees.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);

        dropdown.setSelection(positionList);

        selectedListId = 0;

        editName = (EditText) findViewById(R.id.editName);
        editBeginDate = (TextView) findViewById(R.id.editBeginDate);
        editDeadline = (TextView) findViewById(R.id.editDeadline);
        editStatus = (EditText) findViewById(R.id.editStatus);
        btnDel = (Button) findViewById(R.id.btnDel);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditTaskActivity.this)
                        .setTitle("Видалити дані про завдання")
                        .setMessage("Ви впевнені, що хочете видалити дані про завдання?\n" +
                                "При видаленні також будуть втрачені дані зв'язаних записів!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabaseHelper.deleteTask(position);
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


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedListId = (int) dropdown.getSelectedItemId();
                for (int i = 0; i < employees.size(); i++) {
                    if (selectedListId == i) {
                        selectedListId = employees.get(i).getId();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
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
                DatePickerDialog dpickerListner = new DatePickerDialog(EditTaskActivity.this, EditTaskActivity.this, year_y, month_y, day_y);
                dpickerListner.show();

            }
        });

        editDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                btnID = 2;
                DatePickerDialog dpickerListner = new DatePickerDialog(EditTaskActivity.this, EditTaskActivity.this, year_x, month_x, day_x);
                dpickerListner.show();

            }
        });

        addItemsRunTime();

        editName.setSelection(editName.getText().length());
    }

    public void addItemsRunTime() {
        editName.setText(mDatabaseHelper.getTask(position).getName());

        Date datetime=new Date(mDatabaseHelper.getTask(position).getBeginDate());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editBeginDate.setText(String.valueOf(dateString));
        beginDate = mDatabaseHelper.getTask(position).getBeginDate();

        datetime=new Date(mDatabaseHelper.getTask(position).getDeadline());
        sdf1=new SimpleDateFormat("dd.MM.yyyy");
        dateString = sdf1.format(datetime);
        editDeadline.setText(String.valueOf(dateString));
        deadline = mDatabaseHelper.getTask(position).getDeadline();

        editStatus.setText(String.valueOf(mDatabaseHelper.getTask(position).getStatus()));
    }

    public int AddData() {
        Task task = new Task();

        task.setId(position);

        if (editName.length() == 0) {
            toastMessage("Введіть назву!");
            return 1;
        }
        task.setName(editName.getText().toString());

        if (editBeginDate.length() == 0) {
            toastMessage("Введіть дату початку!");
            return 1;
        }
        task.setBeginDate(beginDate);

        if (editDeadline.length() == 0) {
            toastMessage("Введіть дату завершення!");
            return 1;
        }
        task.setDeadline(deadline);

        if (beginDate > deadline) {
            toastMessage("Дата початку повинна передувати даті завершення!");
            return 1;
        }

        if (editStatus.length() == 0) {
            toastMessage("Введіть статус!");
            return 1;
        }
        int status = Integer.parseInt(editStatus.getText().toString());
        task.setStatus(status);

        task.setP_id(selectedListId);

        mDatabaseHelper.updateTask(task);

        return 0;
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
