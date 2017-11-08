package com.example.anton.coursework;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    private static final String LOG = DatabaseHelper.class.getName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Coursework.db";

    private static final String TABLE_MANAGERS = "managers";
    private static final String TABLE_DEPARTMENTS = "departments";
    private static final String TABLE_EMPLOYEES = "employees";
    private static final String TABLE_TASKS = "tasks";
    private static final String TABLE_STAGES = "stages";

    private static final String MANAGER_ID = "id";
    private static final String MANAGER_NAME = "name";
    private static final String MANAGER_AGE = "age";
    private static final String MANAGER_SEX = "sex";
    private static final String MANAGER_DOB = "dob";
    private static final String MANAGER_PHONE = "phone";
    private static final String MANAGER_EMAIL = "email";
    private static final String MANAGER_ADDRESS = "address";
    private static final String MANAGER_EXPERIENCE = "experience";
    private static final String MANAGER_PASSPORT = "passport";
    private static final String MANAGER_SALARY = "salary";
    private static final String MANAGER_BANK_ACCOUNT = "bankAccount";

    private static final String DEPARTMENT_ID = "id";
    private static final String DEPARTMENT_NAME = "name";
    private static final String DEPARTMENT_PHONE = "phone";
    private static final String DEPARTMENT_EMAIL = "email";
    private static final String DEPARTMENT_P_ID = "p_id";

    private static final String EMPLOYEE_ID = "id";
    private static final String EMPLOYEE_NAME = "name";
    private static final String EMPLOYEE_AGE = "age";
    private static final String EMPLOYEE_SEX = "sex";
    private static final String EMPLOYEE_DOB = "dob";
    private static final String EMPLOYEE_PHONE = "phone";
    private static final String EMPLOYEE_EMAIL = "email";
    private static final String EMPLOYEE_ADDRESS = "address";
    private static final String EMPLOYEE_POSITION = "position";
    private static final String EMPLOYEE_EXPERIENCE = "experience";
    private static final String EMPLOYEE_PASSPORT = "passport";
    private static final String EMPLOYEE_SALARY = "salary";
    private static final String EMPLOYEE_BANK_ACCOUNT = "bankAccount";
    private static final String EMPLOYEE_STATUS = "status";
    private static final String EMPLOYEE_P_ID = "p_id";

    private static final String TASK_ID = "id";
    private static final String TASK_NAME = "name";
    private static final String TASK_BEGIN_DATE = "beginDate";
    private static final String TASK_DEADLINE = "deadline";
    private static final String TASK_STATUS = "status";
    private static final String TASK_P_ID = "p_id";

    private static final String STAGE_ID = "id";
    private static final String STAGE_NAME = "name";
    private static final String STAGE_BEGIN_DATE = "beginDate";
    private static final String STAGE_DEADLINE = "deadline";
    private static final String STAGE_PRIORITY = "priority";
    private static final String STAGE_PROGRESS = "progress";
    private static final String STAGE_WORKING_HOURS = "workingHours";
    private static final String STAGE_P_ID = "p_id";

    private static final String CREATE_TABLE_MANAGERS = "CREATE TABLE "
            + TABLE_MANAGERS + "(" + MANAGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MANAGER_NAME + " TEXT, " +
            MANAGER_AGE + " INTEGER, " +
            MANAGER_SEX + " TEXT, " +
            MANAGER_DOB + " LONG, " +
            MANAGER_PHONE + " TEXT, " +
            MANAGER_EMAIL + " TEXT, " +
            MANAGER_ADDRESS + " TEXT, " +
            MANAGER_EXPERIENCE + " INTEGER, " +
            MANAGER_PASSPORT + " TEXT, " +
            MANAGER_SALARY + " REAL, " +
            MANAGER_BANK_ACCOUNT + " TEXT)";

    private static final String CREATE_TABLE_DEPARTMENTS = "CREATE TABLE " + TABLE_DEPARTMENTS
            + "(" + DEPARTMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DEPARTMENT_NAME + " TEXT, " +
            DEPARTMENT_PHONE + " TEXT, " +
            DEPARTMENT_EMAIL + " TEXT, " +
            DEPARTMENT_P_ID + " INTEGER)";

    private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE "
            + TABLE_EMPLOYEES + "(" + EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EMPLOYEE_NAME + " TEXT, " +
            EMPLOYEE_AGE + " INTEGER, " +
            EMPLOYEE_SEX + " TEXT, " +
            EMPLOYEE_DOB + " LONG, " +
            EMPLOYEE_PHONE + " TEXT, " +
            EMPLOYEE_EMAIL + " TEXT, " +
            EMPLOYEE_ADDRESS + " TEXT, " +
            EMPLOYEE_POSITION + " TEXT, " +
            EMPLOYEE_EXPERIENCE + " INTEGER, " +
            EMPLOYEE_PASSPORT + " TEXT, " +
            EMPLOYEE_SALARY + " REAL, " +
            EMPLOYEE_BANK_ACCOUNT + " TEXT, " +
            EMPLOYEE_STATUS + " INTEGER, " +
            EMPLOYEE_P_ID + " INTEGER)";

    private static final String CREATE_TABLE_TASKS = "CREATE TABLE "
            + TABLE_TASKS + "(" + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASK_NAME + " TEXT, " +
            TASK_BEGIN_DATE + " LONG, " +
            TASK_DEADLINE + " LONG, " +
            TASK_STATUS + " INTEGER, " +
            TASK_P_ID + " INTEGER)";

    private static final String CREATE_TABLE_STAGES = "CREATE TABLE "
            + TABLE_STAGES + "(" + STAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STAGE_NAME + " TEXT, " +
            STAGE_BEGIN_DATE + " LONG, " +
            STAGE_DEADLINE + " LONG, " +
            STAGE_PRIORITY + " INTEGER, " +
            STAGE_PROGRESS + " INTEGER, " +
            STAGE_WORKING_HOURS + " REAL, " +
            STAGE_P_ID + " INTEGER)";

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private Context context;
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MANAGERS);
        db.execSQL(CREATE_TABLE_DEPARTMENTS);
        db.execSQL(CREATE_TABLE_EMPLOYEES);
        db.execSQL(CREATE_TABLE_TASKS);
        db.execSQL(CREATE_TABLE_STAGES);

        Log.e("MANAGER: ", CREATE_TABLE_MANAGERS);
        Log.e("DEPARTMENT: ", CREATE_TABLE_DEPARTMENTS);
        Log.e("EMPLOYEE: ", CREATE_TABLE_EMPLOYEES);
        Log.e("TASK: ", CREATE_TABLE_TASKS);
        Log.e("STAGE: ", CREATE_TABLE_STAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANAGERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EMPLOYEES);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_STAGES);

        onCreate(db);
    }


    // ------------------------ "Managers" table methods ----------------//


    public void addManager(Manager manager) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MANAGER_NAME, manager.getName());
        values.put(MANAGER_AGE, manager.getAge());
        values.put(MANAGER_SEX, manager.getSex());
        values.put(MANAGER_DOB, manager.getDob());
        values.put(MANAGER_PHONE, manager.getPhone());
        values.put(MANAGER_EMAIL, manager.getEmail());
        values.put(MANAGER_ADDRESS, manager.getAddress());
        values.put(MANAGER_EXPERIENCE, manager.getExperience());
        values.put(MANAGER_PASSPORT, manager.getPassport());
        values.put(MANAGER_SALARY, manager.getSalary());
        values.put(MANAGER_BANK_ACCOUNT, manager.getBankAccount());

        db.insert(TABLE_MANAGERS, null, values);

        db.close();
    }

    public Manager getManager(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_MANAGERS +
                " WHERE " + MANAGER_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Manager t = new Manager();
        if (c.moveToFirst()) {
            t.setId(c.getInt((c.getColumnIndex(MANAGER_ID))));
            t.setName(c.getString(c.getColumnIndex(MANAGER_NAME)));
            t.setAge(c.getInt(c.getColumnIndex(MANAGER_AGE)));
            t.setSex(c.getString(c.getColumnIndex(MANAGER_SEX)));
            t.setDob(c.getLong(c.getColumnIndex(MANAGER_DOB)));
            t.setPhone(c.getString(c.getColumnIndex(MANAGER_PHONE)));
            t.setEmail(c.getString(c.getColumnIndex(MANAGER_EMAIL)));
            t.setAddress(c.getString(c.getColumnIndex(MANAGER_ADDRESS)));
            t.setExperience(c.getInt(c.getColumnIndex(MANAGER_EXPERIENCE)));
            t.setPassport(c.getString(c.getColumnIndex(MANAGER_PASSPORT)));
            t.setSalary(c.getDouble(c.getColumnIndex(MANAGER_SALARY)));
            t.setBankAccount(c.getString(c.getColumnIndex(MANAGER_BANK_ACCOUNT)));
        }
        c.close();
        db.close();

        return t;
    }

    public List<Manager> getAllManagers() {
        List<Manager> managers = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MANAGERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Manager t = new Manager();
                t.setId(c.getInt((c.getColumnIndex(MANAGER_ID))));
                t.setName(c.getString(c.getColumnIndex(MANAGER_NAME)));
                t.setAge(c.getInt(c.getColumnIndex(MANAGER_AGE)));
                t.setSex(c.getString(c.getColumnIndex(MANAGER_SEX)));
                t.setDob(c.getLong(c.getColumnIndex(MANAGER_DOB)));
                t.setPhone(c.getString(c.getColumnIndex(MANAGER_PHONE)));
                t.setEmail(c.getString(c.getColumnIndex(MANAGER_EMAIL)));
                t.setAddress(c.getString(c.getColumnIndex(MANAGER_ADDRESS)));
                t.setExperience(c.getInt(c.getColumnIndex(MANAGER_EXPERIENCE)));
                t.setPassport(c.getString(c.getColumnIndex(MANAGER_PASSPORT)));
                t.setSalary(c.getDouble(c.getColumnIndex(MANAGER_SALARY)));
                t.setBankAccount(c.getString(c.getColumnIndex(MANAGER_BANK_ACCOUNT)));
                managers.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return managers;
    }

    public boolean updateManager(Manager manager) {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = manager.getId();
        String selectQuery = "SELECT  * FROM " + TABLE_MANAGERS + " WHERE " + MANAGER_ID + " = " + id;

        ContentValues values = new ContentValues();
        values.put(MANAGER_NAME, manager.getName());
        values.put(MANAGER_AGE, manager.getAge());
        values.put(MANAGER_SEX, manager.getSex());
        values.put(MANAGER_DOB, manager.getDob());
        values.put(MANAGER_PHONE, manager.getPhone());
        values.put(MANAGER_EMAIL, manager.getEmail());
        values.put(MANAGER_ADDRESS, manager.getAddress());
        values.put(MANAGER_EXPERIENCE, manager.getExperience());
        values.put(MANAGER_PASSPORT, manager.getPassport());
        values.put(MANAGER_SALARY, manager.getSalary());
        values.put(MANAGER_BANK_ACCOUNT, manager.getBankAccount());

        long i = db.update(TABLE_MANAGERS, values, MANAGER_ID + " = ?",
                new String[]{String.valueOf(manager.getId())});

        db.close();

        if (i == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteManager(int id) {
        List<Department> departments = getAllDepartments();

        for (Department department : departments) {
            if (department.getP_id() == id) {
                deleteDepartment(id);
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MANAGERS, MANAGER_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    // ------------------------ "DEPARTMENTS" table methods ----------------//


    public void addDepartment (Department department) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DEPARTMENT_NAME, department.getName());
        values.put(DEPARTMENT_PHONE, department.getPhone());
        values.put(DEPARTMENT_EMAIL, department.getEmail());
        values.put(DEPARTMENT_P_ID, department.getP_id());

        long list_id = db.insert(TABLE_DEPARTMENTS, null, values);

        db.close();
    }

    public Department getDepartment(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENTS +
                " WHERE " + DEPARTMENT_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Department t = new Department();
        if (c.moveToFirst()) {
            t.setId(c.getInt((c.getColumnIndex(DEPARTMENT_ID))));
            t.setName(c.getString(c.getColumnIndex(DEPARTMENT_NAME)));
            t.setPhone(c.getString(c.getColumnIndex(DEPARTMENT_PHONE)));
            t.setEmail(c.getString(c.getColumnIndex(DEPARTMENT_EMAIL)));
            t.setP_id(c.getInt(c.getColumnIndex(DEPARTMENT_P_ID)));
        }
        c.close();
        db.close();

        return t;
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Department t = new Department();
                t.setId(c.getInt((c.getColumnIndex(DEPARTMENT_ID))));
                t.setName(c.getString(c.getColumnIndex(DEPARTMENT_NAME)));
                t.setPhone(c.getString(c.getColumnIndex(DEPARTMENT_PHONE)));
                t.setEmail(c.getString(c.getColumnIndex(DEPARTMENT_EMAIL)));
                t.setP_id(c.getInt(c.getColumnIndex(DEPARTMENT_P_ID)));
                departments.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return departments;
    }

    public boolean updateDepartment(Department department) {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = department.getId();
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENTS + " WHERE " + DEPARTMENT_ID + " = " + id;

        ContentValues values = new ContentValues();
        values.put(DEPARTMENT_NAME, department.getName());
        values.put(DEPARTMENT_PHONE, department.getPhone());
        values.put(DEPARTMENT_EMAIL, department.getEmail());
        values.put(DEPARTMENT_P_ID, department.getP_id());

        long i = db.update(TABLE_DEPARTMENTS, values, DEPARTMENT_ID + " = ?",
                new String[]{String.valueOf(department.getId())});

        db.close();

        if (i == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteDepartment(int id) {
        List<Employee> employees = getAllEmployees();

        for (Employee employee : employees) {
            if (employee.getP_id() == id) {
                deleteEmployee(id);
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENTS, DEPARTMENT_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    // ------------------------ "EMPLOYEES" table methods ----------------//


    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EMPLOYEE_NAME, employee.getName());
        values.put(EMPLOYEE_AGE, employee.getAge());
        values.put(EMPLOYEE_SEX, employee.getSex());
        values.put(EMPLOYEE_DOB, employee.getDob());
        values.put(EMPLOYEE_PHONE, employee.getPhone());
        values.put(EMPLOYEE_EMAIL, employee.getEmail());
        values.put(EMPLOYEE_ADDRESS, employee.getAddress());
        values.put(EMPLOYEE_POSITION, employee.getPosition());
        values.put(EMPLOYEE_EXPERIENCE, employee.getExperience());
        values.put(EMPLOYEE_PASSPORT, employee.getPassport());
        values.put(EMPLOYEE_SALARY, employee.getSalary());
        values.put(EMPLOYEE_BANK_ACCOUNT, employee.getBankAccount());
        values.put(EMPLOYEE_STATUS, employee.getStatus());
        values.put(EMPLOYEE_P_ID, employee.getP_id());

        db.insert(TABLE_EMPLOYEES, null, values);

        db.close();
    }

    public Employee getEmployee(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEES +
                " WHERE " + EMPLOYEE_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Employee t = new Employee();
        if (c.moveToFirst()) {
            t.setId(c.getInt((c.getColumnIndex(EMPLOYEE_ID))));
            t.setName(c.getString(c.getColumnIndex(EMPLOYEE_NAME)));
            t.setAge(c.getInt(c.getColumnIndex(EMPLOYEE_AGE)));
            t.setSex(c.getString(c.getColumnIndex(EMPLOYEE_SEX)));
            t.setDob(c.getLong(c.getColumnIndex(EMPLOYEE_DOB)));
            t.setPhone(c.getString(c.getColumnIndex(EMPLOYEE_PHONE)));
            t.setEmail(c.getString(c.getColumnIndex(EMPLOYEE_EMAIL)));
            t.setAddress(c.getString(c.getColumnIndex(EMPLOYEE_ADDRESS)));
            t.setPosition(c.getString(c.getColumnIndex(EMPLOYEE_POSITION)));
            t.setExperience(c.getInt(c.getColumnIndex(EMPLOYEE_EXPERIENCE)));
            t.setPassport(c.getString(c.getColumnIndex(EMPLOYEE_PASSPORT)));
            t.setSalary(c.getDouble(c.getColumnIndex(EMPLOYEE_SALARY)));
            t.setBankAccount(c.getString(c.getColumnIndex(EMPLOYEE_BANK_ACCOUNT)));
            t.setStatus(c.getInt(c.getColumnIndex(EMPLOYEE_STATUS)));
            t.setP_id(c.getInt(c.getColumnIndex(EMPLOYEE_P_ID)));
        }
        c.close();
        db.close();

        return t;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Employee t = new Employee();
                t.setId(c.getInt((c.getColumnIndex(EMPLOYEE_ID))));
                t.setName(c.getString(c.getColumnIndex(EMPLOYEE_NAME)));
                t.setAge(c.getInt(c.getColumnIndex(EMPLOYEE_AGE)));
                t.setSex(c.getString(c.getColumnIndex(EMPLOYEE_SEX)));
                t.setDob(c.getLong(c.getColumnIndex(EMPLOYEE_DOB)));
                t.setPhone(c.getString(c.getColumnIndex(EMPLOYEE_PHONE)));
                t.setEmail(c.getString(c.getColumnIndex(EMPLOYEE_EMAIL)));
                t.setAddress(c.getString(c.getColumnIndex(EMPLOYEE_ADDRESS)));
                t.setPosition(c.getString(c.getColumnIndex(EMPLOYEE_POSITION)));
                t.setExperience(c.getInt(c.getColumnIndex(EMPLOYEE_EXPERIENCE)));
                t.setPassport(c.getString(c.getColumnIndex(EMPLOYEE_PASSPORT)));
                t.setSalary(c.getDouble(c.getColumnIndex(EMPLOYEE_SALARY)));
                t.setBankAccount(c.getString(c.getColumnIndex(EMPLOYEE_BANK_ACCOUNT)));
                t.setStatus(c.getInt(c.getColumnIndex(EMPLOYEE_STATUS)));
                t.setP_id(c.getInt(c.getColumnIndex(EMPLOYEE_P_ID)));
                employees.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return employees;
    }

    public boolean updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = employee.getId();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEES + " WHERE " + EMPLOYEE_ID + " = " + id;

        ContentValues values = new ContentValues();
        values.put(EMPLOYEE_NAME, employee.getName());
        values.put(EMPLOYEE_AGE, employee.getAge());
        values.put(EMPLOYEE_SEX, employee.getSex());
        values.put(EMPLOYEE_DOB, employee.getDob());
        values.put(EMPLOYEE_PHONE, employee.getPhone());
        values.put(EMPLOYEE_EMAIL, employee.getEmail());
        values.put(EMPLOYEE_ADDRESS, employee.getAddress());
        values.put(EMPLOYEE_POSITION, employee.getPosition());
        values.put(EMPLOYEE_EXPERIENCE, employee.getExperience());
        values.put(EMPLOYEE_PASSPORT, employee.getPassport());
        values.put(EMPLOYEE_SALARY, employee.getSalary());
        values.put(EMPLOYEE_BANK_ACCOUNT, employee.getBankAccount());
        values.put(EMPLOYEE_STATUS, employee.getStatus());
        values.put(EMPLOYEE_P_ID, employee.getP_id());

        long i = db.update(TABLE_EMPLOYEES, values, EMPLOYEE_ID + " = ?",
                new String[]{String.valueOf(employee.getId())});

        db.close();

        if (i == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteEmployee(int id) {
        List<Task> tasks = getAllTasks();

        for (Task task : tasks) {
            if (task.getP_id() == id) {
                deleteTask(id);
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEES, EMPLOYEE_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    // ------------------------ "TASKS" table methods ----------------//


    public void addTask (Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task.getName());
        values.put(TASK_BEGIN_DATE, task.getBeginDate());
        values.put(TASK_DEADLINE, task.getDeadline());
        values.put(TASK_STATUS, task.getStatus());
        values.put(TASK_P_ID, task.getP_id());

        db.insert(TABLE_TASKS, null, values);

        db.close();
    }

    public Task getTask(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS +
                " WHERE " + TASK_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Task t = new Task();
        if (c.moveToFirst()) {
            t.setId(c.getInt((c.getColumnIndex(TASK_ID))));
            t.setName(c.getString(c.getColumnIndex(TASK_NAME)));
            t.setBeginDate(c.getLong(c.getColumnIndex(TASK_BEGIN_DATE)));
            t.setDeadline(c.getLong(c.getColumnIndex(TASK_DEADLINE)));
            t.setStatus(c.getInt(c.getColumnIndex(TASK_STATUS)));
            t.setP_id(c.getInt(c.getColumnIndex(TASK_P_ID)));
        }
        c.close();
        db.close();

        return t;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Task t = new Task();
                t.setId(c.getInt((c.getColumnIndex(TASK_ID))));
                t.setName(c.getString(c.getColumnIndex(TASK_NAME)));
                t.setBeginDate(c.getLong(c.getColumnIndex(TASK_BEGIN_DATE)));
                t.setDeadline(c.getLong(c.getColumnIndex(TASK_DEADLINE)));
                t.setStatus(c.getInt(c.getColumnIndex(TASK_STATUS)));
                t.setP_id(c.getInt(c.getColumnIndex(TASK_P_ID)));
                tasks.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return tasks;
    }

    public boolean updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = task.getId();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE " + TASK_ID + " = " + id;

        ContentValues values = new ContentValues();
        values.put(TASK_NAME, task.getName());
        values.put(TASK_BEGIN_DATE, task.getBeginDate());
        values.put(TASK_DEADLINE, task.getDeadline());
        values.put(TASK_STATUS, task.getStatus());
        values.put(TASK_P_ID, task.getP_id());

        long i = db.update(TABLE_TASKS, values, TASK_ID + " = ?",
                new String[]{String.valueOf(task.getId())});

        db.close();

        if (i == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteTask(int id) {
        List<Stage> stages = getAllStages();

        for (Stage stage : stages) {
            if (stage.getP_id() == id) {
                deleteStage(id);
            }
        }

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, TASK_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    // ------------------------ "STAGES" table methods ----------------//


    public void addStage (Stage stage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAGE_NAME, stage.getName());
        values.put(STAGE_BEGIN_DATE, stage.getBeginDate());
        values.put(STAGE_DEADLINE, stage.getDeadline());
        values.put(STAGE_PRIORITY, stage.getPriority());
        values.put(STAGE_PROGRESS, stage.getProgress());
        values.put(STAGE_WORKING_HOURS, stage.getWorkingHours());
        values.put(STAGE_P_ID, stage.getP_id());

        db.insert(TABLE_STAGES, null, values);

        db.close();
    }

    public Stage getStage(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_STAGES +
                " WHERE " + STAGE_ID + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        Stage t = new Stage();
        if (c.moveToFirst()) {
            t.setId(c.getInt((c.getColumnIndex(STAGE_ID))));
            t.setName(c.getString(c.getColumnIndex(STAGE_NAME)));
            t.setBeginDate(c.getLong(c.getColumnIndex(STAGE_BEGIN_DATE)));
            t.setDeadline(c.getLong(c.getColumnIndex(STAGE_DEADLINE)));
            t.setPriority(c.getInt(c.getColumnIndex(STAGE_PRIORITY)));
            t.setProgress(c.getInt(c.getColumnIndex(STAGE_PROGRESS)));
            t.setWorkingHours(c.getDouble(c.getColumnIndex(STAGE_WORKING_HOURS)));
            t.setP_id(c.getInt(c.getColumnIndex(STAGE_P_ID)));
        }
        c.close();
        db.close();

        return t;
    }

    public List<Stage> getAllStages() {
        List<Stage> stages = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_STAGES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Stage t = new Stage();
                t.setId(c.getInt((c.getColumnIndex(STAGE_ID))));
                t.setName(c.getString(c.getColumnIndex(STAGE_NAME)));
                t.setBeginDate(c.getLong(c.getColumnIndex(STAGE_BEGIN_DATE)));
                t.setDeadline(c.getLong(c.getColumnIndex(STAGE_DEADLINE)));
                t.setPriority(c.getInt(c.getColumnIndex(STAGE_PRIORITY)));
                t.setProgress(c.getInt(c.getColumnIndex(STAGE_PROGRESS)));
                t.setWorkingHours(c.getDouble(c.getColumnIndex(STAGE_WORKING_HOURS)));
                t.setP_id(c.getInt(c.getColumnIndex(STAGE_P_ID)));

                stages.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return stages;
    }

    public boolean updateStage(Stage stage) {
        SQLiteDatabase db = this.getWritableDatabase();

        int id = stage.getId();
        String selectQuery = "SELECT  * FROM " + TABLE_STAGES + " WHERE " + STAGE_ID + " = " + id;

        ContentValues values = new ContentValues();
        values.put(STAGE_NAME, stage.getName());
        values.put(STAGE_BEGIN_DATE, stage.getBeginDate());
        values.put(STAGE_DEADLINE, stage.getDeadline());
        values.put(STAGE_PRIORITY, stage.getPriority());
        values.put(STAGE_PROGRESS, stage.getProgress());
        values.put(STAGE_WORKING_HOURS, stage.getWorkingHours());
        values.put(STAGE_P_ID, stage.getP_id());

        long i = db.update(TABLE_STAGES, values, STAGE_ID + " = ?",
                new String[]{String.valueOf(stage.getId())});

        db.close();

        if (i == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void deleteStage(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STAGES, STAGE_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();
    }


    // ------------------------ Additional methods ----------------//


//    public void getDat(int status){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query = "SELECT " +
//                TABLE_MANAGERS + "." + MANAGER_NAME + ", " +
//                TABLE_DEPARTMENTS + "." + DEPARTMENT_NAME + ", " +
//                TABLE_TASKS + "." + TASK_NAME + ", " +
//                TABLE_TASKS + "." + TASK_STATUS + ", " +
//                TABLE_STAGES + "." + STAGE_NAME + ", " +
//                TABLE_STAGES + "." + STAGE_PRIORITY + ", " +
//                TABLE_STAGES + "." + STAGE_PROGRESS +
//                " FROM " + TABLE_MANAGERS +
//                " JOIN " + TABLE_DEPARTMENTS + " ON " +
//                TABLE_MANAGERS + "." + MANAGER_ID + " = " + TABLE_DEPARTMENTS + "." + DEPARTMENT_P_ID +
//                " JOIN " + TABLE_EMPLOYEES + " ON " +
//                TABLE_DEPARTMENTS + "." + DEPARTMENT_ID + " = " + TABLE_EMPLOYEES + "." + EMPLOYEE_P_ID +
//                " JOIN " + TABLE_TASKS + " ON " +
//                TABLE_EMPLOYEES + "." + EMPLOYEE_ID + " = " + TABLE_TASKS + "." + TASK_P_ID +
//                " JOIN " + TABLE_STAGES + " ON " +
//                TABLE_TASKS + "." + TASK_ID + " = " + TABLE_STAGES + "." + STAGE_P_ID +
//                " WHERE " + TABLE_TASKS + "." + TASK_STATUS + " = " + status +
//                " ORDER BY " + TABLE_TASKS + "." + TASK_BEGIN_DATE + " DESC;";
//
//        Log.e("SQL: ", query);
//
////        return db.rawQuery(query, null);
//    }




//    public void getDat2(int status){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query = "SELECT " + "COUNT (" + TABLE_EMPLOYEES + "." + EMPLOYEE_NAME + ")" +
//                " FROM " + TABLE_MANAGERS +
//                " LEFT JOIN " + TABLE_DEPARTMENTS + " ON " +
//                TABLE_MANAGERS + "." + MANAGER_ID + " = " + TABLE_DEPARTMENTS + "." + DEPARTMENT_P_ID +
//                " RIGHT JOIN " + TABLE_EMPLOYEES + " ON " +
//                TABLE_DEPARTMENTS + "." + DEPARTMENT_ID + " = " + TABLE_EMPLOYEES + "." + EMPLOYEE_P_ID +
//                " JOIN " + TABLE_TASKS + " ON " +
//                TABLE_EMPLOYEES + "." + EMPLOYEE_ID + " = " + TABLE_TASKS + "." + TASK_P_ID +
//                " INNER JOIN " + TABLE_STAGES + " ON " +
//                TABLE_TASKS + "." + TASK_ID + " = " + TABLE_STAGES + "." + STAGE_P_ID +
//                " WHERE " + TABLE_TASKS + "." + TASK_STATUS + " = " + status +
//                " AND " + TABLE_STAGES + "." + STAGE_PROGRESS + " > 0" +
//                " AND " + TABLE_STAGES + "." + STAGE_PROGRESS + " < 20" +
//                " ORDER BY " + TABLE_TASKS + "." + TASK_BEGIN_DATE + " ASC;";
//
//        Log.e("SQL: ", query);
//
////        return db.rawQuery(query, null);
//    }


    public Cursor getData3(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT "+ TABLE_TASKS + "." + TASK_NAME +
                " FROM " + TABLE_MANAGERS +
                " CROSS JOIN " + TABLE_DEPARTMENTS + ";";

        return db.rawQuery(query, null);
    }

    public List<Employee> getEmployees(int id) {
        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MANAGERS +
                " JOIN " + TABLE_DEPARTMENTS + " ON " +
                TABLE_MANAGERS + "." + MANAGER_ID + " = " + TABLE_DEPARTMENTS + "." + DEPARTMENT_P_ID +
                " JOIN " + TABLE_EMPLOYEES + " ON " +
                TABLE_DEPARTMENTS + "." + DEPARTMENT_ID + " = " + TABLE_EMPLOYEES + "." + EMPLOYEE_P_ID +
                " WHERE " + TABLE_MANAGERS + "." + MANAGER_ID + " = " + id;

//        Log.e("ALL: ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Employee t = new Employee();
                t.setId(c.getInt((c.getColumnIndex(EMPLOYEE_ID))));
                t.setName(c.getString(c.getColumnIndex(EMPLOYEE_NAME)));
                t.setAge(c.getInt(c.getColumnIndex(EMPLOYEE_AGE)));
                t.setSex(c.getString(c.getColumnIndex(EMPLOYEE_SEX)));
                t.setDob(c.getLong(c.getColumnIndex(EMPLOYEE_DOB)));
                t.setPhone(c.getString(c.getColumnIndex(EMPLOYEE_PHONE)));
                t.setEmail(c.getString(c.getColumnIndex(EMPLOYEE_EMAIL)));
                t.setAddress(c.getString(c.getColumnIndex(EMPLOYEE_ADDRESS)));
                t.setPosition(c.getString(c.getColumnIndex(EMPLOYEE_POSITION)));
                t.setExperience(c.getInt(c.getColumnIndex(EMPLOYEE_EXPERIENCE)));
                t.setPassport(c.getString(c.getColumnIndex(EMPLOYEE_PASSPORT)));
                t.setSalary(c.getDouble(c.getColumnIndex(EMPLOYEE_SALARY)));
                t.setBankAccount(c.getString(c.getColumnIndex(EMPLOYEE_BANK_ACCOUNT)));
                t.setStatus(c.getInt(c.getColumnIndex(EMPLOYEE_STATUS)));
                t.setP_id(c.getInt(c.getColumnIndex(EMPLOYEE_P_ID)));
                employees.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return employees;
    }

    public int getEmployeesCount(int id) {
        String selectQuery = "SELECT " + "COUNT (" + TABLE_EMPLOYEES + "." + EMPLOYEE_ID + ")" +
                " FROM " + TABLE_MANAGERS +
                " JOIN " + TABLE_DEPARTMENTS + " ON " +
                TABLE_MANAGERS + "." + MANAGER_ID + " = " + TABLE_DEPARTMENTS + "." + DEPARTMENT_P_ID +
                " JOIN " + TABLE_EMPLOYEES + " ON " +
                TABLE_DEPARTMENTS + "." + DEPARTMENT_ID + " = " + TABLE_EMPLOYEES + "." + EMPLOYEE_P_ID +
                " WHERE " + TABLE_MANAGERS + "." + MANAGER_ID + " = " + id;

//        Log.e("COUNT: ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        int count = 0;

        if (c.moveToFirst()) {
            count = c.getInt(0);
        }

        c.close();
        db.close();

        return count;
    }



//    public List<Task> getTasks(int id) {
//        List<Task> tasks = new ArrayList<>();
//        String selectQuery = "SELECT " +
//                TABLE_TASKS + "." + TASK_ID + ", " +
//                TABLE_TASKS + "." + TASK_NAME + ", " +
//                TABLE_TASKS + "." + TASK_BEGIN_DATE + ", " +
//                TABLE_TASKS + "." + TASK_DEADLINE + ", " +
//                TABLE_TASKS + "." + TASK_STATUS + ", " +
//                TABLE_TASKS + "." + TASK_P_ID +
//                " FROM " + TABLE_TASKS +
//                " WHERE " + TABLE_TASKS + "." + TASK_P_ID + " = " + 2;
//
////        Log.e("ALL: ", String.valueOf(id) + " | " + selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c.moveToFirst()) {
//            do {
//                Task t = new Task();
//                t.setId(c.getInt((c.getColumnIndex(TASK_ID))));
//                t.setName(c.getString(c.getColumnIndex(TASK_NAME)));
//                t.setBeginDate(c.getLong(c.getColumnIndex(TASK_BEGIN_DATE)));
//                t.setDeadline(c.getLong(c.getColumnIndex(TASK_DEADLINE)));
//                t.setStatus(c.getInt(c.getColumnIndex(TASK_STATUS)));
//                t.setP_id(c.getInt(c.getColumnIndex(TASK_P_ID)));
//
//                tasks.add(t);
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        db.close();
//
//        return tasks;
//    }


    public List<Stage> getStages(int id) {
        List<Stage> stages = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TASKS +
                " JOIN " + TABLE_STAGES + " ON " +
                TABLE_TASKS + "." + TASK_ID + " = " + TABLE_STAGES + "." + STAGE_P_ID +
                " WHERE " + TABLE_TASKS + "." + TASK_ID + " = " + id;

//        Log.e("ALL: ", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Stage t = new Stage();
                t.setId(c.getInt((c.getColumnIndex(STAGE_ID))));
                t.setName(c.getString(c.getColumnIndex(STAGE_NAME)));
                t.setBeginDate(c.getLong(c.getColumnIndex(STAGE_BEGIN_DATE)));
                t.setDeadline(c.getLong(c.getColumnIndex(STAGE_DEADLINE)));
                t.setPriority(c.getInt(c.getColumnIndex(STAGE_PRIORITY)));
                t.setProgress(c.getInt(c.getColumnIndex(STAGE_PROGRESS)));
                t.setWorkingHours(c.getDouble(c.getColumnIndex(STAGE_WORKING_HOURS)));
                t.setP_id(c.getInt(c.getColumnIndex(STAGE_P_ID)));

                stages.add(t);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return stages;
    }
}

