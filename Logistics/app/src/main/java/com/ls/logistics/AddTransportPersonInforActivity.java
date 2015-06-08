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
import com.ls.bean.TransportPerson;
import com.ls.util.InforDBUtils;

/**
 * Created by ls on 15-6-4.
 * 运输人员
 */
public class AddTransportPersonInforActivity extends AppCompatActivity {

    @ViewInject(R.id.activity_addtransportpersoninfor_tb)
    private Toolbar mToolbar;

    @ViewInject(R.id.activity_addtransportpersoninfor_et_driver_id)
    private EditText mDriverIdEditText;
    @ViewInject(R.id.activity_addtransportpersoninfor_et_id_card)
    private EditText mIdCardEditText;
    @ViewInject(R.id.activity_addtransportpersoninfor_et_license_number)
    private EditText mLicenseNumberEditText;
    @ViewInject(R.id.activity_addtransportpersoninfor_et_license_province)
    private EditText mLicenseProvinceEditText;
    @ViewInject(R.id.activity_addtransportpersoninfor_et_license_city)
    private EditText mLicenseCityEditText;
    @ViewInject(R.id.activity_addtransportpersoninfor_et_license_county)
    private EditText mLicenseCountyEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtransportpersoninfor);
        ViewUtils.inject(this);
        initToolbar();
    }
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新增运输人员");
    }
    @OnClick({R.id.activity_addtransportpersoninfor_btn_ensure, R.id.activity_addtransportpersoninfor_btn_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_addtransportpersoninfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_addtransportpersoninfor_btn_ensure:
                saveTransportPerson();
                break;
        }
    }
    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }
    private void saveTransportPerson() {
        String driverID = mDriverIdEditText.getText().toString();
        String idCard = mIdCardEditText.getText().toString();
        String licenseNumber = mLicenseNumberEditText.getText().toString();
        String licenseProvince = mLicenseProvinceEditText.getText().toString();
        String licenseCity = mLicenseCityEditText.getText().toString();
        String licenseCounty = mLicenseCountyEditText.getText().toString();
        if (driverID.equals("") || idCard.equals("") || licenseNumber.equals("") ||
                licenseProvince.equals("") || licenseCity.equals("") || licenseCounty.equals("")) {
            showToast("请输入完整信息");
            return;
        }

        TransportPerson transportPerson = new TransportPerson(System.currentTimeMillis() + "", driverID, idCard, licenseNumber, licenseCity, licenseProvince, licenseCounty);
        InforDBUtils dbUtils = new InforDBUtils(this);
        dbUtils.saveTransportPerson(transportPerson);
        showToast("保存成功");
        this.finish();
    }
}
