package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;


public class SystemTimeData implements CustomSerializable
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
        Calendar date = Calendar.getInstance();
        this.mYear = (short) date.get(1);
        this.mMonth = (short) date.get(2);
        this.mDayOfWeek = (short) date.get(7);
        this.mDay = (short) date.get(5);
        this.mHour = (short) (date.get(10) - 8);
        this.wMinute = (short) date.get(12);
        this.wSecond = (short) date.get(13);
        this.wMilliseconds = (short) date.get(14);
    }

    public void FromLongTicks(long time)
    {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time);
        this.mYear = (short) date.get(1);
        this.mMonth = (short) date.get(2);
        this.mDayOfWeek = (short) date.get(7);
        this.mDay = (short) date.get(5);
        this.mHour = (short) (date.get(10) - 8);
        this.wMinute = (short) date.get(12);
        this.wSecond = (short) date.get(13);
        this.wMilliseconds = (short) date.get(14);
    }

    @Override
    public int WriteToStream(OutputStream os) throws IOException
    {
        byte[] bs = DataTransfer.ShortToBytes(this.mYear);
        os.write(bs, 0, bs.length);
        int len = bs.length;
        byte[] bs2 = DataTransfer.ShortToBytes(this.mMonth);
        os.write(bs2, 0, bs2.length);
        int len2 = len + bs2.length;
        byte[] bs3 = DataTransfer.ShortToBytes(this.mDayOfWeek);
        os.write(bs3, 0, bs3.length);
        int len3 = len2 + bs3.length;
        byte[] bs4 = DataTransfer.ShortToBytes(this.mDay);
        os.write(bs4, 0, bs4.length);
        int len4 = len3 + bs4.length;
        byte[] bs5 = DataTransfer.ShortToBytes(this.mHour);
        os.write(bs5, 0, bs5.length);
        int len5 = len4 + bs5.length;
        byte[] bs6 = DataTransfer.ShortToBytes(this.wMinute);
        os.write(bs6, 0, bs6.length);
        int len6 = len5 + bs6.length;
        byte[] bs7 = DataTransfer.ShortToBytes(this.wSecond);
        os.write(bs7, 0, bs7.length);
        int len7 = len6 + bs7.length;
        byte[] bs8 = DataTransfer.ShortToBytes(this.wMilliseconds);
        os.write(bs8, 0, bs8.length);
        return len7 + bs8.length;
    }

    @Override
    public int InitFromStream(InputStream stream)
    {
        int readLen = 0;
        try
        {
            byte[] bs = new byte[2];
            int readLen2 = stream.read(bs, 0, 2);
            this.mYear = DataTransfer.ByteToShort(bs);
            byte[] bs2 = new byte[2];
            int readLen3 = readLen2 + stream.read(bs2, 0, 2);
            this.mMonth = DataTransfer.ByteToShort(bs2);
            byte[] bs3 = new byte[2];
            int readLen4 = readLen3 + stream.read(bs3, 0, 2);
            this.mDayOfWeek = DataTransfer.ByteToShort(bs3);
            byte[] bs4 = new byte[2];
            int readLen5 = readLen4 + stream.read(bs4, 0, 2);
            this.mDay = DataTransfer.ByteToShort(bs4);
            byte[] bs5 = new byte[2];
            int readLen6 = readLen5 + stream.read(bs5, 0, 2);
            this.mHour = DataTransfer.ByteToShort(bs5);
            byte[] bs6 = new byte[2];
            int readLen7 = readLen6 + stream.read(bs6, 0, 2);
            this.wMinute = DataTransfer.ByteToShort(bs6);
            byte[] bs7 = new byte[2];
            int readLen8 = readLen7 + stream.read(bs7, 0, 2);
            this.wSecond = DataTransfer.ByteToShort(bs7);
            byte[] bs8 = new byte[2];
            readLen = readLen8 + stream.read(bs8, 0, 2);
            this.wMilliseconds = DataTransfer.ByteToShort(bs8);
            return readLen;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return readLen;
        }
    }
}
