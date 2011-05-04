package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CircleList {
	
	ArrayList<Circle> circs;
	LinkedList<SimplerActionListener> listeners;
	
	public CircleList() {
		circs = new ArrayList<Circle>();
	}
	
	public void addCircle(Circle c) {
		circs.add(c);
		sortByY();		
	}
	
	public void sortByY() {
		Collections.sort(circs, new Comparator<Circle>() {
			@Override
			public int compare(Circle c1, Circle c2) {
				return c1.y - c2.y;
			}
		});
	}
	
	public void step() {
		if (circs.get(0).y < circs.get(1).y) {
			//fire change
			sortByY();
		}
	}
	
	public Circle getTop() {
		return circs.get(0);
	}
	
	public void subsribe(SimpleActionListener s) {
		
	}

}
