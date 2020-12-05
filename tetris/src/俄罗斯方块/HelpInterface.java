package 俄罗斯方块;

/**
 * 
 * 帮助界面
 * 
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HelpInterface extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;// serialVersionUID性序列化时保持版本的兼容，相当于JAVA类的身份证

	public JFrame jfmHelp = new JFrame("俄罗斯方块");

	Image help_bg = new ImageIcon("./src/HelpPicture/help_bg.png").getImage(); // 加载背景图片
	Image imgMing = new ImageIcon("./src/MainPicture/ming.jpg").getImage(); // 加载窗口图标
	ImageIcon help_back_menu1 = new ImageIcon("./src/HelpPicture/help_back_menu.jpg"); // 加载按钮图片
	JButton help_back; // 创建按钮对象

	public HelpInterface() {
		jfmHelp.setSize(812, 535); // 框架大小
		jfmHelp.setResizable(false); // 窗口大小不可改变
		jfmHelp.setLocationRelativeTo(null); // 居中
		jfmHelp.setLayout(null); // 取消JFrame布局管理器
		jfmHelp.setIconImage(imgMing);

		help_back = new JButton(help_back_menu1); // 创建按钮对象
		help_back.setBounds(29, 22, 79, 48); // 设置按钮位置及大小
		help_back.addActionListener(this); // 向按钮添加监听者

		this.setBounds(0, 0, 800, 500); // 设置面板位置及大小
		this.setLayout(null); // 取消JPanel布局管理器
		this.add(help_back); // 向面板添加按钮
		jfmHelp.add(this); // 向窗口添加面板
		jfmHelp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 单击窗口的关闭按钮，不做操作
		jfmHelp.setVisible(true); // 可视
		jfmHelp.addWindowListener(new WindowAdapter() {
			// new 一个WindowAdapter 类 重写windowClosing方法
			// WindowAdapter是个适配器类 具体看jdk的帮助文档
			public void windowClosing(WindowEvent e) {
				// 这里写对话框
				Object[] options = { "确定", "取消" };
				int r = JOptionPane.showOptionDialog(jfmHelp, "是否退出游戏", null, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (r == 0) {
					System.exit(0);
				}
			}
		});
	}

	public void paint(Graphics g) {
		g.drawImage(help_bg, 0, 0, this);
		help_back.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == help_back) {
			jfmHelp.dispose();
			new MainInterface();
		}
	}
}
