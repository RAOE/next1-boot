package com.nextone.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-10
 * @description
 **/
public class IpUtils {


    public static String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("X-Forwarded-For") == null) /* 存在 X-Forwarded-For 吗? */ {
            return request.getRemoteAddr(); /* 兼容已有程序 */
        }

        return request.getHeader("X-Forwarded-For"); /* 返回用户真实 IP, 如为多个 IP 时, 则取第一个 */
    }

}
