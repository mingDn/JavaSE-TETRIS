package 俄罗斯方块;

/**
 * 
 * 游戏界面
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameInterface extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;// serialVersionUID性序列化时保持版本的兼容，相当于JAVA类的身份证

	public JFrame jfmGame = new JFrame("俄罗斯方块");

	ImageIcon imgBackground = new ImageIcon("./src/GamePicture/game_bg.png"); // 声明图片
	ImageIcon imgNextone = new ImageIcon("./src/GamePicture/game_nextone.jpg");
	ImageIcon imgState = new ImageIcon("./src/GamePicture/game_state.jpg");
	ImageIcon imgContiue = new ImageIcon("./src/GamePicture/game_contiue.jpg");
	ImageIcon imgPause = new ImageIcon("./src/GamePicture/game_pause.jpg");
	ImageIcon imgScore = new ImageIcon("./src/GamePicture/game_score.jpg");
	ImageIcon imgStop = new ImageIcon("./src/GamePicture/game_stop.jpg");
	ImageIcon imgKeep = new ImageIcon("./src/GamePicture/game_keep.jpg");
	ImageIcon imgRestart = new ImageIcon("./src/GamePicture/game_restart.jpg");
	ImageIcon imgHelp = new ImageIcon("./src/GamePicture/game_help.jpg");
	ImageIcon imgBack = new ImageIcon("./src/GamePicture/game_back.jpg");
	ImageIcon imgMusic1 = new ImageIcon("./src/GamePicture/music_stop.jpg");
	ImageIcon imgMusic2 = new ImageIcon("./src/GamePicture/music_start.jpg");
	Image imgMing = new ImageIcon("./src/MainPicture/ming.jpg").getImage();
	ImageIcon[] img = new ImageIcon[] { imgBackground, imgNextone, imgState, imgContiue, imgScore, imgStop, imgRestart,
			imgHelp, imgBack, imgMusic1 }; // 创建图片数组

	JLabel jlbBackground, jlbNextone, jlbState, jlbStatus, jlbScore; // 声明标签
	JLabel[] jlb = new JLabel[] { jlbBackground, jlbNextone, jlbState, jlbStatus, jlbScore }; // 创建标签数组

	JButton btnState, btnRestart, btnHelp, btnBack, btnMusic; // 声明按钮
	JButton[] btn = { btnState, btnRestart, btnHelp, btnBack, btnMusic }; // 创建按钮数组

	ArrayList<Cell> tetrominos = new ArrayList<Cell>(); // 用来添加已经落下的方块
	Cell cell = new Cell();
	Tetromino tetromino = new Tetromino();
	Tetromino currentOne = tetromino.randomOne();
	Tetromino nextOne;
	Tetromino landOne;
	AudioPlayer audioPlayer;
	final int SIZE = 31;
	int number = 0;
	boolean stop;
	boolean down;
	boolean run = true;

	public GameInterface() {
		audioPlayer = new AudioPlayer("./src/Music/tetris.mp3");
		audioPlayer.start();
		jfmGame.setSize(549, 756);
		jfmGame.setResizable(false); // 不可改变窗口的大小
		jfmGame.setLocationRelativeTo(null);
		jfmGame.setLayout(null); // 取消布局管理器，JFrame默认为边界式布局管理器
		jfmGame.setIconImage(imgMing);
		this.setBounds(0, 0, 549, 756);
		this.setLayout(null); // 取消布局管理器，JPanel默认为流式布局管理器
		CellListener cellListener = new CellListener(this);
		jfmGame.addKeyListener(cellListener); // 向窗口注册监听

		int b_y = 350;
		for (int i = 0; i < btn.length; i++) {// 按钮
			b_y += 60;
			btn[i] = new JButton(img[i + 5]);// 创建按钮对象
			if (i < btn.length - 1) {
				btn[i].setBounds(395, b_y, 85, 40); // 设置位置及大小
			} else {
				btn[i].setBounds(395, b_y, 40, 40);
			}
			btn[i].addActionListener(this); // 添加监听
			this.add(btn[i]); // 向JPanel中添加按钮
		}
		btnState = btn[0];
		btnRestart = btn[1];
		btnHelp = btn[2];
		btnBack = btn[3];
		btnMusic = btn[4];

		for (int i = 0; i < jlb.length; i++) {// 标签
			jlb[i] = new JLabel(img[i]); // 创建标签对象
			if (i == 0)
				jlb[i].setBounds(0, 0, 549, 756);
			else if (i == 1)
				jlb[i].setBounds(390, 50, 95, 43);
			else if (i == 2)
				jlb[i].setBounds(390, 220, 95, 43);
			else if (i == 3)
				jlb[i].setBounds(405, 260, 64, 29);
			else if (i == 4)
				jlb[i].setBounds(390, 300, 95, 43);
			jlb[i].setOpaque(true); // 将标签设为不透明
			this.add(jlb[i]);// 向JPanel添加标签
		}
		jlbStatus = jlb[3];

		jfmGame.add(this); // 将JPanel添加到框架中
		jfmGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 单击窗口的关闭按钮，不做操作
		jfmGame.addWindowListener(new WindowAdapter() {
			// new 一个WindowAdapter 类 重写windowClosing方法
			public void windowClosing(WindowEvent e) {
				stop = true;
				Object[] options = { "确定", "取消" };
				int r = JOptionPane.showOptionDialog(jfmGame, "是否退出游戏", null, JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (r == 0) {
					System.exit(0);
				} else {
					if (run) {
						stop = false;
					} else {
						stop = true;
					}
				}
			}
		});
		jfmGame.setVisible(true); // 是窗口可视
		jfmGame.requestFocus(); // 聚焦到JFrame

		new Thread() { // 匿名线程重画
			public void run() {
				int time = 15;
				nextOne = tetromino.randomOne();
				while (jfmGame.isVisible() && cellListener.isGame()) { // 游戏继续进行的条件
					if (!cellListener.isdrop()) { // 判断是否产生新的方块
						landOne = currentOne; // 将currentOne传给landOne
						for (int i = 0; i < landOne.tetris.length; i++) {
							tetrominos.add(landOne.tetris[i]); // 将landOne添加到tetrominos容器中
						}
						currentOne = nextOne; // 将nextOne传给currentOne
						nextOne = tetromino.randomOne(); // 再随机产生一个nextOne
					}
					time += 5;
					if (time == 20) {
						time = 0;
						cellListener.celldrop();
					}
					cellListener.cellRemove();
					repaint();
					try {
						if (!down) { // 控制方块下落速度
							sleep(120);
						} else if (down) {
							sleep(15);
						}
					} catch (Exception e) {
					}
				}
			}
		}.start();
	}

	/*
	 * 画方法(集合)
	 */
	public void paint(Graphics g) {
		super.paint(g); // 在原来图像的基础上，继续绘图，否则会清空后重新绘制
		g.translate(38, 50);// 平移坐标轴
		g.setColor(Color.BLACK); // 设置字体
		g.setFont(new Font(null, Font.BOLD, 15));
		g.drawString("" + number, 390, 310); // 画出分数
		drawWall(g); // 调用方法
		drawCurrentOne(g);
		drawNextOne(g);
		drawAll(g);
		// 利用数组重画组件
		for (int i = 0; i < btn.length; i++) {
			btn[i].repaint();// 重画，否则会被背景覆盖
		}
		for (int i = 1; i < jlb.length; i++) {
			jlb[i].repaint();
		}
	}

	/*
	 * 画网格
	 */
	public void drawWall(Graphics g) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				int x = j * SIZE;
				int y = i * SIZE;
				g.drawRect(x, y, SIZE, SIZE);
			}
		}
	}

	/*
	 * 画方块
	 */
	public void drawCurrentOne(Graphics g) { // 画正在下落的方块
		Cell[] cells = currentOne.tetris;
		for (Cell c : cells) {
			c.drawImage(g);
		}
	}

	public void drawNextOne(Graphics g) { // 画下一个方块
		Cell[] cells = nextOne.tetris;
		for (Cell c : cells) {
			int x = c.getX() + 255;
			int y = c.getY() + 101;
			g.drawImage(c.getImage(), x, y, null);
		}
	}

	public void drawAll(Graphics g) { // 画出所有方块
		for (int i = 0; i < tetrominos.size(); i++) { // 遍历tetrominos容器,取出数组元素
			int x = tetrominos.get(i).getX();
			int y = tetrominos.get(i).getY();
			g.drawImage(tetrominos.get(i).getImage(), x, y, null); // 画出每一个小方块
		}
	}

	/*
	 * 创建对话框
	 */
	public void dialogOver() { // 游戏结束时
		Object[] options = { "再来一局", "回到菜单" };
		int m = JOptionPane.showOptionDialog(jfmGame, "Game Over——您的得分为:" + number, null, JOptionPane.YES_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (m == 0) {
			audioPlayer.closeMusic();
			jfmGame.dispose();
			new GameInterface();
		} else {
			jfmGame.dispose();
			new MainInterface();
		}
	}

	public void dialogBack() { // 返回菜单时
		int m = JOptionPane.showOptionDialog(jfmGame, "是否返回菜单", null, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (m == 0) {
			audioPlayer.closeMusic();
			jfmGame.dispose();
			new MainInterface();
		} else {
			if (run) {
				stop = false;
			} else {
				stop = true;
			}
		}
	}

	public void dialogRestart() { // 重新开始时
		int m = JOptionPane.showOptionDialog(jfmGame, "是否重新开始游戏", null, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (m == 0) {
			audioPlayer.closeMusic();
			jfmGame.dispose();
			new GameInterface();
		} else {
			if (run) {
				stop = false;
			} else {
				stop = true;
			}
		}
	}

	public void dialogHelp() { // 帮助
		int m = JOptionPane.showConfirmDialog(jfmGame, "方向 ← → 控制方向\n方向 ↑ 旋转方块\n方向 ↓ 加速下落", null,
				JOptionPane.CLOSED_OPTION);
		if (m == 0 | m != 0) {
			if (run) {
				stop = false;
			} else {
				stop = true;
			}
		}
	}

	/*
	 * 按钮事件处理
	 */
	public void actionPerformed(ActionEvent e) { // 点击按钮时的事件处理
		if (e.getSource() == btnHelp) { // 进入帮助界面
			stop = true;
			dialogHelp();
			jfmGame.requestFocus(); // 将焦点重新转移到JFrame
		} else if (e.getSource() == btnBack) { // 返回主界面
			stop = true;
			dialogBack();
			jfmGame.requestFocus(); // 将焦点重新转移到JFrame
		} else if (e.getSource() == btnRestart) { // 重新开始
			stop = true;
			dialogRestart();
			jfmGame.requestFocus(); // 将焦点重新转移到JFrame
		} else if (e.getSource() == btnState) { // 暂停或继续
			if (stop == false) {
				stop = true;
				run = false;
				btnState.setIcon(imgKeep);
				jlbStatus.setIcon(imgPause); // 给标签jlbStatus变更图片
			} else if (stop == true) {
				stop = false;
				run = true;
				btnState.setIcon(imgStop);
				jlbStatus.setIcon(imgContiue); // 给标签jlbStatus变更图片
				jfmGame.requestFocus(); // 将焦点重新转移到JFrame
			}
		} else if (e.getSource() == btnMusic) {
			audioPlayer.stopMusic();
			if (audioPlayer.stop) {
				btnMusic.setIcon(imgMusic2);
			} else if (!audioPlayer.stop) {
				btnMusic.setIcon(imgMusic1);
			}
			jfmGame.requestFocus();
		}
	}
}
