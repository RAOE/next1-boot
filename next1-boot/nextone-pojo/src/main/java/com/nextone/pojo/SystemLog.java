package com.nextone.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 系统日志
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-30
 * @description
 **/
@Table(name="t_syslog")
public class SystemLog {
    @Id
    private Long id;
    /**
     * 操作人
     */
    private String name;
    /**
     * 主机
     */
    private String host;
    /**
     * 地址
     */
    private String address;
    /**
     * 请求地址
     */
    @Column(name="requestPath")
    private String requestPath;
    /**
     * 请求方法
     */
    @Column(name="requestMethod")
    private String requestMethod;
    /**
     * 请求参数
     */
    @Column(name="requestParam")
    private String requestParam;
    /**
     * 状态
     */
    private Boolean status;
    @Column(name="createTime")
    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", address='" + address + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestParam='" + requestParam + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
