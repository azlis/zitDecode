package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

/* loaded from: classes.dex */
public class SystemTimeData implements CustomSerializable {
    public short mDay;
    public short mDayOfWeek;
    public short mHour;
    public short mMonth;
    public short mYear;
    public short wMilliseconds;
    public short wMinute;
    public short wSecond;

    public SystemTimeData() {
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

    public void fromLongTicks(long time) {
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

    @Override // com.webdao.frame.CustomSerializable
    public int writeToStream(OutputStream os) throws IOException {
        byte[] bs = DataTransfer.shortToBytes(this.mYear);
        os.write(bs, 0, bs.length);
        int len = 0 + bs.length;
        byte[] bs2 = DataTransfer.shortToBytes(this.mMonth);
        os.write(bs2, 0, bs2.length);
        int len2 = len + bs2.length;
        byte[] bs3 = DataTransfer.shortToBytes(this.mDayOfWeek);
        os.write(bs3, 0, bs3.length);
        int len3 = len2 + bs3.length;
        byte[] bs4 = DataTransfer.shortToBytes(this.mDay);
        os.write(bs4, 0, bs4.length);
        int len4 = len3 + bs4.length;
        byte[] bs5 = DataTransfer.shortToBytes(this.mHour);
        os.write(bs5, 0, bs5.length);
        int len5 = len4 + bs5.length;
        byte[] bs6 = DataTransfer.shortToBytes(this.wMinute);
        os.write(bs6, 0, bs6.length);
        int len6 = len5 + bs6.length;
        byte[] bs7 = DataTransfer.shortToBytes(this.wSecond);
        os.write(bs7, 0, bs7.length);
        int len7 = len6 + bs7.length;
        byte[] bs8 = DataTransfer.shortToBytes(this.wMilliseconds);
        os.write(bs8, 0, bs8.length);
        return len7 + bs8.length;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int initFromStream(InputStream is) {
        int readLen = 0;
        try {
            byte[] bs = new byte[2];
            int readLen2 = 0 + is.read(bs, 0, 2);
            this.mYear = DataTransfer.byteToShort(bs);
            byte[] bs2 = new byte[2];
            int readLen3 = readLen2 + is.read(bs2, 0, 2);
            this.mMonth = DataTransfer.byteToShort(bs2);
            byte[] bs3 = new byte[2];
            int readLen4 = readLen3 + is.read(bs3, 0, 2);
            this.mDayOfWeek = DataTransfer.byteToShort(bs3);
            byte[] bs4 = new byte[2];
            int readLen5 = readLen4 + is.read(bs4, 0, 2);
            this.mDay = DataTransfer.byteToShort(bs4);
            byte[] bs5 = new byte[2];
            int readLen6 = readLen5 + is.read(bs5, 0, 2);
            this.mHour = DataTransfer.byteToShort(bs5);
            byte[] bs6 = new byte[2];
            int readLen7 = readLen6 + is.read(bs6, 0, 2);
            this.wMinute = DataTransfer.byteToShort(bs6);
            byte[] bs7 = new byte[2];
            int readLen8 = readLen7 + is.read(bs7, 0, 2);
            this.wSecond = DataTransfer.byteToShort(bs7);
            byte[] bs8 = new byte[2];
            readLen = readLen8 + is.read(bs8, 0, 2);
            this.wMilliseconds = DataTransfer.byteToShort(bs8);
            return readLen;
        } catch (IOException e) {
            e.printStackTrace();
            return readLen;
        }
    }
}
