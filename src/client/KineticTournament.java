package client;

import java.util.LinkedList;

public class KineticTournament {
	Game head;
	Circle winner;
	LinkedList<Circle> circles;
	
	public KineticTournament(LinkedList<Circle> circles) {
		this.circles = circles;
		head = new Game(0, 2);
		initiate();
	}
	
	public void initiate() {
		head.setPlayers(circles);
		winner = head.getWinner();
	}
	

}
