package client;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CircleList {
	
	ArrayList<Circle> circs;
	LinkedList<SimpleActionListener> listeners;
	
	public CircleList() {
		circs = new ArrayList<Circle>();
		listeners = new LinkedList<SimpleActionListener>();
	}
	
	public CircleList addCircle(Circle c) {
		circs.add(c);
		//sortByY();	
		return this;
	}
	
	public void sortByY() {
		Collections.sort(circs, new Comparator<Circle>() {
			@Override
			public int compare(Circle c1, Circle c2) {
				return c1.y - c2.y;
			}
		});
	}
	
	public void step(int frameNumber, Graphics2D g2) {
		for (Circle c : circs) {
			c.step(frameNumber, g2);
		}
		Circle curTop = getTop();
		Circle curSec = getSec();
		if (curTop.y > curSec.y) {
			fireEvent(frameNumber, (curTop.y + curTop.y)/2, curSec, curTop);
			sortByY();
		}
		sortByY();
	}
	
	public Circle getTop() {
		return circs.get(0);
	}
	
	public Circle getSec() {
		return circs.get(1);
	}
	
	public void subscribe(SimpleActionListener s) {
		listeners.add(s);
	}
	
	
	/**
	 * Tells all listening that a change in the top has occured
	 * @param frameNum
	 * @param y
	 * @param newTop
	 * @param oldTop
	 */
	public void fireEvent(int frameNum, int y, Circle newTop, Circle oldTop) {
		System.out.println("New Top!");
		for (SimpleActionListener s: listeners) {
			s.act(frameNum, y, newTop, oldTop);
		}
	}

}
