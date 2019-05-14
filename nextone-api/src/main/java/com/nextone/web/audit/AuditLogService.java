package com.nextone.web.audit;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-13
 * @description
 **/
@Service
public class AuditLogService {

    /**
     * 对消息队列的日志进行批量处理
     *
     * @param auditLogList
     */
    public void batchLog(List<AuditLog> auditLogList) {
        auditLogList.parallelStream().forEach((e)->{
            System.out.println(e.toString());
        });
    }
}