package client;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle {
	int x, y;
	int diameter;
	Color color;
	
	int speed = 3;
	
	public Circle(int x, int y, int diameter) {
		this(x, y, diameter, Color.BLACK, 1);
	}

	public Circle(int x, int y, int diameter,Color c, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.diameter = diameter;
		this.color = c;
	}
	
	public void step(int framenum, Graphics2D g2) {
        int newy = y + speed*framenum;

        System.out.println("Circle: " + framenum);
        g2.setPaint(color);
        g2.fillOval(x, newy, diameter, diameter);
        g2.drawLine(x, y, x+5*framenum, newy);
	}

	

}
