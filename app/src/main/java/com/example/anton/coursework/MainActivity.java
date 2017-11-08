package com.example.anton.coursework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_MANAGERS = "managers";
    private static final String TAG_DEPARTMENTS = "departments";
    private static final String TAG_EMPLOYEES = "employees";
    private static final String TAG_TASKS = "tasks";
    private static final String TAG_STAGES = "stages";
    public static String CURRENT_TAG = TAG_MANAGERS;

    public static int navItemIndex = 0;
    private NavigationView navigationView;

    private Handler mHandler;
    private DrawerLayout drawer;

    DatabaseHelper mDatabaseHelper;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = mDatabaseHelper.getInstance(this);
        MainActivity.context = getApplicationContext();

//        mDatabaseHelper.getDat3();

        mHandler = new Handler();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        final ManagersFragment managersFragment = new ManagersFragment();
        getSupportActionBar().setTitle("Таблиця \"Менеджери\"");
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, managersFragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null) mHandler.post(mPendingRunnable);
        drawer.closeDrawers();
        invalidateOptionsMenu();


        navItemIndex = 0;


//        Todo: add test data to tables!

//        Manager manager = new Manager();
//        manager.setName("Пупкін Вася Батькович");
//        manager.setAge(35);
//        manager.setSex("ч");
////        manager.setDob();
//        manager.setPhone("+123456789");
//        manager.setEmail("petro@gmail.com");
//        manager.setAddress("Kiev, Ukraine");
//        manager.setExperience(10);
//        manager.setPassport("TT123457TT");
//        manager.setSalary(10000);
//        manager.setBankAccount("QW12346");
//        mDatabaseHelper.addManager(manager);

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.manager) {
            final ManagersFragment managersFragment = new ManagersFragment();
            getSupportActionBar().setTitle("Таблиця \"Менеджери\"");
            Runnable mPendingRunnable = new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, managersFragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            };
            if (mPendingRunnable != null) mHandler.post(mPendingRunnable);
            drawer.closeDrawers();
            invalidateOptionsMenu();

            navItemIndex = 0;
        }

        else if (id == R.id.department) {
            final DepartmentsFragment departmentsFragment = new DepartmentsFragment();
            getSupportActionBar().setTitle("Таблиця \"Департаменти\"");
            Runnable mPendingRunnable = new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, departmentsFragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            };
            if (mPendingRunnable != null) mHandler.post(mPendingRunnable);
            drawer.closeDrawers();
            invalidateOptionsMenu();

            navItemIndex = 1;
        }

        else if (id == R.id.employee) {
            final EmployeesFragment employeesFragment = new EmployeesFragment();
            getSupportActionBar().setTitle("Таблиця \"Робітники\"");
            Runnable mPendingRunnable = new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, employeesFragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            };
            if (mPendingRunnable != null) mHandler.post(mPendingRunnable);
            drawer.closeDrawers();
            invalidateOptionsMenu();

            navItemIndex = 2;
        }

        else if (id == R.id.task) {
            final TasksFragment tasksFragment = new TasksFragment();
            getSupportActionBar().setTitle("Таблиця \"Завдання\"");
            Runnable mPendingRunnable = new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, tasksFragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            };
            if (mPendingRunnable != null) mHandler.post(mPendingRunnable);
            drawer.closeDrawers();
            invalidateOptionsMenu();

            navItemIndex = 3;
        }

        else if (id == R.id.stage) {
            final StagesFragment stagesFragment = new StagesFragment();
            getSupportActionBar().setTitle("Таблиця \"Етапи\"");
            Runnable mPendingRunnable = new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, stagesFragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            };
            if (mPendingRunnable != null) mHandler.post(mPendingRunnable);
            drawer.closeDrawers();
            invalidateOptionsMenu();

            navItemIndex = 4;
        }

        else if (id == R.id.report1) {
            Intent intent = new Intent(this, Report1Activity.class);
            startActivity(intent);

//            navItemIndex = 5;
        }

        else if (id == R.id.report2) {
            Intent intent = new Intent(this, Report2Activity.class);
            startActivity(intent);

//            navItemIndex = 6;
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

}
