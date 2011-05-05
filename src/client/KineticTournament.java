package client;

import java.util.ArrayList;
import java.util.LinkedList;

public class KineticTournament {
	Game head;
	Circle winner;
	ArrayList<Circle> circles;
	
	public KineticTournament(ArrayList<Circle> circles) {
		this.circles = circles;
		int desiredHeight = (int)Math.floor(Math.log(circles.size())/Math.log(2)) -1;
		
		head = new Game(0, desiredHeight);
		initiate();
	}
	
	public void initiate() {
		head.setPlayers(circles);
		winner = head.getWinner();
	}
	
	public Circle updateWinner() {
		Game.resetExpires();
		winner = head.getWinner();
		System.out.println(Game.expires);
		return winner;
	}
	

}
