package client;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class TopLineList implements SimpleActionListener {

	LinkedList<Line> lines;
	int anchorX, anchorY;
	
	public TopLineList() {
		lines = new LinkedList<Line>();
	}
	public TopLineList(int anchorX, int anchorY) {
		this.anchorX = anchorX;
		this.anchorY = anchorY;
	}
	
	@Override
	public void act(int x, int y, Circle newTop, Circle oldTop) {
		
	}
	
	public void drawLines(Circle c, Graphics2D g, int framenum) {
		for(int i=0;i<lines.size()-1;i++) {
			lines.get(i).draw(g);
		}
		g.setColor(c.color);
		g.drawLine(anchorX, anchorY, framenum*5, c.y);
	}

}
