package com.nextone.web.audit;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-13
 * @description
 **/
@Component
public class AuditLogQueue {
    //    BlockingDequeu为双端阻塞队列，blockingQueue阻塞队列
    private BlockingQueue<AuditLog> blockingQueue = new LinkedBlockingQueue<AuditLog>();

    public void add(AuditLog auditLog) {
        blockingQueue.add(auditLog);
    }
    //poll从队列的头部获取到信息
    public AuditLog poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);//每秒钟执行一次
    }


}
