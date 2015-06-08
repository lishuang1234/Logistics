package com.ls.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.SoftUser;
import com.ls.util.UserDBUtils;

/**
 * Created by ls on 15-6-7.
 */
public class LoginActivity extends AppCompatActivity {
    @ViewInject(R.id.activity_login_et_user_name)
    private EditText mUserNameEditText;
    @ViewInject(R.id.activity_login_et_user_password)
    private EditText mUserPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        checkLogin();
    }

    private void checkLogin() {
        UserDBUtils mUserDBUtils = new UserDBUtils(this);
        if (mUserDBUtils.getSoftUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }

    @OnClick({R.id.activity_login_btn_login, R.id.activity_login_btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_login_btn_login:
                login();
                break;
            case R.id.activity_login_btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                this.finish();
                break;
        }

    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    private void login() {
        String userName = mUserNameEditText.getText().toString();
        String userPassword = mUserPasswordEditText.getText().toString();
        if (userName.equals("") || userPassword.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        UserDBUtils userDBUtils = new UserDBUtils(this);
        SoftUser softUser = userDBUtils.getSoftUser();
        if (softUser != null) {
            if (userName.equals(softUser.getUserName()) && userPassword.equals(softUser.getUserPassword())) {
                showToast("登陆成功");
                startActivity(new Intent(this, MainActivity.class));
                this.finish();
                return;
            }
        }
        showToast("登陆失败");
    }


}
