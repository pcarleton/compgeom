package client;

/*
 * Swing version.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.awt.geom.*;

/* 
 * Draws shapes against a solid background color
 * Part II allows you to make the shapes move
 * Ignore the "timer" stuff, not important to you
 */

//Ignore all this stuff, you don't need to know it

public class ArtLab extends JApplet implements ActionListener {

	public int frameNumber = -1;
	boolean frozen = true;
	Timer timer;
	AnimationPane animationPane;

	void buildUI(Container container) {
		int fps = 0;

		// How many milliseconds between frames?
		int delay = (fps > 0) ? (1000 / fps) : 100;

		// Set up a timer that calls this object's action handler.
		timer = new Timer(delay, this);
		timer.setInitialDelay(0);
		timer.setCoalesce(true);

		animationPane = new AnimationPane();
		container.add(animationPane, BorderLayout.CENTER);

		animationPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (frozen) {
					frozen = false;
					startAnimation();
				} else {
					frozen = true;
					stopAnimation();
				}
			}
		});
	}

	// Can be invoked from any thread.
	public synchronized void startAnimation() {
		if (frozen) {
			// Do nothing. The user has requested that we
			// stop changing the image.
		} else {
			// Start animating!
			if (!timer.isRunning()) {
				timer.start();
			}
		}
	}

	// Can be invoked from any thread.
	public synchronized void stopAnimation() {
		// Stop the animating thread.
		if (timer.isRunning()) {
			timer.stop();
		}
	}

	public void actionPerformed(ActionEvent e) {
		// Advance animation frame.
		if (frameNumber < 200) {
			frameNumber++;

			// Display it.
			animationPane.repaint();
		}

	}

	// Here's where your shapes are drawn!
	public class AnimationPane extends JPanel {

		// declare variables for use with shapes

		AffineTransform original;
		Stroke originalStroke;
		BasicStroke wideStroke;
		Graphics2D g2;

		int screenWidth;
		int screenHeight;

		int width; // width, height, x and y used as parameters for drawXxx and
					// fillXxx methods
		int height;
		int x;
		int y;
		int startAngle; // next two used only with the drawArc and fillArc
						// methods
		int arcAngle;
		int x1; // next four used with the drawLine method
		int y1;
		int x2;
		int y2;
		CircleList cl;
		TopLineList tll;
		Color black = Color.black;
		Color blue = Color.blue;
		Color gray = Color.gray;
		Color red = Color.red;
		Color yellow = Color.yellow;
		Color orange = Color.orange;
		Color cyan = Color.cyan;
		Color green = Color.green;
		Color magenta = Color.magenta;
		Color white = Color.white;
		Color pink = Color.pink;
		
		KineticTournament kt;

		public AnimationPane() {
			cl = new CircleList();
			cl.addCircle(new Circle(500, orange, -7))
					.addCircle(new Circle(100,green, 8))
					.addCircle(new Circle(250,yellow, 1))
					.addCircle(new Circle(400,cyan, -4));
			tll = new TopLineList(cl.getTop().x, cl.getTop().y);
			cl.subscribe(tll);
			Kinetic
			

		}

		// Draw the current frame of animation.
		public void paintComponent(Graphics g) {

			super.paintComponent(g); // paint any space not covered
										// by the background image
			g2 = (Graphics2D) g;

			wideStroke = new BasicStroke(8.0f);
			originalStroke = g2.getStroke();

			// get the screen size
			screenWidth = getWidth();
			screenHeight = getHeight();

			// set the colors


			// draw the background
			g2.setPaint(gray);
			g2.fillRect(0, 0, screenWidth, screenHeight);

			// draw the shapes


			cl.step(frameNumber, g2);
			tll.drawLines(cl.getTop(),g2, frameNumber);
			newShape();


		} // Do not erase this bracket
		// Methods for moving the objects

		// rotates shape so that is spins around itself
		// speed is how fast
		public void spin(int speed) {
			original = g2.getTransform();
			g2.rotate(Math.toRadians(speed * frameNumber), (double) x + width
					/ 2, (double) y + height / 2);
		}

		// rotates line so that is spins around itself
		// speed is how fast
		public void spinLine(int speed) {
			original = g2.getTransform();
			g2.rotate(Math.toRadians(speed * frameNumber),
					(double) (x1 + x2) / 2, (double) (y1 + y2) / 2);
		}

		// rotates shape so that it circles the x,y coordinates
		public void circle(int speed, int x, int y) {
			original = g2.getTransform();
			g2.rotate(Math.toRadians(speed * frameNumber), (double) x,
					(double) y);
		}

		// Returns the x-coordinate that will move the object forward in a line
		// in the x-direction
		// start is where on the screen you want it to start, speed is how fast
		// it should move
		public int xFwdLine(int start, int speed) {
			double x;

			x = start + frameNumber * speed;

			// if component has travelled off-screen
			if (x > screenWidth) {
				x = x - (Math.floor(x / screenWidth)) * screenWidth; // calculate
																		// the
																		// remainder
			}
			return (int) x;
		}

		// Returns the x-coordinate that will move the object backward in a line
		// in the x-direction
		// start is where on the screen you want it to start, speed is how fast
		// it should move
		public int xBwdLine(int start, int speed) {
			double x;

			x = start - frameNumber * speed;

			// if component has travelled off-screen
			if ((x + width) < 0) {
				x = Math.abs(x) - (Math.floor(Math.abs(x) / screenWidth))
						* screenWidth; // calculate the remainder
				x = screenWidth - width - x;
			}
			return (int) x;
		}

		// Returns the y-coordinate that will move the object backward in a line
		// in the y-direction
		// start is where on the screen you want it to start, speed is how fast
		// it should move
		public int yBwdLine(int start, int speed) {
			double y;

			y = start - frameNumber * speed;

			// if component has travelled off-screen
			if ((y + height) < 0) {
				y = Math.abs(y) - (Math.floor(Math.abs(y) / screenHeight))
						* screenHeight; // calculate the remainder
				y = screenHeight - height - y;
			}
			return (int) y;
		}

		// Returns the y-coordinate that will move the object forward in a line
		// in the y-direction
		// start is where on the screen you want it to start, speed is how fast
		// it should move
		public int yFwdLine(int start, int speed) {
			double y;

			y = start + frameNumber * speed;

			// if component has travelled off-screen
			if (y > screenHeight) {
				y = y - (Math.floor(y / screenHeight)) * screenHeight; // calculate
																		// the
																		// remainder
			}
			return (int) y;
		}

		// moves the x-coordinate up and down
		// dist is how far up and down to move it, speed is how fast
		public int xBounce(int dist, int speed) {
			double x;
			x = (dist * (Math.sin(frameNumber * speed) + 10)) + 100;
			return (int) x;
		}

		// moves the y-coordinate up and down
		// dist is how far up and down to move it, speed is how fast
		public int yBounce(int dist, int speed) {
			double y;
			y = (dist * (Math.sin(frameNumber * speed) + 10)) + 100;
			return (int) y;
		}

		// resets the Stroke and rotation attributes of the new shape
		public void newShape() {
			if (original != null) {
				g2.setTransform(original);
			}
			if (g2.getStroke() == wideStroke) {
				g2.setStroke(originalStroke);
			}
		}

		// method that creates a paint that fades from one color to another
		public GradientPaint Fade(Color first, Color second) {
			GradientPaint Gradient = new GradientPaint(x, y, first, x + width,
					y, second);
			return Gradient;
		}

	}

	// Method to start program
	public static void main(String[] args) {

		JFrame f = new JFrame("Art Lab");
		final ArtLab controller = new ArtLab();
		controller.buildUI(f.getContentPane());

		f.addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				controller.stopAnimation();
			}

			public void windowDeiconified(WindowEvent e) {
				controller.startAnimation();
			}

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setSize(new Dimension(800, 800));
		f.setVisible(true);
		controller.startAnimation();
	}
}
