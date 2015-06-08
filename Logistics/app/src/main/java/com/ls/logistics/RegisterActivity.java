package com.ls.logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.SoftUser;
import com.ls.util.UserDBUtils;

/**
 * Created by ls on 15-6-7.
 */
public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @ViewInject(R.id.activity_register_et_user_name)
    private EditText mUserNameEditText;
    @ViewInject(R.id.activity_register_et_user_password)
    private EditText mUserPasswordEditText;
    @ViewInject(R.id.activity_register_et_real_name)
    private EditText mRealNameEditText;
    @ViewInject(R.id.activity_register_sp)
    private Spinner mSpinner;
    private String[] sexArray = {"男", "女"};
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item);
        String level[] = getResources().getStringArray(R.array.type4);
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    @OnClick({R.id.activity_register_btn_login, R.id.activity_register_btn_ensure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_register_btn_login:
                startActivity(new Intent(this, RegisterActivity.class));
                this.finish();
                break;
            case R.id.activity_register_btn_ensure:
                register();
                break;
        }

    }

    private void register() {
        String userName = mUserNameEditText.getText().toString();
        String userPassword = mUserPasswordEditText.getText().toString();
        String userRealName = mRealNameEditText.getText().toString();
        if (userName.equals("") || userPassword.equals("") || userRealName.equals("") || sex.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        UserDBUtils userDBUtils = new UserDBUtils(this);
        SoftUser softUser = new SoftUser(System.currentTimeMillis() + "", userName, userPassword, sex, userRealName);
        userDBUtils.saveUser(softUser);
        showToast("注册成功");
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sex = sexArray[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
