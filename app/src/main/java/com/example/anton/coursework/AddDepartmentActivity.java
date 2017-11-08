package com.example.anton.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddDepartmentActivity extends AppCompatActivity {

    Spinner dropdown;

    DatabaseHelper mDatabaseHelper;
    EditText editName, editPhone, editEmail;

    private static String selectedList;
    private static int selectedListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);


        final ArrayList<Manager> managers = new ArrayList<>();
        managers.addAll(mDatabaseHelper.getAllManagers());
        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < managers.size(); i++) {
            items.add(managers.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item, items);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);

        selectedListId = 0;

        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editEmail = (EditText) findViewById(R.id.editEmail);


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
    }

    public void AddData() {
        Department department = new Department();
        if (editName.length() == 0) {
            toastMessage("Введіть назву!");
            return;
        }
        department.setName(editName.getText().toString());

        if (editPhone.length() == 0) {
            toastMessage("Введіть телефон!");
            return;
        }
        department.setPhone(editPhone.getText().toString());

        if (editEmail.length() == 0) {
            toastMessage("Введіть електронну пошту!");
            return;
        }
        department.setEmail(editEmail.getText().toString());

        if (selectedListId == 0) {
            toastMessage("Wrong manager!");
            return;
        }
        department.setP_id(selectedListId);


        mDatabaseHelper.addDepartment(department);

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
