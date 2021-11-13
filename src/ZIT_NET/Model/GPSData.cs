using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Frame;

namespace ZIT_NET.Model
{
    public class GPSData : ICustomSerializable
    {
        public long longTime;
        public double mCourse;
        public double mLatitude;
        public double mLongitude;
        public double mSpeed;
        public int mUseful;
        public char mcLatitude;
        public char mcLongitude;
        public SystemTimeData sysTime;

        public char[] mReservel = { '1', '2', '3', '4' };
        public char[] mReservel2 = { '1', '2', '3', '4', '5', '6' };

        public int WriteToStream(FileStream fs)
        {
            sysTime = new SystemTimeData();
            int len = sysTime.WriteToStream(fs);
            byte[] bs = DataTransfer.IntToBytes(mUseful);
            fs.Write(bs, 0, bs.Length);
            int len2 = len + bs.Length;
            byte[] bs2 = DataTransfer.CharToBytes(mReservel);
            fs.Write(bs2, 0, bs2.Length);
            int len3 = len2 + bs2.Length;
            byte[] bs3 = DataTransfer.DoubleToBytes(mLongitude);
            fs.Write(bs3, 0, bs3.Length);
            int len4 = len3 + bs3.Length;
            byte[] bs4 = DataTransfer.DoubleToBytes(mLatitude);
            fs.Write(bs4, 0, bs4.Length);
            int len5 = len4 + bs4.Length;
            byte[] bs5 = DataTransfer.DoubleToBytes(mSpeed);
            fs.Write(bs5, 0, bs5.Length);
            int len6 = len5 + bs5.Length;
            byte[] bs6 = DataTransfer.DoubleToBytes(mCourse);
            fs.Write(bs6, 0, bs6.Length);
            int len7 = len6 + bs6.Length;
            byte[] bs7 = DataTransfer.CharToBytes(mcLongitude);
            fs.Write(bs7, 0, bs7.Length);
            int len8 = len7 + bs7.Length;
            byte[] bs8 = DataTransfer.CharToBytes(mcLatitude);
            fs.Write(bs8, 0, bs8.Length);
            int len9 = len8 + bs8.Length;
            byte[] bs9 = DataTransfer.CharToBytes(mReservel2);
            fs.Write(bs9, 0, bs9.Length);
            return len9 + bs9.Length;
        }


        public int InitFromStream(FileStream fs)
        {
            int readLen = 0;
            try
            {
                sysTime = new SystemTimeData();
                byte[] bs = new byte[4];
                int readLen2 = sysTime.InitFromStream(fs) + fs.Read(bs, 0, 4);
                mUseful = DataTransfer.ByteToInt(bs);
                byte[] bs2 = new byte[4];
                int readLen3 = readLen2 + fs.Read(bs2, 0, 4);
                mReservel = DataTransfer.ByteToCharArray(bs2);
                byte[] bs3 = new byte[8];
                mLongitude = DataTransfer.ByteToDouble(bs3);
                byte[] bs4 = new byte[8];
                mLatitude = DataTransfer.ByteToDouble(bs4);
                byte[] bs5 = new byte[8];
                mSpeed = DataTransfer.ByteToDouble(bs5);
                byte[] bs6 = new byte[8];
                mCourse = DataTransfer.ByteToDouble(bs6);
                byte[] bs7 = new byte[1];
                int readLen4 = readLen3 + fs.Read(bs3, 0, 8) + fs.Read(bs4, 0, 8) + fs.Read(bs5, 0, 8) + fs.Read(bs6, 0, 8) + fs.Read(bs7, 0, 1);
                mcLongitude = (char)bs7[0];
                byte[] bs8 = new byte[1];
                int readLen5 = readLen4 + fs.Read(bs8, 0, 1);
                mcLatitude = (char)bs8[0];
                byte[] bs9 = new byte[6];
                readLen = readLen5 + fs.Read(bs9, 0, 6);
                mReservel2 = DataTransfer.ByteToCharArray(bs9);
                return readLen;
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
                return readLen;
            }
        }
    }
}
