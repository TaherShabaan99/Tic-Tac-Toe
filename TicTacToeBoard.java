package tictactoe;

public class TicTacToeBoard {
	
	private Square[][] grid;
	private final int SIZE = 3;
	
	public TicTacToeBoard() {
		createBoard();
	}

	public void createBoard() {
		grid = new Square [SIZE][SIZE];
		for(int i=0;i < SIZE; i++) {
			for(int j=0;j<SIZE;j++) {
				grid[i][j] = new Square(i,j,Players.EMPTY);
			}
		}
	}
	
	public void printBoard(){
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print("" + "    " +"  |  ");
			}
			System.out.println();

		}
	}
	public boolean isEmpty() {
		int EmptySqaures = 0;
		for(int i=0;i < SIZE; i++) {
			for(int j=0;j<SIZE;j++) {
				if(grid[i][j].getPlayer().equals(Players.EMPTY)) {
					EmptySqaures++;
				}
			}
		}
		if(EmptySqaures == 9) return true; return false;// Checking if all the squares are empty or not
	}
	
	public Square getSquare(int x, int y) {
		return this.grid[x][y];
	}
}
