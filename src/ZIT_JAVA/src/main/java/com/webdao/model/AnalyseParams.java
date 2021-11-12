package com.webdao.model;

public class AnalyseParams {
    public float mLength = 10.0f;
    public int mVelocity = 3600;
    public int mFreqResultLen = SamplingParams.POINT_1024_SAMPLING;
    private float mEnlargeStart = 0.0f;
    private float mEnlargeTimes = 1.0f;
    private int mEnlargeType = 0;
    public float mWavelet = 0.0f;
    private int mWaveRotate = 0;
    private int mFilterType = 0;
    private float mHighFrequency = 0.0f;
    public int mIntegrage = 1;
    private float mLowFrequency = 0.0f;
    private int mSmoothPoint = 0;

    public float getEstimateLength() {
        return this.mLength;
    }

    public int getEstimateVelocity() {
        return this.mVelocity;
    }

    public float getWavelet() {
        return this.mWavelet;
    }

    public int isIntegrage() {
        return this.mIntegrage;
    }

    public void setEstimateLength(float length) {
        this.mLength = length;
    }

    public void setEstimateVelocity(int velocity) {
        this.mVelocity = velocity;
    }

    public void setIntegrage(int integrage) {
        this.mIntegrage = integrage;
    }

    public void setWavelet(float wavelet) {
        this.mWavelet = wavelet;
    }

    public int getSmoothPoint() {
        return this.mSmoothPoint;
    }

    public void setSmoothPoint(int smoothPoint) {
        this.mSmoothPoint = smoothPoint;
    }

    public float getLowFrequency() {
        return this.mLowFrequency;
    }

    public void setLowFrequency(float lowFrequency) {
        this.mLowFrequency = lowFrequency;
    }

    public float getEnlargeStart() {
        return this.mEnlargeStart;
    }

    public void setEnlargeStart(float enlargeStart) {
        this.mEnlargeStart = enlargeStart;
    }

    public float getEnlargeTimes() {
        return this.mEnlargeTimes;
    }

    public void setEnlargeTimes(float enlargeTimes) {
        this.mEnlargeTimes = enlargeTimes;
    }

    public int getEnlargeType() {
        return this.mEnlargeType;
    }

    public void setEnlargeType(int enlargeType) {
        this.mEnlargeType = enlargeType;
    }

    public int getFilterType() {
        return this.mFilterType;
    }

    public void setFilterType(int filterType) {
        this.mFilterType = filterType;
    }

    public float getHighFrequency() {
        return this.mHighFrequency;
    }

    public void setHighFrequency(float highFrequency) {
        this.mHighFrequency = highFrequency;
    }

    public int getWaveRotate() {
        return this.mWaveRotate;
    }

    public void setWaveRotate(int waveRotate) {
        this.mWaveRotate = waveRotate;
    }

    public void reset() {
        this.mEnlargeStart = 0.0f;
        this.mEnlargeTimes = 1.0f;
        this.mEnlargeType = 0;
        this.mWavelet = 0.0f;
        this.mWaveRotate = 0;
        this.mFilterType = -1;
        this.mHighFrequency = 0.0f;
        this.mIntegrage = 1;
        this.mLowFrequency = 0.0f;
        this.mSmoothPoint = 0;
        this.mIntegrage = 0;
    }

}
