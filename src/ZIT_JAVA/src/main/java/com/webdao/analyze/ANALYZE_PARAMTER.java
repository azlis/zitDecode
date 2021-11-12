package com.webdao.analyze;

import com.webdao.model.SamplingParams;

/* loaded from: classes.dex */
public class ANALYZE_PARAMTER
{
    public int nFreqLen;
    public int uMagnificationType = 0;
    public float fMagnification = 1.0f;
    public float fMagnificationStart = 0.0f;
    public int uFilterType = 0;
    public float fEndFrequencyHigh = 1000.0f;
    public float fEndFrequencyLow = 100.0f;
    public float fWaveletFactor = 0.0f;
    public float fWaveletRotate = 0.0f;
    public int nSmoothPoints = 0;
    public boolean bIntegral = true;
    public boolean bRemoveDc = true;
    public int nSampleLength = SamplingParams.POINT_1024_SAMPLING;
    public double dSampleSpace = 6.4d;
    public int nDelayPoint = 0;
    public int nWaveSpeed = 3600;
    public float fPileLength = 10.0f;
}
