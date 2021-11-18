package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CFileInfo implements CustomSerializable
{
    /**
     * 文件名
     */
    private String mFileName;
    /**
     * 桩信息
     */
    public CPileInfo mPileInfo;
    /**
     * 初始数据
     */
    public List<CPileData> mPileOriginalData;
    /**
     * 最终数据
     */
    public List<CPileData> mPileResultData;
    /**
     * 声波数量
     */
    public short mWaveNum;

    public CFileInfo(CPileInfo pileInfo)
    {
        this.mFileName = "1-1";
        this.mPileInfo = pileInfo;
        this.mPileResultData = new ArrayList();
        this.mPileOriginalData = new ArrayList();
    }

    public CFileInfo()
    {
        this.mFileName = "1-1";
        this.mPileInfo = new CPileInfo();
        this.mPileResultData = new ArrayList();
        this.mPileOriginalData = new ArrayList();
    }

    /**
     * 获取声波数量
     *
     * @return 声波数量
     */
    public short GetWaveNum()
    {
        if (this.mWaveNum == 0)
        {
            return (short) this.mPileResultData.size();
        }
        return this.mWaveNum;
    }

    /**
     * 清空桩信息
     */
    public void ClearPileData()
    {
        if (this.mPileOriginalData != null)
        {
            this.mPileOriginalData.clear();
        }
        if (this.mPileResultData != null)
        {
            this.mPileResultData.clear();
        }
        this.mWaveNum = 0;
    }

    /**
     * 写出流数据
     *
     * @param os
     * @return
     * @throws IOException
     */
    @Override
    public int WriteToStream(OutputStream os) throws IOException
    {
        int len = this.mPileInfo.WriteToStream(os);
        byte[] bs = DataTransfer.ShortToBytes(GetWaveNum());
        os.write(bs, 0, bs.length);
        int len2 = len + 2;
        if (this.mPileOriginalData != null)
        {
            for (int i = 0; i < this.mPileOriginalData.size(); i++)
            {
                CPileData pileData = this.mPileOriginalData.get(i);
                pileData.SetOrignalData(true);
                len2 += pileData.WriteToStream(os);
                //Log.d("Pile Write mPileOriginalData Length:", String.valueOf(len2));
            }
        }
        if (this.mPileResultData != null)
        {
            for (int i2 = 0; i2 < this.mPileResultData.size(); i2++)
            {
                CPileData pileData2 = this.mPileResultData.get(i2);
                pileData2.SetOrignalData(false);
                len2 += pileData2.WriteToStream(os);
                //Log.d("Pile Write mPileResultData Length*****:", String.valueOf(len2));
            }
        }
        return len2;
    }

    @Override
    public int InitFromStream(InputStream is)
    {
        this.mPileInfo = new CPileInfo();
        int readLen = this.mPileInfo.InitFromStream(is);
        byte[] bs = new byte[2];
        try
        {
            readLen += is.read(bs, 0, 2);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.mWaveNum = DataTransfer.ByteToShort(bs);
        this.mPileOriginalData = new ArrayList();
        for (int i = 0; i < this.mWaveNum; i++)
        {
            CPileData pileData = new CPileData();
            pileData.SetOrignalData(true);
            readLen += pileData.InitFromStream(is);
            this.mPileOriginalData.add(pileData);
            //Log.d("Pile Read mPileOriginalData Length:", String.valueOf(readLen));
        }
        this.mPileResultData = new ArrayList();
        for (int i2 = 0; i2 < this.mWaveNum; i2++)
        {
            CPileData pileData2 = new CPileData();
            pileData2.SetOrignalData(false);
            readLen += pileData2.InitFromStream(is);
            this.mPileResultData.add(pileData2);
            //Log.d("Pile Read mPileResultData Length:*****", String.valueOf(readLen));
        }
        return readLen;
    }

    public void AddFlaw(CFlaw flaw)
    {
        this.mPileInfo.AddFlaw(flaw);
    }

    public void ClearFlaw()
    {
        GetPileInfo().ClearFlaw();
    }

    /**
     * 获取桩信息
     *
     * @return
     */
    public CPileInfo GetPileInfo()
    {
        return this.mPileInfo;
    }

    /**
     * 设置桩信息
     *
     * @param pileInfo
     */
    public void SetPileInfo(CPileInfo pileInfo)
    {
        if (pileInfo != null)
        {
            this.mPileInfo = pileInfo;
        }
    }

    /**
     * 获取文件名
     *
     * @return
     */
    public String GetFileName()
    {
        return this.mFileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName
     */
    public void SetFileName(String fileName)
    {
        this.mFileName = fileName;
    }
}
