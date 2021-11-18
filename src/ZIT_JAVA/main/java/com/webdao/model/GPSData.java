package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;


public class GPSData implements CustomSerializable
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
    DecimalFormat df = new DecimalFormat("0.##");
    public char[] mReservel = {'1', '2', '3', '4'};
    public char[] mReservel2 = {'1', '2', '3', '4', '5', '6'};

    public String ToLatitudeString()
    {
        int[] degs = Dec2deg(this.mLatitude);
        return String.format(Locale.getDefault(), "%1$s:%2$d\u00b0%3$d\u2032%4$d\u2033", this.mcLatitude, degs[0], degs[1], degs[2]);
    }

    public String ToLongitudeString()
    {
        int[] degs = Dec2deg(this.mLongitude);
        return String.format(Locale.getDefault(), "%1$s:%2$d\u00b0%3$d\u2032%4$d\u2033", this.mcLongitude, degs[0], degs[1], degs[2]);
    }

    private int[] Dec2deg(double deci)
    {
        String[] decArr = (Math.abs(deci) + "").split("\\.");
        int deg = Integer.valueOf(decArr[0]);
        BigDecimal min_sec = new BigDecimal("0." + decArr[1]).multiply(new BigDecimal(3600));
        int min = (int) Math.floor(min_sec.divide(new BigDecimal(60)).floatValue());
        return new int[]{deg, min, min_sec.subtract(new BigDecimal(min * 60)).intValue()};
    }

    @Override
    public int WriteToStream(OutputStream os) throws IOException
    {
        this.sysTime = new SystemTimeData();
        int len = this.sysTime.WriteToStream(os);
        byte[] bs = DataTransfer.IntToBytes(this.mUseful);
        os.write(bs, 0, bs.length);
        int len2 = len + bs.length;
        byte[] bs2 = DataTransfer.CharToBytes(this.mReservel);
        os.write(bs2, 0, bs2.length);
        int len3 = len2 + bs2.length;
        byte[] bs3 = DataTransfer.DoubleToBytes(this.mLongitude);
        os.write(bs3, 0, bs3.length);
        int len4 = len3 + bs3.length;
        byte[] bs4 = DataTransfer.DoubleToBytes(this.mLatitude);
        os.write(bs4, 0, bs4.length);
        int len5 = len4 + bs4.length;
        byte[] bs5 = DataTransfer.DoubleToBytes(this.mSpeed);
        os.write(bs5, 0, bs5.length);
        int len6 = len5 + bs5.length;
        byte[] bs6 = DataTransfer.DoubleToBytes(this.mCourse);
        os.write(bs6, 0, bs6.length);
        int len7 = len6 + bs6.length;
        byte[] bs7 = DataTransfer.CharToBytes(this.mcLongitude);
        os.write(bs7, 0, bs7.length);
        int len8 = len7 + bs7.length;
        byte[] bs8 = DataTransfer.CharToBytes(this.mcLatitude);
        os.write(bs8, 0, bs8.length);
        int len9 = len8 + bs8.length;
        byte[] bs9 = DataTransfer.CharToBytes(this.mReservel2);
        os.write(bs9, 0, bs9.length);
        return len9 + bs9.length;
    }

    @Override
    public int InitFromStream(InputStream is)
    {
        int readLen = 0;
        try
        {
            this.sysTime = new SystemTimeData();
            byte[] bs = new byte[4];
            int readLen2 = this.sysTime.InitFromStream(is) + is.read(bs, 0, 4);
            this.mUseful = DataTransfer.ByteToInt(bs);
            byte[] bs2 = new byte[4];
            int readLen3 = readLen2 + is.read(bs2, 0, 4);
            this.mReservel = DataTransfer.ByteToCharArray(bs2);
            byte[] bs3 = new byte[8];
            this.mLongitude = DataTransfer.ByteToDouble(bs3);
            byte[] bs4 = new byte[8];
            this.mLatitude = DataTransfer.ByteToDouble(bs4);
            byte[] bs5 = new byte[8];
            this.mSpeed = DataTransfer.ByteToDouble(bs5);
            byte[] bs6 = new byte[8];
            this.mCourse = DataTransfer.ByteToDouble(bs6);
            byte[] bs7 = new byte[1];
            int readLen4 = readLen3 + is.read(bs3, 0, 8) + is.read(bs4, 0, 8) + is.read(bs5, 0, 8) + is.read(bs6, 0, 8) + is.read(bs7, 0, 1);
            this.mcLongitude = (char) bs7[0];
            byte[] bs8 = new byte[1];
            int readLen5 = readLen4 + is.read(bs8, 0, 1);
            this.mcLatitude = (char) bs8[0];
            byte[] bs9 = new byte[6];
            readLen = readLen5 + is.read(bs9, 0, 6);
            this.mReservel2 = DataTransfer.ByteToCharArray(bs9);
            return readLen;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return readLen;
        }
    }
}
