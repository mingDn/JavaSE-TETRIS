package 俄罗斯方块;

/**
 * 
 * 主界面
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

public class MainInterface extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;// serialVersionUID性序列化时保持版本的兼容，相当于JAVA类的身份证

	public JFrame jfmStart = new JFrame("俄罗斯方块"); // 创建JFrame框架

	Image imgBg = new ImageIcon("./src/MainPicture/tetris_bg.png").getImage(); // 加载图片
	ImageIcon imgBegin = new ImageIcon("./src/MainPicture/tetris_begin.jpg");
	ImageIcon imgHelp = new ImageIcon("./src/MainPicture/tetris_help.jpg");
	ImageIcon imgOver = new ImageIcon("./src/MainPicture/tetris_over.jpg");
	Image imgMing = new ImageIcon("./src/MainPicture/ming.jpg").getImage();
	ImageIcon[] img = new ImageIcon[] { imgBegin, imgHelp, imgOver };

	JButton btnBegin, btnHelp, btnOver;// 声明按钮
	JButton[] btn = new JButton[] { btnBegin, btnHelp, btnOver };

	public MainInterface() {
		jfmStart.setSize(800, 500);
		jfmStart.setResizable(false); // 不可改变窗口大小
		jfmStart.setLocationRelativeTo(null); // 窗口居中
		jfmStart.setLayout(null); // 取消JFrame布局管理器
		jfmStart.setIconImage(imgMing); // 将图片添加到窗口上
		this.setBounds(0, 0, 800, 500); // 设置面板位置及大小
		this.setLayout(null); // 取消JPanel布局管理器

		int btn_x = 80 - 255;
		for (int i = 0; i < btn.length; i++) {
			btn_x += 255;
			btn[i] = new JButton(img[i]); // 创建按钮对象
			btn[i].setBounds(btn_x, 370, 130, 46); // 设置按钮位置及大小
			btn[i].addActionListener(this); // 向按钮添加监听者
			btn[i].setBorderPainted(false); // 去除按钮边框
			this.add(btn[i]); // 向面板添加按钮
		}
		btnBegin = btn[0]; // 传递对象，两者是不是同一对象
		btnHelp = btn[1];
		btnOver = btn[2];

		jfmStart.add(this); // 向框架添加JPanle
		jfmStart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 单击窗口的关闭按钮，不做操作
		jfmStart.setVisible(true); // 可视
		jfmStart.addWindowListener(new WindowAdapter() {
			// new 一个WindowAdapter 类 重写windowClosing方法
			// WindowAdapter是个适配器类
			public void windowClosing(WindowEvent e) {
				// 这里写对话框
				Object[] options = { "确定", "取消" };
				int r = JOptionPane.showOptionDialog(jfmStart, "是否退出游戏", null, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (r == 0) {
					System.exit(0);
				}
			}
		});
	}

	public void paint(Graphics g) {
		g.drawImage(imgBg, 0, 0, this);
		for (int i = 0; i < btn.length; i++) {
			btn[i].repaint(); // 重画组件，防止覆盖
		}
	}

	public void actionPerformed(ActionEvent e) { // 点击按钮时的处理操作
		if (e.getSource() == btnBegin) { // 如果点击begin按钮
			jfmStart.dispose(); // 关闭game_start窗口并释放一部分资源
			new GameInterface(); // 打开game界面
		} else if (e.getSource() == btnHelp) {
			jfmStart.dispose();
			new HelpInterface();
		} else if (e.getSource() == btnOver) {
			jfmStart.dispose();
			System.exit(0); // 结束当前正在运行中的java虚拟机,完全释放内存
		}
	}

	public static void main(String[] args) {
		new MainInterface();
	}
}