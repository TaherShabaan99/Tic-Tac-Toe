package tictactoe;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameEngine {
	
	private TicTacToeBoard board;
	private GameState state;
	private Players player;
	private List <TicTacToeListener> tictactoeListener;
	private String playerName;
	
	public GameEngine() {
		board = new TicTacToeBoard();
		state = GameState.InProgress;
		player = Players.EMPTY;
		tictactoeListener = new ArrayList<>();
		
	}
	
	public GameState getState() {
		return this.state;
	}
	
	public void addTicTacToeListerner(TicTacToeListener listener) {
		this.tictactoeListener.add(listener);
	}
	
	public Players getTurn() {
        return this.player;
    }
	
	public void takeTurn(int x, int y) {
		//Incase no one played before
		
		// if the game is already in progress
		if(this.state.equals(GameState.InProgress)) {
			if(!board.getSquare(x, y).isOccupied()) {
				if(this.player.equals(Players.X)) {
				board.getSquare(x, y).addPlayer(Players.X);
				this.player = Players.O;
			    }else {
				board.getSquare(x, y).addPlayer(Players.O);
				this.player = Players.X;
			    }
				this.tictactoeListener.get(0).handleTicTacToeEvent(new TicTacToeEvent(this,x,y,this.player));

		    }else {
			System.out.print(" The square is already occupied Choose another square ");
		    }
	     } else {
	    	 System.out.print("The game is over");
       }
	}
	
	public boolean hasWon(int row, int col) {
		boolean equals1 = this.board.getSquare(row,1).getPlayer().
				equals(this.board.getSquare(row,2).getPlayer());
		//if this is true then X or Y or won
		if(this.board.getSquare(row,0).getPlayer().equals(equals1)) {
			return true;
		}
		
		boolean equals2 = this.board.getSquare(0,col).getPlayer().
				equals(this.board.getSquare(1,col).getPlayer());
		//if this is true then X or Y or won
		if(this.board.getSquare(2,col).getPlayer().equals(equals2)) {
			return true;
		}
		
		boolean equals3 = this.board.getSquare(0,0).getPlayer().
				equals(this.board.getSquare(1,1).getPlayer());
		//if this is true then X or Y or won
		if(this.board.getSquare(2,2).getPlayer().equals(equals3)) {
			return true;
		}
		
		boolean equals4 = this.board.getSquare(0,2).getPlayer().
				equals(this.board.getSquare(1,1).getPlayer());
		//if this is true then X or Y or won
		if(this.board.getSquare(2,0).getPlayer().equals(equals4)) {
			return true;
		}
		return false;
	}
	
}
