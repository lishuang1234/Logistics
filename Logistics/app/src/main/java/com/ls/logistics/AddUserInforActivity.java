package com.ls.logistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.User;
import com.ls.util.InforDBUtils;

/**
 * Created by ls on 15-6-4.
 * 用户
 */
public class AddUserInforActivity extends AppCompatActivity {
    @ViewInject(R.id.activity_adduserinfor_tb)
    private Toolbar mToolbar;
    @ViewInject(R.id.activity_adduserinfor_et_user_id)
    private EditText mUserIdEditText;
    @ViewInject(R.id.activity_adduserinfor_et_user_name)
    private EditText mUserNameEditText;
    @ViewInject(R.id.activity_adduserinfor_et_real_name)
    private EditText mRealNameEditText;
    @ViewInject(R.id.activity_adduserinfor_et_pass_word)
    private EditText mPassWordEditText;
    @ViewInject(R.id.activity_adduserinfor_et_mobile_number)
    private EditText mMobileNumberEditText;
    @ViewInject(R.id.activity_adduserinfor_et_phone_number)
    private EditText mPhoneNumberEditText;
    @ViewInject(R.id.activity_adduserinfor_et_email)
    private EditText mEmailEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduserinfor);
        ViewUtils.inject(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新增用户");
    }

    @OnClick({R.id.activity_adduserinfor_btn_cancle, R.id.activity_adduserinfor_btn_ensure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_adduserinfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_adduserinfor_btn_ensure:
                saveUserInfor();
                break;
        }
    }

    private void saveUserInfor() {
        String userId = mUserIdEditText.getText().toString();
        String userName = mUserNameEditText.getText().toString();
        String realName = mRealNameEditText.getText().toString();
        String password = mPassWordEditText.getText().toString();
        String mobileNumber = mMobileNumberEditText.getText().toString();
        String phoneNumber = mPhoneNumberEditText.getText().toString();
        String mEmail = mEmailEditText.getText().toString();
        if (userId.equals("") || userName.equals("") || realName.equals("")
                || password.equals("") || mobileNumber.equals("") || phoneNumber.equals("")
                || mEmail.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        User user = new User(userName, userId, System.currentTimeMillis() + "", password, realName, phoneNumber, mobileNumber, mEmail);
        InforDBUtils dbUtils = new InforDBUtils(this);
        boolean state = dbUtils.saveUser(user);
        if (state) {
            showToast("保存信息成功");
            this.finish();
        } else
            showToast("保存信息失败");
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }
}
