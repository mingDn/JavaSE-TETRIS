package ����˹����;

/**
 * 
 * ������
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

	private static final long serialVersionUID = 1L;// serialVersionUID�����л�ʱ���ְ汾�ļ��ݣ��൱��JAVA������֤

	public JFrame jfmStart = new JFrame("����˹����"); // ����JFrame���

	Image imgBg = new ImageIcon("./src/MainPicture/tetris_bg.png").getImage(); // ����ͼƬ
	ImageIcon imgBegin = new ImageIcon("./src/MainPicture/tetris_begin.jpg");
	ImageIcon imgHelp = new ImageIcon("./src/MainPicture/tetris_help.jpg");
	ImageIcon imgOver = new ImageIcon("./src/MainPicture/tetris_over.jpg");
	Image imgMing = new ImageIcon("./src/MainPicture/ming.jpg").getImage();
	ImageIcon[] img = new ImageIcon[] { imgBegin, imgHelp, imgOver };

	JButton btnBegin, btnHelp, btnOver;// ������ť
	JButton[] btn = new JButton[] { btnBegin, btnHelp, btnOver };

	public MainInterface() {
		jfmStart.setSize(800, 500);
		jfmStart.setResizable(false); // ���ɸı䴰�ڴ�С
		jfmStart.setLocationRelativeTo(null); // ���ھ���
		jfmStart.setLayout(null); // ȡ��JFrame���ֹ�����
		jfmStart.setIconImage(imgMing); // ��ͼƬ��ӵ�������
		this.setBounds(0, 0, 800, 500); // �������λ�ü���С
		this.setLayout(null); // ȡ��JPanel���ֹ�����

		int btn_x = 80 - 255;
		for (int i = 0; i < btn.length; i++) {
			btn_x += 255;
			btn[i] = new JButton(img[i]); // ������ť����
			btn[i].setBounds(btn_x, 370, 130, 46); // ���ð�ťλ�ü���С
			btn[i].addActionListener(this); // ��ť��Ӽ�����
			btn[i].setBorderPainted(false); // ȥ����ť�߿�
			this.add(btn[i]); // �������Ӱ�ť
		}
		btnBegin = btn[0]; // ���ݶ��������ǲ���ͬһ����
		btnHelp = btn[1];
		btnOver = btn[2];

		jfmStart.add(this); // �������JPanle
		jfmStart.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // �������ڵĹرհ�ť����������
		jfmStart.setVisible(true); // ����
		jfmStart.addWindowListener(new WindowAdapter() {
			// new һ��WindowAdapter �� ��дwindowClosing����
			// WindowAdapter�Ǹ���������
			public void windowClosing(WindowEvent e) {
				// ����д�Ի���
				Object[] options = { "ȷ��", "ȡ��" };
				int r = JOptionPane.showOptionDialog(jfmStart, "�Ƿ��˳���Ϸ", null, JOptionPane.YES_NO_OPTION,
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
			btn[i].repaint(); // �ػ��������ֹ����
		}
	}

	public void actionPerformed(ActionEvent e) { // �����ťʱ�Ĵ������
		if (e.getSource() == btnBegin) { // ������begin��ť
			jfmStart.dispose(); // �ر�game_start���ڲ��ͷ�һ������Դ
			new GameInterface(); // ��game����
		} else if (e.getSource() == btnHelp) {
			jfmStart.dispose();
			new HelpInterface();
		} else if (e.getSource() == btnOver) {
			jfmStart.dispose();
			System.exit(0); // ������ǰ���������е�java�����,��ȫ�ͷ��ڴ�
		}
	}

	public static void main(String[] args) {
		new MainInterface();
	}
}