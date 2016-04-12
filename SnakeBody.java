package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SnakeBody extends JPanel implements Runnable, KeyListener {
	SnakeList sl = null;
	Font ft = new Font("宋体", Font.BOLD, 16);
	int i = 0; //判断存档
	int jl = 0;
	int score = 0;//分数
	Object o = new Object();
	int jp = 0;//判断是否存档退出
	static int code = 0;
	Direction dr = new Direction();
	int num = 0;
	long id = 0;
	Food fd = new Food();
	int x = fd.getNode().x;
	int y = fd.getNode().y;
	private BufferedReader br;
	SnakeNode sn = null;

	public SnakeBody(long id) {
		this.id = id;
		sl = new SnakeList();
		jp = JOptionPane.showConfirmDialog(null, "是否读取存档");
		if (jp == 0) {
			try {
				sl.clear();
				br = new BufferedReader(new FileReader("record.txt"));
				String s = null;
				try {
					while ((s = br.readLine()) != null) {
						if (i == 0) {
							score = Integer.parseInt(s);
							i++;
						} else if (i == 1) {
							String[] sp = s.split(" ");
							x = Integer.parseInt(sp[0]);
							y = Integer.parseInt(sp[1]);
							i++;
						} else {
							String[] sp = s.split(" ");
							int a = Integer.parseInt(sp[0]);
							int b = Integer.parseInt(sp[1]);
							sl.addLastSnake(new SnakeNode(a, b));
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void save() {
		int rt = 0;
		rt = JOptionPane.showConfirmDialog(null, "保存并退出");
		if (rt == 0) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"record.txt"));
				bw.write(String.valueOf(score));
				bw.newLine();
				bw.flush();
				bw.write(String.valueOf(x));
				bw.write(" ");
				bw.write(String.valueOf(y));
				bw.newLine();
				bw.flush();
				for (int i = 0; i < sl.snakelenght(); i++) {
					bw.write(String.valueOf(sl.getNode(i).x));
					bw.write(" ");
					bw.write(String.valueOf(sl.getNode(i).y));
					bw.newLine();
					bw.flush();
				}
				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
		code = jl;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(ft);
		g.setColor(Color.black);
		g.drawString("分数:", 40, 40);
		g.setColor(Color.red);
		g.drawString(String.valueOf(score), 100, 40);
		g.fillRect(x, y, 10, 10);
		g.fillRect(sl.getNode(0).x, sl.getNode(0).y, 10, 10);
		g.setColor(Color.black);
		g.drawRect(10, 60, 490, 890);
		synchronized (this) {
			for (int i = 1; i < sl.snakelenght(); i++) {
				g.fillRect(sl.getNode(i).x, sl.getNode(i).y, 10, 10);
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 38:
			code = 38;
			break;
		case 39:
			code = 39;
			break;
		case 37:
			code = 37;
			break;
		case 40:
			code = 40;
			break;
		case 32:
			code = 32;
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void run() {
		while (true) {
			if (code == 32) {
				save();
				while (code == 32) {

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (sl.isEmpty()) {

			} else {

				if (sl.getfirst().x == x && sl.getfirst().y == y) {
					score++;
					sl.addSnake(new SnakeNode(x, y));
					x = fd.getNode().x;
					y = fd.getNode().y;
				}
				num = dr.dir(num, code);
				switch (num) {
				case 38:
					sl.snakerun(new SnakeNode(sl.getfirst().x,
							sl.getfirst().y - 10));
					break;
				case 39:
					sl.snakerun(new SnakeNode(sl.getfirst().x + 10, sl
							.getfirst().y));
					break;
				case 37:
					sl.snakerun(new SnakeNode(sl.getfirst().x - 10, sl
							.getfirst().y));
					break;
				case 40:
					sl.snakerun(new SnakeNode(sl.getfirst().x,
							sl.getfirst().y + 10));
					break;
				default:
					break;
				}
				jl = num;
				repaint();
			}
		}
	}
}
