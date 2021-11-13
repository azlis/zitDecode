using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Frame;

namespace ZIT_NET.Model
{
    public class SystemTimeData : ICustomSerializable
    {
        public short mDay;
        public short mDayOfWeek;
        public short mHour;
        public short mMonth;
        public short mYear;
        public short wMilliseconds;
        public short wMinute;
        public short wSecond;

        public SystemTimeData()
        {
            var dt = DateTime.UtcNow;
            mYear = (short)dt.Year;
            mMonth = (short)dt.Month;
            mDayOfWeek = (short)dt.DayOfWeek;
            mDay = (short)dt.Day;
            mHour = (short)dt.Hour;
            wMinute = (short)dt.Minute;
            wSecond = (short)dt.Second;
            wMilliseconds = (short)dt.Millisecond;
        }

        //public void FromLongTicks(long time)
        //{
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
        //}

        public int WriteToStream(FileStream fs)
        {
            byte[]
            bs = DataTransfer.ShortToBytes(mYear);
            fs.Read(bs, 0, bs.Length);
            int len = bs.Length;
            byte[]
            bs2 = DataTransfer.ShortToBytes(mMonth);
            fs.Read(bs2, 0, bs2.Length);
            int len2 = len + bs2.Length;
            byte[]
            bs3 = DataTransfer.ShortToBytes(mDayOfWeek);
            fs.Read(bs3, 0, bs3.Length);
            int len3 = len2 + bs3.Length;
            byte[]
            bs4 = DataTransfer.ShortToBytes(mDay);
            fs.Read(bs4, 0, bs4.Length);
            int len4 = len3 + bs4.Length;
            byte[]
            bs5 = DataTransfer.ShortToBytes(mHour);
            fs.Read(bs5, 0, bs5.Length);
            int len5 = len4 + bs5.Length;
            byte[]
            bs6 = DataTransfer.ShortToBytes(wMinute);
            fs.Read(bs6, 0, bs6.Length);
            int len6 = len5 + bs6.Length;
            byte[]
            bs7 = DataTransfer.ShortToBytes(wSecond);
            fs.Read(bs7, 0, bs7.Length);
            int len7 = len6 + bs7.Length;
            byte[]
            bs8 = DataTransfer.ShortToBytes(wMilliseconds);
            fs.Read(bs8, 0, bs8.Length);
            return len7 + bs8.Length;
        }


        public int InitFromStream(FileStream fs)
        {
            int readLen = 0;
            try
            {
                byte[] bs = new byte[2];
                int readLen2 = fs.Read(bs, 0, 2);
                mYear = DataTransfer.ByteToShort(bs);
                byte[] bs2 = new byte[2];
                int readLen3 = readLen2 + fs.Read(bs2, 0, 2);
                mMonth = DataTransfer.ByteToShort(bs2);
                byte[] bs3 = new byte[2];
                int readLen4 = readLen3 + fs.Read(bs3, 0, 2);
                mDayOfWeek = DataTransfer.ByteToShort(bs3);
                byte[] bs4 = new byte[2];
                int readLen5 = readLen4 + fs.Read(bs4, 0, 2);
                mDay = DataTransfer.ByteToShort(bs4);
                byte[] bs5 = new byte[2];
                int readLen6 = readLen5 + fs.Read(bs5, 0, 2);
                mHour = DataTransfer.ByteToShort(bs5);
                byte[] bs6 = new byte[2];
                int readLen7 = readLen6 + fs.Read(bs6, 0, 2);
                wMinute = DataTransfer.ByteToShort(bs6);
                byte[] bs7 = new byte[2];
                int readLen8 = readLen7 + fs.Read(bs7, 0, 2);
                wSecond = DataTransfer.ByteToShort(bs7);
                byte[] bs8 = new byte[2];
                readLen = readLen8 + fs.Read(bs8, 0, 2);
                wMilliseconds = DataTransfer.ByteToShort(bs8);
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
