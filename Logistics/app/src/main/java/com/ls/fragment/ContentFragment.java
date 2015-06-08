package com.ls.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ls.adapter.ListViewAdapter;
import com.ls.logistics.AddCarInforActivity;
import com.ls.logistics.AddUserInforActivity;
import com.ls.logistics.AddLogisticsInforActivity;
import com.ls.logistics.AddRouteInforActivity;
import com.ls.logistics.AddTransportPersonInforActivity;
import com.ls.logistics.AddGoodsInforActivity;
import com.ls.logistics.AddWaybillInforActivity;
import com.ls.logistics.QueryGoodsInforActivity;
import com.ls.logistics.QueryWaybillInforActivity;
import com.ls.logistics.R;
import com.ls.logistics.GoodsStatisticsActivity;
import com.ls.logistics.WaybillStatisticsActivity;
import com.ls.util.Constant;

import java.util.List;

/**
 * Created by ls on 15-6-4.
 */
public class ContentFragment extends Fragment implements AdapterView.OnItemClickListener {
    private String title;
    private List<String> mListTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_list_view, container, false);
        initView(mLinearLayout);
        return mLinearLayout;
    }

    private void initView(LinearLayout mLinearLayout) {
        ListView mListView = (ListView) mLinearLayout.findViewById(R.id.list_view);
        mListView.setAdapter(new ListViewAdapter(mListTitle));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setListTitle(List<String> dataList) {
        this.mListTitle = dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class<?>[] addInfor = {AddGoodsInforActivity.class, AddWaybillInforActivity.class
                , AddRouteInforActivity.class,AddUserInforActivity.class , AddCarInforActivity.class
                , AddTransportPersonInforActivity.class, AddLogisticsInforActivity.class};
        Class<?>[] queryInfor = {QueryGoodsInforActivity.class, QueryWaybillInforActivity.class};
        Class<?>[] statistics = {GoodsStatisticsActivity.class, WaybillStatisticsActivity.class};
        if (title.equals(Constant.titles[0])) {
            gotoActivity(position, addInfor[position]);
        } else if (title.equals(Constant.titles[1])) {
            gotoActivity(position, queryInfor[position]);
        } else if (title.equals(Constant.titles[2])) {
            gotoActivity(position, statistics[position]);
        }
    }

    private void gotoActivity(int position, Class<?> activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        intent.putExtra("position", position);
        getActivity().startActivity(intent);
    }
}
