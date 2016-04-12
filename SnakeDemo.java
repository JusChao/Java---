package snake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SnakeDemo extends JFrame {
	static long id = 0;
	static SnakeBody sb = new SnakeBody(id);
	public SnakeDemo() {
		setBounds(0, 0, 530, 1000);
		this.add(sb);
		this.addKeyListener(sb);
		Thread td = new Thread(sb);
		id = td.getId();
		td.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		SnakeDemo sd = new SnakeDemo();
		sd.setVisible(true);
	}
	

}
