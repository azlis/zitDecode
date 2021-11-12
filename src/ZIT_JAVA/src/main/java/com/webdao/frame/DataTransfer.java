package com.webdao.frame;

import java.io.UnsupportedEncodingException;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class DataTransfer {
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static int byteToInt(byte[] b) {
        return ((b[0] & 255) << 24) | ((b[1] & 255) << 16) | ((b[2] & 255) << 8) | (b[3] & 255);
    }

    public static byte[] charToBytes(char c) {
        return new byte[]{(byte) c};
    }

    public static byte[] charToBytes(char[] cArr) {
        byte[] result = new byte[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            result[i] = (byte) cArr[i];
        }
        return result;
    }

    public static long charTolong(char[] cArr) {
        long l = 0;
        for (int i = cArr.length - 1; i >= 0; i--) {
            l |= (long) (cArr[(cArr.length - 1) - i] << (i * 2));
        }
        return l;
    }

    public static char[] byteToCharArray(byte[] bArr, int start) {
        int bl = bArr.length - start;
        char[] cArr = new char[bl];
        for (int i = 0; i < bl; i++) {
            cArr[i] = (char) bArr[i];
        }
        return cArr;
    }

    public static char[] byteToCharArray(byte[] bArr) {
        return byteToCharArray(bArr, 0);
    }

    public static String byteToString(byte[] bArr) {
        try {
            return new String(bArr, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return XmlPullParser.NO_NAMESPACE;
        }
    }

    public static byte[] shortToBytes(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((65280 & s) >> 8)};
    }

    public static byte[] shortToBytes(short[] sArr) {
        byte[] result = new byte[sArr.length * 2];
        for (int i = 0; i < sArr.length; i++) {
            result[(i * 2) + 1] = (byte) ((sArr[i] & 65280) >> 8);
            result[i * 2] = (byte) (sArr[i] & 255);
        }
        return result;
    }

    public static byte[] floatToBytes(float f) {
        int i = Float.floatToIntBits(f);
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static byte[] floatToBytes(float[] fArr) {
        byte[] result = new byte[fArr.length * 4];
        for (int i = 0; i < fArr.length; i++) {
            int ii = Float.floatToIntBits(fArr[i]);
            result[(i * 4) + 3] = (byte) ((ii >> 24) & 255);
            result[(i * 4) + 2] = (byte) ((ii >> 16) & 255);
            result[(i * 4) + 1] = (byte) ((ii >> 8) & 255);
            result[i * 4] = (byte) (ii & 255);
        }
        return result;
    }

    public static float byteToFloat(byte[] bArr, int start) {
        int accum = 0;
        for (int i = 0; i < 4; i++) {
            accum |= (bArr[start + i] & 255) << (i * 8);
        }
        return Float.intBitsToFloat(accum);
    }

    public static float byteToFloat(byte[] bArr) {
        return byteToFloat(bArr, 0);
    }

    public static float threeBytesToFloat(byte[] bArr, int start) {
        int accum = 0;
        if (bArr.length > start + 2) {
            for (int i = 0; i < 3; i++) {
                accum += (bArr[(start - i) + 2] & 255) << (i * 8);
            }
        }
        if ((accum & 8388608) == 8388608) {
            accum ^= -16777216;
        }
        return ((float) (accum * -1)) * 7.8125E-4f;
    }

    public static float[] byteToFloatArray(byte[] bArr) {
        int fl = bArr.length / 4;
        float[] fArr = new float[fl];
        for (int i = 0; i < fl; i++) {
            fArr[i] = byteToFloat(bArr, i * 4);
        }
        return fArr;
    }

    public static short byteToShort(byte[] bArr, int start) {
        return (short) (((bArr[start + 1] & 255) << 8) | (bArr[start] & 255));
    }

    public static short byteToShort(byte[] b) {
        return byteToShort(b, 0);
    }

    public static short[] byteToShortArray(byte[] b) {
        int sl = b.length / 2;
        short[] sArr = new short[sl];
        for (int i = 0; i < sl; i++) {
            sArr[i] = byteToShort(b, i * 2);
        }
        return sArr;
    }

    public static double byteToDouble(byte[] b) {
        return Double.longBitsToDouble((long) ((b[0] & -16777216) | (b[1] & 16711680) | (b[2] & 65280) | (b[3] & 255) | (b[4] & -16777216) | (b[5] & 16711680) | (b[6] & 65280) | (b[7] & 255)));
    }

    public static byte[] doubleToBytes(double d) {
        byte[] b = new byte[8];
        long l = Double.doubleToLongBits(d);
        for (int i = 0; i < 4; i++) {
            b[i] = new Long(l).byteValue();
            l >>= 8;
        }
        return b;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    public static byte[] stringToBytes(String str, int len) {
        byte[] bArr = new byte[len];
        try {
            byte[] bytes = new byte[0];
            if (str != null) {
                bytes = str.getBytes("GB2312");
            }
            for (int i = 0; i < len; i++) {
                if (i < bytes.length) {
                    bArr[i] = bytes[i];
                } else {
                    bArr[i] = 0;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bArr;
    }
}
