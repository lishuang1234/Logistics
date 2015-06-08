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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ls.bean.Goods;
import com.ls.util.InforDBUtils;

import java.util.List;

/**
 * Created by ls on 15-6-4.
 */
public class QueryGoodsInforActivity extends AppCompatActivity implements OnItemSelectedListener, OnItemLongClickListener, OnItemClickListener {
    @ViewInject(R.id.goods)
    private Toolbar mToolbar;
    @ViewInject(R.id.activity_querygoodsinfor_sp)
    private Spinner mSpinner;
    @ViewInject(R.id.activity_querygoodsinfor_btn_ensure)
    private Button mEnsure;
    @ViewInject(R.id.activity_querygoodsinfor_lv)
    private ListView mListView;
    @ViewInject(R.id.activity_querygoodsinfor_et_infor)
    private EditText mInfor;
    private String[] idArray = {"cargoId", "userId", "cargoType"};
    private String id;
    private ListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querygoodsinfor);
        ViewUtils.inject(this);
        initToolbar();
        initSpinner();
        initListView();
    }

    private void initListView() {
        mListView.setOnItemLongClickListener(this);
        mAdapter = new ListAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item);
        String level[] = getResources().getStringArray(R.array.type);//资源文件
        for (int i = 0; i < level.length; i++) {
            adapter.add(level[i]);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("查询货物");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mInfor.setText("");
        this.id = idArray[position];
        LogUtils.e("onItemSelected position:" + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick({R.id.activity_querygoodsinfor_btn_ensure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_querygoodsinfor_btn_ensure:
                queryInfor();
                break;
        }
    }

    private void showToast(String infor) {
        Toast.makeText(this, infor, Toast.LENGTH_SHORT).show();
    }

    private void queryInfor() {
        String infor = mInfor.getText().toString();
        if (infor.equals("")) {
            showToast("请填写完整信息");
            return;
        }
        InforDBUtils dbUtils = new InforDBUtils(this);
        List<Goods> goodses = dbUtils.getGoods(id, infor);
        if (goodses != null && goodses.size() > 0) {
            for (Goods goods : goodses)
                LogUtils.e("查找到的：goods是" + goods);
        }
        mAdapter.addData(goodses);

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
                        InforDBUtils dbUtils = new InforDBUtils(QueryGoodsInforActivity.this);
                        dbUtils.deleteGoods((Goods) item);
                        showToast("删除成功");
                        QueryGoodsInforActivity.this.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Goods goods = (Goods) parent.getAdapter().getItem(position);
        Intent intent = new Intent(this, GoodsDetailActivity.class);
        intent.putExtra("goods", goods);
        startActivity(intent);
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
