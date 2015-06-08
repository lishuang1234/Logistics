package com.ls.logistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.Goods;
import com.ls.util.InforDBUtils;

/**
 * Created by ls on 15-6-7.
 */
public class GoodsDetailActivity extends AppCompatActivity {
    @ViewInject(R.id.activity_goodsdetail_tb)
    private Toolbar mToolbar;

    @ViewInject(R.id.activity_goodsdetail_et_user_id)
    private EditText mUserIdEditText;
    @ViewInject(R.id.activity_goodsdetail_et_cargo_id)
    private EditText mCargoIdEditText;
    @ViewInject(R.id.activity_goodsdetail_et_cargo_name)
    private EditText mCargoNameEditText;
    @ViewInject(R.id.activity_goodsdetail_et_cargo_type)
    private EditText mCargoTypeEditText;
    @ViewInject(R.id.activity_goodsdetail_et_weight)
    private EditText mWeightEditText;
    @ViewInject(R.id.activity_goodsdetail_et_volume)
    private EditText mVolumeEditText;
    @ViewInject(R.id.activity_goodsdetail_et_quantity)
    private EditText mQuantityEditText;
    @ViewInject(R.id.activity_goodsdetail_et_publish_time)
    private EditText mPublishTimeEditText;
    @ViewInject(R.id.activity_goodsdetail_et_due_time)
    private EditText mDueTimeEditText;
    @ViewInject(R.id.activity_goodsdetail_et_source_province)
    private EditText mSourceProvinceEditText;
    @ViewInject(R.id.activity_goodsdetail_et_source_city)
    private EditText mSourceCityEditText;
    @ViewInject(R.id.activity_goodsdetail_et_source_county)
    private EditText mSourceCountyEditText;
    @ViewInject(R.id.activity_goodsdetail_et_destination_province)
    private EditText mDestinationProvinceEditText;
    @ViewInject(R.id.activity_goodsdetail_et_destination_city)
    private EditText mDestinationCityEditText;
    @ViewInject(R.id.activity_goodsdetail_et_destination_county)
    private EditText mDestinationCountyEditText;
    @ViewInject(R.id.activity_goodsdetail_et_transport_type)
    private EditText mTransportTypeEditText;
    @ViewInject(R.id.activity_goodsdetail_et_notice_item)
    private EditText mNoticeItemEditText;
    @ViewInject(R.id.activity_goodsdetail_et_contact)
    private EditText mContactEditText;
    @ViewInject(R.id.activity_goodsdetail_et_mobile_number)
    private EditText mMobileNumberEditText;
    @ViewInject(R.id.activity_goodsdetail_et_remarks)
    private EditText mRemarksEditText;
    @ViewInject(R.id.activity_goodsdetail_et_state)
    private EditText mStateEditText;
    @ViewInject(R.id.activity_goodsdetail_et_dealtime)
    private EditText mDealTimeEditText;
    @ViewInject(R.id.activity_goodsdetail_et_longtime_cargo)
    private EditText mLongTimeCargoEditText;

    private Goods goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        ViewUtils.inject(this);
        goods = (Goods) getIntent().getSerializableExtra("goods");
        initToolbar();
        initView();
    }

    private void initView() {
        mUserIdEditText.setText(goods.getUserId());
        mCargoIdEditText.setText(goods.getCargoId());
        mCargoNameEditText.setText(goods.getCargoName());
        mCargoTypeEditText.setText(goods.getCargoType());
        mWeightEditText.setText(goods.getWeight());
        mVolumeEditText.setText(goods.getVolume());
        mQuantityEditText.setText(goods.getQuantity());
        mPublishTimeEditText.setText(goods.getPublishTime());
        mDueTimeEditText.setText(goods.getDueTime());
        mSourceProvinceEditText.setText(goods.getSourceProvince());
        mSourceCityEditText.setText(goods.getSourceCity());
        mSourceCountyEditText.setText(goods.getSourceCounty());
        mDestinationProvinceEditText.setText(goods.getDestinationProvince());
        mDestinationCityEditText.setText(goods.getDestinationCity());
        mDestinationCountyEditText.setText(goods.getDestinationCounty());
        mTransportTypeEditText.setText(goods.getTransportType());
        mNoticeItemEditText.setText(goods.getNoticeItem());
        mContactEditText.setText(goods.getContact());
        mMobileNumberEditText.setText(goods.getMobileNumber());
        mRemarksEditText.setText(goods.getRemarks());
        mStateEditText.setText(goods.getState());
        mDealTimeEditText.setText(goods.getDealtime());
        mLongTimeCargoEditText.setText(goods.getLongtimeCargo());
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("货物详情");
    }

    @OnClick({R.id.activity_goodsdetail_btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_goodsdetail_btn_delete:
                delete();
                break;
        }

    }

    private void delete() {
        new AlertDialog.Builder(this).setTitle("确认删除该信息吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        InforDBUtils dbUtils = new InforDBUtils(GoodsDetailActivity.this);
                        dbUtils.deleteGoods(goods);
                        showToast("删除成功");
                        startActivity(new Intent(GoodsDetailActivity.this, MainActivity.class));
                        GoodsDetailActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作

                    }
                }).show();

    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

}
