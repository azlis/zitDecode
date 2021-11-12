package com.webdao.model;

/* loaded from: classes.dex */
public class OperatorInfo {
    private String ICPArray;
    private String LoginGUID;
    private String mCertificateNo;
    private String mDepartment;
    private String mInspector;
    private String mInstrid;
    private String mPassword;
    private String mServicAddress;
    private String mUserName;
    private String matchStr;

    public String getServicAddress() {
        return this.mServicAddress;
    }

    public void setServicAddress(String servicAddress) {
        this.mServicAddress = servicAddress;
        this.mServicAddress = servicAddress;
    }

    public String getCertificateNo() {
        return this.mCertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.mCertificateNo = certificateNo;
    }

    public String getDepartment() {
        return this.mDepartment;
    }

    public void setDepartment(String department) {
        this.mDepartment = department;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public void setMatchStr(String matchStr) {
        this.matchStr = matchStr;
    }

    public String getMatchStr() {
        return this.matchStr;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getLoginGUID() {
        return this.LoginGUID;
    }

    public void setLoginGUID(String loginGUID) {
        this.LoginGUID = loginGUID;
    }

    public String getInstrid() {
        return this.mInstrid;
    }

    public void setInstrid(String instrid) {
        this.mInstrid = instrid;
    }

    public void setICPArray(String ICPArray) {
        this.ICPArray = ICPArray;
    }

    public String getInspector() {
        return this.mInspector;
    }

    public void setInspector(String mInspector) {
        this.mInspector = mInspector;
    }
}
