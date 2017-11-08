package com.example.anton.coursework;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditDepartmentActivity extends AppCompatActivity {

    Spinner dropdown;

    DatabaseHelper mDatabaseHelper;
    EditText editName, editPhone, editEmail;

    private static String selectedList;
    private static int selectedListId;
    private static int positionList;

    private static int position;

    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("id", -1);
        positionList = 0;

        final ArrayList<Manager> managers = new ArrayList<>();
        managers.addAll(mDatabaseHelper.getAllManagers());
        ArrayList<String> items = new ArrayList<>();
        int temp = mDatabaseHelper.getDepartment(position).getP_id();
        for (int i = 0; i < managers.size(); i++) {
            if (temp == managers.get(i).getId()) positionList = i;
            items.add(managers.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);

        dropdown.setSelection(positionList);

        selectedListId = 0;

        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        btnDel = (Button) findViewById(R.id.btnDel);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedListId = (int) dropdown.getSelectedItemId();
                for (int i = 0; i < managers.size(); i++) {
                    if (selectedListId == i) {
                        selectedListId = managers.get(i).getId();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditDepartmentActivity.this)
                        .setTitle("Видалити дані про департамент")
                        .setMessage("Ви впевнені, що хочете видалити дані про департамент?\n" +
                                "При видаленні також будуть втрачені дані зв'язаних записів!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabaseHelper.deleteDepartment(position);
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


        addItemsRunTime();

        editName.setSelection(editName.getText().length());
    }

    public void addItemsRunTime() {
        editName.setText(mDatabaseHelper.getDepartment(position).getName());
        editPhone.setText(mDatabaseHelper.getDepartment(position).getPhone());
        editEmail.setText(mDatabaseHelper.getDepartment(position).getEmail());
    }

    public int AddData() {
        Department department = new Department();

        department.setId(position);

        if (editName.length() == 0) {
            toastMessage("No name!");
            return 1;
        }
        department.setName(editName.getText().toString());

        if (editPhone.length() == 0) {
            toastMessage("No phone!");
            return 1;
        }
        department.setPhone(editPhone.getText().toString());

        if (editEmail.length() == 0) {
            toastMessage("No Email!");
            return 1;
        }
        department.setEmail(editEmail.getText().toString());

        if (selectedListId == 0) {
            toastMessage("Wrong manager!");
            return 1;
        }
        department.setP_id(selectedListId);


        mDatabaseHelper.updateDepartment(department);

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
