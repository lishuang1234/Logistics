package com.ls.logistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.Logistics;
import com.ls.util.InforDBUtils;

import java.util.Calendar;

/**
 * Created by ls on 15-6-4.
 * 物流企业
 */
public class AddLogisticsInforActivity extends AppCompatActivity implements OnDateSetListener {
    @ViewInject(R.id.activity_addlogisticsinfor_tb)
    private Toolbar mToolbar;


    @ViewInject(R.id.activity_addlogisticsinfor_et_company_id)
    private EditText mCompanyIdEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_license_id)
    private EditText mLicenseIdEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_corperate_representative)
    private EditText mCorperateRepresentativeEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_headquarters_province)
    private EditText mHeadquartersProvinceEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_headquarters_city)
    private EditText mHeadquartersCityEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_headquarters_county)
    private EditText mHeadquartersCountyEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_headquarters_street)
    private EditText mHeadquartersStreetEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_complain_number)
    private EditText mComplainNumberEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_consulting_number)
    private EditText mConsultingNumberEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_website)
    private EditText mWebsiteEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_foundation_time)
    private EditText mFoundationTimeEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_payment_collection)
    private EditText mPaymentCollectionEditText;
    @ViewInject(R.id.activity_addlogisticsinfor_et_introduction)
    private EditText mIntroductionEditText;


    public static final String DATEPICKER_TAG = "datepicker";
    private DatePickerDialog datePickerDialog;
    private int year;
    private int day;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlogisticsinfor);
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
        setTitle("新增物流企业");
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.activity_addlogisticsinfor_btn_ensure, R.id.activity_addlogisticsinfor_btn_cancle
    ,R.id.activity_addlogisticsinfor_et_foundation_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_addlogisticsinfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_addlogisticsinfor_btn_ensure:
                saveLogistics();
                break;
            case R.id.activity_addlogisticsinfor_et_foundation_time:
                showDatePicker();
                break;
        }
    }

    private void saveLogistics() {
        String companyId = mCompanyIdEditText.getText().toString();
        String licenseId = mLicenseIdEditText.getText().toString();
        String corperateRepresentative = mCorperateRepresentativeEditText.getText().toString();
        String headquartersProvince = mHeadquartersProvinceEditText.getText().toString();
        String headquartersCity = mHeadquartersCityEditText.getText().toString();
        String headquartersCounty = mHeadquartersCountyEditText.getText().toString();
        String headquartersStreet = mHeadquartersStreetEditText.getText().toString();
        String complainNumber = mComplainNumberEditText.getText().toString();
        String consultingNumber = mConsultingNumberEditText.getText().toString();
        String website = mWebsiteEditText.getText().toString();
        String foundationTime = mFoundationTimeEditText.getText().toString();
        String paymentCollection = mPaymentCollectionEditText.getText().toString();
        String introduction = mIntroductionEditText.getText().toString();
        if (companyId.equals("") || licenseId.equals("") || corperateRepresentative.equals("") || headquartersProvince.equals("")
                || headquartersCity.equals("") || headquartersCounty.equals("") || licenseId.equals("") || headquartersStreet.equals("")
                || complainNumber.equals("") || consultingNumber.equals("") || website.equals("") || foundationTime.equals("")
                || paymentCollection.equals("") || introduction.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        Logistics logistics = new Logistics(headquartersProvince, licenseId, companyId, System.currentTimeMillis() + "", corperateRepresentative, headquartersCity, headquartersCounty, complainNumber, website, introduction, paymentCollection, foundationTime, consultingNumber, headquartersStreet);
        InforDBUtils dbUtils = new InforDBUtils(this);
        dbUtils.saveLogistics(logistics);
        showToast("保存成功");
        this.finish();
    }

    private void showDatePicker() {
        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
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
        mFoundationTimeEditText.setText(i + "-" + month + "-" + day);
    }
}
