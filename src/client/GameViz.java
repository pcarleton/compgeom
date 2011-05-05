package client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class GameViz {

	static Graphics2D g2;
	static int indent;
	final static int bbX = 0;
	final static int bbY = 100;
	int x, y;
	static LinkedList<GameViz> gvs;
	Color left = Color.black;
	Color right = Color.black;
	Color bg = Color.white;
	
	int bigDiam = 60;
	int smallDiam = 20;
	
	public GameViz(int depth) {
		x = bbX + indent;
		indent += bigDiam;
		y = bbY + depth*(bigDiam + 10);
		if (gvs == null) {
			gvs = new LinkedList<GameViz>();
		}
		gvs.add(this);
	}
	
	public void draw() {
		if (g2 != null) {
			g2.setPaint(bg);
        	g2.fillOval(x, y, bigDiam, bigDiam);
        	g2.setPaint(left);
        	g2.fillOval(x, y+20, smallDiam, smallDiam);
        	g2.setPaint(right);
        	g2.fillOval(x+40, y+20, smallDiam, smallDiam);
		}
	}
	
	public static void repaint() {
		for (GameViz g: gvs) {
			g.draw();
		}
	}
}
