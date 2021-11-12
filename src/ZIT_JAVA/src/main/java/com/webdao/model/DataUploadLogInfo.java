package com.webdao.model;

import java.util.Date;

/* loaded from: classes.dex */
public class DataUploadLogInfo {
    private String ICP;
    private int mFileCount;
    private String mFileName;
    private String mOperator;
    private String mResult;
    private Date mUploadDateTime;

    public int getFileCount() {
        return this.mFileCount;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public String getICP() {
        return this.ICP;
    }

    public String getOperator() {
        return this.mOperator;
    }

    public Date getUploadDateTime() {
        return this.mUploadDateTime;
    }

    public void setFileCount(int fileCount) {
        this.mFileCount = fileCount;
    }

    public void setUploadResult(String result) {
        this.mResult = result;
    }

    public String getUploadResult() {
        return this.mResult;
    }

    public void setFileName(String fileName) {
        this.mFileName = fileName;
    }

    public void setICP(String ICP) {
        this.ICP = ICP;
    }

    public void setOperator(String operator) {
        this.mOperator = operator;
    }

    public void setUploadDateTime(Date uploadDateTime) {
        this.mUploadDateTime = uploadDateTime;
    }
}
