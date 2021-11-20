package com.webdao.frame;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class DataTransfer
{

    //写出文件ToByte

    public static byte[] ShortToBytes(short s)
    {
        var v = new byte[]{(byte) (s & 255), (byte) ((65280 & s) >> 8)};
        return v;
    }

    public static byte[] ShortToBytes(short[] sArr)
    {
        byte[] result = new byte[sArr.length * 2];
        for (int i = 0; i < sArr.length; i++)
        {
            result[(i * 2) + 1] = (byte) ((sArr[i] & 65280) >> 8);
            result[i * 2] = (byte) (sArr[i] & 255);
        }
        return result;
    }

    public static byte[] FloatToBytes(float f)
    {
        int i = Float.floatToIntBits(f);
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static byte[] FloatToBytes(float[] fArr)
    {
        byte[] result = new byte[fArr.length * 4];
        for (int i = 0; i < fArr.length; i++)
        {
            int ii = Float.floatToIntBits(fArr[i]);
            result[(i * 4) + 3] = (byte) ((ii >> 24) & 255);
            result[(i * 4) + 2] = (byte) ((ii >> 16) & 255);
            result[(i * 4) + 1] = (byte) ((ii >> 8) & 255);
            result[i * 4] = (byte) (ii & 255);
        }
        return result;
    }

    public static byte[] DoubleToBytes(double d)
    {
        byte[] b = new byte[8];
        long l = Double.doubleToLongBits(d);
        for (int i = 0; i < 4; i++)
        {
            b[i] = new Long(l).byteValue();
            l >>= 8;
        }
        return b;
    }

    public static byte[] StringToBytes(String str, int len)
    {
        byte[] bArr = new byte[len];
        try
        {
            byte[] bytes = new byte[0];
            if (str != null)
            {
                bytes = str.getBytes("GB2312");
            }
            for (int i = 0; i < len; i++)
            {
                if (i < bytes.length)
                {
                    bArr[i] = bytes[i];
                }
                else
                {
                    bArr[i] = 0;
                }
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        //var a = bytesToHexString(bArr);
        //var s  = a.length();
        return bArr;
    }

    public static String bytesToHexString(byte[] src)
    {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0)
        {
            return null;
        }
        for (int i = 0; i < src.length; i++)
        {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2)
            {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] CharToBytes(char c)
    {
        var b=new byte[]{(byte) c};
        var a = bytesToHexString(b);
        var s  = a.length();
        return new byte[]{(byte) c};
    }

    public static byte[] CharToBytes(char[] cArr)
    {
        byte[] result = new byte[cArr.length];
        for (int i = 0; i < cArr.length; i++)
        {
            result[i] = (byte) cArr[i];
        }
        return result;
    }

    public static byte[] IntToBytes(int i)
    {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    //读取文件ByteTo

    public static float ByteToFloat(byte[] bArr, int start)
    {
        int accum = 0;
        for (int i = 0; i < 4; i++)
        {
            accum |= (bArr[start + i] & 255) << (i * 8);
        }
        return Float.intBitsToFloat(accum);
    }

    public static float ByteToFloat(byte[] bArr)
    {
        return ByteToFloat(bArr, 0);
    }

    public static float[] ByteToFloatArray(byte[] bArr)
    {
        int fl = bArr.length / 4;
        float[] fArr = new float[fl];
        for (int i = 0; i < fl; i++)
        {
            fArr[i] = ByteToFloat(bArr, i * 4);
        }
        return fArr;
    }

    public static short ByteToShort(byte[] bArr, int start)
    {
        return (short) (((bArr[start + 1] & 255) << 8) | (bArr[start] & 255));
    }

    public static short ByteToShort(byte[] b)
    {
        return ByteToShort(b, 0);
    }

    public static short[] ByteToShortArray(byte[] b)
    {
        int sl = b.length / 2;
        short[] sArr = new short[sl];
        for (int i = 0; i < sl; i++)
        {
            sArr[i] = ByteToShort(b, i * 2);
        }
        return sArr;
    }

    public static double ByteToDouble(byte[] b)
    {
        return Double.longBitsToDouble((b[0] & -16777216) | (b[1] & 16711680) | (b[2] & 65280) | (b[3] & 255) | (b[4] & -16777216) | (b[5] & 16711680) | (b[6] & 65280) | (b[7] & 255));
    }

    public static char[] ByteToCharArray(byte[] bArr, int start)
    {
        int bl = bArr.length - start;
        char[] cArr = new char[bl];
        for (int i = 0; i < bl; i++)
        {
            cArr[i] = (char) bArr[i];
        }
        return cArr;
    }

    public static char[] ByteToCharArray(byte[] bArr)
    {
        return ByteToCharArray(bArr, 0);
    }

    public static String ByteToString(byte[] bArr)
    {
        try
        {
            return new String(bArr, "GB2312");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public static int ByteToInt(byte[] b)
    {
        return ((b[0] & 255) << 24) | ((b[1] & 255) << 16) | ((b[2] & 255) << 8) | (b[3] & 255);
    }


}
