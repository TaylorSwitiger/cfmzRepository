package com.xuyiming.cmfz.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by 阿斯加的酱油 on 2018/10/9.
 */
public class Log {

    private String logId;
    private String operateManager;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date operateTime;
    private String resource;
    private String action;
    private String message;
    private String result;


    public String getOperateManager() {
        return operateManager;
    }

    public void setOperateManager(String operateManager) {
        this.operateManager = operateManager;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId='" + logId + '\'' +
                ", operateManager='" + operateManager + '\'' +
                ", operateTime=" + operateTime +
                ", resource='" + resource + '\'' +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
