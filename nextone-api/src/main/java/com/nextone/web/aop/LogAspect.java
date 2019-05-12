package com.nextone.web.aop;
import com.nextone.pojo.AdminUser;
import com.nextone.utils.IpUtils;
import com.nextone.utils.JsonUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 徐塬峰 2019/3/31
 * 使用AOP面向切面编程的方式实现登录拦截 将请求跳转到登录页面
 */
@Aspect
@Configuration
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger("--操作日志--");
    @Before("@annotation(com.nextone.web.annotation.SysLog)")
    public Object log(JoinPoint joinPoint) throws Throwable {
        Object[] ob = joinPoint.getArgs();
        // 直接拿到全局的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AdminUser user = (AdminUser) session.getAttribute("adminUser");
        String username = null;
        if (user != null) {
            username = user.getUsername();
        }
        Object[] args = joinPoint.getArgs();
        String ip = IpUtils.getRemoteIP(request);
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest) {
                args[i] = "request对象";
            } else if (args[i] instanceof ServletResponse) {
                args[i] = "response对象";
            } else if (args[i] instanceof MultipartFile) {
                args[i] = "MultipartFile对象";
            } else if (args[i] instanceof BindingResult) {
                args[i] = "BindingResult对象";
            }
        }
        logger.info("用户id：{}，方法签名：{}，方法参数：{} ip地址:{}", username, joinPoint.getSignature(), JsonUtils.toJson(args), ip);
        return ob;
    }


}
