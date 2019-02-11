package com.pkteam.smartcalendar.view.ViewSetting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pkteam.smartcalendar.DBHelper;
import com.pkteam.smartcalendar.R;

import java.util.ArrayList;

/*
 * Created by paeng on 2018. 8. 14..
 */

public class SettingSleepTime extends AppCompatActivity{

    private Button btnSubmit;
    private TextView tvStartTime, tvEndTime;
    private ArrayList<Integer> timeList;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_setting_sleep_time);

        bindingView();
        loadData();
    }
    private void bindingView(){
        tvStartTime = findViewById(R.id.tv_start);
        tvEndTime = findViewById(R.id.tv_end);
        tvStartTime.setOnClickListener(listener);
        tvEndTime.setOnClickListener(listener);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(listener);

        // for testing
        TextView tvTopBarTesting = findViewById(R.id.tv_top_bar);
        tvTopBarTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), String.valueOf(timeList.get(0))+","+String.valueOf(timeList.get(1)), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadData(){
        dbHelper = new DBHelper(getApplicationContext(), "SmartCal.db", null, 1);

        timeList = dbHelper.getAllSleepTime();
        //et1.setText(timeList.get(0));
        //et2.setText(timeList.get(1));


    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_submit:
                    /*
                    dbHelper.categoryUpdate(et1.getText().toString(), et2.getText().toString(),
                            et3.getText().toString(), et4.getText().toString(), et5.getText().toString());
                    */
                    Toast.makeText(SettingSleepTime.this, "수면시간이 수정되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.tv_start:

                    break;
                case R.id.tv_end:

                    break;

                default:
                    break;
            }
        }
    };
}