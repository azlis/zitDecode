import com.webdao.frame.FileUtility;
import com.webdao.model.CFileInfo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class MainForm
{
    private JButton button_read;
    private JPanel jpanel;
    private JButton button_save;
    private JButton button_select;
    private JButton ButtonCover;
    private JButton deleteButton;
    private String path = null;//文件路径
    private CFileInfo cFileInfo;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("ZIT");
        frame.setContentPane(new MainForm().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public MainForm()
    {
        button_read.addActionListener(e ->
        {
            path = "test.zit";
            cFileInfo = FileUtility.Read(path);
            JOptionPane.showMessageDialog(null, "文件成功读取：" + cFileInfo.mWaveNum);
        });

        button_save.addActionListener(e ->
        {
            var filename = path + "_X.zit";
            FileUtility.SaveData(filename, cFileInfo);
            JOptionPane.showMessageDialog(null, "已保存为：" + filename);
        });

        button_select.addActionListener(e ->
        {
            int result;
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
                cFileInfo = FileUtility.Read(path);
                System.out.println(cFileInfo.mWaveNum);
                JOptionPane.showMessageDialog(null, "文件成功读取：" + cFileInfo.mWaveNum);
            }
        });

        ButtonCover.addActionListener(e ->
        {
            cFileInfo.mPileOriginalData = cFileInfo.mPileResultData;
            JOptionPane.showMessageDialog(null, "ok" );
        });

        deleteButton.addActionListener(e ->
        {
/*            cFileInfo.mPileInfo.SetBlowNumber(1);
            cFileInfo.mWaveNum = 1;

            cFileInfo.mPileOriginalData.remove(3);
            cFileInfo.mPileOriginalData.remove(2);
            cFileInfo.mPileOriginalData.remove(1);

            cFileInfo.mPileResultData.remove(3);
            cFileInfo.mPileResultData.remove(2);
            cFileInfo.mPileResultData.remove(1);*/

            cFileInfo.mPileInfo.ClearFlaw();

            JOptionPane.showMessageDialog(null, "ok" );
        });
    }


}
