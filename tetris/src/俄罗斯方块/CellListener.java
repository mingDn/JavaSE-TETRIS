package 俄罗斯方块;

/**
 * 
 * 游戏逻辑
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
	 * 移动方法
	 */
	public void celldrop() { // 方块下降方法
		Cell[] cells = game.currentOne.tetris;
		if (!game.stop && isdrop() && !downisCell()) {
			for (Cell c : cells) { // foreach遍历数组执行drop方法
				c.drop(); // 调用drop方法
			}
		}
	}

	public boolean isdrop() { // 判断是否可以继续下落的方法
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (c.getY() >= 19 * game.SIZE || downisCell()) { // 如果出界或下方有方块
				game.tetromino.count = 100000; // 充值计数器
				return false; // 出界返回false，不可再下落
			}
		}
		return true;
	}

	public void cellLift() { // 方块左移
		Cell[] cells = game.currentOne.tetris;
		if (!leftoutWall() && !leftisCell()) { // 如果左侧未出界且未碰撞
			for (Cell c : cells) {
				c.left();
			}
		}
	}

	public void cellLiftone() { // 方块左移一格
		Cell[] cells = game.currentOne.tetris;
		if (!leftoutWall() && !leftisCell()) { // 如果左侧未出界且未碰撞
			for (Cell c : cells) {
				c.left();
			}
		}
	}

	public void cellRight() { // 方块右移
		Cell[] cells = game.currentOne.tetris;
		if (!rightoutWall() && !rightisCell()) { // 如果右侧未出界且未碰撞
			for (Cell c : cells) {
				c.right();
			}
		}
	}

	public void cellRightone() { // 方块右移一格
		Cell[] cells = game.currentOne.tetris;
		if (!rightoutWall() && !rightisCell()) { // 如果右侧未出界且未碰撞
			for (Cell c : cells) {
				c.right();
			}
		}
	}

	public boolean leftoutWall() { // 防止方块左出界的方法
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (c.getX() <= 0) { // 如果方块x<=0
				return true; // 返回true，左侧出界
			}
		}
		return false;
	}

	public boolean rightoutWall() { // 防止方块右出界的方法
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (c.getX() >= 9 * game.SIZE) { // 如果方块x>=9*SIZE
				return true;
			}
		}
		return false;
	}

	/*
	 * 碰撞检测
	 */
	public boolean downisCell() { // 下方碰撞检测
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			int x = c.getX();
			int y = c.getY();
			for (int i = 0; i < game.tetrominos.size(); i++) {
				if (y + game.SIZE == game.tetrominos.get(i).getY() && x == game.tetrominos.get(i).getX()) { // 如果右侧有方块
					return true;
				}
			}
		}
		return false;
	}

	public boolean leftisCell() { // 左侧碰撞检测
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			int x = c.getX();
			int y = c.getY();
			for (int i = 0; i < game.tetrominos.size(); i++) {
				if (x - game.SIZE == game.tetrominos.get(i).getX() && y == game.tetrominos.get(i).getY()) { // 如果左边有方块
					return true;
				}
			}
		}
		return false;
	}

	public boolean rightisCell() { // 右侧碰撞检测
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			int x = c.getX();
			int y = c.getY();
			for (int i = 0; i < game.tetrominos.size(); i++) {
				if (x + game.SIZE == game.tetrominos.get(i).getX() && y == game.tetrominos.get(i).getY()) { // 如果下方有方块
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * 顺时针，向右旋转四格方块
	 */
	public void rotateRight() {
		// 旋转一次，计数器自增长1
		game.tetromino.count++;
		State s = game.currentOne.states[game.tetromino.count % game.currentOne.states.length]; // 选取即将变换的状态
		int x = game.currentOne.tetris[0].getX(); // 获取tetris[0]的x坐标
		int y = game.currentOne.tetris[0].getY(); // 获取tetris[0]的y坐标
		game.currentOne.tetris[1].setX(x + s.getX1()); // 基于tetris[0]的坐标改变其他方块的坐标，达成旋转
		game.currentOne.tetris[1].setY(y + s.getY1());
		game.currentOne.tetris[2].setX(x + s.getX2());
		game.currentOne.tetris[2].setY(y + s.getY2());
		game.currentOne.tetris[3].setX(x + s.getX3());
		game.currentOne.tetris[3].setY(y + s.getY3());
	}

	public boolean isRotate() { // 判断方块是否可以旋转
		game.tetromino.count++;
		State s = game.currentOne.states[game.tetromino.count % game.currentOne.states.length];
		int x1 = game.currentOne.tetris[0].getX() + s.getX1(); // 获取方块即将旋转到的位置坐标
		int y1 = game.currentOne.tetris[0].getY() + s.getY1();
		int x2 = game.currentOne.tetris[0].getX() + s.getX2();
		int y2 = game.currentOne.tetris[0].getY() + s.getY2();
		int x3 = game.currentOne.tetris[0].getX() + s.getX3();
		int y3 = game.currentOne.tetris[0].getY() + s.getY3();
		if (x1 < 0 || x2 < 0 || x3 < 0 || x1 > 9 * game.SIZE || x2 > 9 * game.SIZE || x3 > 9 * game.SIZE
				|| y1 > 19 * game.SIZE || y2 > 19 * game.SIZE || y3 > 19 * game.SIZE) { // 判断目标位置是否出界
			game.tetromino.count--; // 还原计数器
			return false;
		}
		for (int i = 0; i < game.tetrominos.size(); i++) { // 遍历tetrominos容器
			int x = game.tetrominos.get(i).getX();
			int y = game.tetrominos.get(i).getY();
			if ((x1 == x && y1 == y) | (x2 == x && y2 == y) | (x3 == x && y3 == y)) { // 判断目标位置是否有方块
				game.tetromino.count--;
				return false;
			}
		}
		game.tetromino.count--;
		return true;
	}

	public void rotateAction() { // 方块进行旋转
		if (isRotate()) { // 如果可以旋转
			rotateRight();
		}
	}

	/*
	 * 消行
	 */
	public void cellRemove() { // 消行
		int m = 0;
		int y = 20 * game.SIZE - game.SIZE;
		for (int i = 0; i < 20; i++) {// 网格_行
			for (int k = 0; k < game.tetrominos.size(); k++) {// 遍历容器tetrominos
				if (game.tetrominos.get(k).getY() == y) { // 如果此行每有一个方块
					m++;
				}
			}
			if (m == 10) { // 如果此行满10个方块
				game.number++; // 分数加一
				for (int p = 0; p < game.tetrominos.size(); p++) { // 对容器中的元素进行遍历
					if (game.tetrominos.get(p).getY() == y) { // 如果方块在此行
						game.tetrominos.remove(p); // 从容器中移除此方块
						p--; // 索引减一，防止漏过元素以至于出现未消除现象
					}
				}
				for (int h = 0; h < game.tetrominos.size(); h++) {// 消行后方块下落
					if (game.tetrominos.get(h).getY() < y && game.tetrominos.get(h).getY() + game.SIZE < 20 * game.SIZE) // 如果方块处于此行上方且下落后未出界
						game.tetrominos.get(h).setY(game.tetrominos.get(h).getY() + game.SIZE); // 方块下移
				}
			}
			y -= game.SIZE; // 遍历下一行
			m = 0;
		}
	}

	public boolean isGame() { // 判断是否游戏结束
		Cell[] cells = game.currentOne.tetris;
		for (Cell c : cells) {
			if (downisCell() && c.getY() <= 0) { // 如果下方有方块且上方达到网格最高处
				game.dialogOver();
				return false;
			}
		}
		return true;
	}

	/*
	 * 按键监听
	 */
	public void keyPressed(KeyEvent e) { // 按键监听，按下键时进行事件处理
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.game.down = true;
		}
	}

	public void keyReleased(KeyEvent e) { // 按键监听，抬起时进行事件处理
		if (e.getKeyCode() == KeyEvent.VK_UP && isdrop()) {
			rotateAction();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && isdrop()) { // 抬起左键且方块可以继续下落时
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
