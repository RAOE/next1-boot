package com.nextone.web.audit;

import java.util.Date;

/**
 * 日志记录实体层
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-13
 * @description
 **/
public class AuditLog {

    private String id;
    private String description;
    private String method;
    private String type;
    private String requestIp;
    private String exceptionCode;
    private String exceptionDetail;
    private String params;
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", exceptionCode='" + exceptionCode + '\'' +
                ", exceptionDetail='" + exceptionDetail + '\'' +
                ", params='" + params + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
