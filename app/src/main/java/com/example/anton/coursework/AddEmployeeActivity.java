package com.example.anton.coursework;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddEmployeeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatabaseHelper mDatabaseHelper;
    EditText editName, editAge, editSex, editPhone, editEmail, editAddress, editPosition, editExperience, editPassport, editSalary, editBankAccount;
    Spinner dropdown;
    TextView editDob;
    private static int selectedListId;

    Switch editStatus;
    private static int status;


    int  day_x, month_x, year_x;
    Calendar today;
    long dob = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);


        final ArrayList<Department> departments = new ArrayList<>();
        departments.addAll(mDatabaseHelper.getAllDepartments());
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            items.add(departments.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);

        selectedListId = 0;


        editName = (EditText) findViewById(R.id.editName);
//        editAge = (EditText) findViewById(R.id.editAge);
        editSex = (EditText) findViewById(R.id.editSex);
        editDob = (TextView) findViewById(R.id.editDob);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editPosition = (EditText) findViewById(R.id.editPosition);
        editExperience = (EditText) findViewById(R.id.editExperience);
        editPassport = (EditText) findViewById(R.id.editPassport);
        editSalary = (EditText) findViewById(R.id.editSalary);
        editBankAccount = (EditText) findViewById(R.id.editBankAccount);
        editStatus = (Switch) findViewById(R.id.switch1);

        status = 0;


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedListId = (int) dropdown.getSelectedItemId();
                for (int i = 0; i < departments.size(); i++) {
                    if (selectedListId == i) {
                        selectedListId = departments.get(i).getId();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        editStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = 1;
                    editStatus.setText("Статус: зайнятий");
                }
                else {
                    status = 0;
                    editStatus.setText("Статус: вільний");
                }
            }
        });


        today = Calendar.getInstance();

        day_x = today.get(Calendar.DAY_OF_MONTH);
        month_x = today.get(Calendar.MONTH);
        year_x = today.get(Calendar.YEAR);

        editDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                DatePickerDialog dpickerListner = new DatePickerDialog(AddEmployeeActivity.this, AddEmployeeActivity.this, year_x, month_x, day_x);
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
        Employee employee = new Employee();
        if (editName.length() == 0) {
            toastMessage("Введіть ПІБ!");
            return;
        }
        employee.setName(editName.getText().toString());

//        if (editAge.length() == 0) {
//            toastMessage("Введіть вік!");
//            return;
//        }
//        int age = Integer.parseInt(editAge.getText().toString());
//        if (age >= 18 && age < 100) employee.setAge(age);
//        else {
//            toastMessage("Вік має бути більшим 18 і меншим 100!");
//            return;
//        }

        if (editSex.length() == 0) {
            toastMessage("Введіть стать!");
            return;
        }
        employee.setSex(editSex.getText().toString());

        if (editDob.length() == 0) {
            toastMessage("Введіть дату нарождення!");
            return;
        }
        employee.setDob(dob);

        if (editPhone.length() == 0) {
            toastMessage("Введіть телефон!");
            return;
        }
        employee.setPhone(editPhone.getText().toString());

        if (editEmail.length() == 0) {
            toastMessage("Введіть електронну пошту!");
            return;
        }
        employee.setEmail(editEmail.getText().toString());

        if (editAddress.length() == 0) {
            toastMessage("Введіть адресу!");
            return;
        }
        employee.setAddress(editAddress.getText().toString());

        if (editPosition.length() == 0) {
            toastMessage("Введіть посаду!");
            return;
        }
        employee.setPosition(editPosition.getText().toString());

        if (editExperience.length() == 0) {
            toastMessage("Введіть досвід!");
            return;
        }
        int experience = Integer.parseInt(editExperience.getText().toString());
        if (experience >= 0 && experience <= 100) employee.setExperience(experience);
        else {
            toastMessage("Досвід має належати діапазону [0, 100] !");
            return;
        }

        if (editPassport.length() == 0) {
            toastMessage("Введіть паспортні дані!");
            return;
        }
        employee.setPassport(editPassport.getText().toString());

        if (editSalary.length() == 0) {
            toastMessage("Введіть зарплату!");
            return;
        }
        double salary = Double.parseDouble(editSalary.getText().toString());
        if (salary > 0) employee.setSalary(salary);
        else {
            toastMessage("Введіть коректну зарплату!");
            return;
        }

        if (editBankAccount.length() == 0) {
            toastMessage("Введіть банківський рахунок!");
            return;
        }
        employee.setBankAccount(editBankAccount.getText().toString());

//        if (editStatus.length() == 0) {
//            toastMessage("No status!");
//            return;
//        }
//        int status;
//        try {
//            status = Integer.parseInt(editStatus.getText().toString());
//        } catch (NumberFormatException e) {
//            toastMessage("Wrong status!");
//            return;
//        }
//        if (status > 0) employee.setStatus(status);
//        else {
//            toastMessage("Wrong status!");
//            return;
//        }

        employee.setStatus(status);

        if (selectedListId == 0) {
            toastMessage("Wrong department!");
            return;
        }
        employee.setP_id(selectedListId);


        Date d = new Date(dob);
        Date now = new Date(System.currentTimeMillis());
        int year = d.getYear();
        int yearNow = now.getYear();

        Date temp = new Date(dob);
        temp.setYear(now.getYear());
        if (temp.before(now)) {
            employee.setAge(yearNow - year - 1);
        }
        else {
            employee.setAge(yearNow - year);
        }

        now.setYear(now.getYear()-18);
        if (now.before(d)) {
            toastMessage("Вік має бути більшим 18!");
            return;
        }


        mDatabaseHelper.addEmployee(employee);

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
