package com.webdao.model;

import com.webdao.frame.CustomSerializable;
import com.webdao.frame.DataTransfer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CFileInfo implements CustomSerializable {
    private String mFileName;
    public CPileInfo mPileInfo;
    public List<CPileData> mPileOriginalData;
    public List<CPileData> mPileResultData;
    public short mWaveNum;

    public CFileInfo(CPileInfo pileInfo) {
        this.mFileName = "1-1";
        this.mPileInfo = pileInfo;
        this.mPileResultData = new ArrayList();
        this.mPileOriginalData = new ArrayList();
    }

    public CFileInfo() {
        this.mFileName = "1-1";
        this.mPileInfo = new CPileInfo();
        this.mPileResultData = new ArrayList();
        this.mPileOriginalData = new ArrayList();
    }

    public short getWaveNum() {
        if (this.mWaveNum == 0) {
            return (short) this.mPileResultData.size();
        }
        return this.mWaveNum;
    }

    public void clearPileData() {
        if (this.mPileOriginalData != null) {
            this.mPileOriginalData.clear();
        }
        if (this.mPileResultData != null) {
            this.mPileResultData.clear();
        }
        this.mWaveNum = 0;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int writeToStream(OutputStream os) throws IOException {
        int len = 0 + this.mPileInfo.writeToStream(os);
        byte[] bs = DataTransfer.shortToBytes(getWaveNum());
        os.write(bs, 0, bs.length);
        int len2 = len + 2;
        if (this.mPileOriginalData != null) {
            for (int i = 0; i < this.mPileOriginalData.size(); i++) {
                CPileData pileData = this.mPileOriginalData.get(i);
                pileData.setOrignalData(true);
                len2 += pileData.writeToStream(os);
                //Log.d("Pile Write mPileOriginalData Length:", String.valueOf(len2));
            }
        }
        if (this.mPileResultData != null) {
            for (int i2 = 0; i2 < this.mPileResultData.size(); i2++) {
                CPileData pileData2 = this.mPileResultData.get(i2);
                pileData2.setOrignalData(false);
                len2 += pileData2.writeToStream(os);
                //Log.d("Pile Write mPileResultData Length*****:", String.valueOf(len2));
            }
        }
        return len2;
    }

    @Override // com.webdao.frame.CustomSerializable
    public int initFromStream(InputStream is) {
        this.mPileInfo = new CPileInfo();
        int readLen = 0 + this.mPileInfo.initFromStream(is);
        byte[] bs = new byte[2];
        try {
            readLen += is.read(bs, 0, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mWaveNum = DataTransfer.byteToShort(bs);
        this.mPileOriginalData = new ArrayList();
        for (int i = 0; i < this.mWaveNum; i++) {
            CPileData pileData = new CPileData();
            pileData.setOrignalData(true);
            readLen += pileData.initFromStream(is);
            this.mPileOriginalData.add(pileData);
            //Log.d("Pile Read mPileOriginalData Length:", String.valueOf(readLen));
        }
        this.mPileResultData = new ArrayList();
        for (int i2 = 0; i2 < this.mWaveNum; i2++) {
            CPileData pileData2 = new CPileData();
            pileData2.setOrignalData(false);
            readLen += pileData2.initFromStream(is);
            this.mPileResultData.add(pileData2);
            //Log.d("Pile Read mPileResultData Length:*****", String.valueOf(readLen));
        }
        return readLen;
    }

    public void addFlaw(CFlaw flaw) {
        this.mPileInfo.addFlaw(flaw);
    }

    public void clearFlaw() {
        getPileInfo().clearFlaw();
    }

    public CPileInfo getPileInfo() {
        return this.mPileInfo;
    }

    public void setPileInfo(CPileInfo pileInfo) {
        if (pileInfo != null) {
            this.mPileInfo = pileInfo;
        }
    }

    public String getFileName() {
        return this.mFileName;
    }

    public void setFileName(String fileName) {
        this.mFileName = fileName;
    }
}
