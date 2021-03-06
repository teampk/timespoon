package com.pkteam.smartcalendar.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pkteam.smartcalendar.DBHelper;
import com.pkteam.smartcalendar.R;
import com.pkteam.smartcalendar.databinding.FragmentSettingBinding;
import com.pkteam.smartcalendar.view.DataCheckActivity;
import com.pkteam.smartcalendar.view.SettingCategory;
import com.pkteam.smartcalendar.view.SettingLogin;
import com.pkteam.smartcalendar.view.SettingProfile;
import com.pkteam.smartcalendar.view.SettingSleepTime;
import com.singh.daman.gentletoast.GentleToast;

import java.util.Date;

/*
 * Created by paeng on 2018. 7. 4..
 */

public class FragmentSetting extends Fragment {

    FragmentSettingBinding binding;
    private boolean signedIn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.setSetting(this);
        View mView = binding.getRoot();
        setLoginText();
        setupConcealerNSV();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setLoginText();

    }

    private void setupConcealerNSV() {
        // if you're setting header and footer views at the very start of the layout creation (onCreate),
        // as the views are not drawn yet, the library cant get their correct sizes, so you have to do this:

        binding.crdHeaderView.post(new Runnable() {
            @Override
            public void run() {
                binding.concealerNSV.setHeaderView(binding.crdHeaderView, 0);
            }
        });

        // pass a true to setHeaderFastHide to make views hide faster
        binding.concealerNSV.setHeaderFastHide(true);
    }

    private void setLoginText(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            binding.tvProfile.setText(user.getDisplayName());
            binding.tvProfileInfo.setText("내 프로필 보기");
            signedIn = true;
        }else{
            binding.tvProfile.setText("로그인");
            binding.tvProfileInfo.setText("로그인이 필요합니다.");
            signedIn = false;
        }
    }

    public void profileListener(View view){
        if(signedIn){
            Intent intentProfile = new Intent(getContext(), SettingProfile.class);
            startActivity(intentProfile);
        }else{
            Intent intentLogin = new Intent(getContext(), SettingLogin.class);
            startActivity(intentLogin);
        }
    }

    public void categoryListener(View view){
        startActivity(new Intent(getContext(), SettingCategory.class));
    }
    public void sleepTimeListener(View view){
        startActivity(new Intent(getContext(), SettingSleepTime.class));
    }
    public void schedulingModeListener(View view){
        startActivity(new Intent(getContext(), SettingSchedulingMode.class));
    }

    public void infAppListener(View view){
        startActivity(new Intent(getContext(), SettingAppInfo.class));
    }

    public void checkAllDataListener(View view){
        startActivity(new Intent(getContext(), DataCheckActivity.class));
    }

    public void dataDeleteListener(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("모든 데이터를 삭제합니다");
        builder.setMessage("앱 내에서 추가한 모든 일정 데이터를 삭제합니다.\n계속하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(getContext(), "SmartCal.db", null, 1);
                        dbHelper.deleteTodoDataAll();
                        dbHelper.initializeRepeatId();
                        GentleToast.with(getContext()).longToast("모든 일정이 삭제되었습니다.").setTextColor(R.color.material_white_1000).setBackgroundColor(R.color.colorPrimary).setBackgroundRadius(100).setImage(R.drawable.logo_ts).show();


                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }


    public void testingListener(View view){
        Intent intent = new Intent(getContext(), TestActivity.class);
        startActivity(intent);
    }


}
