package tictactoe;

import java.util.EventObject;

public class TicTacToeEvent extends EventObject {

	private int x,y;
	private Players turn;
	
	public TicTacToeEvent(GameEngine source, int x, int y, Players turn) {
		super(source);
		this.x = x;
		this.y = y;
		this.turn = turn;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Players getTurn() {
		return this.turn;
	}
	
	

}
