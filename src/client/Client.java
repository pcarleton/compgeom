package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;

public class Client {
	private Ellipse2D.Double circle = new Ellipse2D.Double(10, 10, 5, 5);

	int x, y;
	private Timer timer;
	Graphics2D g2d;

	public void paintComponent(Graphics g) {
		JFrame frame = new JFrame();

		final JLabel label = new JLabel();
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		x = 10;
		y = 10;
		clear(g);
		g2d = (Graphics2D) g;
		g2d.fill(circle);
		circle.setFrame(10, 40, 5, 5);

		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				move();
			}
		});

		timer.start();
	}

	// super.paintComponent clears offscreen pixmap,
	// since we're using double buffering by default.

	protected void clear(Graphics g) {
		super.paintComponent(g);
	}

	protected void move() {
		System.out.println("MOVE");
		System.out.println(circle.x);
		circle.setFrame(circle.getCenterX() + 10, circle.getCenterY() + 10, 5,
				5);
	}

	protected Ellipse2D.Double getCircle() {
		return (circle);
	}

	public static void main(String[] args) {
		WindowUtilities.openInJFrame(new Client(), 380, 400);
	}
}
