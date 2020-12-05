package 俄罗斯方块;

/**
 * 方块类
 * 
 * @author 12279
 *
 */

public class Tetromino {
	Cell[] tetris = new Cell[4]; // 创建方块数组
	State[] states; // 创建状态数组
	int count = 100000; // 计数器

	public Tetromino randomOne() { // 利用随机数随机产生一个方块
		Tetromino t = null;
		int num = (int) (Math.random() * 7);
		switch (num) {
		case 0:
			t = new I();
			break;
		case 1:
			t = new J();
			break;
		case 2:
			t = new L();
			break;
		case 3:
			t = new O();
			break;
		case 4:
			t = new S();
			break;
		case 5:
			t = new T();
			break;
		case 6:
			t = new Z();
			break;
		}
		return t;
	}
}

class I extends Tetromino {
	Cell cell = new Cell();

	public I() {
		tetris[0] = new Cell(4, 0, cell.I);
		tetris[1] = new Cell(3, 0, cell.I);
		tetris[2] = new Cell(5, 0, cell.I);
		tetris[3] = new Cell(6, 0, cell.I);
		states = new State[4];
		states[0] = new State(0, 0, -1, 0, 1, 0, 2, 0);
		states[1] = new State(0, 0, 0, -1, 0, 1, 0, 2);
		states[2] = new State(0, 0, 1, 0, -1, 0, -2, 0);
		states[3] = new State(0, 0, 0, 1, 0, -1, 0, -2);
	}
}

class J extends Tetromino {
	Cell cell = new Cell();

	public J() {
		tetris[0] = new Cell(4, 1, cell.J);
		tetris[1] = new Cell(5, 1, cell.J);
		tetris[2] = new Cell(3, 1, cell.J);
		tetris[3] = new Cell(3, 0, cell.J);
		states = new State[4];
		states[0] = new State(0, 0, 1, 0, -1, 0, -1, -1);
		states[1] = new State(0, 0, 0, 1, 0, -1, 1, -1);
		states[2] = new State(0, 0, -1, 0, 1, 0, 1, 1);
		states[3] = new State(0, 0, 0, -1, 0, 1, -1, 1);
	}
}

class L extends Tetromino {
	Cell cell = new Cell();

	public L() {
		tetris[0] = new Cell(4, 1, cell.L);
		tetris[1] = new Cell(5, 1, cell.L);
		tetris[2] = new Cell(3, 1, cell.L);
		tetris[3] = new Cell(5, 0, cell.L);
		states = new State[4];
		states[0] = new State(0, 0, 1, 0, -1, 0, 1, -1);
		states[1] = new State(0, 0, 0, 1, 0, -1, 1, 1);
		states[2] = new State(0, 0, -1, 0, 1, 0, -1, 1);
		states[3] = new State(0, 0, 0, -1, 0, 1, -1, -1);
	}
}

class O extends Tetromino {
	Cell cell = new Cell();

	public O() {
		tetris[0] = new Cell(4, 0, cell.O);
		tetris[1] = new Cell(5, 0, cell.O);
		tetris[2] = new Cell(4, 1, cell.O);
		tetris[3] = new Cell(5, 1, cell.O);
		states = new State[1];
		states[0] = new State(0, 0, 1, 0, 0, 1, 1, 1);
	}
}

class S extends Tetromino {
	Cell cell = new Cell();

	public S() {
		tetris[0] = new Cell(4, 0, cell.S);
		tetris[1] = new Cell(5, 0, cell.S);
		tetris[2] = new Cell(3, 1, cell.S);
		tetris[3] = new Cell(4, 1, cell.S);
		states = new State[4];
		states[0] = new State(0, 0, 1, 0, -1, 1, 0, 1);
		states[1] = new State(0, 0, 0, -1, 1, 0, 1, 1);
		states[2] = new State(0, 0, 1, -1, 0, -1, -1, 0);
		states[3] = new State(0, 0, 0, 1, -1, 0, -1, -1);
	}
}

class T extends Tetromino {
	Cell cell = new Cell();

	public T() {
		tetris[0] = new Cell(4, 0, cell.T);
		tetris[1] = new Cell(3, 1, cell.T);
		tetris[2] = new Cell(4, 1, cell.T);
		tetris[3] = new Cell(5, 1, cell.T);
		states = new State[4];
		states[0] = new State(0, 0, 1, 0, -1, 0, 0, -1);
		states[1] = new State(0, 0, 0, 1, 0, -1, 1, 0);
		states[2] = new State(0, 0, -1, 0, 1, 0, 0, 1);
		states[3] = new State(0, 0, 0, -1, 0, 1, -1, 0);
	}
}

class Z extends Tetromino {
	Cell cell = new Cell();

	public Z() {
		tetris[0] = new Cell(4, 0, cell.Z);
		tetris[1] = new Cell(3, 0, cell.Z);
		tetris[2] = new Cell(5, 1, cell.Z);
		tetris[3] = new Cell(4, 1, cell.Z);
		states = new State[4];
		states[0] = new State(0, 0, -1, 0, 1, 1, 0, 1);
		states[1] = new State(0, 0, 0, -1, -1, 1, -1, 0);
		states[2] = new State(0, 0, 1, 0, -1, -1, 0, -1);
		states[3] = new State(0, 0, 0, 1, 1, -1, 1, 0);
	}
}