package client;

/*
 * Swing version.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.geom.*;

/* 
 * Draws shapes against a solid background color
 * Part II allows you to make the shapes move
 * Ignore the "timer" stuff, not important to you
 */

//Ignore all this stuff, you don't need to know it

public class ArtLab extends JApplet implements ActionListener {

	
	void buildUI(Container container) {
		
		JPanel mainPanel = new JPanel(new BorderLayout());

		animationPane = new AnimationPane();

		theT = new TPane();
		animationPane.setPreferredSize(new Dimension(870, 200));
		theT.setPreferredSize(new Dimension(700, 200));
		mainPanel.add(animationPane, BorderLayout.WEST);
		mainPanel.add(theT, BorderLayout.EAST);
		container.add(mainPanel);
		JButton b1 = new JButton("Disable middle button");
	    b1.setVerticalTextPosition(AbstractButton.CENTER);
	    b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
	    b1.setMnemonic(KeyEvent.VK_D);
	    b1.setActionCommand("disable");
	    b1.setSize(100, 50);
	    b1.setPreferredSize(new Dimension(100,50));
	    mainPanel.add(b1);
	    mainPanel.validate();
		
	}

	// Can be invoked from any thread.
	

	
	// Here's where your shapes are drawn!
	
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
		f.setSize(new Dimension(1600, 1000));
		f.setVisible(true);
		controller.startAnimation();
	}
}
