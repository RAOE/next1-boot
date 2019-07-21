package com.nextone.generator;

import com.nextone.utils.CommonUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-09
 * @description
 **/
public class DataUtils {
    /**
     * 对数据进行统一处理工具类
     *
     * @author 徐塬峰
     * @version V1.0
     * @date: 2019年3月1日 上午11:13:57
     */
    public static String getPath(List<Map<String, String>> list, String pathName) {
        for (Map<String, String> map : list) {
            String path = map.get(pathName);
            if (!CommonUtils.isEmpty(path)) {
                return path;
            }
        }
        return null;
    }

    /**
     * 返回数据库中所有表名
     *
     * @param list
     * @param key
     * @return
     */
    public static List<String> getValue(List<Map<String, String>> list, String key) {
        // 从该数据集中返回tableName表名
        List<String> tableList = new ArrayList<String>();
        for (Map<String, String> map : list) {
            String tableName = map.get(key);
            if (!CommonUtils.isEmpty(tableName)) {
                tableList.add(tableName);
            }
        }
        return tableList;
    }

    /**
     * 将类名添加.JAVA后缀
     *
     * @param modelList
     * @return
     */
    public static List<String> dealClassName(List<String> modelList) {
        List<String> list = new ArrayList<String>();
        for (String str : modelList) {
            list.add(new StringBuffer(str).append(".java").toString());
        }
        return list;
    }

    /**
     * 在末尾拼接param
     *
     * @param modelList
     * @return
     */
    public static List<String> dealClassNameByParam(List<String> modelList, String param) {
        List<String> list = new ArrayList<String>();
        for (String str : modelList) {
            list.add(new StringBuffer(str).append(param).toString());
        }
        return list;
    }


    /**
     * 返回数据库中获取的所有列名 map集合
     *
     * @param string
     * @return
     */
    public static Map<String, String> getColumnMap(String string) {
        // 查詢数据库中的所有列明并且添加到Map集合中
        Map<String, String> columnMap = new HashMap<String, String>();
        ResultSet rs = null;
        try {
            rs = JDBCUtils.executeQuery("select * from " + string);
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();// 获取有多少列
            for (int i = 0; i < numberOfColumns; i++) {
                String columnName = dealColumnName(metaData, i);
                String columnType = dealColumnType(metaData, i);
                columnMap.put(columnName, columnType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库查询异常");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return columnMap;
    }

    /**
     * 处理sql返回结果的列名
     *
     * @param rsmd
     * @param i
     * @return
     */
    private static String dealColumnName(ResultSetMetaData rsmd, int i) {
        String columnName = null;
        try {
            columnName = rsmd.getColumnName(i + 1).toLowerCase();
            String charAfterLine = String.valueOf(columnName.charAt((columnName.indexOf("_") + 1)));
            String convertedChar = charAfterLine.toUpperCase();
            columnName = columnName.replace("_" + charAfterLine, convertedChar);
            return columnName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnName;
    }

    /**
     * 处理sql返回的类型名称
     *
     * @param rsmd
     * @param i
     * @return
     */

    private static String dealColumnType(ResultSetMetaData rsmd, int i) {
        String columnType = null;
        try {
            columnType = rsmd.getColumnTypeName(i + 1).toLowerCase();
            String charAfterLine = String.valueOf(columnType.charAt((columnType.indexOf("_") + 1)));
            String convertedChar = charAfterLine.toUpperCase();
            columnType = columnType.replace("_" + charAfterLine, convertedChar);
            return columnType;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnType;
    }


    /**
     * 从集合中取出map里的key
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String value = null;
        for (Map<String, String> map : GeneratorUtils.list) {
            value = map.get(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * 转换为java属性
     *
     * @param columnMap
     * @return
     */
    public static Map<String, String> formateMap(Map<String, String> columnMap) {
        Map<String, String> map = new HashMap<>();
        //遍历map集合，将其中的数据库字段属性转换为java字段属性
        for (Map.Entry<String, String> entry : columnMap.entrySet()) {
            switch (entry.getValue()) {
                case "bigint":
                    map.put(entry.getKey(), "Long");
                    break;
                case "varchar":
                    map.put(entry.getKey(), "String");
                    break;
                case "datetime":
                    map.put(entry.getKey(), "Date");
                    break;
                case "int":
                    map.put(entry.getKey(), "int");
                case "float":
                    map.put(entry.getKey(), "float");
                case "tinyint":
                    map.put(entry.getKey(), "boolean");
            }
        }
        return map;
    }
}
