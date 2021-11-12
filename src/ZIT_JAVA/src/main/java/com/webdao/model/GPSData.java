package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/* loaded from: classes.dex */
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

    public String toLatitudeString()
    {
        int[] degs = dec2deg(this.mLatitude);
        return String.format(Locale.getDefault(), "%1$s:%2$d\u00b0%3$d\u2032%4$d\u2033", Character.valueOf(this.mcLatitude), Integer.valueOf(degs[0]), Integer.valueOf(degs[1]), Integer.valueOf(degs[2]));
    }

    public String toLongitudeString()
    {
        int[] degs = dec2deg(this.mLongitude);
        return String.format(Locale.getDefault(), "%1$s:%2$d\u00b0%3$d\u2032%4$d\u2033", Character.valueOf(this.mcLongitude), Integer.valueOf(degs[0]), Integer.valueOf(degs[1]), Integer.valueOf(degs[2]));
    }

    private int[] dec2deg(double deci)
    {
        String[] decArr = (Math.abs(deci) + XmlPullParser.NO_NAMESPACE).split("\\.");
        int deg = Integer.valueOf(decArr[0]).intValue();
        BigDecimal min_sec = new BigDecimal("0." + decArr[1]).multiply(new BigDecimal(3600));
        int min = (int) Math.floor((double) min_sec.divide(new BigDecimal(60)).floatValue());
        return new int[]{deg, min, min_sec.subtract(new BigDecimal(min * 60)).intValue()};
    }

    @Override // com.webdao.frame.CustomSerializable
    public int writeToStream(OutputStream os) throws IOException
    {
        this.sysTime = new SystemTimeData();
        int len = 0 + this.sysTime.writeToStream(os);
        byte[] bs = DataTransfer.intToBytes(this.mUseful);
        os.write(bs, 0, bs.length);
        int len2 = len + bs.length;
        byte[] bs2 = DataTransfer.charToBytes(this.mReservel);
        os.write(bs2, 0, bs2.length);
        int len3 = len2 + bs2.length;
        byte[] bs3 = DataTransfer.doubleToBytes(this.mLongitude);
        os.write(bs3, 0, bs3.length);
        int len4 = len3 + bs3.length;
        byte[] bs4 = DataTransfer.doubleToBytes(this.mLatitude);
        os.write(bs4, 0, bs4.length);
        int len5 = len4 + bs4.length;
        byte[] bs5 = DataTransfer.doubleToBytes(this.mSpeed);
        os.write(bs5, 0, bs5.length);
        int len6 = len5 + bs5.length;
        byte[] bs6 = DataTransfer.doubleToBytes(this.mCourse);
        os.write(bs6, 0, bs6.length);
        int len7 = len6 + bs6.length;
        byte[] bs7 = DataTransfer.charToBytes(this.mcLongitude);
        os.write(bs7, 0, bs7.length);
        int len8 = len7 + bs7.length;
        byte[] bs8 = DataTransfer.charToBytes(this.mcLatitude);
        os.write(bs8, 0, bs8.length);
        int len9 = len8 + bs8.length;
        byte[] bs9 = DataTransfer.charToBytes(this.mReservel2);
        os.write(bs9, 0, bs9.length);
        return len9 + bs9.length;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int initFromStream(InputStream is)
    {
        int readLen = 0;
        try
        {
            this.sysTime = new SystemTimeData();
            byte[] bs = new byte[4];
            int readLen2 = 0 + this.sysTime.initFromStream(is) + is.read(bs, 0, 4);
            this.mUseful = DataTransfer.byteToInt(bs);
            byte[] bs2 = new byte[4];
            int readLen3 = readLen2 + is.read(bs2, 0, 4);
            this.mReservel = DataTransfer.byteToCharArray(bs2);
            byte[] bs3 = new byte[8];
            this.mLongitude = DataTransfer.byteToDouble(bs3);
            byte[] bs4 = new byte[8];
            this.mLatitude = DataTransfer.byteToDouble(bs4);
            byte[] bs5 = new byte[8];
            this.mSpeed = DataTransfer.byteToDouble(bs5);
            byte[] bs6 = new byte[8];
            this.mCourse = DataTransfer.byteToDouble(bs6);
            byte[] bs7 = new byte[1];
            int readLen4 = readLen3 + is.read(bs3, 0, 8) + is.read(bs4, 0, 8) + is.read(bs5, 0, 8) + is.read(bs6, 0, 8) + is.read(bs7, 0, 1);
            this.mcLongitude = (char) bs7[0];
            byte[] bs8 = new byte[1];
            int readLen5 = readLen4 + is.read(bs8, 0, 1);
            this.mcLatitude = (char) bs8[0];
            byte[] bs9 = new byte[6];
            readLen = readLen5 + is.read(bs9, 0, 6);
            this.mReservel2 = DataTransfer.byteToCharArray(bs9);
            return readLen;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return readLen;
        }
    }
}
