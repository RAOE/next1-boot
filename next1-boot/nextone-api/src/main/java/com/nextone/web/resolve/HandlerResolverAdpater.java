package com.nextone.web.resolve;
import com.nextone.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 徐塬峰 2019/3/31
 * 统一异常解决方案
 */
@Component
public class HandlerResolverAdpater implements HandlerExceptionResolver {

    private final String HTTP_HEADER="X-Requested-With";
    private static final Logger logger = LogManager.getLogger(HandlerResolverAdpater.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        logger.error("服务器错误");
        //如果是ajax请求，就返回一个json格式的出错提示信息
        if (request.getHeader(HTTP_HEADER) != null) {
            try {
                response.getWriter().println(JsonUtils.toJson("服务器出错了"));
            } catch (IOException e) {
                logger.error("服务器失败时发送错误提示信息失败", e);
            }
            //返回一个空的ModelAndView表示已经手动生成响应
            //return null表示使用默认的处理方式，等于没处理
            return new ModelAndView();
        } else {
            return new ModelAndView("500");
        }
    }
}
