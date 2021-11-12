import com.webdao.frame.FileUtility;
import com.webdao.model.CFileInfo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class MainForm
{
    private static JFrame frame;
    private JButton button_read;
    private JPanel jpanel;
    private JButton button_save;
    private String path = null;//文件路径
    private   CFileInfo cFileInfo;
    public MainForm()
    {
        button_read.addActionListener(e ->
        {
            int result = 0;
            FileNameExtensionFilter filter = new FileNameExtensionFilter("曲线文件(*.zit)", "zit");// 文件过滤
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            FileSystemView fsv = FileSystemView.getFileSystemView();//注意了，这里重要的一句
            System.out.println(fsv.getHomeDirectory());//得到桌面路径
            fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
            fileChooser.setDialogTitle("请选择要处理的文件...");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            result = fileChooser.showOpenDialog(null);
            if (JFileChooser.APPROVE_OPTION == result)
            {
                path = fileChooser.getSelectedFile().getPath();
                System.out.println("文件路径: " + path);
                cFileInfo = FileUtility.read(path);
                System.out.println(cFileInfo.mWaveNum);
            }
        });

        button_save.addActionListener(e ->
        {
            var filename = path+"_X.zit";
            FileUtility.saveData(filename,cFileInfo);
            JOptionPane.showMessageDialog(null,"已保存为："+filename);
        });
    }

    /**
     * 程序入口
     * @param args 参数
     */
    public static void main(String[] args)
    {
        frame = new JFrame("ZIT");
        frame.setContentPane(new MainForm().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}