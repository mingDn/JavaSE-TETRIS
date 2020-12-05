package 俄罗斯方块;

/**
 * 音乐播放器类
 * 
 * @author
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

// 继承自线程类Thread
public class AudioPlayer extends Thread {
	static Player player;
	String music;
	boolean stop;
	boolean die;

	// 构造方法
	public AudioPlayer(String music) {
		this.music = music;
		this.stop = false;
		this.die = false;
	}

	// 重写run方法
	@Override
	public void run() {
		super.run();
		while (!die) {
			try {
				if (!stop) {
					play();
				}
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}

	// 播放方法
	public void play() throws FileNotFoundException, JavaLayerException {

		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
		player = new Player(buffer);
		player.play();
	}

	public void stopMusic() {
		stop = !stop;
		if (stop) {
			player.close();
		} else if (!stop) {
			try {
				player.play();
			} catch (JavaLayerException e) {
			}
		}
	}

	public void closeMusic() {
		die = true;
		player.close();
	}
}
