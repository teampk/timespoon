package com.pkteam.smartcalendar.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.pkteam.smartcalendar.view.Fragments.FragmentCalendar;
import com.pkteam.smartcalendar.view.Fragments.FragmentHome;
import com.pkteam.smartcalendar.view.Fragments.FragmentSetting;
import com.pkteam.smartcalendar.R;


public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new FragmentHome();
                    break;
                case R.id.nav_calendar:
                    selectedFragment = new FragmentCalendar();
                    break;
                case R.id.nav_setting:
                    selectedFragment = new FragmentSetting();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Bottom Navigation Bar
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FragmentHome()).commit();




    }
    long pressTime;
    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() - pressTime <2000){
            finish();
            return;
        }
        Toast.makeText(this,"한번 더 누르시면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
        pressTime = System.currentTimeMillis();

    }


}