import com.webdao.frame.FileUtility;
import com.webdao.model.CFileInfo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        button_read.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                int result = 0;
                FileNameExtensionFilter filter = new FileNameExtensionFilter("曲线文件(*.zit)", "zit");// 文件过滤
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(filter);
                FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
                System.out.println(fsv.getHomeDirectory());                //得到桌面路径
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
            }
        });
        button_save.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                var filename = path+"_j.zit";
                FileUtility.saveData(filename,cFileInfo);
                JOptionPane.showMessageDialog(null,"已保存为："+filename);
            }
        });
    }

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
