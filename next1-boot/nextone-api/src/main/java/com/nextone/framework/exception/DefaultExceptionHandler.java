package com.nextone.framework.exception;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-07-06
 * @description
 **/
import com.nextone.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理器
 *
 * @author numberone
 */
@RestControllerAdvice
public class DefaultExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public JsonResult handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e.getMessage());
        return JsonResult.errorMsg("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public JsonResult notFount(RuntimeException e)
    {
        log.error("运行时异常:", e.getMessage());
        return JsonResult.errorMsg("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e)
    {
        log.error(e.getMessage(), e.getMessage());
        return JsonResult.errorMsg("服务器错误，请联系管理员");
    }

}
