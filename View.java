package tictactoe;

	import java.awt.BorderLayout;
    import java.awt.Color;
	import java.awt.Container;
	import java.awt.Dimension;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
	import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

	public class View extends JFrame implements TicTacToeListener {

		private JPanel container, gamePanel;
		JButton board[][];// This will be a board of squares
		private final int size = 3; // The size of the board
		private GameEngine model;
		private JMenuBar menuBar;
		private JMenuItem menuItemHelp, menuItemQuit, menuItemReset, menuItemUndo, menuItemRedo, menuItemHint;


		public View(GameEngine gameModel) {
			super();
			this.model = gameModel;
			container = new JPanel();
			//add(container); //add the container to the jframe
			this.setLayout(new BorderLayout());
			setTitle("TicTacToe");
			setSize(700, 700);
			setResizable(false);
			addMenuItems();
			createBoard();
			this.setVisible(true);
			model.addTicTacToeListerner(this);
			
		}
		
		public int getBoardSize() {
			return this.size;
		}
		/*
		 * The board will be created then we are adding the buttons in side it and also
		 * adding background color
		 */
		public void createBoard() {
			container.setLayout(new GridLayout(size, size));
			gamePanel = new JPanel(new GridLayout());
			gamePanel.add(container);
			board = new JButton[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					JButton button = new JButton();
					board[i][j] = button;
					button.setPreferredSize(new Dimension(200, 200));
					button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
					button.setBackground(new Color(0, 204, 0));// the backGround of the buttons
					button.setOpaque(true);
					button.addActionListener(new TicTacToeButtonListener(this.model,i,j));
					container.add(button);
				}
			}
			this.add(gamePanel);
		}
		/**
		 * Create a menu that will allow the user to choose if they want help or quit.
		 */
		public void addMenuItems() {
			// Create menu bar
			menuBar = new JMenuBar();

			// Add undo button
			menuItemUndo = new JMenuItem("Undo");
			menuItemUndo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemUndo);

			// Add redo button
			menuItemRedo = new JMenuItem("Redo");
			menuItemRedo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemRedo);

			// Add help button
			menuItemHelp = new JMenuItem("Help");
			menuItemHelp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemHelp);

			// Add reset button
			menuItemReset = new JMenuItem("Reset");
			menuItemReset.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemReset);

			// Add quit button
			menuItemQuit = new JMenuItem("Quit");
			menuItemQuit.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemQuit);

			menuItemHint = new JMenuItem("Hint", KeyEvent.VK_H);
			menuItemHint.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemHint);

			add(menuBar, BorderLayout.NORTH);
		}


		@Override
		public void handleTicTacToeEvent(TicTacToeEvent e) {
			// TODO Auto-generated method stub
			this.board[e.getX()][e.getY()].setText(e.getTurn()+"");
			
		}
		
		public static void main(String[] args) {		
			GameEngine game = new GameEngine();
			View view = new View(game);
		}
		
		

		
}
