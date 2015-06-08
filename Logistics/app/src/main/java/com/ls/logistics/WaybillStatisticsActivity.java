package com.ls.logistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.Waybill;
import com.ls.util.InforDBUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ls on 15-6-4.
 */
public class WaybillStatisticsActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    @ViewInject(R.id.activity_waybillstatisticsinfor_tb)
    private Toolbar mToolbar;
    @ViewInject(R.id.activity_waybillstatisticsinfor_lv)
    private ListView mListView;
    @ViewInject(R.id.activity_waybillstatisticsinfor_et_publish_time_end)
    private EditText mTimeEndEditText;
    @ViewInject(R.id.activity_waybillstatisticsinfor_et_publish_time_start)
    private EditText mTimeStartEditText;

    public static final String DATEPICKER_TAG = "datepicker";
    private DatePickerDialog datePickerDialog;
    private int year;
    private int day;
    private int month;
    private ListAdapter mAdapter;


    private int viewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybillstatisticsinfor);
        ViewUtils.inject(this);
        initToolbar();
        initTimePicker();
        initListView();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("统计运单");
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

    private void initListView() {
        mListView.setOnItemLongClickListener(this);
        mAdapter = new ListAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }


    private void showDatePicker() {
        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    private boolean isVibrate() {
        return false;
    }

    private boolean isCloseOnSingleTapDay() {
        return false;
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
        switch (viewId) {
            case R.id.activity_waybillstatisticsinfor_et_publish_time_end:
                mTimeEndEditText.setText(i + "-" + month + "-" + day);
                break;
            case R.id.activity_waybillstatisticsinfor_et_publish_time_start:
                mTimeStartEditText.setText(i + "-" + month + "-" + day);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Waybill waybill = (Waybill) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, WaybillDetailActivity.class);
        intent.putExtra("waybill", waybill);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        delete((Waybill) parent.getAdapter().getItem(position));
        return true;
    }

    private void delete(final Waybill waybill) {
        new AlertDialog.Builder(this).setTitle("确认删除该信息吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        InforDBUtils dbUtils = new InforDBUtils(WaybillStatisticsActivity.this);
                        dbUtils.deleteWaybill(waybill);
                        showToast("删除成功");
                        WaybillStatisticsActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作

                    }
                }).show();
    }

    @OnClick({R.id.activity_waybillstatisticsinfor_et_publish_time_end, R.id.activity_waybillstatisticsinfor_et_publish_time_start
            , R.id.activity_waybillstatisticsinfor_btn_ensure})
    public void onClick(View v) {
        viewId = v.getId();
        switch (v.getId()) {
            case R.id.activity_waybillstatisticsinfor_btn_ensure:
                queryInfor();
                break;
            case R.id.activity_waybillstatisticsinfor_et_publish_time_end:
                showDatePicker();
                break;
            case R.id.activity_waybillstatisticsinfor_et_publish_time_start:
                showDatePicker();
                break;
        }
    }

    private void queryInfor() {
        String publishTimeStart = mTimeStartEditText.getText().toString();
        String publishTimeEnd = mTimeEndEditText.getText().toString();
        if (publishTimeStart.equals("") || publishTimeEnd.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        LogUtils.e("统计货物：开始时间 " + publishTimeStart + " 结束时间 " + publishTimeEnd);
        InforDBUtils dbUtils = new InforDBUtils(this);
        List<Waybill> waybillList = dbUtils.findWaybill(publishTimeStart, publishTimeEnd);
        if (waybillList != null && waybillList.size() > 0) {
            for (Waybill waybill : waybillList)
                LogUtils.e("查找到的：goods是" + waybill);
        }
        mAdapter.addData(waybillList);
    }


    class ListAdapter extends BaseAdapter {
        private List<Waybill> waybills;

        public void addData(List<Waybill> waybills) {
            this.waybills = waybills;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return waybills == null ? 0 : waybills.size();
        }

        @Override
        public Object getItem(int position) {
            return waybills == null ? null : waybills.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_waybill_list_item, null);
                viewHolder.textView1 = (TextView) convertView.findViewById(R.id.query_waybill_list_item_1);
                viewHolder.textView2 = (TextView) convertView.findViewById(R.id.query_waybill_list_item_2);
                viewHolder.textView3 = (TextView) convertView.findViewById(R.id.query_waybill_list_item_3);
                viewHolder.textView4 = (TextView) convertView.findViewById(R.id.query_waybill_list_item_4);
                convertView.setTag(viewHolder);

            } else viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.textView1.setText(waybills.get(position).getOrderId());
            viewHolder.textView2.setText(waybills.get(position).getUserId());
            viewHolder.textView3.setText(waybills.get(position).getCargoId());
            viewHolder.textView4.setText(waybills.get(position).getDoTime());
            return convertView;
        }

        class ViewHolder {
            TextView textView1;
            TextView textView2;
            TextView textView3;
            TextView textView4;
        }
    }
}
