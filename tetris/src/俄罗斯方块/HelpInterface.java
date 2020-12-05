package ����˹����;

/**
 * 
 * ��������
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

	private static final long serialVersionUID = 1L;// serialVersionUID�����л�ʱ���ְ汾�ļ��ݣ��൱��JAVA������֤

	public JFrame jfmHelp = new JFrame("����˹����");

	Image help_bg = new ImageIcon("./src/HelpPicture/help_bg.png").getImage(); // ���ر���ͼƬ
	Image imgMing = new ImageIcon("./src/MainPicture/ming.jpg").getImage(); // ���ش���ͼ��
	ImageIcon help_back_menu1 = new ImageIcon("./src/HelpPicture/help_back_menu.jpg"); // ���ذ�ťͼƬ
	JButton help_back; // ������ť����

	public HelpInterface() {
		jfmHelp.setSize(812, 535); // ��ܴ�С
		jfmHelp.setResizable(false); // ���ڴ�С���ɸı�
		jfmHelp.setLocationRelativeTo(null); // ����
		jfmHelp.setLayout(null); // ȡ��JFrame���ֹ�����
		jfmHelp.setIconImage(imgMing);

		help_back = new JButton(help_back_menu1); // ������ť����
		help_back.setBounds(29, 22, 79, 48); // ���ð�ťλ�ü���С
		help_back.addActionListener(this); // ��ť��Ӽ�����

		this.setBounds(0, 0, 800, 500); // �������λ�ü���С
		this.setLayout(null); // ȡ��JPanel���ֹ�����
		this.add(help_back); // �������Ӱ�ť
		jfmHelp.add(this); // �򴰿�������
		jfmHelp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // �������ڵĹرհ�ť����������
		jfmHelp.setVisible(true); // ����
		jfmHelp.addWindowListener(new WindowAdapter() {
			// new һ��WindowAdapter �� ��дwindowClosing����
			// WindowAdapter�Ǹ��������� ���忴jdk�İ����ĵ�
			public void windowClosing(WindowEvent e) {
				// ����д�Ի���
				Object[] options = { "ȷ��", "ȡ��" };
				int r = JOptionPane.showOptionDialog(jfmHelp, "�Ƿ��˳���Ϸ", null, JOptionPane.YES_NO_OPTION,
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
