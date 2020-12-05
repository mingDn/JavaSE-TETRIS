package ����˹����;

/**
 * 
 * ��Ϸ�߼�
 * 
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CellListener extends KeyAdapter {
	public GameInterface game;

	public CellListener(GameInterface game) {
		this.game = game;
	}

	/*
	 * �ƶ�����
	 */
	public void celldrop() { // �����½�����
		Cell[] cells = game.currentOne.tetris;
		if (!game.stop && isdrop() && !downisCell()) {
			for (Cell c : cells) { // foreach��������ִ��drop����
				c.drop(); // ����drop����
			}
		}
	}

	public boolean isdrop() { // �ж��Ƿ���Լ�������ķ���
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (c.getY() >= 19 * game.SIZE || downisCell()) { // ���������·��з���
				game.tetromino.count = 100000; // ��ֵ������
				return false; // ���緵��false������������
			}
		}
		return true;
	}

	public void cellLift() { // ��������
		Cell[] cells = game.currentOne.tetris;
		if (!leftoutWall() && !leftisCell()) { // ������δ������δ��ײ
			for (Cell c : cells) {
				c.left();
			}
		}
	}

	public void cellLiftone() { // ��������һ��
		Cell[] cells = game.currentOne.tetris;
		if (!leftoutWall() && !leftisCell()) { // ������δ������δ��ײ
			for (Cell c : cells) {
				c.left();
			}
		}
	}

	public void cellRight() { // ��������
		Cell[] cells = game.currentOne.tetris;
		if (!rightoutWall() && !rightisCell()) { // ����Ҳ�δ������δ��ײ
			for (Cell c : cells) {
				c.right();
			}
		}
	}

	public void cellRightone() { // ��������һ��
		Cell[] cells = game.currentOne.tetris;
		if (!rightoutWall() && !rightisCell()) { // ����Ҳ�δ������δ��ײ
			for (Cell c : cells) {
				c.right();
			}
		}
	}

	public boolean leftoutWall() { // ��ֹ���������ķ���
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (c.getX() <= 0) { // �������x<=0
				return true; // ����true��������
			}
		}
		return false;
	}

	public boolean rightoutWall() { // ��ֹ�����ҳ���ķ���
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (c.getX() >= 9 * game.SIZE) { // �������x>=9*SIZE
				return true;
			}
		}
		return false;
	}

	/*
	 * ��ײ���
	 */
	public boolean downisCell() { // �·���ײ���
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			int x = c.getX();
			int y = c.getY();
			for (int i = 0; i < game.tetrominos.size(); i++) {
				if (y + game.SIZE == game.tetrominos.get(i).getY() && x == game.tetrominos.get(i).getX()) { // ����Ҳ��з���
					return true;
				}
			}
		}
		return false;
	}

	public boolean leftisCell() { // �����ײ���
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			int x = c.getX();
			int y = c.getY();
			for (int i = 0; i < game.tetrominos.size(); i++) {
				if (x - game.SIZE == game.tetrominos.get(i).getX() && y == game.tetrominos.get(i).getY()) { // �������з���
					return true;
				}
			}
		}
		return false;
	}

	public boolean rightisCell() { // �Ҳ���ײ���
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			int x = c.getX();
			int y = c.getY();
			for (int i = 0; i < game.tetrominos.size(); i++) {
				if (x + game.SIZE == game.tetrominos.get(i).getX() && y == game.tetrominos.get(i).getY()) { // ����·��з���
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * ˳ʱ�룬������ת�ĸ񷽿�
	 */
	public void rotateRight() {
		// ��תһ�Σ�������������1
		game.tetromino.count++;
		State s = game.currentOne.states[game.tetromino.count % game.currentOne.states.length]; // ѡȡ�����任��״̬
		int x = game.currentOne.tetris[0].getX(); // ��ȡtetris[0]��x����
		int y = game.currentOne.tetris[0].getY(); // ��ȡtetris[0]��y����
		game.currentOne.tetris[1].setX(x + s.getX1()); // ����tetris[0]������ı�������������꣬�����ת
		game.currentOne.tetris[1].setY(y + s.getY1());
		game.currentOne.tetris[2].setX(x + s.getX2());
		game.currentOne.tetris[2].setY(y + s.getY2());
		game.currentOne.tetris[3].setX(x + s.getX3());
		game.currentOne.tetris[3].setY(y + s.getY3());
	}

	public boolean isRotate() { // �жϷ����Ƿ������ת
		game.tetromino.count++;
		State s = game.currentOne.states[game.tetromino.count % game.currentOne.states.length];
		int x1 = game.currentOne.tetris[0].getX() + s.getX1(); // ��ȡ���鼴����ת����λ������
		int y1 = game.currentOne.tetris[0].getY() + s.getY1();
		int x2 = game.currentOne.tetris[0].getX() + s.getX2();
		int y2 = game.currentOne.tetris[0].getY() + s.getY2();
		int x3 = game.currentOne.tetris[0].getX() + s.getX3();
		int y3 = game.currentOne.tetris[0].getY() + s.getY3();
		if (x1 < 0 || x2 < 0 || x3 < 0 || x1 > 9 * game.SIZE || x2 > 9 * game.SIZE || x3 > 9 * game.SIZE
				|| y1 > 19 * game.SIZE || y2 > 19 * game.SIZE || y3 > 19 * game.SIZE) { // �ж�Ŀ��λ���Ƿ����
			game.tetromino.count--; // ��ԭ������
			return false;
		}
		for (int i = 0; i < game.tetrominos.size(); i++) { // ����tetrominos����
			int x = game.tetrominos.get(i).getX();
			int y = game.tetrominos.get(i).getY();
			if ((x1 == x && y1 == y) | (x2 == x && y2 == y) | (x3 == x && y3 == y)) { // �ж�Ŀ��λ���Ƿ��з���
				game.tetromino.count--;
				return false;
			}
		}
		game.tetromino.count--;
		return true;
	}

	public void rotateAction() { // ���������ת
		if (isRotate()) { // ���������ת
			rotateRight();
		}
	}

	/*
	 * ����
	 */
	public void cellRemove() { // ����
		int m = 0;
		int y = 20 * game.SIZE - game.SIZE;
		for (int i = 0; i < 20; i++) {// ����_��
			for (int k = 0; k < game.tetrominos.size(); k++) {// ��������tetrominos
				if (game.tetrominos.get(k).getY() == y) { // �������ÿ��һ������
					m++;
				}
			}
			if (m == 10) { // ���������10������
				game.number++; // ������һ
				for (int p = 0; p < game.tetrominos.size(); p++) { // �������е�Ԫ�ؽ��б���
					if (game.tetrominos.get(p).getY() == y) { // ��������ڴ���
						game.tetrominos.remove(p); // ���������Ƴ��˷���
						p--; // ������һ����ֹ©��Ԫ�������ڳ���δ��������
					}
				}
				for (int h = 0; h < game.tetrominos.size(); h++) {// ���к󷽿�����
					if (game.tetrominos.get(h).getY() < y && game.tetrominos.get(h).getY() + game.SIZE < 20 * game.SIZE) // ������鴦�ڴ����Ϸ��������δ����
						game.tetrominos.get(h).setY(game.tetrominos.get(h).getY() + game.SIZE); // ��������
				}
			}
			y -= game.SIZE; // ������һ��
			m = 0;
		}
	}

	public boolean isGame() { // �ж��Ƿ���Ϸ����
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (downisCell() && c.getY() <= 0) { // ����·��з������Ϸ��ﵽ������ߴ�
				game.dialogOver();
				return false;
			}
		}
		return true;
	}

	/*
	 * ��������
	 */
	public void keyPressed(KeyEvent e) { // �������������¼�ʱ�����¼�����
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.game.down = true;
		}
	}

	public void keyReleased(KeyEvent e) { // ����������̧��ʱ�����¼�����
		if (e.getKeyCode() == KeyEvent.VK_UP && isdrop()) {
			rotateAction();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && isdrop()) { // ̧������ҷ�����Լ�������ʱ
			cellLift();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && !isdrop()) {
			cellLiftone();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && isdrop()) {
			cellRight();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !isdrop()) {
			cellRightone();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.game.down = false;
		}
	}
}
