package com.webdao.frame;

//import android.os.Environment;
//import android.os.StatFs;
import com.webdao.model.CFileInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class FileUtility {

    public static CFileInfo read(String fileName) {
        CFileInfo fileInfo = new CFileInfo();
        try {
            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(fileName);
            fileInfo.initFromStream(inputStream);
            fileInfo.setFileName(file.getName());
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return fileInfo;
    }

    public static void saveData(String fileName, byte[] bytes) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(fileName);
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean saveData(String fileName, CFileInfo fileInfo) {
        try {
            FileOutputStream os = new FileOutputStream(fileName);
            fileInfo.writeToStream(os);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
