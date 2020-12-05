package ����˹����;

/**
 * 
 * ��Ϸ����
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

	private static final long serialVersionUID = 1L;// serialVersionUID�����л�ʱ���ְ汾�ļ��ݣ��൱��JAVA������֤

	public JFrame jfmGame = new JFrame("����˹����");

	ImageIcon imgBackground = new ImageIcon("./src/GamePicture/game_bg.png"); // ����ͼƬ
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
			imgHelp, imgBack, imgMusic1 }; // ����ͼƬ����

	JLabel jlbBackground, jlbNextone, jlbState, jlbStatus, jlbScore; // ������ǩ
	JLabel[] jlb = new JLabel[] { jlbBackground, jlbNextone, jlbState, jlbStatus, jlbScore }; // ������ǩ����

	JButton btnState, btnRestart, btnHelp, btnBack, btnMusic; // ������ť
	JButton[] btn = { btnState, btnRestart, btnHelp, btnBack, btnMusic }; // ������ť����

	ArrayList<Cell> tetrominos = new ArrayList<Cell>(); // ��������Ѿ����µķ���
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
		jfmGame.setResizable(false); // ���ɸı䴰�ڵĴ�С
		jfmGame.setLocationRelativeTo(null);
		jfmGame.setLayout(null); // ȡ�����ֹ�������JFrameĬ��Ϊ�߽�ʽ���ֹ�����
		jfmGame.setIconImage(imgMing);
		this.setBounds(0, 0, 549, 756);
		this.setLayout(null); // ȡ�����ֹ�������JPanelĬ��Ϊ��ʽ���ֹ�����
		CellListener cellListener = new CellListener(this);
		jfmGame.addKeyListener(cellListener); // �򴰿�ע�����

		int b_y = 350;
		for (int i = 0; i < btn.length; i++) {// ��ť
			b_y += 60;
			btn[i] = new JButton(img[i + 5]);// ������ť����
			if (i < btn.length - 1) {
				btn[i].setBounds(395, b_y, 85, 40); // ����λ�ü���С
			} else {
				btn[i].setBounds(395, b_y, 40, 40);
			}
			btn[i].addActionListener(this); // ��Ӽ���
			this.add(btn[i]); // ��JPanel����Ӱ�ť
		}
		btnState = btn[0];
		btnRestart = btn[1];
		btnHelp = btn[2];
		btnBack = btn[3];
		btnMusic = btn[4];

		for (int i = 0; i < jlb.length; i++) {// ��ǩ
			jlb[i] = new JLabel(img[i]); // ������ǩ����
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
			jlb[i].setOpaque(true); // ����ǩ��Ϊ��͸��
			this.add(jlb[i]);// ��JPanel��ӱ�ǩ
		}
		jlbStatus = jlb[3];

		jfmGame.add(this); // ��JPanel��ӵ������
		jfmGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // �������ڵĹرհ�ť����������
		jfmGame.addWindowListener(new WindowAdapter() {
			// new һ��WindowAdapter �� ��дwindowClosing����
			public void windowClosing(WindowEvent e) {
				stop = true;
				Object[] options = { "ȷ��", "ȡ��" };
				int r = JOptionPane.showOptionDialog(jfmGame, "�Ƿ��˳���Ϸ", null, JOptionPane.YES_NO_OPTION,
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
		jfmGame.setVisible(true); // �Ǵ��ڿ���
		jfmGame.requestFocus(); // �۽���JFrame

		new Thread() { // �����߳��ػ�
			public void run() {
				int time = 15;
				nextOne = tetromino.randomOne();
				while (jfmGame.isVisible() && cellListener.isGame()) { // ��Ϸ�������е�����
					if (!cellListener.isdrop()) { // �ж��Ƿ�����µķ���
						landOne = currentOne; // ��currentOne����landOne
						for (int i = 0; i < landOne.tetris.length; i++) {
							tetrominos.add(landOne.tetris[i]); // ��landOne��ӵ�tetrominos������
						}
						currentOne = nextOne; // ��nextOne����currentOne
						nextOne = tetromino.randomOne(); // ���������һ��nextOne
					}
					time += 5;
					if (time == 20) {
						time = 0;
						cellListener.celldrop();
					}
					cellListener.cellRemove();
					repaint();
					try {
						if (!down) { // ���Ʒ��������ٶ�
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
	 * ������(����)
	 */
	public void paint(Graphics g) {
		super.paint(g); // ��ԭ��ͼ��Ļ����ϣ�������ͼ���������պ����»���
		g.translate(38, 50);// ƽ��������
		g.setColor(Color.BLACK); // ��������
		g.setFont(new Font(null, Font.BOLD, 15));
		g.drawString("" + number, 390, 310); // ��������
		drawWall(g); // ���÷���
		drawCurrentOne(g);
		drawNextOne(g);
		drawAll(g);
		// ���������ػ����
		for (int i = 0; i < btn.length; i++) {
			btn[i].repaint();// �ػ�������ᱻ��������
		}
		for (int i = 1; i < jlb.length; i++) {
			jlb[i].repaint();
		}
	}

	/*
	 * ������
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
	 * ������
	 */
	public void drawCurrentOne(Graphics g) { // ����������ķ���
		Cell[] cells = currentOne.tetris;
		for (Cell c : cells) {
			c.drawImage(g);
		}
	}

	public void drawNextOne(Graphics g) { // ����һ������
		Cell[] cells = nextOne.tetris;
		for (Cell c : cells) {
			int x = c.getX() + 255;
			int y = c.getY() + 101;
			g.drawImage(c.getImage(), x, y, null);
		}
	}

	public void drawAll(Graphics g) { // �������з���
		for (int i = 0; i < tetrominos.size(); i++) { // ����tetrominos����,ȡ������Ԫ��
			int x = tetrominos.get(i).getX();
			int y = tetrominos.get(i).getY();
			g.drawImage(tetrominos.get(i).getImage(), x, y, null); // ����ÿһ��С����
		}
	}

	/*
	 * �����Ի���
	 */
	public void dialogOver() { // ��Ϸ����ʱ
		Object[] options = { "����һ��", "�ص��˵�" };
		int m = JOptionPane.showOptionDialog(jfmGame, "Game Over�������ĵ÷�Ϊ:" + number, null, JOptionPane.YES_OPTION,
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

	public void dialogBack() { // ���ز˵�ʱ
		int m = JOptionPane.showOptionDialog(jfmGame, "�Ƿ񷵻ز˵�", null, JOptionPane.YES_NO_OPTION,
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

	public void dialogRestart() { // ���¿�ʼʱ
		int m = JOptionPane.showOptionDialog(jfmGame, "�Ƿ����¿�ʼ��Ϸ", null, JOptionPane.YES_NO_OPTION,
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

	public void dialogHelp() { // ����
		int m = JOptionPane.showConfirmDialog(jfmGame, "���� �� �� ���Ʒ���\n���� �� ��ת����\n���� �� ��������", null,
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
	 * ��ť�¼�����
	 */
	public void actionPerformed(ActionEvent e) { // �����ťʱ���¼�����
		if (e.getSource() == btnHelp) { // �����������
			stop = true;
			dialogHelp();
			jfmGame.requestFocus(); // ����������ת�Ƶ�JFrame
		} else if (e.getSource() == btnBack) { // ����������
			stop = true;
			dialogBack();
			jfmGame.requestFocus(); // ����������ת�Ƶ�JFrame
		} else if (e.getSource() == btnRestart) { // ���¿�ʼ
			stop = true;
			dialogRestart();
			jfmGame.requestFocus(); // ����������ת�Ƶ�JFrame
		} else if (e.getSource() == btnState) { // ��ͣ�����
			if (stop == false) {
				stop = true;
				run = false;
				btnState.setIcon(imgKeep);
				jlbStatus.setIcon(imgPause); // ����ǩjlbStatus���ͼƬ
			} else if (stop == true) {
				stop = false;
				run = true;
				btnState.setIcon(imgStop);
				jlbStatus.setIcon(imgContiue); // ����ǩjlbStatus���ͼƬ
				jfmGame.requestFocus(); // ����������ת�Ƶ�JFrame
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
