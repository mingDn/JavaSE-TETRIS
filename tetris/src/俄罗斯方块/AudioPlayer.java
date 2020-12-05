package ����˹����;

/**
 * ���ֲ�������
 * 
 * @author
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

// �̳����߳���Thread
public class AudioPlayer extends Thread {
	static Player player;
	String music;
	boolean stop;
	boolean die;

	// ���췽��
	public AudioPlayer(String music) {
		this.music = music;
		this.stop = false;
		this.die = false;
	}

	// ��дrun����
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

	// ���ŷ���
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
