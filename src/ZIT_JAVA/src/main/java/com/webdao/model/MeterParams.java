package com.webdao.model;

/* loaded from: classes.dex */
public class MeterParams
{
    public static final int HIGH_TRIGGER_LEVEL = 10485;
    public static final int LOW_TRIGGER_LEVEL = 1000;
    public static final int MIDDLE_TRIGGER_LEVEL = 3000;
    private String mSensorCode;
    private String mSensorName;
    private String mSensorVersion;
    private float mInterval = 6.4f;
    private float mFrequence = 1.0f;
    private int mGain = 2;
    private int mTriggerLevel = 1000;

    public int getIntervalIndex()
    {
        float[] vArr = {0.0f, 6.4f, 12.8f, 25.6f, 51.2f, 102.4f, 204.8f, 409.6f, 819.2f, 1638.4f};
        for (int i = 0; i < vArr.length; i++)
        {
            if (vArr[i] == this.mInterval)
            {
                return i;
            }
        }
        return 2;
    }

    public float getInterval()
    {
        return this.mInterval;
    }

    public void setInterval(float interval)
    {
        this.mInterval = interval;
    }

    public float getFrequence()
    {
        return this.mFrequence;
    }

    public void setFrequence(float frequence)
    {
        this.mFrequence = frequence;
    }

    public int getGain()
    {
        return this.mGain;
    }

    public void setGain(int gain)
    {
        this.mGain = gain;
    }

    public String getSensorName()
    {
        return this.mSensorName;
    }

    public void setSensorName(String sensorName)
    {
        this.mSensorName = sensorName;
    }

    public String getSensorCode()
    {
        return this.mSensorCode;
    }

    public void setSensorCode(String sensorCode)
    {
        this.mSensorCode = sensorCode;
    }

    public String getSensorVersion()
    {
        return this.mSensorVersion;
    }

    public void setSensorVersion(String sensorVersion)
    {
        this.mSensorVersion = sensorVersion;
    }

    public int getTriggerLevel()
    {
        return this.mTriggerLevel;
    }

    public void setTriggerLevel(int triggerLevel)
    {
        this.mTriggerLevel = triggerLevel;
    }
}
