package client;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	Game leftChild;
	Game rightChild; 
	Circle playerLeft;
	Circle playerRight;
	int expire;
	int depth;
	static int indent = 0;
	Circle winner;
	GameViz gv;
	
	static LinkedList<Integer> expires;
	
	static ArrayList<Circle> players;
	
	public Game(int depth, int desiredHeight) {
		this.depth = depth;
		if (depth < desiredHeight) {
			leftChild = new Game(depth+1, desiredHeight);
			gv = new GameViz(depth);
			gv.isLeaf = false;
			rightChild = new Game(depth+1, desiredHeight);
		} else {
			gv = new GameViz(depth);
			gv.isLeaf = true;
			leftChild = null;
			rightChild = null;
		}
		if (expires == null) {
			expires = new LinkedList<Integer>();
		}
	}
	
	public void setPlayers(ArrayList<Circle> circles) {
		players = circles;
	}
	
	
	
	public Circle getWinner() {
		if (leftChild == null && rightChild == null) {
			if (playerRight == null && playerLeft == null) {
				System.out.println("Circles length " + players.size() + " and Depth=" + depth);
				playerLeft = players.remove(0);
				playerRight = players.remove(0);
			}
		} else {
			playerLeft = leftChild.getWinner();
			playerRight = rightChild.getWinner();
		}
		
		expire = 1 + (playerLeft.origY - playerRight.origY)/(playerRight.speed - playerLeft.speed);
		expires.add(expire);
		
		gv.left = playerLeft.color;
		gv.right = playerRight.color;
		
		if (playerLeft.y < playerRight.y) {
			 winner = playerLeft;
		} else {
			winner =  playerRight;
		}
		if(depth == 0) {
			GameViz.winningCircle.color = winner.color;
		}
		gv.draw();
		return winner;
	}
	
	static void resetExpires() {
		expires = new LinkedList<Integer>();
	}



	
	

}
