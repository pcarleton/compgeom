package client;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class TPane extends JPanel {
		AffineTransform original;
		Stroke originalStroke;
		BasicStroke wideStroke;
		Graphics2D g2;

		int screenWidth;
		int screenHeight;

		int width;

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // paint any space not covered
										// by the background image
			g2 = (Graphics2D) g;

			wideStroke = new BasicStroke(8.0f);
			originalStroke = g2.getStroke();

			// get the screen size
			screenWidth = 500;
			screenHeight = 200;

			// g2.setPaint(Color.black);
			// g2.fillRect(0, 0, screenWidth, screenHeight);
			GameViz.g2 = g2;
			GameViz.repaint();

		}

	}
