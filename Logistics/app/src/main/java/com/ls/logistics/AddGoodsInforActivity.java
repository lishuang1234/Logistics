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
import com.ls.bean.Goods;
import com.ls.util.InforDBUtils;

import java.util.Calendar;

/**
 * Created by ls on 15-6-4.
 * 货物
 */
public class AddGoodsInforActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @ViewInject(R.id.activity_addgoodsinfor_tb)
    private Toolbar mToolbar;

    @ViewInject(R.id.activity_addgoodsinfor_et_user_id)
    private EditText mUserIdEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_cargo_id)
    private EditText mCargoIdEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_cargo_name)
    private EditText mCargoNameEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_cargo_type)
    private EditText mCargoTypeEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_weight)
    private EditText mWeightEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_volume)
    private EditText mVolumeEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_quantity)
    private EditText mQuantityEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_publish_time)
    private EditText mPublishTimeEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_due_time)
    private EditText mDueTimeEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_source_province)
    private EditText mSourceProvinceEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_source_city)
    private EditText mSourceCityEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_source_county)
    private EditText mSourceCountyEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_destination_province)
    private EditText mDestinationProvinceEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_destination_city)
    private EditText mDestinationCityEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_destination_county)
    private EditText mDestinationCountyEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_transport_type)
    private EditText mTransportTypeEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_notice_item)
    private EditText mNoticeItemEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_contact)
    private EditText mContactEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_mobile_number)
    private EditText mMobileNumberEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_remarks)
    private EditText mRemarksEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_state)
    private EditText mStateEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_dealtime)
    private EditText mDealTimeEditText;
    @ViewInject(R.id.activity_addgoodsinfor_et_longtime_cargo)
    private EditText mLongTimeCargoEditText;

    public static final String DATEPICKER_TAG = "datepicker";
    // private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private int year;
    private int day;
    private int month;
    //    private int minute;
//    private int hour;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoodsinfor);
        ViewUtils.inject(this);
        initToolbar();
        initTimePicker();
    }

    private void initTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
//        hour = calendar.get(Calendar.HOUR_OF_DAY);
//        minute = calendar.get(Calendar.MINUTE);
        datePickerDialog = DatePickerDialog.newInstance(this, year, month, day, isVibrate());
        //  timePickerDialog = TimePickerDialog.newInstance(this, hour, minute, false, false);
        datePickerDialog.setVibrate(isVibrate());
        datePickerDialog.setYearRange(year, 2028);
        datePickerDialog.setCloseOnSingleTapDay(isCloseOnSingleTapDay());
        // timePickerDialog.setVibrate(isVibrate());
        // timePickerDialog.setCloseOnSingleTapMinute(isCloseOnSingleTapMinute());
    }

    private boolean isVibrate() {

        return false;
    }

    private boolean isCloseOnSingleTapDay() {

        return false;
    }

    private boolean isCloseOnSingleTapMinute() {

        return false;
    }


    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新增货物");
    }

    @OnClick({R.id.activity_addgoodsinfor_btn_ensure, R.id.activity_addgoodsinfor_btn_cancle,
            R.id.activity_addgoodsinfor_et_longtime_cargo, R.id.activity_addgoodsinfor_et_dealtime
            , R.id.activity_addgoodsinfor_et_publish_time, R.id.activity_addgoodsinfor_et_due_time})
    public void onClick(View view) {
        id = view.getId();
        switch (view.getId()) {
            case R.id.activity_addgoodsinfor_btn_ensure:
                saveGoods();
                break;
            case R.id.activity_addgoodsinfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_addgoodsinfor_et_due_time:
                showDatePicker();
                break;
            case R.id.activity_addgoodsinfor_et_publish_time:
                showDatePicker();
                break;
            case R.id.activity_addgoodsinfor_et_dealtime:
                showDatePicker();
                break;
            case R.id.activity_addgoodsinfor_et_longtime_cargo:
                showDatePicker();
                break;
        }
    }

    private void saveGoods() {
        String userId = mUserIdEditText.getText().toString();
        String cargoId = mCargoIdEditText.getText().toString();
        String cargoName = mCargoNameEditText.getText().toString();
        String cargoType = mCargoTypeEditText.getText().toString();
        String weight = mWeightEditText.getText().toString();
        String volume = mVolumeEditText.getText().toString();
        String quantity = mQuantityEditText.getText().toString();
        String publishTime = mPublishTimeEditText.getText().toString();
        String dueTime = mDueTimeEditText.getText().toString();
        String sourceProvince = mSourceProvinceEditText.getText().toString();
        String sourceCity = mSourceCityEditText.getText().toString();
        String sourceCounty = mSourceCountyEditText.getText().toString();
        String destinationProvince = mDestinationProvinceEditText.getText().toString();
        String destinationCity = mDestinationCityEditText.getText().toString();
        String destinationCounty = mDestinationCountyEditText.getText().toString();
        String transportType = mTransportTypeEditText.getText().toString();
        String noticeItem = mNoticeItemEditText.getText().toString();
        String contact = mContactEditText.getText().toString();
        String mobileNumber = mMobileNumberEditText.getText().toString();
        String remarks = mRemarksEditText.getText().toString();
        String state = mStateEditText.getText().toString();
        String dealTime = mDealTimeEditText.getText().toString();
        String longTimeCargo = mLongTimeCargoEditText.getText().toString();
        if (userId.equals("") || cargoId.equals("") || cargoName.equals("") ||
                cargoType.equals("") || weight.equals("") || volume.equals("") ||
                quantity.equals("") || publishTime.equals("") || dueTime.equals("") ||
                sourceProvince.equals("") || sourceCity.equals("") || sourceCounty.equals("")
                || destinationProvince.equals("") || destinationCity.equals("") || destinationCounty.equals("") ||
                transportType.equals("") || noticeItem.equals("") || contact.equals("") ||
                mobileNumber.equals("") || remarks.equals("") ||
                state.equals("") || dealTime.equals("") || longTimeCargo.equals("")) {
            showToast("请填写完整信息");
            return;
        }

        Goods goods = new Goods(System.currentTimeMillis() + "", cargoId, cargoName, userId,
                cargoType, weight, volume, publishTime, quantity, sourceProvince, dueTime,
                sourceCity, sourceCounty, destinationProvince, destinationCity, destinationCounty,
                transportType, noticeItem, contact, mobileNumber, remarks, state, dealTime, longTimeCargo);
        InforDBUtils dbUtils = new InforDBUtils(this);
        dbUtils.saveGoods(goods);
        showToast("保存成功");
        this.finish();

    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
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
        if (i2 /10 == 0) {
            day = "0" + i2;
        } else
            day = i2 + "";
        LogUtils.e("获得日期是:年:" + i + " 月:" + month + " 天:" + day);

        switch (id) {
            case R.id.activity_addgoodsinfor_et_due_time:
                mDueTimeEditText.setText(i + "-" + month + "-" + day);
                break;
            case R.id.activity_addgoodsinfor_et_publish_time:
                mPublishTimeEditText.setText(i + "-" + month + "-" + day);
                break;
            case R.id.activity_addgoodsinfor_et_dealtime:
                mDealTimeEditText.setText(i + "-" + month + "-" + day);
                break;
            case R.id.activity_addgoodsinfor_et_longtime_cargo:
                mLongTimeCargoEditText.setText(i + "-" + month + "-" + day);
                break;
        }
    }

    private void showDatePicker() {
        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
    }
}
