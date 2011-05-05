package client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
	boolean isLeaf = false;
	static Circle winningCircle;
	int depth;
	int bigDiam = 60;
	int smallDiam = 20;
	
	public GameViz(int depth) {
		this.depth = depth;
		x = bbX + indent;
		indent += bigDiam;
		y = bbY + depth*(bigDiam + 10);
		if (gvs == null) {
			gvs = new LinkedList<GameViz>();
		}
		gvs.add(this);
		if(depth == 0) {
			winningCircle = new Circle(y-40);
			winningCircle.x = x+20;
			winningCircle.diameter = smallDiam;
		}
	}
	
	public void draw() {
		if (g2 != null) {
			g2.setPaint(bg);
        	g2.fillOval(x, y, bigDiam, bigDiam);
        	g2.setPaint(left);
        	g2.fillOval(x, y+20, smallDiam, smallDiam);
        	g2.setPaint(right);
        	g2.fillOval(x+40, y+20, smallDiam, smallDiam);
        	if(!isLeaf) {
        		Stroke b = g2.getStroke();
        		g2.setStroke(new BasicStroke(8.0f));
        		g2.setPaint(left);
        		g2.drawLine(x, y+35, x-30, y+70);
        		g2.setPaint(right);
        		g2.drawLine(x+60, y+35, x+90, y+70);
        		g2.setStroke(b);
        	}
        	if(depth == 0){
        		winningCircle.step(0, g2);
        	}
		}
	}
	
	public static void repaint() {
		for (GameViz g: gvs) {
			g.draw();
		}
	}
}
