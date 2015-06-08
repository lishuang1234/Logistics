package com.ls.util;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.ls.bean.SoftUser;

import java.util.List;

/**
 * Created by ls on 15-6-7.
 */
public class UserDBUtils {
    private DbUtils mUserDbUtils;

    public UserDBUtils(Context context) {
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName("user.db");
        mUserDbUtils = DbUtils.create(config);
    }

    public void saveUser(SoftUser softUser) {
        clearSoftUser();
        try {
            mUserDbUtils.save(softUser);
        } catch (DbException e) {
            LogUtils.e("softUser存入数据库失败");
            e.printStackTrace();
        }
    }

    public SoftUser getSoftUser() {
        List<SoftUser> softUsers = null;
        try {
            softUsers = mUserDbUtils.findAll(SoftUser.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (softUsers != null && softUsers.size() > 0)
            return softUsers.get(0);
        else
            return null;
    }

    public void clearSoftUser() {
        try {
            mUserDbUtils.deleteAll(SoftUser.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
}
