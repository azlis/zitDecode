package com.webdao.frame;

import com.webdao.model.CFileInfo;

import java.io.*;

public class FileUtility
{

    /**
     * 读取zit文件
     *
     * @param fileName 文件路径
     * @return
     */
    public static CFileInfo Read(String fileName)
    {
        CFileInfo fileInfo = new CFileInfo();
        try
        {
            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(fileName);
            fileInfo.InitFromStream(inputStream);
            fileInfo.SetFileName(file.getName());
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileInfo;
    }

    /**
     * 保存zit数据
     *
     * @param fileName 文件路径名称
     * @param bytes    数据集合
     */
    public static void SaveData(String fileName, byte[] bytes)
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(fileName);
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存zit数据
     *
     * @param fileName 文件路径名称
     * @param fileInfo 文件实列类
     * @return
     */
    public static boolean SaveData(String fileName, CFileInfo fileInfo)
    {
        try
        {
            FileOutputStream os = new FileOutputStream(fileName);
            fileInfo.WriteToStream(os);
            os.flush();
            os.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
