package com.nextone.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * 使用Jackson完成JSON操作
 * ObjectMapper是线程安全的，可以作为并推荐作为单例使用
 * 注意，把对象转换成json字符串时要保证对象中不存在循环（递归）引用，如A引用B，而B引用同一个A
 * 转换时默认的日期格式是yyyy-MM-dd HH:mm:ss，如果想使用其他格式，需要调用getNewInstance()创建一个新的ObjectMapper对象自己手动设置
 * 值为null的字段不参与生成json格式字符串
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        //设置转换时日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //字段为null时不参与序列化
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
