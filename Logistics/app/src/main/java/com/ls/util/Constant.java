package com.ls.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ls on 15-6-4.
 */
public class Constant {
    public static final String[] titles = new String[]{"更新信息", "查询信息", "数据统计"};
    public static final String[] data1 = new String[]{"新增货物", "新增运单", "新增路线", "新增用户", "新增车辆", "新增运输人员", "新增物流企业"};
    public static final String[] data2 = new String[]{"查询货物", "查询运单"};
    public static final String[] data3 = new String[]{"货物统计", "运单统计"};

    public static List<String> getListTitle(String title) {
        List<String> data = null;
        String[] temp = null;
        if (title.equals("更新信息")) {
            temp = data1;
        } else if (title.equals("查询信息")) {
            temp = data2;
        } else if (title.equals("数据统计")) {
            temp = data3;
        }
        if (temp != null)
            data = Arrays.asList(temp);
        return data;
    }

}
