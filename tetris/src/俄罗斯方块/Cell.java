package 俄罗斯方块;

/**
 * 
 * 参数类
 * 方块初始参数
 * 
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Cell {

	final int SIZE = 31;
	private int x, y; // 小方块坐标
	private Image image;

	Image I = new ImageIcon("./src/TetrisPicture/tetris_I.jpg").getImage(); // 加载方块图片
	Image J = new ImageIcon("./src/TetrisPicture/tetris_J.jpg").getImage();
	Image L = new ImageIcon("./src/TetrisPicture/tetris_L.jpg").getImage();
	Image O = new ImageIcon("./src/TetrisPicture/tetris_O.jpg").getImage();
	Image S = new ImageIcon("./src/TetrisPicture/tetris_S.jpg").getImage();
	Image T = new ImageIcon("./src/TetrisPicture/tetris_T.jpg").getImage();
	Image Z = new ImageIcon("./src/TetrisPicture/tetris_Z.jpg").getImage();

	public Cell() {
	}

	public Cell(int x, int y, Image image) {
		this.x = x * SIZE;
		this.y = y * SIZE - SIZE;
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void drawImage(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	/*
	 * 基础移动方法
	 */
	public void left() {
		this.x -= SIZE;
	}

	public void right() {
		this.x += SIZE;
	}

	public void drop() { // 下降方法
		this.y += SIZE;
	}
}