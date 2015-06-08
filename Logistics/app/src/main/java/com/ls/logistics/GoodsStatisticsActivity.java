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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.Goods;
import com.ls.util.InforDBUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ls on 15-6-4.
 */
public class GoodsStatisticsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,
        AdapterView.OnItemSelectedListener, OnDateSetListener {
    @ViewInject(R.id.activity_goodsstatisticsinfor_tb)
    private Toolbar mToolbar;
    @ViewInject(R.id.activity_goodsstatisticsinfor_sp)
    private Spinner mSpinner;
    @ViewInject(R.id.activity_goodsstatisticsinfor_lv)
    private ListView mListView;
    @ViewInject(R.id.activity_goodsstatisticsinfor_et_publish_time_end)
    private EditText mPublishTimeEndEditText;
    @ViewInject(R.id.activity_goodsstatisticsinfor_et_publish_time_start)
    private EditText mPublishTimeStartEditText;


    private String[] idArray = {"1", "2", "3", "4"};
    private String id;
    //   private ListAdapter mAdapter;
    private int viewId;


    public static final String DATEPICKER_TAG = "datepicker";
    private DatePickerDialog datePickerDialog;
    private int year;
    private int day;
    private int month;
    private ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsstatisticsinfor);
        ViewUtils.inject(this);
        initToolbar();
        initSpinner();
        initTimePicker();
        initListView();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("统计货物");
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item);
        String level[] = getResources().getStringArray(R.array.type3);//资源文件
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.id = idArray[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.activity_goodsstatisticsinfor_et_publish_time_end, R.id.activity_goodsstatisticsinfor_et_publish_time_start
            , R.id.activity_goodsstatisticsinfor_btn_ensure})
    public void onClick(View v) {
        viewId = v.getId();
        switch (v.getId()) {
            case R.id.activity_goodsstatisticsinfor_btn_ensure:
                queryInfor();
                break;
            case R.id.activity_goodsstatisticsinfor_et_publish_time_end:
                showDatePicker();
                break;
            case R.id.activity_goodsstatisticsinfor_et_publish_time_start:
                showDatePicker();
                break;
        }
    }

    private void queryInfor() {
        String publishTimeStart = mPublishTimeStartEditText.getText().toString();
        String publishTimeEnd = mPublishTimeEndEditText.getText().toString();
        if (publishTimeStart.equals("") || publishTimeEnd.equals("") || id.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        LogUtils.e("统计货物：开始时间 " + publishTimeStart + " 结束时间 " + publishTimeEnd + " id" + id);
        InforDBUtils dbUtils = new InforDBUtils(this);
        List<Goods> goodsList = dbUtils.findGoods(publishTimeStart, publishTimeEnd, id);
        if (goodsList != null && goodsList.size() > 0) {
            for (Goods goods : goodsList)
                LogUtils.e("查找到的：goods是" + goods);
        }
        mAdapter.addData(goodsList);
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
            case R.id.activity_goodsstatisticsinfor_et_publish_time_end:
                mPublishTimeEndEditText.setText(i + "-" + month + "-" + day);
                break;
            case R.id.activity_goodsstatisticsinfor_et_publish_time_start:
                mPublishTimeStartEditText.setText(i + "-" + month + "-" + day);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Goods goods = (Goods) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, GoodsDetailActivity.class);
        intent.putExtra("goods", goods);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        delete(parent.getAdapter().getItem(position));
        return true;
    }

    private void delete(final Object item) {
        new AlertDialog.Builder(this).setTitle("确认删除该信息吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InforDBUtils dbUtils = new InforDBUtils(GoodsStatisticsActivity.this);
                        dbUtils.deleteGoods((Goods) item);
                        showToast("删除成功");
                        GoodsStatisticsActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }



    class ListAdapter extends BaseAdapter {
        private List<Goods> goodses;

        public void addData(List<Goods> goodses) {
            this.goodses = goodses;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return goodses == null ? 0 : goodses.size();
        }

        @Override
        public Object getItem(int position) {
            return goodses == null ? null : goodses.get(position);
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
                convertView =  LayoutInflater.from(parent.getContext()).inflate(R.layout.query_goods_list_item, null);
                viewHolder.textView1 = (TextView) convertView.findViewById(R.id.query_goods_list_item_1);
                viewHolder.textView2 = (TextView) convertView.findViewById(R.id.query_goods_list_item_2);
                viewHolder.textView3 = (TextView) convertView.findViewById(R.id.query_goods_list_item_3);
                viewHolder.textView4 = (TextView) convertView.findViewById(R.id.query_goods_list_item_4);
                convertView.setTag(viewHolder);

            } else viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.textView1.setText(goodses.get(position).getCargoId());
            viewHolder.textView2.setText(goodses.get(position).getUserId());
            viewHolder.textView3.setText(goodses.get(position).getCargoName());
            viewHolder.textView4.setText(goodses.get(position).getCargoType());
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
