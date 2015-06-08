package com.ls.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ls.logistics.R;

import java.util.List;

/**
 * Created by ls on 15-6-4.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<String> mListTitle;

    public ListViewAdapter(List<String> mList) {
        this.mListTitle = mList;
    }

    @Override
    public int getCount() {
        return mListTitle == null ? 0 : mListTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return mListTitle == null ? position : mListTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mListTitle == null)
            return null;
        ViewHolder mViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mTitle = (TextView) convertView.findViewById(R.id.list_item_tx_title);
            convertView.setTag(mViewHolder);
        } else
            mViewHolder = (ViewHolder) convertView.getTag();
        mViewHolder.mTitle.setText(mListTitle.get(position));
        return convertView;
    }


    static class ViewHolder {
        TextView mTitle;
    }
}
