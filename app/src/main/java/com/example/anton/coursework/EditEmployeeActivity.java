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

public class EditEmployeeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatabaseHelper mDatabaseHelper;
    EditText editName, editAge, editSex, editPhone, editEmail, editAddress, editPosition, editExperience, editPassport, editSalary, editBankAccount;
    Spinner dropdown;
    TextView editDob;
    private static int selectedListId;

    Switch editStatus;
    private static int status;

    private static int position;

    private static int positionList;

    Button btnDel;


    int  day_x, month_x, year_x;
    Calendar today;
    long dob = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("id", -1);

        positionList = 0;

        final ArrayList<Department> departments = new ArrayList<>();
        departments.addAll(mDatabaseHelper.getAllDepartments());
        ArrayList<String> items = new ArrayList<>();
        int temp = mDatabaseHelper.getEmployee(position).getP_id();
        for (int i = 0; i < departments.size(); i++) {
            if (temp == departments.get(i).getId()) positionList = i;
            items.add(departments.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);

        dropdown.setSelection(positionList);

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
        btnDel = (Button) findViewById(R.id.btnDel);

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

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditEmployeeActivity.this)
                        .setTitle("Видалити дані про робітника")
                        .setMessage("Ви впевнені, що хочете видалити дані про робітника?\n" +
                                "При видаленні також будуть втрачені дані зв'язаних записів!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabaseHelper.deleteEmployee(position);
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
                DatePickerDialog dpickerListner = new DatePickerDialog(EditEmployeeActivity.this, EditEmployeeActivity.this, year_x, month_x, day_x);
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
        editName.setText(mDatabaseHelper.getEmployee(position).getName());
//        editAge.setText(String.valueOf(mDatabaseHelper.getEmployee(position).getAge()));
        editSex.setText(mDatabaseHelper.getEmployee(position).getSex());
        Date datetime=new Date(mDatabaseHelper.getEmployee(position).getDob());
        SimpleDateFormat sdf1=new SimpleDateFormat("dd.MM.yyyy");
        String dateString = sdf1.format(datetime);
        editDob.setText(String.valueOf(dateString));
        dob = mDatabaseHelper.getEmployee(position).getDob();
        editPhone.setText(mDatabaseHelper.getEmployee(position).getPhone());
        editEmail.setText(mDatabaseHelper.getEmployee(position).getEmail());
        editAddress.setText(mDatabaseHelper.getEmployee(position).getAddress());
        editPosition.setText(mDatabaseHelper.getEmployee(position).getPosition());
        editExperience.setText(String.valueOf(mDatabaseHelper.getEmployee(position).getExperience()));
        editPassport.setText(mDatabaseHelper.getEmployee(position).getPassport());
        editSalary.setText(String.valueOf(mDatabaseHelper.getEmployee(position).getSalary()));
        editBankAccount.setText(mDatabaseHelper.getEmployee(position).getBankAccount());

        if (mDatabaseHelper.getEmployee(position).getStatus() == 1) editStatus.setChecked(true);
        else editStatus.setChecked(false);

    }


    public int AddData() {
        Employee employee = new Employee();

        employee.setId(position);

        if (editName.length() == 0) {
            toastMessage("Введіть ПІБ!");
            return 1;
        }
        employee.setName(editName.getText().toString());

//        if (editAge.length() == 0) {
//            toastMessage("Введіть вік!");
//            return 1;
//        }
//        int age = Integer.parseInt(editAge.getText().toString());
//        if (age >= 18 && age < 100) employee.setAge(age);
//        else {
//            toastMessage("Вік має бути більшим 18 і меншим 100!");
//            return 1;
//        }

        if (editSex.length() == 0) {
            toastMessage("Введіть стать!");
            return 1;
        }
        employee.setSex(editSex.getText().toString());

        if (editDob.length() == 0) {
            toastMessage("Введіть дату нарождення!");
            return 1;
        }
        employee.setDob(dob);

        if (editPhone.length() == 0) {
            toastMessage("Введіть телефон!");
            return 1;
        }
        employee.setPhone(editPhone.getText().toString());

        if (editEmail.length() == 0) {
            toastMessage("Введіть електронну пошту!");
            return 1;
        }
        employee.setEmail(editEmail.getText().toString());

        if (editAddress.length() == 0) {
            toastMessage("Введіть адресу!");
            return 1;
        }
        employee.setAddress(editAddress.getText().toString());

        if (editPosition.length() == 0) {
            toastMessage("Введіть посаду!");
            return 1;
        }
        employee.setPosition(editPosition.getText().toString());

        if (editExperience.length() == 0) {
            toastMessage("Введіть досвід!");
            return 1;
        }
        int experience = Integer.parseInt(editExperience.getText().toString());
        if (experience >= 0 && experience <= 100) employee.setExperience(experience);
        else {
            toastMessage("Досвід має належати діапазону [0, 100] !");
            return 1;
        }

        if (editPassport.length() == 0) {
            toastMessage("Введіть паспортні дані!");
            return 1;
        }
        employee.setPassport(editPassport.getText().toString());

        if (editSalary.length() == 0) {
            toastMessage("Введіть зарплату!");
            return 1;
        }
        double salary = Double.parseDouble(editSalary.getText().toString());
        if (salary > 0) employee.setSalary(salary);
        else {
            toastMessage("Введіть коректну зарплату!");
            return 1;
        }

        if (editBankAccount.length() == 0) {
            toastMessage("Введіть банківський рахунок!");
            return 1;
        }
        employee.setBankAccount(editBankAccount.getText().toString());

        employee.setStatus(status);

        if (selectedListId == 0) {
            toastMessage("Wrong manager!");
            return 1;
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
            return 1;
        }


        mDatabaseHelper.updateEmployee(employee);

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
