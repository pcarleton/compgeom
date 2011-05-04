package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client {

	static Timer timer;
	final static int DELAY = 100;
	final static int HEIGHT = 500;
	final static int WIDTH = 500;
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		
		final MoveableShape shape = new Circle(0, 0, 5);
		ShapeIcon icon = new ShapeIcon(shape, WIDTH, HEIGHT);

		final JLabel label = new JLabel(icon);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);



		timer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shape.translate(0,1);
				label.repaint();
			}
		});

		timer.start();
	}
	
	public static void start() {
		timer.start();
	}
	
	public static void stop() {
		timer.stop();
	}


}
