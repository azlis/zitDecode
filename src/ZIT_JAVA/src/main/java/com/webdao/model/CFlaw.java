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

    @Override // com.webdao.frame.CustomSerializable
    public int writeToStream(OutputStream os) throws IOException
    {
        byte[] bs = DataTransfer.shortToBytes(this.mFlawType);
        os.write(bs, 0, bs.length);
        int len = 0 + bs.length;
        byte[] bs2 = DataTransfer.floatToBytes(this.mFlawPosition);
        os.write(bs2, 0, bs2.length);
        int len2 = len + bs2.length;
        byte[] bs3 = DataTransfer.shortToBytes(this.mFlawGrade);
        os.write(bs3, 0, bs3.length);
        return len2 + bs3.length;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int initFromStream(InputStream is)
    {
        int readLen = 0;
        try
        {
            byte[] bs = new byte[2];
            int readLen2 = 0 + is.read(bs, 0, 2);
            this.mFlawType = DataTransfer.byteToShort(bs);
            byte[] bs2 = new byte[4];
            int readLen3 = readLen2 + is.read(bs2, 0, 4);
            this.mFlawPosition = DataTransfer.byteToFloat(bs2);
            byte[] bs3 = new byte[2];
            readLen = readLen3 + is.read(bs3, 0, 2);
            this.mFlawGrade = DataTransfer.byteToShort(bs3);
            return readLen;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return readLen;
        }
    }
}
