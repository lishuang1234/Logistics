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
import com.ls.bean.Car;
import com.ls.util.InforDBUtils;

/**
 * Created by ls on 15-6-4.
 * 车辆
 */
public class AddCarInforActivity extends AppCompatActivity {
    @ViewInject(R.id.activity_addcarinfor_tb)
    private Toolbar mToolbar;


    @ViewInject(R.id.activity_addcarinfor_et_vehicle_id)
    private EditText mVehicleIdEditText;
    @ViewInject(R.id.activity_addcarinfor_et_vehicle_type)
    private EditText mVehicleTypeEditText;
    @ViewInject(R.id.activity_addcarinfor_et_user_id)
    private EditText mUserIdEditText;
    @ViewInject(R.id.activity_addcarinfor_et_plate_number)
    private EditText mPlateNumberEditText;
    @ViewInject(R.id.activity_addcarinfor_et_length)
    private EditText mLengthEditText;
    @ViewInject(R.id.activity_addcarinfor_et_max_load)
    private EditText mMaxLoadEditText;
    @ViewInject(R.id.activity_addcarinfor_et_body_type)
    private EditText mBodyTypeEditText;
    @ViewInject(R.id.activity_addcarinfor_et_contact)
    private EditText mContactEditText;
    @ViewInject(R.id.activity_addcarinfor_et_mobile_number)
    private EditText mMobileNumberEditText;
    @ViewInject(R.id.activity_addcarinfor_et_description)
    private EditText mDescriptionEditText;
    @ViewInject(R.id.activity_addcarinfor_et_state)
    private EditText mStateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcarinfor);
        ViewUtils.inject(this);
        initToolbar();

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新增车辆");
    }

    @OnClick({R.id.activity_addcarinfor_btn_ensure, R.id.activity_addcarinfor_btn_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_addcarinfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_addcarinfor_btn_ensure:
                saveCar();
                break;
        }

    }

    private void saveCar() {
        String vehicleId = mVehicleIdEditText.getText().toString();
        String userId = mUserIdEditText.getText().toString();
        String plateNumber = mPlateNumberEditText.getText().toString();
        String length = mLengthEditText.getText().toString();
        String maxLoad = mMaxLoadEditText.getText().toString();
        String vehicleType = mVehicleTypeEditText.getText().toString();
        String bodyType = mBodyTypeEditText.getText().toString();
        String contact = mContactEditText.getText().toString();
        String mobileNumber = mMobileNumberEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();
        String state = mStateEditText.getText().toString();
        if (vehicleId.equals("") || userId.equals("") || plateNumber.equals("") ||
                length.equals("") || maxLoad.equals("") || vehicleType.equals("")
                || bodyType.equals("") || contact.equals("") || mobileNumber.equals("")
                || description.equals("") || state.equals("")) {
            showToast("请填写完成信息");
            return;
        }
        Car car = new Car(vehicleId, System.currentTimeMillis() + "", userId, length, plateNumber, maxLoad, vehicleType, bodyType, contact, mobileNumber, description, state);
        InforDBUtils dbUtils = new InforDBUtils(this);
        dbUtils.saveCar(car);
        showToast("保存成功");
        this.finish();

    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }
}
