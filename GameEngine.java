package tictactoe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameEngine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TicTacToeBoard board;
	private GameState state;
	private Players player;
	private List <TicTacToeListener> tictactoeListener;
	private String playerName;
    private int nXO;          // Number of X,O played.  Used to detect full grid (i.e. a draw)
    private final int SIZE=3;
	String filename = "file.ser"; 


	
	public GameEngine() {
		board = new TicTacToeBoard();
		state = GameState.InProgress;
		player = Players.EMPTY;
		tictactoeListener = new ArrayList<>();
		nXO = 0;
		
	}
	
	public GameState getState() {
		return this.state;
	}
	
	public String quitMessage() {
		return "Thanks for playing. Goodbye";
	}
	
	public void addTicTacToeListerner(TicTacToeListener listener) {
		this.tictactoeListener.add(listener);
	}
	
	public Players getTurn() {
        return this.player;
    }
	public boolean isGridFull() {
		this.nXO++;
		if(nXO == 9) return true; return false;
	}
	
	public void takeTurn(int x, int y) {
		
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
				isGridFull();

		    }else {
			System.out.print(" The square is already occupied Choose another square ");
		    }
	     } else {
	    	 System.out.print("The game is over");
       }
	}
	/** An internal (private) method that looks for the winner, with 
     * the assumption that the search begins from the latest checker
     * Hence the method being made private. The method is used for readability.
     * @param    row      Where last checker WAS added
     * @param    column   Where last checker WAS added
     * @return   IN_PROGRESS, X, O, DRAW
     */
	public Players hasWon(int row, int column) {
		int numToWin = SIZE;
		Players player = this.board.getSquare(row, column).getPlayer();
		// Look horizontally - left than right    
        int count;
        
        count = 1;
        for (int c = column-1; c > 0; c--) {
            if (this.board.getSquare(row, column).getPlayer() == this.board.getSquare(row, c).getPlayer()) {
                count++;
                if (count == numToWin) {
                    return player;
                }
            } // else, look in another direction
        }
        
        count = 1;
        for (int c = column+1; c < SIZE; c++) {
            if (this.board.getSquare(row, column).getPlayer() == this.board.getSquare(row, c).getPlayer()) {
                count++;
                if (count == numToWin) {
                    return  player;
                }
            } // else, look in another direction
        }
    
        // Look vertically
        count = 1;
        for (int r = row-1; r > 0; r--) {
            if (this.board.getSquare(r, column).getPlayer() == player) {
                count++;
                if (count == numToWin) {
                     return player;
                }
            } // else, look in another direction
        }
        
        count = 1;
        for (int r = row+1; r < SIZE; r++) {
            if (player == this.board.getSquare(r, column).getPlayer()) {
                count++;
                if (count == numToWin) {
                    return player;
                }
            } // else, look in another direction
        } 
        
        // Look diagonally - Left-down - TBD
        
        // Look diagonally - Right-down - TBD

        if (nXO == SIZE * SIZE) {
            return player.DRAW;
        }        
        return player.StillPlaying;
	}
        
		
        
        public void saveGame() {
		try {
			// Saving of object in a file
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(this);

			out.close();
			file.close();

		}

		catch (IOException ex) {
			System.out.println("IOException is caught. Couldn't save.\n");
			ex.printStackTrace();
		}
	}

	public GameEngine loadGame() {

		GameEngine object1 = null;

		try {
			// Reading the object from a file
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			object1 = (GameEngine) in.readObject();

			in.close();
			file.close();

			return object1;
		}

		catch (IOException ex) {
			System.out.println("IOException is caught. Couldn't load.");
			return object1;
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
			return object1;
		}
	}
		
	
}
