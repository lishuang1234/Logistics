package com.ls.util;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.ls.bean.Car;
import com.ls.bean.Goods;
import com.ls.bean.Logistics;
import com.ls.bean.Route;
import com.ls.bean.TransportPerson;
import com.ls.bean.User;
import com.ls.bean.Waybill;

import java.util.List;

/**
 * Created by ls on 15-4-20.
 */
public class InforDBUtils {
    // private static final DBUtils dbUtils = new DBUtils();

    private DbUtils mInforDbUtils;
//    private DbUtils mUserCircleDbUtils;
//    private DbUtils mUserFriendDbUtils;

    public InforDBUtils(Context context) {
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName("infor.db");
        mInforDbUtils = DbUtils.create(config);
    }

    public boolean saveUser(User user) {
        boolean state = true;
        try {
            mInforDbUtils.save(user);
        } catch (DbException e) {
            LogUtils.e("user存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public boolean saveGoods(Goods goods) {
        boolean state = true;
        try {
            mInforDbUtils.save(goods);
        } catch (DbException e) {
            LogUtils.e("goods存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public boolean saveWaybill(Waybill waybill) {
        boolean state = true;
        try {
            mInforDbUtils.save(waybill);
        } catch (DbException e) {
            LogUtils.e("waybill存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public boolean saveRoute(Route route) {
        boolean state = true;
        try {
            mInforDbUtils.save(route);
        } catch (DbException e) {
            LogUtils.e("route存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public boolean saveCar(Car car) {
        boolean state = true;
        try {
            mInforDbUtils.save(car);
        } catch (DbException e) {
            LogUtils.e("car存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public boolean saveTransportPerson(TransportPerson transportPerson) {
        boolean state = true;
        try {
            mInforDbUtils.save(transportPerson);
        } catch (DbException e) {
            LogUtils.e("transportPerson存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public boolean saveLogistics(Logistics logistics) {
        boolean state = true;
        try {
            mInforDbUtils.save(logistics);
        } catch (DbException e) {
            LogUtils.e("logistics存入数据库失败");
            e.printStackTrace();
            state = false;
        }
        return state;
    }

    public List<Goods> getGoods(String id, String infor) {
        List<Goods> goods = null;
        try {
            goods = mInforDbUtils.findAll(Selector.from(Goods.class).where(id, "=", infor));
        } catch (DbException e) {
            LogUtils.e("goods查找失败");
            e.printStackTrace();
        }
        return goods;
    }


    public List<Waybill> getWaybill(String id, String infor) {
        List<Waybill> waybills = null;
        try {
            waybills = mInforDbUtils.findAll(Selector.from(Waybill.class).where(id, "=", infor));
        } catch (DbException e) {
            LogUtils.e("Waybill查找失败");
            e.printStackTrace();
        }
        return waybills;
    }

    public void deleteGoods(Goods goods) {
        try {
            mInforDbUtils.delete(goods);
        } catch (DbException e) {
            LogUtils.e("goods删除失败");
            e.printStackTrace();
        }
    }

    public void deleteWaybill(Waybill waybill) {
        try {
            mInforDbUtils.delete(waybill);
        } catch (DbException e) {
            LogUtils.e("waybill删除失败");
            e.printStackTrace();
        }
    }

    public List<Goods> findGoods(String publishTimeStart, String publishTimeEnd, String cargoType) {
        List<Goods> list = null;
        try {
            list = mInforDbUtils.findAll(Selector.from(Goods.class)
                            .where("cargoType", "=", cargoType)
                            .and(WhereBuilder.b("publishTime", ">", publishTimeStart)).and("publishTime", "<", publishTimeEnd)
                            .orderBy("id")
                            .limit(5)
            );
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Waybill> findWaybill(String publishTimeStart, String publishTimeEnd) {
        List<Waybill> list = null;
        try {
            list = mInforDbUtils.findAll(Selector.from(Waybill.class)
                            .where(WhereBuilder.b("doTime", ">", publishTimeStart)).and("doTime", "<", publishTimeEnd)
                            .orderBy("doTime",true)
                            .limit(5)
            );
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
