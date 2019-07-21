package com.nextone.framework.web;

import com.nextone.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-07-07
 * @description 用于拓展 controller 第一层
 **/
public class SimpleController {

    /**
     * 日志对象
     *
     * @since 1.0.0
     */
    protected static Logger log = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;


    /**
     * HttpServletResponse
     *
     * @since 1.0.0
     */
    @Autowired
    protected HttpServletResponse response;

    /**
     * Spring applicationContext
     *
     * @since 1.0.0
     */
    protected ApplicationContext applicationContext;


    /**
     * 获取webBasePath【如 http://127.0.0.1:8080/projectname】
     *
     * @return webBasePath
     * @since 1.0.2
     */
    protected String getWebBasePath() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


    /**
     * 获取来访的ip地址
     *
     * @param request
     * @return ip地址
     * @since 1.0.6
     */
    protected String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**
     * 获取经过nginx后的ip地址
     *
     * @param request
     * @return ip地址
     * @since 4.0.1
     */
    protected String getNginxForwardIp(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!CommonUtils.isEmpty(ip)) {
            ip = ip.split(",")[0];
        }
        return ip;

    }

}
