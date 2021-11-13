package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CFlaw implements CustomSerializable
{
    public static final short BREAK = 2;
    public static short COMMON = 0;
    public static final short DEFECT = 6;
    public static final short FLAW = 5;
    public static final short LIQUATE = 3;
    public static short LiGHT = 0;
    public static final short MUD = 4;
    public static final short NECKFAT = 1;
    public static final short NECKTHIN = 0;
    public static final short NONE = -1;
    public static short SERIOUS;
    public short mFlawGrade;
    public float mFlawPosition;
    public short mFlawType;
    public int mPosIndex;

    public CFlaw(int type, int grade, float position)
    {
        this.mFlawType = (short) type;
        this.mFlawGrade = (short) grade;
        this.mFlawPosition = position;
    }

    public CFlaw()
    {
    }

    @Override
    public int WriteToStream(OutputStream os) throws IOException
    {
        byte[] bs = DataTransfer.ShortToBytes(this.mFlawType);
        os.write(bs, 0, bs.length);
        int len = bs.length;
        byte[] bs2 = DataTransfer.FloatToBytes(this.mFlawPosition);
        os.write(bs2, 0, bs2.length);
        int len2 = len + bs2.length;
        byte[] bs3 = DataTransfer.ShortToBytes(this.mFlawGrade);
        os.write(bs3, 0, bs3.length);
        return len2 + bs3.length;
    }

    @Override
    public int InitFromStream(InputStream is)
    {
        int readLen = 0;
        try
        {
            byte[] bs = new byte[2];
            int readLen2 = is.read(bs, 0, 2);
            this.mFlawType = DataTransfer.ByteToShort(bs);
            byte[] bs2 = new byte[4];
            int readLen3 = readLen2 + is.read(bs2, 0, 4);
            this.mFlawPosition = DataTransfer.ByteToFloat(bs2);
            byte[] bs3 = new byte[2];
            readLen = readLen3 + is.read(bs3, 0, 2);
            this.mFlawGrade = DataTransfer.ByteToShort(bs3);
            return readLen;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return readLen;
        }
    }
}
