package com.ls.logistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.Waybill;
import com.ls.util.InforDBUtils;

import java.util.Calendar;

/**
 * Created by ls on 15-6-4.
 * 运单
 */
public class AddWaybillInforActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @ViewInject(R.id.activity_addwaybillinfor_tb)
    private Toolbar mToolbar;


    @ViewInject(R.id.activity_addwaybillinfor_et_order_id)
    private EditText mOrderIdEditText;
    @ViewInject(R.id.activity_addwaybillinfor_et_cargo_id)
    private EditText mCargoIdEditText;
    @ViewInject(R.id.activity_addwaybillinfor_et_user_id)
    private EditText mUserIdEditText;
    @ViewInject(R.id.activity_addwaybillinfor_et_do_time)
    private EditText mDoTimeEditText;
    @ViewInject(R.id.activity_addwaybillinfor_et_source_place)
    private EditText mSourcePlaceEditText;
    @ViewInject(R.id.activity_addwaybillinfor_et_end_place)
    private EditText mEndPlaceEditText;
    @ViewInject(R.id.activity_addwaybillinfor_et_remarks)
    private EditText mRemarksEditText;

    public static final String DATEPICKER_TAG = "datepicker";
    private DatePickerDialog datePickerDialog;
    private int year;
    private int day;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwaybillinfor);
        ViewUtils.inject(this);
        initToolbar();
        initTimePicker();
    }

    private void initTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = DatePickerDialog.newInstance(this, year, month, day, isVibrate());
        datePickerDialog.setVibrate(isVibrate());
        datePickerDialog.setYearRange(year, 2028);
        datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
    }

    private boolean isVibrate() {
        return false;
    }

    private boolean isCloseOnSingleTapDay() {
        return false;
    }


    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新增运单");
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    private void showDatePicker() {
        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
    }

    @OnClick({R.id.activity_addwaybillinfor_btn_cancle, R.id.activity_addwaybillinfor_btn_ensure,
            R.id.activity_addwaybillinfor_et_do_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_addwaybillinfor_btn_ensure:
                saveWaybill();
                break;
            case R.id.activity_addwaybillinfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_addwaybillinfor_et_do_time:
                showDatePicker();
                break;
        }

    }

    private void saveWaybill() {
        String orderId = mOrderIdEditText.getText().toString();
        String cargoId = mCargoIdEditText.getText().toString();
        String userId = mUserIdEditText.getText().toString();
        String doTime = mDoTimeEditText.getText().toString();
        String sourcePlace = mSourcePlaceEditText.getText().toString();
        String endPlace = mEndPlaceEditText.getText().toString();
        String remarks = mRemarksEditText.getText().toString();
        if (orderId.equals("") || cargoId.equals("") || userId.equals("") || doTime.equals("")
                || sourcePlace.equals("") || endPlace.equals("") || remarks.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        Waybill waybill = new Waybill(System.currentTimeMillis() + "", orderId, cargoId, userId, doTime, sourcePlace, endPlace, remarks);
        InforDBUtils dbUtils = new InforDBUtils(this);
        dbUtils.saveWaybill(waybill);
        showToast("保存成功");
        this.finish();
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {
        String month;
        String day;
        i1++;
        if (i1 / 10 == 0) {
            month = "0" + i1;
        } else
            month = i1 + "";
        if (i2 / 10 == 0) {
            day = "0" + i2;
        } else
            day = i2 + "";
        LogUtils.e("获得日期是:年:" + i + " 月:" + month + " 天:" + day);
        mDoTimeEditText.setText(i + "-" + month + "-" + day);
    }
}
