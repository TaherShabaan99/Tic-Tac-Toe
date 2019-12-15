package tictactoe;

public class Square {
	
	private boolean isOcuppied;
	private int x,y;
	private Players player;
	private boolean isEmpty;
	private int countPlayer;

	
	public Square(int x,int y, Players player) {
		this.isOcuppied = false;
		this.x = x;
		this.y = y;
		this.isEmpty = true;
		countPlayer = 0;
		this.player = player;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void SetX(int x) {
		 this.x = x;
	}
	
	public void SetY(int y) {
		 this.y = y;
	}
	
	public boolean isOccupied() {
		return this.isOcuppied;
	}
	
	public Players getPlayer() {
		return this.player;
	}
	
	public void addPlayer(Players p) {
			this.player = p;
	}
	
	public boolean isEmpty() {
		return this.isEmpty;
	}
	
	public String toString() {
		return this.player + "";
	}

}
