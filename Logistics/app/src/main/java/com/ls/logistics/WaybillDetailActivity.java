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
import com.ls.bean.Waybill;
import com.ls.util.InforDBUtils;

/**
 * Created by ls on 15-6-7.
 */
public class WaybillDetailActivity extends AppCompatActivity {
    @ViewInject(R.id.activity_waybillidetail_tb)
    private Toolbar mToolbar;

    @ViewInject(R.id.activity_waybillidetail_et_order_id)
    private EditText mOrderIdEditText;
    @ViewInject(R.id.activity_waybillidetail_et_cargo_id)
    private EditText mCargoIdEditText;
    @ViewInject(R.id.activity_waybillidetail_et_user_id)
    private EditText mUserIdEditText;
    @ViewInject(R.id.activity_waybillidetail_et_do_time)
    private EditText mDoTimeEditText;
    @ViewInject(R.id.activity_waybillidetail_et_source_place)
    private EditText mSourcePlaceEditText;
    @ViewInject(R.id.activity_waybillidetail_et_end_place)
    private EditText mEndPlaceEditText;
    @ViewInject(R.id.activity_waybillidetail_et_remarks)
    private EditText mRemarksEditText;


    private Waybill waybill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybilldetail);
        ViewUtils.inject(this);
        waybill = (Waybill) getIntent().getSerializableExtra("waybill");
        initToolbar();
        initView();
    }

    private void initView() {
        mOrderIdEditText.setText(waybill.getOrderId());
        mCargoIdEditText.setText(waybill.getCargoId());
        mUserIdEditText.setText(waybill.getUserId());
        mDoTimeEditText.setText(waybill.getDoTime());
        mSourcePlaceEditText.setText(waybill.getSourcePlace());
        mEndPlaceEditText.setText(waybill.getEndPlace());
        mRemarksEditText.setText(waybill.getRemarks());
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("运单详情");
    }


    @OnClick({R.id.activity_waybillidetail_btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_waybillidetail_btn_delete:
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
                        InforDBUtils dbUtils = new InforDBUtils(WaybillDetailActivity.this);
                        dbUtils.deleteWaybill(waybill);
                        showToast("删除成功");
                        startActivity(new Intent(WaybillDetailActivity.this, MainActivity.class));
                        WaybillDetailActivity.this.finish();
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
