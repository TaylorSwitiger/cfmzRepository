package com.baizhi.cmfz.entity;

public class Manager {
    private String mgrId;

    private String mgrName;

    private String mgrPwd;

    private String salt;

    private String status;

    public String getMgrId() {
        return mgrId;
    }

    public void setMgrId(String mgrId) {
        this.mgrId = mgrId == null ? null : mgrId.trim();
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName == null ? null : mgrName.trim();
    }

    public String getMgrPwd() {
        return mgrPwd;
    }

    public void setMgrPwd(String mgrPwd) {
        this.mgrPwd = mgrPwd == null ? null : mgrPwd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}