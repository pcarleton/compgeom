package client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Line {
	int x1;
	int y1;
	int x2;
	int y2;
	Color color;
	
	public Line(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(8.0f));
		
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
}
