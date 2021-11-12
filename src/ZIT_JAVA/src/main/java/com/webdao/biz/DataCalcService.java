package com.webdao.biz;

import com.webdao.analyze.ANALYZE_PARAMTER;
import com.webdao.analyze.Analyze;
import com.webdao.model.AnalyseParams;
import com.webdao.model.CPileData;
import com.webdao.model.SamplingParams;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DataCalcService
{
    Analyze mAna = new Analyze();

    public CPileData analyzeOneWave(CPileData orginData, AnalyseParams analyseParams, SamplingParams samplingParams)
    {
        ANALYZE_PARAMTER paramter = getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen);
        orginData.mPileBottom = (float) ((2000.0d * ((double) analyseParams.mLength)) / ((double) analyseParams.mVelocity));
        CPileData result = orginData.clone();
        if (result.mSignalData != null)
        {
            result.topIndex = this.mAna.analyzeOneWave(result.mSignalData, paramter);
            result.botIndex = this.mAna.calculatePileBottomPoint(result.topIndex, paramter) - result.topIndex;
        }
        return result;
    }

    public int findFirstWavePosition(CPileData orginData, AnalyseParams analyseParams, SamplingParams samplingParams)
    {
        ANALYZE_PARAMTER paramter = getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen);
        if (orginData.mSignalData != null)
        {
            return this.mAna.findFirstWavePosition(orginData.mSignalData, paramter);
        }
        return 0;
    }

    public int findFrequencyPeak(CPileData pileData, SamplingParams samplingParams, AnalyseParams analyseParams)
    {
        int[] frequencyPeakPos = new int[5];
        ANALYZE_PARAMTER paramter = getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen);
        int result = this.mAna.findFrequencyPeak(pileData.mFreqResultData, pileData.mMaxAmp, pileData.mSaveLen, frequencyPeakPos);
        if (result > 5)
        {
            result = 5;
        }
        pileData.mFreqPeak = new float[result];
        for (int i = 0; i < result; i++)
        {
            pileData.mFreqPeak[i] = this.mAna.calculateF(frequencyPeakPos[i], paramter);
        }
        int[] posTemp = new int[result];
        for (int i2 = 0; i2 < result; i2++)
        {
            posTemp[i2] = frequencyPeakPos[i2];
        }
        pileData.mResolution = 1000000.0f / (((float) samplingParams.getLength()) * samplingParams.getInterval());
        pileData.mFreqPeakPos = posTemp;
        pileData.mCurFreq = (short) result;
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float exchangeOneWaveToFrequencyWave(CPileData pileData, AnalyseParams analyseParams, SamplingParams samplingParams)
    {
        int i = pileData.mFreqResultLen;
        float[] fResult = new float[i];
        float[] fResultPart = new float[i / 2];
        int[] maxPosi = new int[1];
        float result = 0.0f;
        ANALYZE_PARAMTER paramter = getAnalyzeParamter(analyseParams, samplingParams, i);
        if (pileData.mSignalData != null)
        {
            result = this.mAna.exchangeOneWaveToFrequencyWave(pileData.mSignalData, fResult, (short) i, paramter, maxPosi);
        }
        System.arraycopy(fResult, 0, fResultPart, 0, i / 2);
        pileData.mFreqResultData = fResultPart;
        pileData.mMaxAmp = result;
        pileData.mMainFreq = (float) maxPosi[0];
        pileData.mResolution = 1000000.0f / (((float) samplingParams.getLength()) * samplingParams.getInterval());
        return result;
    }

    private float getEndValue(float[] datas)
    {
        if (datas == null)
        {
            return 0.0f;
        }
        float[] m = {0.0f, 0.0f};
        int length = datas.length;
        for (int i = 0; i < length; i++)
        {
            float f = datas[i];
            m[0] = m[0] < f ? f : m[0];
            if (m[1] <= f)
            {
                f = m[1];
            }
            m[1] = f;
        }
        return Math.abs(m[0]) > Math.abs(m[1]) ? Math.abs(m[0]) : Math.abs(m[1]);
    }

    public float calcDataPrecent(float[] datas)
    {
        return (getEndValue(datas) * 100.0f) / 6553.6f;
    }

    public int calculatePileBottomPoint(int topPoint, AnalyseParams analyseParams, SamplingParams samplingParams)
    {
        return this.mAna.calculatePileBottomPoint(topPoint, getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    public float calculateSampleSpace(AnalyseParams analyseParams, SamplingParams samplingParams)
    {
        return this.mAna.calculateSampleSpace(getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    public float calculateT(int posi, int topPosi, SamplingParams samplingParams, AnalyseParams analyseParams)
    {
        return this.mAna.calculateT(posi, topPosi, getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    public float calculateL(int posi, int topPosi, SamplingParams samplingParams, AnalyseParams analyseParams)
    {
        return this.mAna.calculateL(posi, topPosi, getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    public float calculateC(int posi, int topPosi, SamplingParams samplingParams, AnalyseParams analyseParams)
    {
        return this.mAna.calculateC(posi, topPosi, getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    public float calculateV(int posi, float[] data)
    {
        return this.mAna.calculateV(posi, data);
    }

    public float calculateR(float cursorV, float sensitivity, SamplingParams samplingParams, AnalyseParams analyseParams)
    {
        return this.mAna.calculateR(cursorV, sensitivity, getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    public float calculateF(int posi, SamplingParams samplingParams, AnalyseParams analyseParams)
    {
        return this.mAna.calculateF(posi, getAnalyzeParamter(analyseParams, samplingParams, analyseParams.mFreqResultLen));
    }

    private ANALYZE_PARAMTER getAnalyzeParamter(AnalyseParams analyseParams, SamplingParams samplingParams, int frequencyLength)
    {
        boolean z = true;
        ANALYZE_PARAMTER paramter = new ANALYZE_PARAMTER();
        paramter.uMagnificationType = analyseParams.getEnlargeType();
        paramter.fMagnification = analyseParams.getEnlargeTimes();
        paramter.fMagnificationStart = analyseParams.getEnlargeStart();
        paramter.uFilterType = analyseParams.getFilterType();
        paramter.fEndFrequencyHigh = analyseParams.getHighFrequency();
        paramter.fEndFrequencyLow = analyseParams.getLowFrequency();
        paramter.fWaveletFactor = analyseParams.getWavelet();
        paramter.fWaveletRotate = (float) analyseParams.getWaveRotate();
        paramter.nSmoothPoints = analyseParams.getSmoothPoint();
        if (analyseParams.isIntegrage() != 1)
        {
            z = false;
        }
        paramter.bIntegral = z;
        paramter.nSampleLength = samplingParams.getLength();
        paramter.dSampleSpace = (double) samplingParams.getInterval();
        paramter.nDelayPoint = samplingParams.getDelayPoint();
        paramter.nWaveSpeed = analyseParams.getEstimateVelocity();
        paramter.fPileLength = analyseParams.getEstimateLength();
        paramter.nFreqLen = frequencyLength;
        return paramter;
    }

    public CPileData average(CPileData[] mOriginalDataCache, int currentPosi, int hammersPerLine, int saveLen)
    {
        int start = currentPosi * hammersPerLine;
        int end = start + hammersPerLine;
        List<CPileData> temp = new ArrayList<>();
        int i = start;
        while (i < end && mOriginalDataCache[i] != null)
        {
            if (mOriginalDataCache[i].mDelFlag != 1)
            {
                temp.add(mOriginalDataCache[i]);
            }
            i++;
        }
        CPileData ava = new CPileData();
        ava.mSignalData = new float[saveLen];
        int count = temp.size();
        if (count == 1)
        {
            return temp.get(0);
        }
        for (int j = 0; j < saveLen; j++)
        {
            for (CPileData data : temp)
            {
                float[] fArr = ava.mSignalData;
                fArr[j] = fArr[j] + data.mSignalData[j];
            }
            ava.mSignalData[j] = ava.mSignalData[j] / ((float) count);
        }
        return ava;
    }

    public float calculateInternal(float length, float speed, int count)
    {
        return (3000000.0f * length) / (((float) count) * speed);
    }
}
