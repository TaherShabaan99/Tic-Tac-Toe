package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeButtonListener implements ActionListener {

	private int x,y;
	private GameEngine model;
	
	public TicTacToeButtonListener(GameEngine game,int x, int y) {
		model = game;
		this.x = x;
		this.y = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		model.takeTurn(x, y);
		
	}
}
