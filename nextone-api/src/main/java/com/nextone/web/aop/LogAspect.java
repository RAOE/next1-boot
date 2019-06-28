package com.nextone.web.aop;
import com.nextone.utils.IpUtils;
import com.nextone.utils.JsonUtils;
import com.nextone.web.audit.AuditLog;
import com.nextone.web.audit.AuditLogQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 徐塬峰 2019/3/31
 * 使用AOP面向切面编程的方式实现登录拦截 将请求跳转到登录页面
 */
@Aspect
@Configuration
public class LogAspect {

    @Autowired
    private AuditLogQueue auditLogQueue;

    private static final Logger logger = LogManager.getLogger("用户操作日志");
    @Before("@annotation(com.nextone.web.annotation.SysLog)")
    public Object SysLog(JoinPoint joinPoint) throws Throwable {
        Object[] ob = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String username = null;
        String ip = IpUtils.getRemoteIP(request);

        Object[] args = joinPoint.getArgs();
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
        //控制台输出
         logger.info("用户id：{}，方法签名：{}，方法参数：{} ip地址:{}", username, joinPoint.getSignature(), JsonUtils.toJson(args), ip);
        //*========数据库日志=========*//
//        AuditLog log = new AuditLog();
//        log.setId(Sid.nextShort());
//        log.setDescription(joinPoint.getSignature().toString());
//        log.setMethod(joinPoint.getSignature().toString());
//        log.setParams(JsonUtils.toJson(args));
//        log.setType("0");
//        log.setRequestIp(ip);
//        log.setExceptionCode( null);
//        log.setExceptionDetail( null);
//        log.setCreateDate(new Date());
        //保存数据库
//        auditLogQueue.add(log);
        //拿到请求中的所有参数args
        return ob;
    }

}
