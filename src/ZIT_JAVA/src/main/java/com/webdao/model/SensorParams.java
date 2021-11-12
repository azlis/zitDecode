package com.webdao.model;

import java.util.Date;

/* loaded from: classes.dex */
public class SensorParams {
    private Date mCalibrationDate;
    private String mCalibrationNo;
    private String mSensorNo;
    private String mSensorModel = "ZBL-P8000";
    private String mSensorVersion = "1.0.0.0";
    private String mType = "2131231241";
    private int mCalCircle = 1;
    private float mSensitivity = 1.0f;

    public String getType() {
        return this.mType;
    }

    public String getCalibrationNo() {
        return this.mCalibrationNo.trim();
    }

    public void setCalibrationNo(String mCalibrationNo) {
        this.mCalibrationNo = mCalibrationNo;
    }

    public Date getCalibrationDate() {
        return this.mCalibrationDate;
    }

    public void setCalibrationDate(Date mCalibrationDate) {
        this.mCalibrationDate = mCalibrationDate;
    }

    public int getCalCircle() {
        return this.mCalCircle;
    }

    public void setCalCircle(int mCalCircle) {
        this.mCalCircle = mCalCircle;
    }

    public String getSensorNo() {
        return this.mSensorNo.trim();
    }

    public void setSensorNo(String mSensorNo) {
        this.mSensorNo = mSensorNo;
    }

    public String getSensorModel() {
        return this.mSensorModel;
    }

    public void setSensorModel(String mSensorModel) {
        this.mSensorModel = mSensorModel;
    }

    public String getSensorVersion() {
        return this.mSensorVersion;
    }

    public void setSensorVersion(String sensorVersion) {
        this.mSensorVersion = sensorVersion;
    }

    public float getSensitivity() {
        return this.mSensitivity;
    }

    public void setSensitivity(float sensitivity) {
        this.mSensitivity = sensitivity;
    }
}
