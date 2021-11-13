using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using ZIT_NET.Frame;

namespace ZIT_NET.Model
{
    public class CFlaw : ICustomSerializable
    {
        public static readonly short BREAK = 2;
        public static short COMMON = 0;
        public static readonly short DEFECT = 6;
        public static readonly short FLAW = 5;
        public static readonly short LIQUATE = 3;
        public static short LiGHT = 0;
        public static readonly short MUD = 4;
        public static readonly short NECKFAT = 1;
        public static readonly short NECKTHIN = 0;
        public static readonly short NONE = -1;
        public static short SERIOUS;
        public short mFlawGrade;
        public float mFlawPosition;
        public short mFlawType;
        public int mPosIndex;

        public CFlaw(int type, int grade, float position)
        {
            mFlawType = (short)type;
            mFlawGrade = (short)grade;
            mFlawPosition = position;
        }

        public CFlaw()
        {
        }


        public int WriteToStream(FileStream os)
        {
            byte[]
            bs = DataTransfer.ShortToBytes(mFlawType);
            os.Write(bs, 0, bs.Length);
            int len = bs.Length;
            byte[]
            bs2 = DataTransfer.FloatToBytes(mFlawPosition);
            os.Write(bs2, 0, bs2.Length);
            int len2 = len + bs2.Length;
            byte[]
            bs3 = DataTransfer.ShortToBytes(mFlawGrade);
            os.Write(bs3, 0, bs3.Length);
            return len2 + bs3.Length;
        }


        public int InitFromStream(FileStream fs)
        {
            int readLen = 0;
            try
            {
                byte[] bs = new byte[2];
                int readLen2 = fs.Read(bs, 0, 2);
                mFlawType = DataTransfer.ByteToShort(bs);
                byte[] bs2 = new byte[4];
                int readLen3 = readLen2 + fs.Read(bs2, 0, 4);
                mFlawPosition = DataTransfer.ByteToFloat(bs2);
                byte[] bs3 = new byte[2];
                readLen = readLen3 + fs.Read(bs3, 0, 2);
                mFlawGrade = DataTransfer.ByteToShort(bs3);
                return readLen;
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                Console.WriteLine(e.StackTrace);
                return readLen;
            }
        }
    }
}
