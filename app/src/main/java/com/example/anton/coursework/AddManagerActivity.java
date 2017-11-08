package com.example.anton.coursework;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddManagerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatabaseHelper mDatabaseHelper;
    EditText editName, editAge, editSex, editPhone, editEmail, editAddress, editExperience, editPassport, editSalary, editBankAccount;
    TextView editDob;


    int  day_x, month_x, year_x;
    Calendar today;
    long dob = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manager);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);

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





        today = Calendar.getInstance();

        day_x = today.get(Calendar.DAY_OF_MONTH);
        month_x = today.get(Calendar.MONTH);
        year_x = today.get(Calendar.YEAR);

        editDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                DatePickerDialog dpickerListner = new DatePickerDialog(AddManagerActivity.this, AddManagerActivity.this, year_x, month_x, day_x);
                dpickerListner.show();
            }
        });

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



    public void AddData() {
        Manager manager = new Manager();
        if (editName.length() == 0) {
            toastMessage("Введіть ПІБ!");
            return;
        }
        manager.setName(editName.getText().toString());

//        if (editAge.length() == 0) {
//            toastMessage("Введіть вік!");
//            return;
//        }
//        int age = Integer.parseInt(editAge.getText().toString());
//        if (age >= 18 && age < 100) manager.setAge(age);
//        else {
//            toastMessage("Вік має бути більшим 18 і меншим 100!");
//            return;
//        }

        if (editSex.length() == 0) {
            toastMessage("Введіть стать!");
            return;
        }
        manager.setSex(editSex.getText().toString());

        if (editDob.length() == 0) {
            toastMessage("Введіть дату нарождення!");
            return;
        }
        manager.setDob(dob);

        if (editPhone.length() == 0) {
            toastMessage("Введіть телефон!");
            return;
        }
        manager.setPhone(editPhone.getText().toString());

        if (editEmail.length() == 0) {
            toastMessage("Введіть електронну пошту!");
            return;
        }
        manager.setEmail(editEmail.getText().toString());

        if (editAddress.length() == 0) {
            toastMessage("Введіть адресу!");
            return;
        }
        manager.setAddress(editAddress.getText().toString());

        if (editExperience.length() == 0) {
            toastMessage("Введіть досвід!");
            return;
        }
        int experience = Integer.parseInt(editExperience.getText().toString());
        if (experience >= 0 && experience <= 100) manager.setExperience(experience);
        else {
            toastMessage("Досвід має належати діапазону [0, 100] !");
            return;
        }

        if (editPassport.length() == 0) {
            toastMessage("Введіть паспортні дані!");
            return;
        }
        manager.setPassport(editPassport.getText().toString());

        if (editSalary.length() == 0) {
            toastMessage("Введіть зарплату!");
            return;
        }
        double salary = Double.parseDouble(editSalary.getText().toString());
        if (salary > 0) manager.setSalary(salary);
        else {
            toastMessage("Введіть коректну зарплату!");
            return;
        }

        if (editBankAccount.length() == 0) {
            toastMessage("Введіть банківський рахунок!");
            return;
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
        if (now.after(d)) {
            toastMessage("Вік має бути більшим 18!");
            return;
        }

        mDatabaseHelper.addManager(manager);

        finish();
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            AddData();
            return true;
        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
