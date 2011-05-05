package client;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.LinkedList;

public class TopLineList implements SimpleActionListener {

	LinkedList<Line> lines;
	int anchorX, anchorY;
	
	public TopLineList() {
		lines = new LinkedList<Line>();
	}
	public TopLineList(int anchorX, int anchorY) {
		lines = new LinkedList<Line>();
		this.anchorX = anchorX;
		this.anchorY = anchorY;
	}
	
	@Override
	public void act(int framenum, int y, Circle newTop, Circle oldTop) {
		lines.add(new Line(anchorX, anchorY, Circle.x + (framenum-1)*5, y-oldTop.speed, oldTop.color));
		anchorX = Circle.x + (framenum-1)*5;
		anchorY = y-oldTop.speed;
	}
	
	public void drawLines(Circle c, Graphics2D g, int framenum) {
		for(int i=0;i<lines.size();i++) {
			lines.get(i).draw(g);
		}
		Stroke b = g.getStroke();
		g.setStroke(new BasicStroke(8.0f));
		g.setColor(c.color);
		g.drawLine(anchorX, anchorY, Circle.x + framenum*5, c.y);
		g.setStroke(b);
	}

}
