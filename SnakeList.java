package snake;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class SnakeList {
	LinkedList<SnakeNode> sl = new LinkedList<SnakeNode>();

	public SnakeList() {
		SnakeNode sn = new SnakeNode(100, 100);
		sl.addFirst(sn);
	}
	
	public void clear() {
		sl.clear();
	}
	
	public LinkedList addSnake(SnakeNode sn) {
		sl.addFirst(sn);
		return sl;
	}
	
	public LinkedList addLastSnake(SnakeNode sn) {
		sl.addLast(sn);
		return sl;
	}
	
	public int snakelenght() {
		return sl.size();
	}

	public SnakeNode getNode(int i) {
		return sl.get(i);
	}

	public SnakeNode getLast() {
		return sl.getLast();
	}

	public SnakeNode getfirst() {
		return sl.getFirst();
	}

	public void snakerun(SnakeNode sn) {
		if (!sl.isEmpty()) {
			synchronized (sn) {
				sl.addFirst(sn);
				sl.remove(sl.size() - 1);
			}
		}
		for (int i = 0; i < sl.size(); i++) {
			if (sl.get(i).y < 60) {
				sl.get(i).y = 940;
			}
			if (sl.get(i).y > 940) {
				sl.get(i).y = 60;
			}
			if (sl.get(i).x < 10) {
				sl.get(i).x = 490;
			}
			if (sl.get(i).x > 490) {
				sl.get(i).x = 10;
			}
		}
		for (int i = 1; i < sl.size(); i++) {
			if (sl.getFirst().x == sl.get(i).x
					&& sl.getFirst().y == sl.get(i).y) {
				JOptionPane.showMessageDialog(null, "×²ÉÏÁË");
				System.exit(0);
			}
		}
	}

	public boolean isEmpty() {
		return sl.isEmpty();
	}
}
