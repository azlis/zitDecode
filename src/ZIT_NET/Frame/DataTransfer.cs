using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace ZIT_NET.Frame
{
    public class DataTransfer
    {
        protected static readonly char[] hexArray = "0123456789ABCDEF".ToCharArray();
        public static byte[] IntToBytes(int i)
        {
            return new byte[] { (byte)((i >> 24) & 255), (byte)((i >> 16) & 255), (byte)((i >> 8) & 255), (byte)(i & 255) };
        }

        public static int ByteToInt(byte[] b)
        {
            return ((b[0] & 255) << 24) | ((b[1] & 255) << 16) | ((b[2] & 255) << 8) | (b[3] & 255);
        }

        public static byte[] CharToBytes(char c)
        {
            return new byte[] { (byte)c };
        }

        public static byte[] CharToBytes(char[] cArr)
        {
            byte[] result = new byte[cArr.Length];
            for (int i = 0; i < cArr.Length; i++)
            {
                result[i] = (byte)cArr[i];
            }
            return result;
        }

        public static long CharTolong(char[] cArr)
        {
            long l = 0;
            for (int i = cArr.Length - 1; i >= 0; i--)
            {
                l |= (long)cArr[(cArr.Length - 1) - i] << (i * 2);
            }
            return l;
        }

        public static char[] ByteToCharArray(byte[] bArr, int start)
        {
            int bl = bArr.Length - start;
            char[] cArr = new char[bl];
            for (int i = 0; i < bl; i++)
            {
                cArr[i] = (char)bArr[i];
            }
            return cArr;
        }

        public static char[] ByteToCharArray(byte[] bArr)
        {
            return ByteToCharArray(bArr, 0);
        }

        public static string ByteToString(byte[] bArr)
        {
            try
            {
                Encoding.RegisterProvider(CodePagesEncodingProvider.Instance);
                var v = Encoding.GetEncoding("GB2312").GetString(bArr);
                return v;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
                return "";
            }
        }

        public static byte[] ShortToBytes(short s)
        {
            return new byte[] { (byte)(s & 255), (byte)((65280 & s) >> 8) };
        }

        public static byte[] ShortToBytes(short[] sArr)
        {
            byte[] result = new byte[sArr.Length * 2];
            for (int i = 0; i < sArr.Length; i++)
            {
                result[(i * 2) + 1] = (byte)((sArr[i] & 65280) >> 8);
                result[i * 2] = (byte)(sArr[i] & 255);
            }
            return result;
        }

        public static byte[] FloatToBytes(float f)
        {
            //int i = Float.floatToIntBits(f);
            int i = BitConverter.SingleToInt32Bits(f);
            return new byte[] { (byte)(i & 255), (byte)((i >> 8) & 255), (byte)((i >> 16) & 255), (byte)((i >> 24) & 255) };
        }

        public static byte[] FloatToBytes(float[] fArr)
        {
            byte[] result = new byte[fArr.Length * 4];
            for (int i = 0; i < fArr.Length; i++)
            {
                //int ii = Float.floatToIntBits(fArr[i]);

                int ii = BitConverter.SingleToInt32Bits(fArr[i]);
                result[(i * 4) + 3] = (byte)((ii >> 24) & 255);
                result[(i * 4) + 2] = (byte)((ii >> 16) & 255);
                result[(i * 4) + 1] = (byte)((ii >> 8) & 255);
                result[i * 4] = (byte)(ii & 255);
            }
            return result;
        }

        public static float ByteToFloat(byte[] bArr, int start)
        {
            int accum = 0;
            for (int i = 0; i < 4; i++)
            {
                accum |= (bArr[start + i] & 255) << (i * 8);
            }
            //return Float.intBitsToFloat(accum);
            var b = BitConverter.GetBytes(accum);
            return BitConverter.ToSingle(b, 0);
        }

        public static float ByteToFloat(byte[] bArr)
        {
            return ByteToFloat(bArr, 0);
        }

        public static float ThreeBytesToFloat(byte[] bArr, int start)
        {
            int accum = 0;
            if (bArr.Length > start + 2)
            {
                for (int i = 0; i < 3; i++)
                {
                    accum += (bArr[(start - i) + 2] & 255) << (i * 8);
                }
            }
            if ((accum & 8388608) == 8388608)
            {
                accum ^= -16777216;
            }
            return ((float)(accum * -1)) * 7.8125E-4f;
        }

        public static float[] ByteToFloatArray(byte[] bArr)
        {
            int fl = bArr.Length / 4;
            float[] fArr = new float[fl];
            for (int i = 0; i < fl; i++)
            {
                fArr[i] = ByteToFloat(bArr, i * 4);
            }
            return fArr;
        }

        public static short ByteToShort(byte[] bArr, int start)
        {
            return (short)(((bArr[start + 1] & 255) << 8) | (bArr[start] & 255));
        }

        public static short ByteToShort(byte[] b)
        {
            return ByteToShort(b, 0);
        }

        public static short[] ByteToShortArray(byte[] b)
        {
            int sl = b.Length / 2;
            short[] sArr = new short[sl];
            for (int i = 0; i < sl; i++)
            {
                sArr[i] = ByteToShort(b, i * 2);
            }
            return sArr;
        }

        public static double ByteToDouble(byte[] b)
        {
            var v = (b[0] & -16777216) | (b[1] & 16711680) | (b[2] & 65280) | (b[3] & 255) | (b[4] & -16777216) | (b[5] & 16711680) | (b[6] & 65280) | (b[7] & 255);

            // return double.longBitsToDouble();
            return BitConverter.Int64BitsToDouble(v);
        }

        public static byte[] DoubleToBytes(double d)
        {
            byte[] b = new byte[8];

            //long l = Double.doubleToLongBits(d);
            long l = BitConverter.DoubleToInt64Bits(d);

            for (int i = 0; i < 4; i++)
            {
                b[i] = (byte)l;
                //b[i] = new Long(l).byteValue();
                l >>= 8;
            }
            return b;
        }

        public static string BytesToHex(byte[] bytes)
        {
            char[] hexChars = new char[bytes.Length * 2];
            for (int j = 0; j < bytes.Length; j++)
            {
                int v = bytes[j] & 255;
                //hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2] = hexArray[(uint)v >> 4];
                hexChars[(j * 2) + 1] = hexArray[v & 15];
            }
            return new string(hexChars);
        }

        public static byte[] StringToBytes(string str, int len)
        {
            byte[] bArr = new byte[len];
            try
            {
                byte[] bytes = new byte[0];
                if (str != null)
                {
                    Encoding.RegisterProvider(CodePagesEncodingProvider.Instance);
                    bytes = Encoding.GetEncoding("GB2312").GetBytes(str);
                }
                for (int i = 0; i < len; i++)
                {
                    if (i < bytes.Length)
                    {
                        bArr[i] = bytes[i];
                    }
                    else
                    {
                        bArr[i] = 0;
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return bArr;
        }
    }
}
