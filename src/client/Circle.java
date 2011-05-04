package client;

import java.awt.Color;
import java.awt.Graphics2D;

public class Circle {
	int origY;
	int y;
	static int diameter = 10;
	static int x = 100;
	Color color;
	
	int speed = 3;
	
	public Circle(int y) {
		this(y, Color.BLACK, 1);
	}

	public Circle(int y,Color c, int speed) {
		this.origY = y;
		this.y = y;
		this.speed = speed;
		this.color = c;
	}
	
	public void step(int framenum, Graphics2D g2) {
        y = origY + speed*framenum;

        System.out.println("Circle: " + framenum);
        g2.setPaint(color);
        g2.fillOval(x, y, diameter, diameter);

	}

	

}
