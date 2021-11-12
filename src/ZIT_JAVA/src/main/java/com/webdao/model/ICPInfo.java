package com.webdao.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class ICPInfo {
    public static final int LINK_BLUETOOTH = 1;
    public static final int LINK_WIFI = 0;
    private String createTime;
    private String mContact;
    private String mContactTele;
    private String mContractCount;
    private Date mCreatTime;
    private String mCustomIcpCode;
    private String mDataFileName;
    private String mDataPath;
    private String mFinishCount;
    private String mICPCode;
    private String[] mICPList;
    private String mInstructorID;
    private String mSensorCode;
    private String mSuffix;
    private String mTestBase;
    private String mTestMethod;
    private String mTestMethodJson;
    private String mTestPosition;
    private String mTestType;
    private HashMap<String, List<ICPInfo>> mWorkList;
    private String mWorkName;
    public boolean isCustomerICP = false;
    public int wifiIsInBuilt = 1;
    private int mLinkMethod = 0;

    public String getContactTele() {
        return this.mContactTele;
    }

    public void setContactTele(String tele) {
        this.mContactTele = tele;
    }

    public String getContact() {
        return this.mContact;
    }

    public void setContact(String contact) {
        this.mContact = contact;
    }

    public String getTestType() {
        return this.mTestType;
    }

    public void setTestType(String testType) {
        this.mTestType = testType;
    }

    public String getTestMethodJson() {
        return this.mTestMethodJson;
    }

    public String getCustomIcpCode() {
        return this.mCustomIcpCode;
    }

    public void setCustomIcpCode(String customIcpCode) {
        this.mCustomIcpCode = customIcpCode;
    }

    public void setCreatTime(Date creatTime) {
        this.mCreatTime = creatTime;
    }

    public String getCreateTime() {
        return new SimpleDateFormat("MM-dd hh:mm").format(this.mCreatTime);
    }

    public String getTestBase() {
        return this.mTestBase;
    }

    public void setTestBase(String testBase) {
        this.mTestBase = testBase;
    }

    public String getContractCount() {
        return this.mContractCount;
    }

    public void setContractCount(String contractCount) {
        this.mContractCount = contractCount;
    }

    public void setTestMethodJson(String testMethod) {
        this.mTestMethodJson = testMethod;
    }

    public String getInstructorID() {
        return this.mInstructorID;
    }

    public void setInstructorID(String instructorID) {
        this.mInstructorID = instructorID;
    }

    public String getICPCode() {
        return this.mICPCode;
    }

    public void setICPCode(String icpCode) {
        this.mICPCode = icpCode;
    }

    public String getWorkName() {
        return this.mWorkName;
    }

    public void setWorkName(String workName) {
        this.mWorkName = workName;
    }

    public HashMap<String, List<ICPInfo>> getWorkList() {
        return this.mWorkList;
    }

    public String[] getWorkArray() {
        return (String[]) this.mWorkList.keySet().toArray(new String[this.mWorkList.size()]);
    }

    public void setWorkList(HashMap<String, List<ICPInfo>> workList) {
        this.mWorkList = workList;
    }

    public String[] getICPList() {
        return this.mICPList;
    }

    public void setICPList(String[] ICPList) {
        this.mICPList = ICPList;
    }

    public int getLinkMethod() {
        return this.mLinkMethod;
    }

    public void setLinkMethod(int linkMethod) {
        this.mLinkMethod = linkMethod;
    }

    public String getDataFileName() {
        return this.mDataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.mDataFileName = dataFileName;
    }

    public String getDataPath() {
        return this.mDataPath;
    }

    public void setDataPath(String dataPath) {
        this.mDataPath = dataPath;
    }

    public String getTestMethod() {
        return this.mTestMethod;
    }

    public void setTestMethod(String testMethod) {
        this.mTestMethod = testMethod;
    }

    public String getSensorCode() {
        return this.mSensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.mSensorCode = sensorCode;
    }

    public String getFinishCount() {
        return this.mFinishCount;
    }

    public void setFinishCount(String finishCount) {
        this.mFinishCount = finishCount;
    }

    public String getTestPosition() {
        return this.mTestPosition;
    }

    public void setTestPosition(String testPosition) {
        this.mTestPosition = testPosition;
    }

    public String getSuffix() {
        return this.mSuffix;
    }

    public void setSuffix(String mSuffix) {
        this.mSuffix = mSuffix;
    }
}
