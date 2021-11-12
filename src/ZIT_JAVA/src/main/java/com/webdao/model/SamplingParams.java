package com.webdao.model;

/* loaded from: classes.dex */
public class SamplingParams
{
    public static final int ALL_ACCEPT = 2;
    public static final int AUTO_ACCEPT = 0;
    public static final int CONTINUITY_STYLE = 1;
    public static final int HIGH_TRIGGER_LEVEL = 10485;
    public static final int HUMAN_ACCEPT = 1;
    public static final int LOW_TRIGGER_LEVEL = 1000;
    public static final int MIDDLE_TRIGGER_LEVEL = 3000;
    public static final int POINT_1024_SAMPLING = 1024;
    public static final int POINT_2048_SAMPLING = 2048;
    public static final int SINGLE_STYLE = 0;
    public static final int TEST_TRIGGER_LEVEL = 50;
    public static int SAMPLING_REPEATE_COUNT = 0;
    public static int MAX_REPEATE_COUNT = 3;
    final float[] gains = {0.25f, 0.5f, 1.0f, 2.0f, 4.0f, 8.0f, 16.0f, 32.0f, 64.0f};
    private int mAcceptModel = 2;
    private int mCount = 6;
    private int mHammersPerLine = 1;
    private int mGain = 5;
    private float mInterval = 6.4f;
    private int mLength = POINT_1024_SAMPLING;
    private int mStyle = 1;
    private int mTriggerLevel = 3000;
    private int mDelayPoint = -100;

    public int getDelayPoint()
    {
        return this.mDelayPoint;
    }

    public void setDelayPoint(int delayPoint)
    {
        this.mDelayPoint = delayPoint;
    }

    public int getAcceptModel()
    {
        return this.mAcceptModel;
    }

    public int getHammersPerLine()
    {
        return this.mHammersPerLine;
    }

    public float getInterval()
    {
        return this.mInterval;
    }

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

    public int getCount()
    {
        return this.mCount;
    }

    public int getGain()
    {
        return this.mGain;
    }

    public int getGainTimes()
    {
        return (int) Math.floor((double) this.gains[this.mGain]);
    }

    public void setGain(int gain)
    {
        this.mGain = gain;
    }

    public void fromGainTimes(int gainTimes)
    {
        for (int i = 0; i < this.gains.length; i++)
        {
            if (this.gains[i] == ((float) gainTimes))
            {
                this.mGain = i;
                return;
            }
        }
    }

    public int getTriggerLevel()
    {
        return this.mTriggerLevel;
    }

    public int getTriggerLevelIndex()
    {
        switch (this.mTriggerLevel)
        {
            case 1000:
            default:
                return 0;
            case 3000:
                return 1;
            case 10485:
                return 2;
        }
    }

    public int getStyle()
    {
        return this.mStyle;
    }

    public int getLength()
    {
        return this.mLength;
    }

    public void setAcceptModel(int acceptModel)
    {
        this.mAcceptModel = acceptModel;
    }

    public void setCountPerHammer(int countPerHammer)
    {
        this.mHammersPerLine = countPerHammer;
    }

    public void setCount(int count)
    {
        this.mCount = count;
    }

    public void setInterval(float interval)
    {
        this.mInterval = interval;
    }

    public void setLength(int len)
    {
        this.mLength = len;
    }

    public void setStyle(int style)
    {
        this.mStyle = style;
    }

    public void setTriggerLevel(int triggerLevel)
    {
        this.mTriggerLevel = triggerLevel;
    }

    public void setTriggerLevelIndex(int triggerLevelIndex)
    {
        switch (triggerLevelIndex)
        {
            case 0:
                this.mTriggerLevel = 1000;
                return;
            case 1:
                this.mTriggerLevel = 3000;
                return;
            case 2:
                this.mTriggerLevel = 10485;
                return;
            default:
                return;
        }
    }
}
