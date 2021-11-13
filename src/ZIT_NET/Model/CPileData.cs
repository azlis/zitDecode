using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Frame;

namespace ZIT_NET.Model
{
    public class CPileData : ICustomSerializable
    {
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
        private bool isOrignalData = false;
        public int topIndex = 0;
        public int botIndex = 0;
        public short mCursorX = 0;
        public short mDispNap = 1;
        public short msAmplify = 0;
        public float mFAmplify = 1.0f;
        public float mfDispNap = 1.0f;
        public float mBase = 0.0f;
        public char[] mReserve = { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
        public short mFreqResultLen = 1024;
        public float[] mFreqPeak = new float[5];
        public int[] mFreqPeakPos = new int[5];
        public float[] mFreqResultData = new float[0];
        public short msDispNap1 = 1;
        public short mFlag = 0;

        public void SetOrignalData(bool b)
        {
            isOrignalData = b;
        }

        public CPileData Clone()
        {
            CPileData d = new CPileData();
            d.mSaveLen = mSaveLen;
            d.mPileTop = mPileTop;
            d.mCursorX = mCursorX;
            d.mDispNap = mDispNap;
            d.mPileBottom = mPileBottom;
            d.mDelFlag = mDelFlag;
            d.msAmplify = msAmplify;
            d.mFAmplify = mFAmplify;
            d.mfDispNap = mfDispNap;
            d.mBase = mBase;
            d.mChnGain = mChnGain;
            d.mMaxDataPercent = mMaxDataPercent;
            d.mFreqResultLen = mFreqResultLen;
            d.mCurFreq = mCurFreq;
            d.mResolution = mResolution;
            d.mMainFreq = mMainFreq;
            d.mMaxAmp = mMaxAmp;
            int l = mFreqResultData.Length;
            d.mFreqResultData = new float[l];
            Array.Copy(mFreqResultData, 0, d.mFreqResultData, 0, l);
            d.msDispNap1 = msDispNap1;
            d.mFlag = mFlag;
            float[] data = new float[mSignalData.Length];
            Array.Copy(mSignalData, 0, data, 0, mSignalData.Length);
            d.mSignalData = data;
            float[] d2 = new float[mFreqPeak.Length];
            Array.Copy(mFreqPeak, 0, d2, 0, mFreqPeak.Length);
            d.mFreqPeak = d2;
            return d;
        }


        public int WriteToStream(FileStream fs)
        {
            byte[]
            bs = DataTransfer.ShortToBytes(mSaveLen);
            fs.Write(bs, 0, bs.Length);
            int len = bs.Length;
            byte[]
            bs2 = DataTransfer.FloatToBytes(mPileTop);
            fs.Write(bs2, 0, bs2.Length);
            int len2 = len + bs2.Length;
            byte[]
            bs3 = DataTransfer.ShortToBytes(mCursorX);
            fs.Write(bs3, 0, bs3.Length);
            int len3 = len2 + bs3.Length;
            byte[]
            bs4 = DataTransfer.ShortToBytes(mDispNap);
            fs.Write(bs4, 0, bs4.Length);
            int len4 = len3 + bs4.Length;
            byte[]
            bs5 = DataTransfer.FloatToBytes(mPileBottom);
            fs.Write(bs5, 0, bs5.Length);
            int len5 = len4 + bs5.Length;
            byte[]
            bs6 = DataTransfer.ShortToBytes(mDelFlag);
            fs.Write(bs6, 0, bs6.Length);
            int len6 = len5 + bs6.Length;
            byte[]
            bs7 = DataTransfer.ShortToBytes(msAmplify);
            fs.Write(bs7, 0, bs7.Length);
            int len7 = len6 + bs7.Length;
            byte[]
            bs8 = DataTransfer.FloatToBytes(mFAmplify);
            fs.Write(bs8, 0, bs8.Length);
            int len8 = len7 + bs8.Length;
            byte[]
            bs9 = DataTransfer.FloatToBytes(mfDispNap);
            fs.Write(bs9, 0, bs9.Length);
            int len9 = len8 + bs9.Length;
            byte[]
            bs10 = DataTransfer.FloatToBytes(mBase);
            fs.Write(bs10, 0, bs10.Length);
            int len10 = len9 + bs10.Length;
            byte[]
            bs11 = DataTransfer.ShortToBytes(mChnGain);
            fs.Write(bs11, 0, bs11.Length);
            int len11 = len10 + bs11.Length;
            byte[]
            bs12 = DataTransfer.FloatToBytes(mMaxDataPercent);
            fs.Write(bs12, 0, bs12.Length);
            int len12 = len11 + bs12.Length;
            byte[]
            bs13 = DataTransfer.CharToBytes(mReserve);
            fs.Write(bs13, 0, bs13.Length);
            int len13 = len12 + bs13.Length;
            byte[]
            bs14 = DataTransfer.ShortToBytes(mFreqResultLen);
            fs.Write(bs14, 0, bs14.Length);
            int len14 = len13 + bs14.Length;
            byte[]
            bs15 = DataTransfer.FloatToBytes(mSignalData);
            fs.Write(bs15, 0, bs15.Length);
            int len15 = len14 + bs15.Length;
            if (isOrignalData)
            {
                return len15;
            }
            byte[]
            bs16 = DataTransfer.ShortToBytes(mCurFreq);
            fs.Write(bs16, 0, bs16.Length);
            int len16 = len15 + bs16.Length;
            if (mFreqPeak.Length < 5)
            {
                float[] fArr = new float[5];
                for (int i = 0; i < 5; i++)
                {
                    if (i < mFreqPeak.Length)
                    {
                        fArr[i] = mFreqPeak[i];
                    }
                }
                mFreqPeak = fArr;
            }
            byte[]
            bs17 = DataTransfer.FloatToBytes(mFreqPeak);
            fs.Write(bs17, 0, bs17.Length);
            int len17 = len16 + bs17.Length;
            byte[]
            bs18 = DataTransfer.FloatToBytes(mResolution);
            fs.Write(bs18, 0, bs18.Length);
            int len18 = len17 + bs18.Length;
            byte[]
            bs19 = DataTransfer.FloatToBytes(mMainFreq);
            fs.Write(bs19, 0, bs19.Length);
            int len19 = len18 + bs19.Length;
            byte[]
            bs20 = DataTransfer.FloatToBytes(mMaxAmp);
            fs.Write(bs20, 0, bs20.Length);
            int len20 = len19 + bs20.Length;
            byte[]
            bs21 = DataTransfer.FloatToBytes(mFreqResultData);
            fs.Write(bs21, 0, bs21.Length);
            int len21 = len20 + bs21.Length;
            byte[]
            bs22 = DataTransfer.ShortToBytes(msDispNap1);
            fs.Write(bs22, 0, bs22.Length);
            int len22 = len21 + bs22.Length;
            byte[]
            bs23 = DataTransfer.ShortToBytes(mFlag);
            fs.Write(bs23, 0, bs23.Length);
            return len22 + bs23.Length;
        }

        public int InitFromStream(FileStream fs)
        {
            short s = 0;
            int readLen = 0;
            try
            {
                byte[] bs = new byte[2];
                int readLen2 = fs.Read(bs, 0, 2);
                mSaveLen = DataTransfer.ByteToShort(bs);
                mSaveLen = (short)(mSaveLen < 0 ? 0 : mSaveLen);
                byte[] bs2 = new byte[4];
                int readLen3 = readLen2 + fs.Read(bs2, 0, 4);
                mPileTop = DataTransfer.ByteToFloat(bs2);
                byte[] bs3 = new byte[2];
                int readLen4 = readLen3 + fs.Read(bs3, 0, 2);
                mCursorX = DataTransfer.ByteToShort(bs3);
                byte[] bs4 = new byte[2];
                int readLen5 = readLen4 + fs.Read(bs4, 0, 2);
                mDispNap = DataTransfer.ByteToShort(bs4);
                byte[] bs5 = new byte[4];
                int readLen6 = readLen5 + fs.Read(bs5, 0, 4);
                mPileBottom = DataTransfer.ByteToFloat(bs5);
                byte[] bs6 = new byte[2];
                int readLen7 = readLen6 + fs.Read(bs6, 0, 2);
                mDelFlag = DataTransfer.ByteToShort(bs6);
                byte[] bs7 = new byte[2];
                int readLen8 = readLen7 + fs.Read(bs7, 0, 2);
                msAmplify = DataTransfer.ByteToShort(bs7);
                byte[] bs8 = new byte[4];
                int readLen9 = readLen8 + fs.Read(bs8, 0, 4);
                mFAmplify = DataTransfer.ByteToFloat(bs8);
                byte[] bs9 = new byte[4];
                int readLen10 = readLen9 + fs.Read(bs9, 0, 4);
                mfDispNap = DataTransfer.ByteToFloat(bs9);
                byte[] bs10 = new byte[4];
                int readLen11 = readLen10 + fs.Read(bs10, 0, 4);
                mBase = DataTransfer.ByteToFloat(bs10);
                byte[] bs11 = new byte[2];
                int readLen12 = readLen11 + fs.Read(bs11, 0, 2);
                mChnGain = DataTransfer.ByteToShort(bs11);
                byte[] bs12 = new byte[4];
                int readLen13 = readLen12 + fs.Read(bs12, 0, 4);
                mMaxDataPercent = DataTransfer.ByteToFloat(bs12);
                byte[] bs13 = new byte[34];
                int readLen14 = readLen13 + fs.Read(bs13, 0, 34);
                mReserve = DataTransfer.ByteToCharArray(bs13);
                byte[] bs14 = new byte[2];
                int readLen15 = readLen14 + fs.Read(bs14, 0, 2);
                mFreqResultLen = DataTransfer.ByteToShort(bs14);
                if (mFreqResultLen >= 0)
                {
                    s = mFreqResultLen;
                }
                mFreqResultLen = s;
                byte[] bs15 = new byte[mSaveLen * 4];
                readLen = readLen15 + fs.Read(bs15, 0, mSaveLen * 4);
                mSignalData = DataTransfer.ByteToFloatArray(bs15);
                if (!isOrignalData)
                {
                    byte[] bs16 = new byte[2];
                    int readLen16 = readLen + fs.Read(bs16, 0, 2);
                    mCurFreq = DataTransfer.ByteToShort(bs16);
                    byte[] bs17 = new byte[20];
                    int readLen17 = readLen16 + fs.Read(bs17, 0, 20);
                    mFreqPeak = DataTransfer.ByteToFloatArray(bs17);
                    byte[] bs18 = new byte[4];
                    int readLen18 = readLen17 + fs.Read(bs18, 0, 4);
                    mResolution = DataTransfer.ByteToFloat(bs18);
                    if (mResolution > 0.0f)
                    {
                        for (int i = 0; i < 5; i++)
                        {
                            mFreqPeakPos[i] = (int)(mFreqPeak[i] / mResolution);
                        }
                    }
                    byte[] bs19 = new byte[4];
                    int readLen19 = readLen18 + fs.Read(bs19, 0, 4);
                    mMainFreq = DataTransfer.ByteToFloat(bs19);
                    byte[] bs20 = new byte[4];
                    int readLen20 = readLen19 + fs.Read(bs20, 0, 4);
                    mMaxAmp = DataTransfer.ByteToFloat(bs20);
                    byte[] bs21 = new byte[mFreqResultLen * 2];
                    int readLen21 = readLen20 + fs.Read(bs21, 0, mFreqResultLen * 2);
                    mFreqResultData = DataTransfer.ByteToFloatArray(bs21);
                    byte[] bs22 = new byte[2];
                    int readLen22 = readLen21 + fs.Read(bs22, 0, 2);
                    msDispNap1 = DataTransfer.ByteToShort(bs22);
                    byte[] bs23 = new byte[2];
                    readLen = readLen22 + fs.Read(bs23, 0, 2);
                    mFlag = DataTransfer.ByteToShort(bs23);
                }
                return readLen;
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
                return readLen;
            }
        }

        public void SetFreqNum(int freqNum)
        {
            mCurFreq = (short)freqNum;
        }

        public int GetFreqNum()
        {
            return mCurFreq;
        }

        public int SetFreqPeak(float pos, float peak)
        {
            if (mCurFreq >= 5)
            {
                return -1;
            }

            //修正
            foreach (var i in mFreqPeakPos)
            {
                if (((float)i) == ((float)((int)pos)))
                {
                    return -2;
                }
            }
            //原代码
            //for (int i :mFreqPeakPos)
            //{
            //    if (((float)i) == ((float)((int)pos)))
            //    {
            //        return -2;
            //    }
            //}

            int count = mCurFreq + 1;
            int[] tempPos = new int[count];
            float[] tempFreq = new float[count];
            float maxValue = 0.0f;
            for (int i2 = 0; i2 < count; i2++)
            {
                if (i2 >= mCurFreq)
                {
                    tempPos[i2] = (int)pos;
                }
                else
                {
                    tempPos[i2] = mFreqPeakPos[i2];
                }
                if (i2 >= mCurFreq)
                {
                    tempFreq[i2] = peak;
                }
                else
                {
                    tempFreq[i2] = mFreqPeak[i2];
                }
                if (maxValue < tempFreq[i2])
                {
                    maxValue = tempFreq[i2];
                }
            }
            Array.Sort(tempFreq);
            Array.Sort(tempPos);
            mFreqPeakPos = new int[5];
            mFreqPeak = new float[5];
            for (int i3 = 0; i3 < 5; i3++)
            {
                if (i3 < count)
                {
                    mFreqPeak[i3] = tempFreq[i3];
                    mFreqPeakPos[i3] = tempPos[i3];
                }
            }
            mMainFreq = maxValue;
            SetFreqNum(count);
            return 1;
        }

        public int RemovePeak(int cursor)
        {
            if (cursor <= 0)
            {
                return 0;
            }
            int[] dis = new int[mFreqPeakPos.Length];
            int minDis = -1;
            for (int i = 0; i < mFreqPeakPos.Length; i++)
            {
                if (mFreqPeakPos[i] > 0)
                {
                    dis[i] = Math.Abs(mFreqPeakPos[i] - cursor);
                    if (minDis == -1)
                    {
                        minDis = dis[i];
                    }
                    if (dis[i] < minDis)
                    {
                        minDis = dis[i];
                    }
                }
            }
            if (minDis > 4)
            {
                return 2;
            }
            int minIndex = 0;
            for (int i2 = 0; i2 < dis.Length; i2++)
            {
                if (dis[i2] == minDis)
                {
                    minIndex = i2;
                }
            }
            if (minIndex < mFreqPeakPos.Length)
            {
                mFreqPeakPos[minIndex] = 0;
            }
            if (minIndex < mFreqPeak.Length)
            {
                mFreqPeak[minIndex] = 0.0f;
            }

            Dictionary<int, float> map = new Dictionary<int, float>();

            float maxValue = 0.0f;
            Array.Sort(mFreqPeak);
            Array.Sort(mFreqPeakPos);
            int i3 = 0;
            while (i3 < mFreqPeakPos.Length && i3 < mFreqPeak.Length)
            {
                if (mFreqPeakPos[i3] > 0)
                {
                    map.Add(mFreqPeakPos[i3], mFreqPeak[i3]);
                    if (maxValue < mFreqPeak[i3])
                    {
                        maxValue = mFreqPeak[i3];
                    }
                }
                i3++;
            }
            mFreqPeakPos = new int[5];
            mFreqPeak = new float[5];
            int i4 = 0;

            //修正
            foreach (int pos in map.Keys)
            {
                mFreqPeakPos[i4] = pos;
                mFreqPeak[i4] = map[pos];
                i4++;
            }

            //原来的
            //for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext();)
            //{
            //    Integer pos = iterator.next();
            //    this.mFreqPeakPos[i4] = pos;
            //    this.mFreqPeak[i4] = map.get(pos);
            //    i4++;
            //}

            SetFreqNum(map.Count);
            mMainFreq = maxValue;
            return 1;
        }
    }
}
