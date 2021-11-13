using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Frame;

using ZIT_NET.Model;

namespace ZIT_NET.Model
{
    public class CPileInfo : ICustomSerializable
    {
        private short mAnalogFilter;
        private short mAnalyzeID;
        private short mBlowNumber;
        private short mBottomType;
        private short mChannelSelected;
        private short mCursorX;
        private short mDelayPoint;
        private short mDisplayAmplify;
        private short mDisplayNap;
        private float mEndFrequencyHigh;
        private float mEndFrequencyLow;
        private short mFilterType;
        private short mFlawNum;
        private CFlaw[] mFlaws;
        private short mGainTimes;
        private short mGaintype;
        private GPSData mGpsData;
        private short mIntegralFlag;
        private short mIntegrityDescribe;
        private char[] mIntegrityEvaluate;
        private float mMagnification;
        private float mMagnificationStart;
        private short mMagnificationType;
        private char[] mOtherPileType;
        private float mPileBottom;
        private String mPileCreateDate;
        private float mPileLength;
        private String mPileName;
        private float mPileTop;
        private short mPileType;
        private short mPileWidth1;
        private short mPileWidth2;
        private String mProjectName;
        private short mRemoveDCFlag;
        private char[] mReserve3;
        private char[] mReserveAnalysePara;
        private char[] mReserveAnalyseResult;
        private char[] mReserveSamplePara;
        private float mSampleInterval;
        private short mSampleLength;
        private float mSensitivity1;
        private float mSensitivity2;
        private short mSensorType;
        private short mSmoothPoints;
        private String mTestDate;
        private short[] mTestTime;
        private short mTriggerLevel;
        private short mTriggerMode;
        private short mValidGpsFlag;
        private char[] mVersion;
        private short mWaveSpeed;
        private float mWaveletFactor;
        private short mWaveletRotate;
        private short mWaveletType;
        private int pileShape;

        public short GetWaveSpeed()
        {
            return this.mWaveSpeed;
        }

        public void SetWaveSpeed(int mWaveSpeed)
        {
            this.mWaveSpeed = (short)mWaveSpeed;
        }

        public short GetSampleLength()
        {
            return this.mSampleLength;
        }

        public void SetSampleLength(int sampleLength)
        {
            this.mSampleLength = (short)sampleLength;
        }

        public void SetPileLength(float mPileLength)
        {
            this.mPileLength = mPileLength;
        }

        public float GetPileLength()
        {
            return this.mPileLength;
        }

        public String GetPileName()
        {
            return this.mPileName;
        }

        public void SetPileName(String pileName)
        {
            this.mPileName = pileName;
        }

        public String GetProjectName()
        {
            return this.mProjectName;
        }

        public void SetProjectName(String projectName)
        {
            this.mProjectName = projectName;
        }

        public char[] GetVersion()
        {
            return this.mVersion;
        }

        public char[] GetIntegrityEvaluate()
        {
            return this.mIntegrityEvaluate;
        }

        public short GetIntegrityDescribe()
        {
            return this.mIntegrityDescribe;
        }

        public String GetPileCreateDate()
        {
            return this.mPileCreateDate;
        }

        public char[] GetOtherPileType()
        {
            return this.mOtherPileType;
        }

        public String GetTestDate()
        {
            return this.mTestDate;
        }

        public void SetTestDate(String testDate)
        {
            this.mTestDate = testDate;
        }

        public short GetPileType()
        {
            return this.mPileType;
        }

        public void SetPileType(short mPileType)
        {
            this.mPileType = mPileType;
        }

        public short GetPileWidth1()
        {
            return this.mPileWidth1;
        }

        public void SetPileWidth1(short mPileWidth1)
        {
            this.mPileWidth1 = mPileWidth1;
        }

        public float GetSampleInterval()
        {
            return this.mSampleInterval;
        }

        public void SetSampleInterval(float mSampleInterval)
        {
            this.mSampleInterval = mSampleInterval;
        }

        public float GetSensitivity1()
        {
            return this.mSensitivity1;
        }

        public void SetSensitivity1(float mSensitivity1)
        {
            this.mSensitivity1 = mSensitivity1;
        }

        public short GetGainTimes()
        {
            return this.mGainTimes;
        }

        public void SetGainTimes(int gain)
        {
            this.mGainTimes = (short)gain;
        }

        public short GetTriggerLevel()
        {
            return this.mTriggerLevel;
        }

        public void SetTriggerLevel(int triggerLevel)
        {
            this.mTriggerLevel = (short)triggerLevel;
        }

        public short GetDelayPoint()
        {
            return this.mDelayPoint;
        }

        public void SetDelayPoint(int delayPoint)
        {
            this.mDelayPoint = (short)delayPoint;
        }

        public short GetBlowNumber()
        {
            return this.mBlowNumber;
        }

        public void SetBlowNumber(int blowNumber)
        {
            this.mBlowNumber = (short)blowNumber;
        }

        public short GetAnalyzeID()
        {
            return this.mAnalyzeID;
        }

        public void SetAnalyzeID(short mAnalyzeID)
        {
            this.mAnalyzeID = mAnalyzeID;
        }

        public short GetChannelSelected()
        {
            return this.mChannelSelected;
        }

        public float GetSensitivity2()
        {
            return this.mSensitivity2;
        }

        public short GetPileWidth2()
        {
            return this.mPileWidth2;
        }

        public void SetPileWidth2(short mPileWidth2)
        {
            this.mPileWidth2 = mPileWidth2;
        }

        public short GetGaintype()
        {
            return this.mGaintype;
        }

        public char[] GetReserveSamplePara()
        {
            return this.mReserveSamplePara;
        }

        public void SetReserveSamplePara(char[] mReserveSamplePara)
        {
            this.mReserveSamplePara = mReserveSamplePara;
        }

        public short GetIntegralFlag()
        {
            return this.mIntegralFlag;
        }

        public void SetIntegralFlag(int integralFlag)
        {
            this.mIntegralFlag = (short)integralFlag;
        }

        public short GetSmoothPoints()
        {
            return this.mSmoothPoints;
        }

        public void SetSmoothPoints(short mSmoothPoints)
        {
            this.mSmoothPoints = mSmoothPoints;
        }

        public short GetFilterType()
        {
            return this.mFilterType;
        }

        public void SetFilterType(int filterType)
        {
            this.mFilterType = (short)filterType;
        }

        public float GetEndFrequencyLow()
        {
            return this.mEndFrequencyLow;
        }

        public void SetEndFrequencyLow(float mEndFrequencyLow)
        {
            this.mEndFrequencyLow = mEndFrequencyLow;
        }

        public float GetEndFrequencyHigh()
        {
            return this.mEndFrequencyHigh;
        }

        public void SetEndFrequencyHigh(float mEndFrequencyHigh)
        {
            this.mEndFrequencyHigh = mEndFrequencyHigh;
        }

        public short GetMagnificationType()
        {
            return this.mMagnificationType;
        }

        public void SetMagnificationType(short mMagnificationType)
        {
            this.mMagnificationType = mMagnificationType;
        }

        public float GetMagnification()
        {
            return this.mMagnification;
        }

        public void SetMagnification(float mMagnification)
        {
            this.mMagnification = mMagnification;
        }

        public float GetMagnificationStart()
        {
            return this.mMagnificationStart;
        }

        public void SetMagnificationStart(float mMagnificationStart)
        {
            this.mMagnificationStart = mMagnificationStart;
        }

        public short GetWaveletRotate()
        {
            return this.mWaveletRotate;
        }

        public void SetWaveletRotate(short mWaveletRotate)
        {
            this.mWaveletRotate = mWaveletRotate;
        }

        public short GetWaveletType()
        {
            return this.mWaveletType;
        }

        public float GetWaveletFactor()
        {
            return this.mWaveletFactor;
        }

        public void SetWaveletFactor(float waveletFactor)
        {
            this.mWaveletFactor = waveletFactor;
        }

        public short GetRemoveDCFlag()
        {
            return this.mRemoveDCFlag;
        }

        public char[] GetReserveAnalysePara()
        {
            return this.mReserveAnalysePara;
        }

        public void SetReserveAnalysePara(char[] mReserveAnalysePara)
        {
            this.mReserveAnalysePara = mReserveAnalysePara;
        }

        public float GetPileTop()
        {
            return this.mPileTop;
        }

        public void SetPileTop(float mPileTop)
        {
            this.mPileTop = mPileTop;
        }

        public float GetPileBottom()
        {
            return this.mPileBottom;
        }

        public void SetPileBottom(float mPileBottom)
        {
            this.mPileBottom = mPileBottom;
        }

        public short GetDisplayNap()
        {
            return this.mDisplayNap;
        }

        public void SetDisplayNap(short displayNap)
        {
            this.mDisplayNap = displayNap;
        }

        public short GetFlawNum()
        {
            return this.mFlawNum;
        }

        public void SetFlawNum(short mFlawNum)
        {
            this.mFlawNum = mFlawNum;
        }

        public short GetDisplayAmplify()
        {
            return this.mDisplayAmplify;
        }

        public short GetCursorX()
        {
            return this.mCursorX;
        }

        public CFlaw[] GetFlaw()
        {
            return this.mFlaws;
        }

        public void AddFlaw(CFlaw flaw)
        {
            CFlaw[] fArr;
            this.mFlawNum = (short)(this.mFlawNum + 1);
            if (this.mFlawNum == 1)
            {
                this.mFlaws = new CFlaw[] { flaw };
                return;
            }
            bool b = false;
            foreach (var f in mFlaws)
            {
                if (f.mPosIndex == flaw.mPosIndex)
                {
                    b = true;
                    break;
                }
            }
            //for (CFlaw f : this.mFlaws)
            //{
            //    if (f.mPosIndex == flaw.mPosIndex)
            //    {
            //        b = true;
            //        break;
            //    }
            //}
            if (b)
            {
                this.mFlawNum = (short)(this.mFlawNum - 1);
                fArr = new CFlaw[this.mFlawNum];
                for (int i = 0; i < this.mFlaws.Length; i++)
                {
                    if (this.mFlaws[i].mPosIndex == flaw.mPosIndex)
                    {
                        fArr[i] = flaw;
                    }
                    else
                    {
                        fArr[i] = this.mFlaws[i];
                    }
                }
            }
            else
            {
                fArr = new CFlaw[this.mFlawNum];
                Array.Copy(this.mFlaws, 0, fArr, 0, this.mFlaws.Length);
                fArr[this.mFlawNum - 1] = flaw;
            }
            this.mFlaws = fArr;
        }

        public void ClearFlaw()
        {
            this.mFlaws = null;
            this.mFlawNum = 0;
        }

        public short GetBottomType()
        {
            return this.mBottomType;
        }

        public void SetBottomType(short mBottomType)
        {
            this.mBottomType = mBottomType;
        }

        public char[] GetReserveAnalyseResult()
        {
            return mReserveAnalyseResult;
        }

        public void SetReserveAnalyseResult(char[] mReserveAnalyseResult)
        {
            this.mReserveAnalyseResult = mReserveAnalyseResult;
        }

        public short[] GetTestTime()
        {
            return mTestTime;
        }

        public void SetTestTime(DateTime date)
        {

            int hours = date.Hour;
            int minute = date.Minute;
            int second = date.Second;

            mTestTime[0] = (short)hours;
            mTestTime[1] = (short)minute;
            mTestTime[2] = (short)second;
        }

        public short GetValidGpsFlag()
        {
            return mValidGpsFlag;
        }

        public void SetValidGpsFlag(short mValidGpsFlag)
        {
            this.mValidGpsFlag = mValidGpsFlag;
        }

        public GPSData GetGpsData()
        {
            return this.mGpsData;
        }

        public void SetGpsData(GPSData mGpsData)
        {
            this.mGpsData = mGpsData;
        }

        public char[] GetReserve3()
        {
            return this.mReserve3;
        }

        public void SetReserve3(char[] reserve3)
        {
            this.mReserve3 = reserve3;
        }


        public int WriteToStream(FileStream os)
        {
            int len;
            byte[]
            bs = DataTransfer.CharToBytes(this.mVersion);
            os.Write(bs, 0, bs.Length);
            int len2 = bs.Length;
            byte[]
            bs2 = DataTransfer.StringToBytes(this.mProjectName, 50);
            os.Write(bs2, 0, bs2.Length);
            int len3 = len2 + bs2.Length;
            byte[]
            bs3 = DataTransfer.StringToBytes(this.mPileName, 20);
            os.Write(bs3, 0, bs3.Length);
            int len4 = len3 + bs3.Length;
            byte[]
            bs4 = DataTransfer.CharToBytes(this.mIntegrityEvaluate);
            os.Write(bs4, 0, bs4.Length);
            int len5 = len4 + bs4.Length;
            byte[]
            bs5 = DataTransfer.ShortToBytes(this.mIntegrityDescribe);
            os.Write(bs5, 0, bs5.Length);
            int len6 = len5 + bs5.Length;
            byte[]
            bs6 = DataTransfer.StringToBytes(this.mPileCreateDate, 16);
            os.Write(bs6, 0, bs6.Length);
            int len7 = len6 + bs6.Length;
            byte[]
            bs7 = DataTransfer.CharToBytes(this.mOtherPileType);
            os.Write(bs7, 0, bs7.Length);
            int len8 = len7 + bs7.Length;
            byte[]
            bs8 = DataTransfer.StringToBytes(this.mTestDate, 16);
            os.Write(bs8, 0, bs8.Length);
            int len9 = len8 + bs8.Length;
            byte[]
            bs9 = DataTransfer.ShortToBytes(this.mPileType);
            os.Write(bs9, 0, bs9.Length);
            int len10 = len9 + bs9.Length;
            byte[]
            bs10 = DataTransfer.ShortToBytes(this.mPileWidth1);
            os.Write(bs10, 0, bs10.Length);
            int len11 = len10 + bs10.Length;
            byte[]
            bs11 = DataTransfer.FloatToBytes(this.mPileLength);
            os.Write(bs11, 0, bs11.Length);
            int len12 = len11 + bs11.Length;
            byte[]
            bs12 = DataTransfer.ShortToBytes(this.mWaveSpeed);
            os.Write(bs12, 0, bs12.Length);
            int len13 = len12 + bs12.Length;
            byte[]
            bs13 = DataTransfer.ShortToBytes(this.mSampleLength);
            os.Write(bs13, 0, bs13.Length);
            int len14 = len13 + bs13.Length;
            byte[]
            bs14 = DataTransfer.FloatToBytes(this.mSampleInterval);
            os.Write(bs14, 0, bs14.Length);
            int len15 = len14 + bs14.Length;
            byte[]
            bs15 = DataTransfer.ShortToBytes(this.mTriggerMode);
            os.Write(bs15, 0, bs15.Length);
            int len16 = len15 + bs15.Length;
            byte[]
            bs16 = DataTransfer.ShortToBytes(this.mAnalogFilter);
            os.Write(bs16, 0, bs16.Length);
            int len17 = len16 + bs16.Length;
            byte[]
            bs17 = DataTransfer.ShortToBytes(this.mSensorType);
            os.Write(bs17, 0, bs17.Length);
            int len18 = len17 + bs17.Length;
            byte[]
            bs18 = DataTransfer.FloatToBytes(this.mSensitivity1);
            os.Write(bs18, 0, bs18.Length);
            int len19 = len18 + bs18.Length;
            byte[]
            bs19 = DataTransfer.ShortToBytes(this.mGainTimes);
            os.Write(bs19, 0, bs19.Length);
            int len20 = len19 + bs19.Length;
            byte[]
            bs20 = DataTransfer.ShortToBytes(this.mTriggerLevel);
            os.Write(bs20, 0, bs20.Length);
            int len21 = len20 + bs20.Length;
            byte[]
            bs21 = DataTransfer.ShortToBytes(this.mDelayPoint);
            os.Write(bs21, 0, bs21.Length);
            int len22 = len21 + bs21.Length;
            byte[]
            bs22 = DataTransfer.ShortToBytes(this.mBlowNumber);
            os.Write(bs22, 0, bs22.Length);
            int len23 = len22 + bs22.Length;
            byte[]
            bs23 = DataTransfer.ShortToBytes(this.mAnalyzeID);
            os.Write(bs23, 0, bs23.Length);
            int len24 = len23 + bs23.Length;
            byte[]
            bs24 = DataTransfer.ShortToBytes(this.mChannelSelected);
            os.Write(bs24, 0, bs24.Length);
            int len25 = len24 + bs24.Length;
            byte[]
            bs25 = DataTransfer.FloatToBytes(this.mSensitivity2);
            os.Write(bs25, 0, bs25.Length);
            int len26 = len25 + bs25.Length;
            if (this.pileShape == 0)
            {
                byte[] bs26 = DataTransfer.ShortToBytes((short)0);
                os.Write(bs26, 0, bs26.Length);
                len = len26 + bs26.Length;
            }
            else
            {
                byte[] bs27 = DataTransfer.ShortToBytes(this.mPileWidth2);
                os.Write(bs27, 0, bs27.Length);
                len = len26 + bs27.Length;
            }
            byte[]
            bs28 = DataTransfer.ShortToBytes(this.mGaintype);
            os.Write(bs28, 0, bs28.Length);
            int len27 = len + bs28.Length;
            byte[]
            bs29 = DataTransfer.CharToBytes(this.mReserveSamplePara);
            os.Write(bs29, 0, bs29.Length);
            int len28 = len27 + bs29.Length;
            byte[]
            bs30 = DataTransfer.ShortToBytes(this.mIntegralFlag);
            os.Write(bs30, 0, bs30.Length);
            int len29 = len28 + bs30.Length;
            byte[]
            bs31 = DataTransfer.ShortToBytes(this.mSmoothPoints);
            os.Write(bs31, 0, bs31.Length);
            int len30 = len29 + bs31.Length;
            byte[]
            bs32 = DataTransfer.ShortToBytes(this.mFilterType);
            os.Write(bs32, 0, bs32.Length);
            int len31 = len30 + bs32.Length;
            byte[]
            bs33 = DataTransfer.FloatToBytes(this.mEndFrequencyLow);
            os.Write(bs33, 0, bs33.Length);
            int len32 = len31 + bs33.Length;
            byte[]
            bs34 = DataTransfer.FloatToBytes(this.mEndFrequencyHigh);
            os.Write(bs34, 0, bs34.Length);
            int len33 = len32 + bs34.Length;
            byte[]
            bs35 = DataTransfer.ShortToBytes(this.mMagnificationType);
            os.Write(bs35, 0, bs35.Length);
            int len34 = len33 + bs35.Length;
            byte[]
            bs36 = DataTransfer.FloatToBytes(this.mMagnification);
            os.Write(bs36, 0, bs36.Length);
            int len35 = len34 + bs36.Length;
            byte[]
            bs37 = DataTransfer.FloatToBytes(this.mMagnificationStart);
            os.Write(bs37, 0, bs37.Length);
            int len36 = len35 + bs37.Length;
            byte[]
            bs38 = DataTransfer.ShortToBytes(this.mWaveletRotate);
            os.Write(bs38, 0, bs38.Length);
            int len37 = len36 + bs38.Length;
            byte[]
            bs39 = DataTransfer.ShortToBytes(this.mWaveletType);
            os.Write(bs39, 0, bs39.Length);
            int len38 = len37 + bs39.Length;
            byte[]
            bs40 = DataTransfer.FloatToBytes(this.mWaveletFactor);
            os.Write(bs40, 0, bs40.Length);
            int len39 = len38 + bs40.Length;
            byte[]
            bs41 = DataTransfer.ShortToBytes(this.mRemoveDCFlag);
            os.Write(bs41, 0, bs41.Length);
            int len40 = len39 + bs41.Length;
            byte[]
            bs42 = DataTransfer.CharToBytes(this.mReserveAnalysePara);
            os.Write(bs42, 0, bs42.Length);
            int len41 = len40 + bs42.Length;
            byte[]
            bs43 = DataTransfer.FloatToBytes(this.mPileTop);
            os.Write(bs43, 0, bs43.Length);
            int len42 = len41 + bs43.Length;
            byte[]
            bs44 = DataTransfer.ShortToBytes(this.mDisplayNap);
            os.Write(bs44, 0, bs44.Length);
            int len43 = len42 + bs44.Length;
            byte[]
            bs45 = DataTransfer.FloatToBytes(this.mPileBottom);
            os.Write(bs45, 0, bs45.Length);
            int len44 = len43 + bs45.Length;
            byte[]
            bs46 = DataTransfer.ShortToBytes(this.mFlawNum);
            os.Write(bs46, 0, bs46.Length);
            int len45 = len44 + bs46.Length;
            byte[]
            bs47 = DataTransfer.ShortToBytes(this.mDisplayAmplify);
            os.Write(bs47, 0, bs47.Length);
            int len46 = len45 + bs47.Length;
            byte[]
            bs48 = DataTransfer.ShortToBytes(this.mCursorX);
            os.Write(bs48, 0, bs48.Length);
            int len47 = len46 + bs48.Length;
            for (int i = 0; i < this.mFlawNum; i++)
            {
                len47 += this.mFlaws[i].WriteToStream(os);
            }
            byte[]
            bs49 = DataTransfer.ShortToBytes(this.mBottomType);
            os.Write(bs49, 0, bs49.Length);
            int len48 = len47 + bs49.Length;
            byte[]
            bs50 = DataTransfer.CharToBytes(this.mReserveAnalyseResult);
            os.Write(bs50, 0, bs50.Length);
            int len49 = len48 + bs50.Length;
            byte[]
            bs51 = DataTransfer.ShortToBytes(this.mTestTime);
            os.Write(bs51, 0, bs51.Length);
            int len50 = len49 + bs51.Length;
            byte[]
            bs52 = DataTransfer.ShortToBytes(this.mValidGpsFlag);
            os.Write(bs52, 0, bs52.Length);
            int len51 = len50 + bs52.Length;
            this.mGpsData = new GPSData();
            int len52 = len51 + this.mGpsData.WriteToStream(os);
            byte[] bs53 = DataTransfer.CharToBytes(this.mReserve3);
            os.Write(bs53, 0, bs53.Length);
            return len52 + bs53.Length;
        }


        public int InitFromStream(FileStream fs)
        {
            int readLen = 0;
            try
            {
                byte[] bs = new byte[10];
                int readLen2 = fs.Read(bs, 0, 10);
                this.mVersion = DataTransfer.ByteToCharArray(bs);
                byte[] bs2 = new byte[50];
                int readLen3 = readLen2 + fs.Read(bs2, 0, 50);
                this.mProjectName = DataTransfer.ByteToString(bs2);
                byte[] bs3 = new byte[20];
                int readLen4 = readLen3 + fs.Read(bs3, 0, 20);
                this.mPileName = DataTransfer.ByteToString(bs3);
                byte[] bs4 = new byte[100];
                int readLen5 = readLen4 + fs.Read(bs4, 0, 100);
                this.mIntegrityEvaluate = DataTransfer.ByteToCharArray(bs4);
                byte[] bs5 = new byte[2];
                int readLen6 = readLen5 + fs.Read(bs5, 0, 2);
                this.mIntegrityDescribe = DataTransfer.ByteToShort(bs5);
                byte[] bs6 = new byte[16];
                int readLen7 = readLen6 + fs.Read(bs6, 0, 16);
                this.mPileCreateDate = DataTransfer.ByteToString(bs6);
                byte[] bs7 = new byte[20];
                int readLen8 = readLen7 + fs.Read(bs7, 0, 20);
                this.mOtherPileType = DataTransfer.ByteToCharArray(bs7);
                byte[] bs8 = new byte[16];
                int readLen9 = readLen8 + fs.Read(bs8, 0, 16);
                this.mTestDate = DataTransfer.ByteToString(bs8);
                byte[] bs9 = new byte[2];
                int readLen10 = readLen9 + fs.Read(bs9, 0, 2);
                this.mPileType = DataTransfer.ByteToShort(bs9);
                byte[] bs10 = new byte[2];
                int readLen11 = readLen10 + fs.Read(bs10, 0, 2);
                this.mPileWidth1 = DataTransfer.ByteToShort(bs10);
                byte[] bs11 = new byte[4];
                int readLen12 = readLen11 + fs.Read(bs11, 0, 4);
                this.mPileLength = DataTransfer.ByteToFloat(bs11);
                byte[] bs12 = new byte[2];
                int readLen13 = readLen12 + fs.Read(bs12, 0, 2);
                this.mWaveSpeed = DataTransfer.ByteToShort(bs12);
                byte[] bs13 = new byte[2];
                int readLen14 = readLen13 + fs.Read(bs13, 0, 2);
                this.mSampleLength = DataTransfer.ByteToShort(bs13);
                byte[] bs14 = new byte[4];
                int readLen15 = readLen14 + fs.Read(bs14, 0, 4);
                this.mSampleInterval = DataTransfer.ByteToFloat(bs14);
                byte[] bs15 = new byte[2];
                int readLen16 = readLen15 + fs.Read(bs15, 0, 2);
                this.mTriggerMode = DataTransfer.ByteToShort(bs15);
                byte[] bs16 = new byte[2];
                int readLen17 = readLen16 + fs.Read(bs16, 0, 2);
                this.mAnalogFilter = DataTransfer.ByteToShort(bs16);
                byte[] bs17 = new byte[2];
                int readLen18 = readLen17 + fs.Read(bs17, 0, 2);
                this.mSensorType = DataTransfer.ByteToShort(bs17);
                byte[] bs18 = new byte[4];
                int readLen19 = readLen18 + fs.Read(bs18, 0, 4);
                this.mSensitivity1 = DataTransfer.ByteToFloat(bs18);
                byte[] bs19 = new byte[2];
                int readLen20 = readLen19 + fs.Read(bs19, 0, 2);
                this.mGainTimes = DataTransfer.ByteToShort(bs19);
                byte[] bs20 = new byte[2];
                int readLen21 = readLen20 + fs.Read(bs20, 0, 2);
                this.mTriggerLevel = DataTransfer.ByteToShort(bs20);
                byte[] bs21 = new byte[2];
                int readLen22 = readLen21 + fs.Read(bs21, 0, 2);
                this.mDelayPoint = DataTransfer.ByteToShort(bs21);
                byte[] bs22 = new byte[2];
                int readLen23 = readLen22 + fs.Read(bs22, 0, 2);
                this.mBlowNumber = DataTransfer.ByteToShort(bs22);
                byte[] bs23 = new byte[2];
                int readLen24 = readLen23 + fs.Read(bs23, 0, 2);
                this.mAnalyzeID = DataTransfer.ByteToShort(bs23);
                byte[] bs24 = new byte[2];
                int readLen25 = readLen24 + fs.Read(bs24, 0, 2);
                this.mChannelSelected = DataTransfer.ByteToShort(bs24);
                byte[] bs25 = new byte[4];
                int readLen26 = readLen25 + fs.Read(bs25, 0, 4);
                this.mSensitivity2 = DataTransfer.ByteToFloat(bs25);
                byte[] bs26 = new byte[2];
                int readLen27 = readLen26 + fs.Read(bs26, 0, 2);
                this.mPileWidth2 = DataTransfer.ByteToShort(bs26);
                if (this.mPileWidth2 > 0)
                {
                    this.pileShape = 1;
                }
                else
                {
                    this.pileShape = 0;
                }
                byte[] bs27 = new byte[2];
                int readLen28 = readLen27 + fs.Read(bs27, 0, 2);
                this.mGaintype = DataTransfer.ByteToShort(bs27);
                byte[] bs28 = new byte[10];
                int readLen29 = readLen28 + fs.Read(bs28, 0, 10);
                this.mReserveSamplePara = DataTransfer.ByteToCharArray(bs28);
                byte[] bs29 = new byte[2];
                int readLen30 = readLen29 + fs.Read(bs29, 0, 2);
                this.mIntegralFlag = DataTransfer.ByteToShort(bs29);
                byte[] bs30 = new byte[2];
                int readLen31 = readLen30 + fs.Read(bs30, 0, 2);
                this.mSmoothPoints = DataTransfer.ByteToShort(bs30);
                byte[] bs31 = new byte[2];
                int readLen32 = readLen31 + fs.Read(bs31, 0, 2);
                this.mFilterType = DataTransfer.ByteToShort(bs31);
                byte[] bs32 = new byte[4];
                int readLen33 = readLen32 + fs.Read(bs32, 0, 4);
                this.mEndFrequencyLow = DataTransfer.ByteToFloat(bs32);
                byte[] bs33 = new byte[4];
                int readLen34 = readLen33 + fs.Read(bs33, 0, 4);
                this.mEndFrequencyHigh = DataTransfer.ByteToFloat(bs33);
                byte[] bs34 = new byte[2];
                int readLen35 = readLen34 + fs.Read(bs34, 0, 2);
                this.mMagnificationType = DataTransfer.ByteToShort(bs34);
                byte[] bs35 = new byte[4];
                int readLen36 = readLen35 + fs.Read(bs35, 0, 4);
                this.mMagnification = DataTransfer.ByteToFloat(bs35);
                byte[] bs36 = new byte[4];
                int readLen37 = readLen36 + fs.Read(bs36, 0, 4);
                this.mMagnificationStart = DataTransfer.ByteToFloat(bs36);
                byte[] bs37 = new byte[2];
                int readLen38 = readLen37 + fs.Read(bs37, 0, 2);
                this.mWaveletRotate = DataTransfer.ByteToShort(bs37);
                byte[] bs38 = new byte[2];
                int readLen39 = readLen38 + fs.Read(bs38, 0, 2);
                this.mWaveletType = DataTransfer.ByteToShort(bs38);
                byte[] bs39 = new byte[4];
                int readLen40 = readLen39 + fs.Read(bs39, 0, 4);
                this.mWaveletFactor = DataTransfer.ByteToFloat(bs39);
                byte[] bs40 = new byte[2];
                int readLen41 = readLen40 + fs.Read(bs40, 0, 2);
                this.mRemoveDCFlag = DataTransfer.ByteToShort(bs40);
                byte[] bs41 = new byte[12];
                int readLen42 = readLen41 + fs.Read(bs41, 0, 12);
                this.mReserveAnalysePara = DataTransfer.ByteToCharArray(bs41);
                byte[] bs42 = new byte[4];
                int readLen43 = readLen42 + fs.Read(bs42, 0, 4);
                this.mPileTop = DataTransfer.ByteToFloat(bs42);
                byte[] bs43 = new byte[2];
                int readLen44 = readLen43 + fs.Read(bs43, 0, 2);
                this.mDisplayNap = DataTransfer.ByteToShort(bs43);
                byte[] bs44 = new byte[4];
                int readLen45 = readLen44 + fs.Read(bs44, 0, 4);
                this.mPileBottom = DataTransfer.ByteToFloat(bs44);
                byte[] bs45 = new byte[2];
                int readLen46 = readLen45 + fs.Read(bs45, 0, 2);
                this.mFlawNum = DataTransfer.ByteToShort(bs45);
                byte[] bs46 = new byte[2];
                int readLen47 = readLen46 + fs.Read(bs46, 0, 2);
                this.mDisplayAmplify = DataTransfer.ByteToShort(bs46);
                byte[] bs47 = new byte[2];
                int readLen48 = readLen47 + fs.Read(bs47, 0, 2);
                this.mCursorX = DataTransfer.ByteToShort(bs47);
                this.mFlaws = new CFlaw[this.mFlawNum];
                for (int i = 0; i < this.mFlawNum; i++)
                {
                    CFlaw flaw = new CFlaw();
                    readLen48 += flaw.InitFromStream(fs);
                    flaw.mPosIndex = (int)((flaw.mFlawPosition * 1000.0f) / this.mSampleInterval);
                    this.mFlaws[i] = flaw;
                }
                byte[] bs48 = new byte[2];
                int readLen49 = readLen48 + fs.Read(bs48, 0, 2);
                this.mBottomType = DataTransfer.ByteToShort(bs48);
                byte[] bs49 = new byte[18];
                int readLen50 = readLen49 + fs.Read(bs49, 0, 18);
                this.mReserveAnalyseResult = DataTransfer.ByteToCharArray(bs49);
                byte[] bs50 = new byte[6];
                int readLen51 = readLen50 + fs.Read(bs50, 0, 6);
                this.mTestTime = DataTransfer.ByteToShortArray(bs50);
                byte[] bs51 = new byte[2];
                int readLen52 = readLen51 + fs.Read(bs51, 0, 2);
                this.mValidGpsFlag = DataTransfer.ByteToShort(bs51);
                this.mGpsData = new GPSData();
                byte[] bs52 = new byte[100];
                readLen = readLen52 + this.mGpsData.InitFromStream(fs) + fs.Read(bs52, 0, 100);
                this.mReserve3 = DataTransfer.ByteToCharArray(bs52);
                return readLen;
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
                return readLen;
            }
        }

        public CPileInfo()
        {
            mVersion = new char[] { 'P', '8', 'W', 'V', '3', '.', '0', '0', (char)0, (char)0 };
            mProjectName = "SITE";
            mPileName = "1-1";
            mIntegrityEvaluate = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            mIntegrityDescribe = 0;
            mOtherPileType = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            mPileWidth1 = 1000;
            mPileWidth2 = 1000;
            mPileLength = 10.0f;
            mWaveSpeed = 3600;
            mTriggerMode = 0;
            mAnalogFilter = 0;
            mSensorType = 0;
            mDelayPoint = -100;
            mChannelSelected = 0;
            mSensitivity2 = 1.0f;
            mGaintype = 1;
            mReserveSamplePara = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            mWaveletType = 0;
            mRemoveDCFlag = 1;
            mReserveAnalysePara = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            mDisplayNap = 1;
            mDisplayAmplify = 1;
            mCursorX = 0;
            mReserveAnalyseResult = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            mTestTime = new short[3];
            mReserve3 = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            pileShape = 0;

            //    Calendar date = Calendar.getInstance();
            //    date.setTimeInMillis(time);
            //    mYear = (short)date.get(1);
            //    mMonth = (short)date.get(2);
            //    mDayOfWeek = (short)date.get(7);
            //    mDay = (short)date.get(5);
            //    mHour = (short)(date.get(10) - 8);
            //    wMinute = (short)date.get(12);
            //    wSecond = (short)date.get(13);
            //    wMilliseconds = (short)date.get(14);

            var dt = DateTime.UtcNow;
            mTestTime[0] = (short)dt.Hour;
            mTestTime[1] = (short)dt.Minute;
            mTestTime[2] = (short)dt.Second;
        }

        public void InitSystemTime()
        {
            var dt = DateTime.UtcNow;
            mTestTime[0] = (short)dt.Hour;
            mTestTime[1] = (short)dt.Minute;
            mTestTime[2] = (short)dt.Second;

            this.mTestDate = dt.ToString("yyyy-MM-dd");
            this.mPileCreateDate = dt.ToString("yyyy-MM-dd");
        }

        public CPileInfo(String workName, String pileName, float length, short speed)
        {
            this.mVersion = new char[] { 'P', '8', 'W', 'V', '3', '.', '0', '0', (char)0, (char)0 };
            this.mProjectName = "SITE";
            this.mPileName = "1-1";
            this.mIntegrityEvaluate = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            this.mIntegrityDescribe = 0;
            this.mOtherPileType = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            this.mPileWidth1 = 1000;
            this.mPileWidth2 = 1000;
            this.mPileLength = 10.0f;
            this.mWaveSpeed = 3600;
            this.mTriggerMode = 0;
            this.mAnalogFilter = 0;
            this.mSensorType = 0;
            this.mDelayPoint = -100;
            this.mChannelSelected = 0;
            this.mSensitivity2 = 1.0f;
            this.mGaintype = 1;
            this.mReserveSamplePara = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            this.mWaveletType = 0;
            this.mRemoveDCFlag = 1;
            this.mReserveAnalysePara = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            this.mDisplayNap = 1;
            this.mDisplayAmplify = 1;
            this.mCursorX = 0;
            this.mReserveAnalyseResult = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            this.mTestTime = new short[3];
            this.mReserve3 = new char[] { (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0, (char)0 };
            this.pileShape = 0;
            SetProjectName(workName);
            SetPileName(pileName);
            SetPileLength(length);
            SetWaveSpeed(speed);
        }

        public int GetPileShape()
        {
            return pileShape;
        }

        public void SetPileShape(int pileShape)
        {
            this.pileShape = pileShape;
        }
    }
}
