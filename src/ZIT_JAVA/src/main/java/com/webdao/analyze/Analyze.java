package com.webdao.analyze;

/* loaded from: classes.dex */
public class Analyze
{
    public native int analyzeOneWave(float[] fArr, ANALYZE_PARAMTER analyze_paramter);

    public native float calculateC(int i, int i2, ANALYZE_PARAMTER analyze_paramter);

    public native float calculateF(int i, ANALYZE_PARAMTER analyze_paramter);

    public native float calculateL(int i, int i2, ANALYZE_PARAMTER analyze_paramter);

    public native int calculatePileBottomPoint(int i, ANALYZE_PARAMTER analyze_paramter);

    public native float calculateR(float f, float f2, ANALYZE_PARAMTER analyze_paramter);

    public native float calculateSampleSpace(ANALYZE_PARAMTER analyze_paramter);

    public native float calculateT(int i, int i2, ANALYZE_PARAMTER analyze_paramter);

    public native float calculateV(int i, float[] fArr);

    public native float exchangeOneWaveToFrequencyWave(float[] fArr, float[] fArr2, short s, ANALYZE_PARAMTER analyze_paramter, int[] iArr);

    public native int findFirstWavePosition(float[] fArr, ANALYZE_PARAMTER analyze_paramter);

    public native int findFrequencyPeak(float[] fArr, float f, int i, int[] iArr);

    static
    {
        System.loadLibrary("Analyze");
    }
}
