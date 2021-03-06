package com.pkteam.smartcalendar.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pkteam.smartcalendar.R;
import com.pkteam.smartcalendar.databinding.FragmentSettingProfileBinding;
import com.singh.daman.gentletoast.GentleToast;

public class SettingProfile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FragmentSettingProfileBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_setting_profile);
        binding.setProfile(this);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser().getDisplayName()!=null){
            binding.tvName.setText(mAuth.getCurrentUser().getDisplayName());
        }else{
            binding.tvName.setText("닉네임을 설정해주세요.");
        }
        binding.tvEmail.setText(mAuth.getCurrentUser().getEmail());




    }

    public void finishView(View view){
        finish();
    }
    public void signOut(View view){
        mAuth.signOut();
        GentleToast.with(getApplicationContext()).longToast("로그아웃 되었습니다.").setTextColor(R.color.material_white_1000).setBackgroundColor(R.color.colorPrimary).setBackgroundRadius(100).setImage(R.drawable.logo_ts).show();

        finish();
    }
}
