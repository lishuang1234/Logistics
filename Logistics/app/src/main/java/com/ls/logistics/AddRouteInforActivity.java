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
import com.ls.bean.Route;
import com.ls.util.InforDBUtils;

/**
 * Created by ls on 15-6-4.
 * 路线
 */
public class AddRouteInforActivity extends AppCompatActivity {
    @ViewInject(R.id.activity_addrouteinfor_tb)
    private Toolbar mToolbar;

    @ViewInject(R.id.activity_addrouteinfor_et_line_id)
    private EditText mLineIdEditText;
    @ViewInject(R.id.activity_addrouteinfor_et_special_line)
    private EditText mSperialLineEditText;
    @ViewInject(R.id.activity_addrouteinfor_et_company_id)
    private EditText mCompanyIdEditText;
    @ViewInject(R.id.activity_addrouteinfor_et_route)
    private EditText mRouteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrouteinfor);
        ViewUtils.inject(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新增路线");
    }

    @OnClick({R.id.activity_addrouteinfor_btn_ensure, R.id.activity_addrouteinfor_btn_cancle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_addrouteinfor_btn_cancle:
                this.finish();
                break;
            case R.id.activity_addrouteinfor_btn_ensure:
                saveRoute();
                break;
        }

    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    private void saveRoute() {
        String lineId = mLineIdEditText.getText().toString();
        String specialLine = mSperialLineEditText.getText().toString();
        String routeString = mRouteEditText.getText().toString();
        String companyId = mCompanyIdEditText.getText().toString();
        if (lineId.equals("") || specialLine.equals("") ||
                routeString.equals("") || companyId.equals("")
                ) {
            showToast("请输入完整信息");
            return;
        }
        Route route = new Route(lineId, System.currentTimeMillis() + "", specialLine, routeString, companyId);
        InforDBUtils dbUtils = new InforDBUtils(this);
        dbUtils.saveRoute(route);
        showToast("保存信息成功");
        this.finish();
    }

}
