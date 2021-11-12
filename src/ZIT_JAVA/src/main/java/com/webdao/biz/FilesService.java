package com.webdao.biz;


import com.webdao.frame.CommonUtility;
import com.webdao.frame.FileUtility;
import com.webdao.model.AnalyseParams;
import com.webdao.model.CFileInfo;
import com.webdao.model.CPileInfo;
import com.webdao.model.DataUploadLogInfo;
import com.webdao.model.ICPInfo;
import com.webdao.model.OperatorInfo;
import com.webdao.model.SamplingParams;
import com.webdao.model.SensorParams;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class FilesService
{
    private final int MB = 1048576;
    private ArrayList<CFileInfo> mFileInfos;
    private double mStorageCapitial;

    public boolean isExistsWork(String workName)
    {
        for (File f : getWorks())
        {
            if (f.getName().equals(workName))
            {
                return true;
            }
        }
        return false;
    }

    public List<File> getWorks()
    {
        //工作目录
        String root = "C:\\Users\\yx-08\\Desktop\\3PL库";
        File path = new File(root);

        if (!path.exists())
        {
            path.mkdir();
        }
        File[] works = path.listFiles();
        if (works == null)
        {
            return null;
        }
        List<File> result = new ArrayList<>(works.length);
        for (File work : works)
        {
            if (work.isDirectory())
            {
                result.add(work);
            }
        }
        Collections.sort(result, new FileComparator());
        return result;
    }

    public String getDefaultFileName(String workName, String pileName)
    {
        String pileName2 = pileName.trim();
        String root = "C:\\Users\\yx-08\\Desktop\\3PL库";
        if (!new File(root + pileName2 + ".zit").exists())
        {
            return pileName2;
        }
        int index = pileName2.length();
        String[] files = new File(root + workName).list();
        int count = 0;
        int i = 0;
        while (i < files.length && files[i].length() >= index)
        {
            if (pileName2.equals(files[i].substring(0, index)))
            {
                count++;
                if (!new File(root + workName.trim() + pileName2 + "-" + count + ".zit").exists())
                {
                    break;
                }
            }
            i++;
        }
        return pileName2 + "-" + count;
    }

    public List<File> getFilesInWorks(String workName)
    {
        List<File> result = null;
        if (!(workName == null || workName.trim().length() == 0))
        {
            String root = "C:\\Users\\yx-08\\Desktop\\3PL库";
            File path = new File(root + workName);
            if (path.exists())
            {
                File[] files = path.listFiles();
                result = new ArrayList<>(files.length);
                for (File file : files)
                {
                    if (file.isFile() && file.getName().indexOf(".zit") > 0)
                    {
                        result.add(file);
                    }
                }
                Collections.sort(result, new FileComparator());
            }
        }
        return result;
    }

    /* loaded from: classes.dex */
    public class FileComparator implements Comparator<File>
    {
        public FileComparator()
        {
        }

        public int compare(File file1, File file2)
        {
            if (file1.lastModified() < file2.lastModified())
            {
                return 1;
            }
            return -1;
        }
    }

    public CFileInfo read(String fileName)
    {
        return FileUtility.read(fileName);
    }

    public static SensorParams refreshSensorParamsFromPileInfo(SensorParams sensorParams, CPileInfo pileInfo)
    {
        sensorParams.setSensitivity(pileInfo.getSensitivity1());
        return sensorParams;
    }

    public static SamplingParams refreshSamplingParamsFromPileInfo(SamplingParams samParams, CPileInfo pileInfo)
    {
        samParams.setLength(pileInfo.getSampleLength());
        samParams.fromGainTimes(pileInfo.getGainTimes());
        samParams.setTriggerLevelIndex(pileInfo.getTriggerLevel());
        samParams.setInterval(pileInfo.getSampleInterval());
        samParams.setCount(pileInfo.getBlowNumber());
        samParams.setDelayPoint(pileInfo.getDelayPoint());
        return samParams;
    }

    public static AnalyseParams refreshAnalyseParamsFromPileInfo(AnalyseParams anaParams, CPileInfo pileInfo)
    {
        anaParams.setEnlargeType(pileInfo.getMagnificationType());
        anaParams.setEnlargeTimes(pileInfo.getMagnification());
        anaParams.setEnlargeStart(pileInfo.getMagnificationStart());
        anaParams.setIntegrage(pileInfo.getIntegralFlag());
        anaParams.setWavelet(pileInfo.getWaveletFactor());
        anaParams.setEstimateLength(pileInfo.getPileLength());
        anaParams.setEstimateVelocity(pileInfo.getWaveSpeed());
        anaParams.setWaveRotate(pileInfo.getWaveletRotate());
        anaParams.setFilterType(pileInfo.getFilterType());
        anaParams.setSmoothPoint(pileInfo.getSmoothPoints());
        anaParams.setLowFrequency(pileInfo.getEndFrequencyLow());
        anaParams.setHighFrequency(pileInfo.getEndFrequencyHigh());
        return anaParams;
    }

    public CFileInfo readNext(String workName, String fileName)
    {
        List<File> fileList = getFilesInWorks(workName);
        if (fileName.length() > 4 && fileName.toLowerCase().substring(fileName.length() - 4).equals(".zit"))
        {
            fileName = fileName.substring(0, fileName.length() - 4);
        }
        if (fileList == null)
        {
            return null;
        }
        CFileInfo fileInfo = null;
        int i = 0;
        while (true)
        {
            if (i >= fileList.size())
            {
                break;
            }
            String fn = fileList.get(i).getName();
            if (fn.length() > 4 && fn.toLowerCase().substring(fn.length() - 4).equals(".zit"))
            {
                fn = fn.substring(0, fn.length() - 4);
            }
            if (fn.equals(fileName))
            {
                int i2 = i + 1 == fileList.size() ? 0 : i + 1;
                fileInfo = read(fileList.get(i2).getAbsolutePath());
                fileInfo.setFileName(fileList.get(i2).getName());
            } else
            {
                i++;
            }
        }
        if (fileList.size() <= 0 || fileInfo != null)
        {
            return fileInfo;
        }
        CFileInfo fileInfo2 = read(fileList.get(0).getAbsolutePath());
        fileInfo2.setFileName(fileList.get(0).getName());
        return fileInfo2;
    }

    public CFileInfo readPrevious(String workName, String fileName)
    {
        List<File> fileList = getFilesInWorks(workName);
        CFileInfo fileInfo = null;
        int i = 0;
        while (i < fileList.size())
        {
            if (fileList.get(i).getName() == fileName)
            {
                i = i + -1 == 0 ? fileList.size() - 1 : i - 1;
                fileInfo = read(fileList.get(i).getAbsolutePath());
            }
            i++;
        }
        return fileInfo;
    }

    public boolean saveData(CFileInfo fileInfo, String fileName)
    {
        String filePath;
        CPileInfo pileInfo = fileInfo.getPileInfo();
        String filePath2 = "C:\\Users\\yx-08\\Desktop\\3PL库";
        if (pileInfo == null)
        {
            return false;
        }
        String filePath3 = filePath2 + pileInfo.getProjectName().trim();
        File file = new File(filePath3);
        if (!file.exists())
        {
            file.mkdirs();
        }
        if (fileName.contains(".zit"))
        {
            filePath = filePath3 + fileName;
            fileInfo.setFileName(fileName);
        } else
        {
            filePath = filePath3 + fileName + ".zit";
            fileInfo.setFileName(fileName + ".zit");
        }
        return FileUtility.saveData(filePath, fileInfo);
    }

    public ArrayList<HashMap<String, Object>> getReadFile(List<File> fileList)
    {
        ArrayList<HashMap<String, Object>> filesData = new ArrayList<>();
        for (File file : fileList)
        {
            HashMap<String, Object> map = new HashMap<>();
            map.put("filename", file.getName());
            map.put("filepath", file.getAbsolutePath());
            map.put("savedate", new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(file.lastModified())));
            filesData.add(map);
        }
        return filesData;
    }
}
