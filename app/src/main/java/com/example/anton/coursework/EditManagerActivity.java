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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditManagerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatabaseHelper mDatabaseHelper;
    EditText editName, editAge, editSex, editPhone, editEmail, editAddress, editExperience, editPassport, editSalary, editBankAccount;
    Button btnDel;
    TextView editDob;

    private static int position;




    int  day_x, month_x, year_x;
    Calendar today;
    long dob = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_manager);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("id", -1);

        editName = (EditText) findViewById(R.id.editName);
//        editAge = (EditText) findViewById(R.id.editAge);
        editSex = (EditText) findViewById(R.id.editSex);
        editDob = (TextView) findViewById(R.id.editDob);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editExperience = (EditText) findViewById(R.id.editExperience);
        editPassport = (EditText) findViewById(R.id.editPassport);
        editSalary = (EditText) findViewById(R.id.editSalary);
        editBankAccount = (EditText) findViewById(R.id.editBankAccount);
        btnDel = (Button) findViewById(R.id.btnDel);

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditManagerActivity.this)
                        .setTitle("Видалити дані про менеджера")
                        .setMessage("Ви впевнені, що хочете видалити дані про менеджера?\n" +
                                "При видаленні також будуть втрачені дані зв'язаних записів!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabaseHelper.deleteManager(position);
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

        editDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                DatePickerDialog dpickerListner = new DatePickerDialog(EditManagerActivity.this, EditManagerActivity.this, year_x, month_x, day_x);
                dpickerListner.show();
            }
        });

        addItemsRunTime();

        editName.setSelection(editName.getText().length());
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        year_x = year;
        month_x = month;
        day_x = dayOfMonth;

        today.set(year_x, month_x, day_x);
        dob = today.getTimeInMillis();

        Date datetime=new Date(dob);
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editDob.setText(dateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    }



    public void addItemsRunTime() {
        editName.setText(mDatabaseHelper.getManager(position).getName());
//        editAge.setText(String.valueOf(mDatabaseHelper.getManager(position).getAge()));
        editSex.setText(mDatabaseHelper.getManager(position).getSex());
        Date datetime=new Date(mDatabaseHelper.getManager(position).getDob());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editDob.setText(String.valueOf(dateString));
        dob = mDatabaseHelper.getManager(position).getDob();
        editPhone.setText(mDatabaseHelper.getManager(position).getPhone());
        editEmail.setText(mDatabaseHelper.getManager(position).getEmail());
        editAddress.setText(mDatabaseHelper.getManager(position).getAddress());
        editExperience.setText(String.valueOf(mDatabaseHelper.getManager(position).getExperience()));
        editPassport.setText(mDatabaseHelper.getManager(position).getPassport());
        editSalary.setText(String.valueOf(mDatabaseHelper.getManager(position).getSalary()));
        editBankAccount.setText(mDatabaseHelper.getManager(position).getBankAccount());
    }

    public int AddData() {
        Manager manager = new Manager();

        manager.setId(position);

        if (editName.length() == 0) {
            toastMessage("No name!");
            return 1;
        }
        manager.setName(editName.getText().toString());

//        if (editAge.length() == 0) {
//            toastMessage("No age!");
//            return 1;
//        }
//        int age;
//        try {
//            age = Integer.parseInt(editAge.getText().toString());
//        } catch (NumberFormatException e) {
//            toastMessage("Wrong age!");
//            return 1;
//        }
//        if (age > 0 && age < 100) manager.setAge(age);
//        else {
//            toastMessage("Age must be between 1 and 99!");
//            return 1;
//        }

        if (editSex.length() == 0) {
            toastMessage("No sex!");
            return 1;
        }
        manager.setSex(editSex.getText().toString());

        if (editDob.length() == 0) {
            toastMessage("Введіть дату нарождення!");
            return 1;
        }
        manager.setDob(dob);

        if (editPhone.length() == 0) {
            toastMessage("No phone!");
            return 1;
        }
        manager.setPhone(editPhone.getText().toString());

        if (editEmail.length() == 0) {
            toastMessage("No Email!");
            return 1;
        }
        manager.setEmail(editEmail.getText().toString());

        if (editAddress.length() == 0) {
            toastMessage("No address!");
            return 1;
        }
        manager.setAddress(editAddress.getText().toString());

        if (editExperience.length() == 0) {
            toastMessage("No experience!");
            return 1;
        }
        int experience;
        try {
            experience = Integer.parseInt(editExperience.getText().toString());
        } catch (NumberFormatException e) {
            toastMessage("Wrong experience!");
            return 1;
        }
        if (experience > 0) manager.setExperience(experience);
        else {
            toastMessage("Wrong experience!");
            return 1;
        }

        if (editPassport.length() == 0) {
            toastMessage("No passport!");
            return 1;
        }
        manager.setPassport(editPassport.getText().toString());

        if (editSalary.length() == 0) {
            toastMessage("No salary!");
            return 1;
        }
        double salary;
        try {
            salary = Double.parseDouble(editSalary.getText().toString());
        } catch (NumberFormatException e) {
            toastMessage("Wrong salary!");
            return 1;
        }
        if (salary > 0) manager.setSalary(salary);
        else {
            toastMessage("Wrong salary!");
            return 1;
        }

        if (editBankAccount.length() == 0) {
            toastMessage("No bank account!");
            return 1;
        }
        manager.setBankAccount(editBankAccount.getText().toString());


        Date d = new Date(dob);
        Date now = new Date(System.currentTimeMillis());
        int year = d.getYear();
        int yearNow = now.getYear();

        Date temp = new Date(dob);
        temp.setYear(now.getYear());
        if (temp.before(now)) {
            manager.setAge(yearNow - year - 1);
        }
        else {
            manager.setAge(yearNow - year);
        }

        now.setYear(now.getYear()-18);
        if (now.before(d)) {
            toastMessage("Вік має бути більшим 18!");
            return 1;
        }

        mDatabaseHelper.updateManager(manager);

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
