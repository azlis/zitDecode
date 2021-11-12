package com.webdao.model;

/* loaded from: classes.dex */
public class ShowParams
{
    public static final int X_AXIS_LENGTH = 1;
    public static final int X_AXIS_RATE = 3;
    public static final int X_AXIS_TIME = 0;
    private int mPreCOuntPerScreen = 3;
    private boolean isAnalysisStatus = false;
    private int mCountPerScreen = 3;
    private int mLineWidth = 1;
    private int mXAxisType = 1;
    private float mXZoomScale = 1.0f;

    public int getCountPerScreenByStatus()
    {
        if (this.isAnalysisStatus)
        {
            return 1;
        }
        return this.mCountPerScreen;
    }

    public int getCountPerScreen()
    {
        return this.mCountPerScreen;
    }

    public int getLineWidth()
    {
        return this.mLineWidth;
    }

    public int getXAxisType()
    {
        return this.mXAxisType;
    }

    public void setCountPerScreen(int count)
    {
        this.mCountPerScreen = count;
    }

    public void setLineWidth(int lineWidth)
    {
        this.mLineWidth = lineWidth;
    }

    public void setXAxisType(int xAxisType)
    {
        this.mXAxisType = xAxisType;
    }

    public float getXZoomScale()
    {
        return this.mXZoomScale;
    }

    public void setXZoomScale(float mXZoomScale)
    {
        this.mXZoomScale = mXZoomScale;
    }

    public void setAnalysisStatus(boolean isAnalysis)
    {
        this.isAnalysisStatus = isAnalysis;
    }
}
