package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CPileData implements CustomSerializable {
    public short mChnGain;
    public short mCurFreq;
    public short mDelFlag;
    public float mMainFreq;
    public float mMaxAmp;
    public float mMaxDataPercent;
    public float mPileBottom;
    public float mPileTop;
    public float mResolution;
    public short mSaveLen;
    public float[] mSignalData;
    private boolean isOrignalData = false;
    public int topIndex = 0;
    public int botIndex = 0;
    public short mCursorX = 0;
    public short mDispNap = 1;
    public short msAmplify = 0;
    public float mFAmplify = 1.0f;
    public float mfDispNap = 1.0f;
    public float mBase = 0.0f;
    public char[] mReserve = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public short mFreqResultLen = 1024;
    public float[] mFreqPeak = new float[5];
    public int[] mFreqPeakPos = new int[5];
    public float[] mFreqResultData = new float[0];
    public short msDispNap1 = 1;
    public short mFlag = 0;

    public void setOrignalData(boolean b) {
        this.isOrignalData = b;
    }

    public CPileData clone() {
        CPileData d = new CPileData();
        d.mSaveLen = this.mSaveLen;
        d.mPileTop = this.mPileTop;
        d.mCursorX = this.mCursorX;
        d.mDispNap = this.mDispNap;
        d.mPileBottom = this.mPileBottom;
        d.mDelFlag = this.mDelFlag;
        d.msAmplify = this.msAmplify;
        d.mFAmplify = this.mFAmplify;
        d.mfDispNap = this.mfDispNap;
        d.mBase = this.mBase;
        d.mChnGain = this.mChnGain;
        d.mMaxDataPercent = this.mMaxDataPercent;
        d.mFreqResultLen = this.mFreqResultLen;
        d.mCurFreq = this.mCurFreq;
        d.mResolution = this.mResolution;
        d.mMainFreq = this.mMainFreq;
        d.mMaxAmp = this.mMaxAmp;
        int l = this.mFreqResultData.length;
        d.mFreqResultData = new float[l];
        for (int i = 0; i < l; i++) {
            d.mFreqResultData[i] = this.mFreqResultData[i];
        }
        d.msDispNap1 = this.msDispNap1;
        d.mFlag = this.mFlag;
        float[] data = new float[this.mSignalData.length];
        for (int i2 = 0; i2 < this.mSignalData.length; i2++) {
            data[i2] = this.mSignalData[i2];
        }
        d.mSignalData = data;
        float[] d2 = new float[this.mFreqPeak.length];
        for (int i3 = 0; i3 < this.mFreqPeak.length; i3++) {
            d2[i3] = this.mFreqPeak[i3];
        }
        d.mFreqPeak = d2;
        return d;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int writeToStream(OutputStream os) throws IOException {
        byte[] bs = DataTransfer.shortToBytes(this.mSaveLen);
        os.write(bs, 0, bs.length);
        int len = 0 + bs.length;
        byte[] bs2 = DataTransfer.floatToBytes(this.mPileTop);
        os.write(bs2, 0, bs2.length);
        int len2 = len + bs2.length;
        byte[] bs3 = DataTransfer.shortToBytes(this.mCursorX);
        os.write(bs3, 0, bs3.length);
        int len3 = len2 + bs3.length;
        byte[] bs4 = DataTransfer.shortToBytes(this.mDispNap);
        os.write(bs4, 0, bs4.length);
        int len4 = len3 + bs4.length;
        byte[] bs5 = DataTransfer.floatToBytes(this.mPileBottom);
        os.write(bs5, 0, bs5.length);
        int len5 = len4 + bs5.length;
        byte[] bs6 = DataTransfer.shortToBytes(this.mDelFlag);
        os.write(bs6, 0, bs6.length);
        int len6 = len5 + bs6.length;
        byte[] bs7 = DataTransfer.shortToBytes(this.msAmplify);
        os.write(bs7, 0, bs7.length);
        int len7 = len6 + bs7.length;
        byte[] bs8 = DataTransfer.floatToBytes(this.mFAmplify);
        os.write(bs8, 0, bs8.length);
        int len8 = len7 + bs8.length;
        byte[] bs9 = DataTransfer.floatToBytes(this.mfDispNap);
        os.write(bs9, 0, bs9.length);
        int len9 = len8 + bs9.length;
        byte[] bs10 = DataTransfer.floatToBytes(this.mBase);
        os.write(bs10, 0, bs10.length);
        int len10 = len9 + bs10.length;
        byte[] bs11 = DataTransfer.shortToBytes(this.mChnGain);
        os.write(bs11, 0, bs11.length);
        int len11 = len10 + bs11.length;
        byte[] bs12 = DataTransfer.floatToBytes(this.mMaxDataPercent);
        os.write(bs12, 0, bs12.length);
        int len12 = len11 + bs12.length;
        byte[] bs13 = DataTransfer.charToBytes(this.mReserve);
        os.write(bs13, 0, bs13.length);
        int len13 = len12 + bs13.length;
        byte[] bs14 = DataTransfer.shortToBytes(this.mFreqResultLen);
        os.write(bs14, 0, bs14.length);
        int len14 = len13 + bs14.length;
        byte[] bs15 = DataTransfer.floatToBytes(this.mSignalData);
        os.write(bs15, 0, bs15.length);
        int len15 = len14 + bs15.length;
        if (this.isOrignalData) {
            return len15;
        }
        byte[] bs16 = DataTransfer.shortToBytes(this.mCurFreq);
        os.write(bs16, 0, bs16.length);
        int len16 = len15 + bs16.length;
        if (this.mFreqPeak.length < 5) {
            float[] fArr = new float[5];
            for (int i = 0; i < 5; i++) {
                if (i < this.mFreqPeak.length) {
                    fArr[i] = this.mFreqPeak[i];
                }
            }
            this.mFreqPeak = fArr;
        }
        byte[] bs17 = DataTransfer.floatToBytes(this.mFreqPeak);
        os.write(bs17, 0, bs17.length);
        int len17 = len16 + bs17.length;
        byte[] bs18 = DataTransfer.floatToBytes(this.mResolution);
        os.write(bs18, 0, bs18.length);
        int len18 = len17 + bs18.length;
        byte[] bs19 = DataTransfer.floatToBytes(this.mMainFreq);
        os.write(bs19, 0, bs19.length);
        int len19 = len18 + bs19.length;
        byte[] bs20 = DataTransfer.floatToBytes(this.mMaxAmp);
        os.write(bs20, 0, bs20.length);
        int len20 = len19 + bs20.length;
        byte[] bs21 = DataTransfer.floatToBytes(this.mFreqResultData);
        os.write(bs21, 0, bs21.length);
        int len21 = len20 + bs21.length;
        byte[] bs22 = DataTransfer.shortToBytes(this.msDispNap1);
        os.write(bs22, 0, bs22.length);
        int len22 = len21 + bs22.length;
        byte[] bs23 = DataTransfer.shortToBytes(this.mFlag);
        os.write(bs23, 0, bs23.length);
        return len22 + bs23.length;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int initFromStream(InputStream is) {
        short s = 0;
        int readLen = 0;
        try {
            byte[] bs = new byte[2];
            int readLen2 = 0 + is.read(bs, 0, 2);
            this.mSaveLen = DataTransfer.byteToShort(bs);
            this.mSaveLen = this.mSaveLen < 0 ? 0 : this.mSaveLen;
            byte[] bs2 = new byte[4];
            int readLen3 = readLen2 + is.read(bs2, 0, 4);
            this.mPileTop = DataTransfer.byteToFloat(bs2);
            byte[] bs3 = new byte[2];
            int readLen4 = readLen3 + is.read(bs3, 0, 2);
            this.mCursorX = DataTransfer.byteToShort(bs3);
            byte[] bs4 = new byte[2];
            int readLen5 = readLen4 + is.read(bs4, 0, 2);
            this.mDispNap = DataTransfer.byteToShort(bs4);
            byte[] bs5 = new byte[4];
            int readLen6 = readLen5 + is.read(bs5, 0, 4);
            this.mPileBottom = DataTransfer.byteToFloat(bs5);
            byte[] bs6 = new byte[2];
            int readLen7 = readLen6 + is.read(bs6, 0, 2);
            this.mDelFlag = DataTransfer.byteToShort(bs6);
            byte[] bs7 = new byte[2];
            int readLen8 = readLen7 + is.read(bs7, 0, 2);
            this.msAmplify = DataTransfer.byteToShort(bs7);
            byte[] bs8 = new byte[4];
            int readLen9 = readLen8 + is.read(bs8, 0, 4);
            this.mFAmplify = DataTransfer.byteToFloat(bs8);
            byte[] bs9 = new byte[4];
            int readLen10 = readLen9 + is.read(bs9, 0, 4);
            this.mfDispNap = DataTransfer.byteToFloat(bs9);
            byte[] bs10 = new byte[4];
            int readLen11 = readLen10 + is.read(bs10, 0, 4);
            this.mBase = DataTransfer.byteToFloat(bs10);
            byte[] bs11 = new byte[2];
            int readLen12 = readLen11 + is.read(bs11, 0, 2);
            this.mChnGain = DataTransfer.byteToShort(bs11);
            byte[] bs12 = new byte[4];
            int readLen13 = readLen12 + is.read(bs12, 0, 4);
            this.mMaxDataPercent = DataTransfer.byteToFloat(bs12);
            byte[] bs13 = new byte[34];
            int readLen14 = readLen13 + is.read(bs13, 0, 34);
            this.mReserve = DataTransfer.byteToCharArray(bs13);
            byte[] bs14 = new byte[2];
            int readLen15 = readLen14 + is.read(bs14, 0, 2);
            this.mFreqResultLen = DataTransfer.byteToShort(bs14);
            if (this.mFreqResultLen >= 0) {
                s = this.mFreqResultLen;
            }
            this.mFreqResultLen = s;
            byte[] bs15 = new byte[this.mSaveLen * 4];
            readLen = readLen15 + is.read(bs15, 0, this.mSaveLen * 4);
            this.mSignalData = DataTransfer.byteToFloatArray(bs15);
            if (!this.isOrignalData) {
                byte[] bs16 = new byte[2];
                int readLen16 = readLen + is.read(bs16, 0, 2);
                this.mCurFreq = DataTransfer.byteToShort(bs16);
                byte[] bs17 = new byte[20];
                int readLen17 = readLen16 + is.read(bs17, 0, 20);
                this.mFreqPeak = DataTransfer.byteToFloatArray(bs17);
                byte[] bs18 = new byte[4];
                int readLen18 = readLen17 + is.read(bs18, 0, 4);
                this.mResolution = DataTransfer.byteToFloat(bs18);
                if (this.mResolution > 0.0f) {
                    for (int i = 0; i < 5; i++) {
                        this.mFreqPeakPos[i] = (int) (this.mFreqPeak[i] / this.mResolution);
                    }
                }
                byte[] bs19 = new byte[4];
                int readLen19 = readLen18 + is.read(bs19, 0, 4);
                this.mMainFreq = DataTransfer.byteToFloat(bs19);
                byte[] bs20 = new byte[4];
                int readLen20 = readLen19 + is.read(bs20, 0, 4);
                this.mMaxAmp = DataTransfer.byteToFloat(bs20);
                byte[] bs21 = new byte[this.mFreqResultLen * 2];
                int readLen21 = readLen20 + is.read(bs21, 0, this.mFreqResultLen * 2);
                this.mFreqResultData = DataTransfer.byteToFloatArray(bs21);
                byte[] bs22 = new byte[2];
                int readLen22 = readLen21 + is.read(bs22, 0, 2);
                this.msDispNap1 = DataTransfer.byteToShort(bs22);
                byte[] bs23 = new byte[2];
                readLen = readLen22 + is.read(bs23, 0, 2);
                this.mFlag = DataTransfer.byteToShort(bs23);
            }
            return readLen;
        } catch (IOException e) {
            e.printStackTrace();
            return readLen;
        }
    }

    public void setFreqNum(int freqNum) {
        this.mCurFreq = (short) freqNum;
    }

    public int getFreqNum() {
        return this.mCurFreq;
    }

    public int setFreqPeak(float pos, float peak) {
        if (this.mCurFreq >= 5) {
            return -1;
        }
        for (int i : this.mFreqPeakPos) {
            if (((float) i) == ((float) ((int) pos))) {
                return -2;
            }
        }
        int count = this.mCurFreq + 1;
        int[] tempPos = new int[count];
        float[] tempFreq = new float[count];
        float maxValue = 0.0f;
        for (int i2 = 0; i2 < count; i2++) {
            if (i2 >= this.mCurFreq || i2 >= count) {
                tempPos[i2] = (int) pos;
            } else {
                tempPos[i2] = this.mFreqPeakPos[i2];
            }
            if (i2 >= this.mCurFreq || i2 >= count) {
                tempFreq[i2] = peak;
            } else {
                tempFreq[i2] = this.mFreqPeak[i2];
            }
            if (maxValue < tempFreq[i2]) {
                maxValue = tempFreq[i2];
            }
        }
        Arrays.sort(tempFreq);
        Arrays.sort(tempPos);
        this.mFreqPeakPos = new int[5];
        this.mFreqPeak = new float[5];
        for (int i3 = 0; i3 < 5; i3++) {
            if (i3 < count) {
                this.mFreqPeak[i3] = tempFreq[i3];
                this.mFreqPeakPos[i3] = tempPos[i3];
            }
        }
        this.mMainFreq = maxValue;
        setFreqNum(count);
        return 1;
    }

    public int removePeak(int cursor) {
        if (cursor <= 0) {
            return 0;
        }
        int[] dis = new int[this.mFreqPeakPos.length];
        int minDis = -1;
        for (int i = 0; i < this.mFreqPeakPos.length; i++) {
            if (this.mFreqPeakPos[i] > 0) {
                dis[i] = Math.abs(this.mFreqPeakPos[i] - cursor);
                if (minDis == -1) {
                    minDis = dis[i];
                }
                if (dis[i] < minDis) {
                    minDis = dis[i];
                }
            }
        }
        if (minDis > 4) {
            return 2;
        }
        int minIndex = 0;
        for (int i2 = 0; i2 < dis.length; i2++) {
            if (dis[i2] == minDis) {
                minIndex = i2;
            }
        }
        if (minIndex < this.mFreqPeakPos.length) {
            this.mFreqPeakPos[minIndex] = 0;
        }
        if (minIndex < this.mFreqPeak.length) {
            this.mFreqPeak[minIndex] = 0.0f;
        }
        HashMap<Integer, Float> map = new HashMap<>();
        float maxValue = 0.0f;
        Arrays.sort(this.mFreqPeak);
        Arrays.sort(this.mFreqPeakPos);
        int i3 = 0;
        while (i3 < this.mFreqPeakPos.length && i3 < this.mFreqPeak.length) {
            if (this.mFreqPeakPos[i3] > 0) {
                map.put(Integer.valueOf(this.mFreqPeakPos[i3]), Float.valueOf(this.mFreqPeak[i3]));
                if (maxValue < this.mFreqPeak[i3]) {
                    maxValue = this.mFreqPeak[i3];
                }
            }
            i3++;
        }
        this.mFreqPeakPos = new int[5];
        this.mFreqPeak = new float[5];
        int i4 = 0;
        for (Integer pos : map.keySet()) {
            this.mFreqPeakPos[i4] = pos.intValue();
            this.mFreqPeak[i4] = map.get(pos).floatValue();
            i4++;
        }
        setFreqNum(map.size());
        this.mMainFreq = maxValue;
        return 1;
    }
}
