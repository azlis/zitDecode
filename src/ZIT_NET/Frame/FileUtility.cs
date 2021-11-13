using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Model;

namespace ZIT_NET.Frame
{
    public class FileUtility
    {


        public static CFileInfo Read(string fileName)
        {
            CFileInfo fileInfo = new CFileInfo();
            try
            {
                //File file = new File(fileName);
                //FileInputStream inputStream = new FileInputStream(fileName);
                //fileInfo.InitFromStream(inputStream);
                //fileInfo.SetFileName(file.getName());
                //inputStream.close();

                FileStream os = new FileStream(fileName, FileMode.OpenOrCreate);
                fileInfo.InitFromStream(os);
                string name = Path.GetFileName(fileName);
                fileInfo.SetFileName(name);
                os.Close();
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
            }
            return fileInfo;
        }


        public static void SaveData(string fileName, byte[] bytes)
        {
            if (!File.Exists(fileName))
            {
                try
                {
                    FileStream os = new FileStream(fileName, FileMode.OpenOrCreate);
                    os.Write(bytes);
                    os.Flush();
                    os.Close();
                }
                catch (IOException e)
                {
                    //e.printStackTrace();
                    Console.WriteLine(e.StackTrace);
                }
            }
        }


        public static bool SaveData(string fileName, CFileInfo fileInfo)
        {
            try
            {
                //FileOutputStream os = new FileOutputStream(fileName);
                //fileInfo.WriteToStream(os);
                //os.flush();
                //os.close();

                FileStream os = new FileStream(fileName, FileMode.OpenOrCreate);
                fileInfo.WriteToStream(os);
                os.Flush();
                os.Close();

                return true;
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
                return false;
            }
        }
    }
}
