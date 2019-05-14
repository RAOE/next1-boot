package com.nextone.web.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-13
 * @description 消息队列的消费者
 * 日志保存的线程
 **/
@Component
public class AuditLogConsumer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(AuditLogConsumer.class);
    private static final int DEFAULT_BATCH_SIZE = 16;
    private AuditLogQueue auditLogQueue;
    private AuditLogService auditLogService;
    private int batchSize = DEFAULT_BATCH_SIZE;
    private boolean active = true;
    private Thread thread;

    //使用该注解在项目启动时候启动线程
    @PostConstruct
    public void init() {
        thread = new Thread(this);
        thread.start();
    }

    //使用该注解在项目结束的舒缓关闭
    @PreDestroy
    public void close() {
        active = false;
    }


    @Override
    public void run() {
        while (active) {
            execute();
        }
    }

    public void execute() {
        List<AuditLog> auditLogList = new ArrayList<AuditLog>();
        try {
            int size = 0;
            while (size < batchSize) {
                AuditLog auditLog = auditLogQueue.poll();//从队列中取出一个
                if (auditLog == null) {
                    continue;
                }
                auditLogList.add(auditLog);
                size++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        //如果当前的日志list不为空
        if (!auditLogList.isEmpty()) {
           // auditLogService.batchLog(auditDtos);
            auditLogService.batchLog(auditLogList);
            //在这里执行了servivce的方法
        }
    }

    @Resource
    public void setAuditLogQueue(AuditLogQueue auditLogQueue) {
        this.auditLogQueue = auditLogQueue;
    }
    @Resource
    public void setAuditLogService(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

}
