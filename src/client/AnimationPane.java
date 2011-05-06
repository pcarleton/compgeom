package client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AnimationPane extends JPanel {

	// declare variables for use with shapes
	public static int frameNumber = -1;
	AffineTransform original;
	Stroke originalStroke;
	BasicStroke wideStroke;
	Graphics2D g2;
	public MainFrame theMain;
	ArrayList<Circle> theCircs = new ArrayList<Circle>();

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

	public AnimationPane(MainFrame m) {
		theMain =  m;
		System.out.println(theMain);
		cl = new CircleList();
		

	}

	public void generate() {
		cl = new CircleList();
		cl.addCircle(new Circle(210, red, Integer.parseInt(theMain.RedEdit.getText())))
			.addCircle(new Circle(50, blue, Integer.parseInt(theMain.BlueEdit.getText())))
			.addCircle(new Circle(100, green, Integer.parseInt(theMain.GreenEdit.getText())))
			.addCircle(new Circle(250, yellow, Integer.parseInt(theMain.YellowEdit.getText())))
			.addCircle(new Circle(300, white, Integer.parseInt(theMain.WhiteEdit.getText())))
			.addCircle(new Circle(400, cyan, Integer.parseInt(theMain.CyanEdit.getText())))
			.addCircle(new Circle(500, magenta, Integer.parseInt(theMain.PinkEdit.getText())))
			.addCircle(new Circle(540, orange, Integer.parseInt(theMain.OrangeEdit.getText())));
		theCircs = new ArrayList<Circle>();
		theCircs.add(cl.circs.get(0));
		theCircs.add(cl.circs.get(1));
		theCircs.add(cl.circs.get(2));
		theCircs.add(cl.circs.get(3));
		theCircs.add(cl.circs.get(4));
		theCircs.add(cl.circs.get(5));
		theCircs.add(cl.circs.get(6));
		theCircs.add(cl.circs.get(7));
		cl.sortByY();
		tll = new TopLineList(cl.getTop().x, cl.getTop().y);
		cl.subscribe(tll);
		kt = new KineticTournament((ArrayList<Circle>) cl.circs.clone());
	}
	public void reset() {
		frameNumber = 0;
		cl = new CircleList();
		cl.addCircle(new Circle(210, red, Integer.parseInt(theMain.RedEdit.getText())))
			.addCircle(new Circle(50, blue, Integer.parseInt(theMain.BlueEdit.getText())))
			.addCircle(new Circle(100, green, Integer.parseInt(theMain.GreenEdit.getText())))
			.addCircle(new Circle(250, yellow, Integer.parseInt(theMain.YellowEdit.getText())))
			.addCircle(new Circle(300, white, Integer.parseInt(theMain.WhiteEdit.getText())))
			.addCircle(new Circle(400, cyan, Integer.parseInt(theMain.CyanEdit.getText())))
			.addCircle(new Circle(500, magenta, Integer.parseInt(theMain.PinkEdit.getText())))
			.addCircle(new Circle(540, orange, Integer.parseInt(theMain.OrangeEdit.getText())));
		theCircs = new ArrayList<Circle>();
		theCircs.add(cl.circs.get(0));
		theCircs.add(cl.circs.get(1));
		theCircs.add(cl.circs.get(2));
		theCircs.add(cl.circs.get(3));
		theCircs.add(cl.circs.get(4));
		theCircs.add(cl.circs.get(5));
		theCircs.add(cl.circs.get(6));
		theCircs.add(cl.circs.get(7));
		cl.sortByY();
		tll = new TopLineList(cl.getTop().x, cl.getTop().y);
		cl.subscribe(tll);
		GameViz.indent = 0;
		kt = new KineticTournament((ArrayList<Circle>) cl.circs.clone());

	}

	// Draw the current frame of animation.
	public void paintComponent(Graphics g) {
		if(cl.circs.size() != 0) {
			theCircs.get(0).speed = Integer.parseInt(theMain.RedEdit.getText());
			theCircs.get(1).speed = Integer.parseInt(theMain.BlueEdit.getText());
			theCircs.get(2).speed = Integer.parseInt(theMain.GreenEdit.getText());
			theCircs.get(3).speed = Integer.parseInt(theMain.YellowEdit.getText());
			theCircs.get(4).speed = Integer.parseInt(theMain.WhiteEdit.getText());
			theCircs.get(5).speed = Integer.parseInt(theMain.CyanEdit.getText());
			theCircs.get(6).speed = Integer.parseInt(theMain.PinkEdit.getText());
			theCircs.get(7).speed = Integer.parseInt(theMain.OrangeEdit.getText());
		}
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
		if(cl.circs.size() != 0) {
			cl.step(frameNumber, g2);
			tll.drawLines(cl.getTop(), g2, frameNumber);
		}
		newShape();

	} // Do not erase this bracket
		// Methods for moving the objects

	// rotates shape so that is spins around itself
	// speed is how fast
	public void spin(int speed) {
		original = g2.getTransform();
		g2.rotate(Math.toRadians(speed * frameNumber), (double) x + width / 2,
				(double) y + height / 2);
	}

	// rotates line so that is spins around itself
	// speed is how fast
	public void spinLine(int speed) {
		original = g2.getTransform();
		g2.rotate(Math.toRadians(speed * frameNumber), (double) (x1 + x2) / 2,
				(double) (y1 + y2) / 2);
	}

	// rotates shape so that it circles the x,y coordinates
	public void circle(int speed, int x, int y) {
		original = g2.getTransform();
		g2.rotate(Math.toRadians(speed * frameNumber), (double) x, (double) y);
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
		GradientPaint Gradient = new GradientPaint(x, y, first, x + width, y,
				second);
		return Gradient;
	}

}
