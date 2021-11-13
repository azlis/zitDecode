// See https://aka.ms/new-console-template for more information
using System.IO;

using ZIT_NET.Frame;
using ZIT_NET.Model;

Console.WriteLine("Hello, World!");
var path = "test.zit";
var cFileInfo = FileUtility.Read(path);
Console.WriteLine(cFileInfo.mWaveNum);
Console.ReadKey();