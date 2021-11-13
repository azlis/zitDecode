using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Frame;

namespace ZIT_NET.Model
{
    public class CFileInfo : ICustomSerializable
    {
        /**
         * 文件名
         */
        private String mFileName;
        /**
         * 桩信息
         */
        public CPileInfo mPileInfo;
        /**
         * 初始数据
         */
        public List<CPileData> mPileOriginalData;
        /**
         * 最终数据
         */
        public List<CPileData> mPileResultData;
        /**
         * 声波数量
         */
        public short mWaveNum;

        public CFileInfo(CPileInfo pileInfo)
        {
            mFileName = "1-1";
            mPileInfo = pileInfo;
            mPileResultData = new List<CPileData>();
            mPileOriginalData = new List<CPileData>();
        }

        public CFileInfo()
        {
            mFileName = "1-1";
            mPileInfo = new CPileInfo();
            mPileResultData = new List<CPileData>();
            mPileOriginalData = new List<CPileData>();
        }

        /**
         * 获取声波数量
         *
         * @return 声波数量
         */
        public short GetWaveNum()
        {
            if (mWaveNum == 0)
            {
                //return (short)mPileResultData.size();
                return (short)mPileResultData.Count;
            }
            return mWaveNum;
        }

        /**
         * 清空桩信息
         */
        public void ClearPileData()
        {
            if (this.mPileOriginalData != null)
            {
                this.mPileOriginalData.Clear();
            }
            if (this.mPileResultData != null)
            {
                this.mPileResultData.Clear();
            }
            this.mWaveNum = 0;
        }

        /**
         * 写出流数据
         *
         * @param os
         * @return
         * @throws IOException
         */

        public int WriteToStream(FileStream os)
        {
            int len = this.mPileInfo.WriteToStream(os);
            byte[]
            bs = DataTransfer.ShortToBytes(GetWaveNum());
            os.Write(bs, 0, bs.Length);
            int len2 = len + 2;
            if (this.mPileOriginalData != null)
            {
                for (int i = 0; i < this.mPileOriginalData.Count; i++)
                {
                    CPileData pileData = this.mPileOriginalData[i];
                    pileData.SetOrignalData(true);
                    len2 += pileData.WriteToStream(os);
                    //Log.d("Pile Write mPileOriginalData Length:", String.valueOf(len2));
                }
            }
            if (this.mPileResultData != null)
            {
                for (int i2 = 0; i2 < this.mPileResultData.Count; i2++)
                {
                    CPileData pileData2 = this.mPileResultData[i2];
                    pileData2.SetOrignalData(false);
                    len2 += pileData2.WriteToStream(os);
                    //Log.d("Pile Write mPileResultData Length*****:", String.valueOf(len2));
                }
            }
            return len2;
        }

        public int InitFromStream(FileStream fs)
        {
            mPileInfo = new CPileInfo();
            int readLen = mPileInfo.InitFromStream(fs);
            byte[] bs = new byte[2];
            try
            {
                readLen += fs.Read(bs, 0, 2);
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
            }
            this.mWaveNum = DataTransfer.ByteToShort(bs);
            this.mPileOriginalData = new List<CPileData>();
            for (int i = 0; i < this.mWaveNum; i++)
            {
                CPileData pileData = new CPileData();
                pileData.SetOrignalData(true);
                readLen += pileData.InitFromStream(fs);
                this.mPileOriginalData.Add(pileData);
                //Log.d("Pile Read mPileOriginalData Length:", String.valueOf(readLen));
            }
            this.mPileResultData = new List<CPileData>();
            for (int i2 = 0; i2 < this.mWaveNum; i2++)
            {
                CPileData pileData2 = new CPileData();
                pileData2.SetOrignalData(false);
                readLen += pileData2.InitFromStream(fs);
                this.mPileResultData.Add(pileData2);
                //Log.d("Pile Read mPileResultData Length:*****", String.valueOf(readLen));
            }
            return readLen;
        }

        public void AddFlaw(CFlaw flaw)
        {
            mPileInfo.AddFlaw(flaw);
        }

        public void ClearFlaw()
        {
            GetPileInfo().ClearFlaw();
        }

        /// <summary>
        /// 获取桩信息
        /// </summary>
        /// <returns></returns>
        public CPileInfo GetPileInfo()
        {
            return mPileInfo;
        }

        /// <summary>
        /// 设置桩信息
        /// </summary>
        /// <param name="pileInfo">CPileInfo 实列</param>
        public void SetPileInfo(CPileInfo pileInfo)
        {
            if (pileInfo != null)
            {
                this.mPileInfo = pileInfo;
            }
        }

        /// <summary>
        /// 获取文件名
        /// </summary>
        /// <returns></returns>
        public String GetFileName()
        {
            return mFileName;
        }

        /// <summary>
        /// 设置文件名
        /// </summary>
        /// <param name="fileName">文件名</param>
        public void SetFileName(string fileName)
        {
            this.mFileName = fileName;
        }
    }
}
