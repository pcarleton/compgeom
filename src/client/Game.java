package client;

import java.util.LinkedList;

public class Game {
	Game left;
	Game right; 
	Circle playerLeft;
	Circle playerRight;
	int expire;
	int depth;
	
	static LinkedList<Circle> players;
	
	public Game(int depth, int desiredHeight) {
		this.depth = depth;
		if (depth <= desiredHeight) {
			left = new Game(depth+1, desiredHeight);
			right = new Game(depth+1, desiredHeight);
		} else {
			left = null;
			right = null;
		}
	}
	
	public void setPlayers(LinkedList<Circle> circles) {
		players = circles;
	}
	
	
	
	public Circle getWinner() {
		if (left == null && right == null) {
			if (playerRight == null && playerLeft == null) {
				playerLeft = players.pop();
				playerRight = players.pop();
			}
		} else {
			playerLeft = left.getWinner();
			playerRight = right.getWinner();
		}
		
		expire = (playerLeft.y - playerRight.y)/(playerRight.speed - playerLeft.speed);
		
		if (playerLeft.y < playerRight.y) {
			return playerLeft;
		} else {
			return playerRight;
		}
	}
	

	public Game getLeft() {
		return left;
	}

	public void setLeft(Game left) {
		this.left = left;
	}

	public Game getRight() {
		return right;
	}

	public void setRight(Game right) {
		this.right = right;
	}

	public Circle getPlayerLeft() {
		return playerLeft;
	}

	public void setPlayerLeft(Circle playerLeft) {
		this.playerLeft = playerLeft;
	}

	public Circle getPlayerRight() {
		return playerRight;
	}

	public void setPlayerRight(Circle playerRight) {
		this.playerRight = playerRight;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}


	
	

}
