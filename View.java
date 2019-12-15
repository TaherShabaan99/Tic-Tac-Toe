package tictactoe;

	import java.awt.BorderLayout;
    import java.awt.Color;
	import java.awt.Container;
	import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.BorderFactory;
	import javax.swing.JButton;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

	public class View extends JFrame implements TicTacToeListener {

		private JPanel container, gamePanel,startPage;
		JButton board[][];// This will be a board of squares
		private final int size = 3; // The size of the board
		private GameEngine model;
		private JMenuBar menuBar;
		private JMenuItem menuItemQuit, menuItemReset, menuItemHint,menuItemSave;
		private JButton newGameBtn, loadGameBtn;
		//private Stack<JPanel> previousPanels;



		public View(GameEngine gameModel) {
			super();
			this.model = gameModel;
			container = new JPanel();
			//add(container); //add the container to the jframe
			this.setLayout(new BorderLayout());
			setTitle("TicTacToe");
			setSize(700, 700);
			setResizable(false);
			//previousPanels = new Stack<>();
			addMenuItems();
			createBoard();
			this.setVisible(true);
			model.addTicTacToeListerner(this);

			
		}
		
		class SaveListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayMessage("Saved");
				model.saveGame();
			}
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
			//previousPanels.add(gamePanel);
			this.add(gamePanel);
		}
		/**
		 * Create a menu that will allow the user to choose if they want help or quit.
		 */
		public void addMenuItems() {
			// Create menu bar
			menuBar = new JMenuBar();

			/*
			// Add undo button
			menuItemUndo = new JMenuItem("Undo");
			menuItemUndo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemUndo);

			// Add redo button
			menuItemRedo = new JMenuItem("Redo");
			menuItemRedo.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemRedo);
*/
			// Add save button
			menuItemSave = new JMenuItem("Save");
			menuItemSave.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemSave);

			// Add reset button
			menuItemReset = new JMenuItem("Reset");
			menuItemReset.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemReset);

			// Add quit button
			menuItemQuit = new JMenuItem("Quit");
			menuItemQuit.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemQuit);
			menuItemQuit.addActionListener(event -> {
				displayMessage(model.quitMessage());
				dispose();

			});

			menuItemHint = new JMenuItem("Hint", KeyEvent.VK_H);
			menuItemHint.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
			menuBar.add(menuItemHint);

			add(menuBar, BorderLayout.NORTH);
		}
		
		/**
		 * Creating start page
		 
		private void createStartPage() {
			startPage = new JPanel();

			startPage.setLayout(null);
			startPage.setBackground(new Color(0, 204, 0));

			// Adding button to go to levels page
			newGameBtn = new JButton("Start a New Game");
			newGameBtn.setBounds(250, 350, 210, 50);
			newGameBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
			newGameBtn.setBackground(Color.WHITE);
			newGameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			newGameBtn.setForeground(Color.BLUE);

			// Adding button to load previous game
			loadGameBtn = new JButton("Load Previous Game");
			loadGameBtn.setBounds(230, 450, 250, 50);
			loadGameBtn.setFont(new Font("Monospaced", Font.BOLD, 20));
			loadGameBtn.setBackground(Color.WHITE);
			loadGameBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			loadGameBtn.setForeground(Color.BLUE);

			// Adding game title
			JLabel title = new JLabel("JUMP IN");
			title.setFont(new Font("Monospaced", Font.BOLD, 125));
			title.setBounds(70, 100, 900, 100);
			title.setForeground(Color.WHITE);

			// Adding all components to panel
			startPage.add(title);
			startPage.add(newGameBtn);
			startPage.add(loadGameBtn);

			add(startPage);
			//previousPanels.add(startPage);

		}
		 */
		public void displayMessage(String message) {
			JOptionPane.showMessageDialog(this, message);
		}


		@Override
		public void handleTicTacToeEvent(TicTacToeEvent e) {
			// TODO Auto-generated method stub
			this.board[e.getX()][e.getY()].setText(e.getTurn()+"");
			
		}
		
		/**
		 * Listens for when the user wants to save the current game
		 * 
		 * @param a
		 */
		public void addSaveGameListener(ActionListener a) {
			menuItemSave.addActionListener(a);
		}

		/**
		 * Listens for when the user wants to load a previuos game
		 * 
		 * @param a
		 */
		public void addLoadGameListener(ActionListener a) {
			loadGameBtn.addActionListener(a);
		}

		
		public static void main(String[] args) {		
			GameEngine game = new GameEngine();
			View view = new View(game);
		}
		/*
		 * public String printGameInstructions() {
		String title = "JumpIN Instructions: \n\n";
		String obstacles = "\tThe pieces are: Mushroom, Fox, Rabbit, Hole.\n\n"
				+ "\tFoxes take up two spaces, head and tail. " + "All other obstacles occupy one square.\n\n";

		String movements = "MOVEMENT RULES: The following explains how the obstacles move around the board:\n\n"

				+ "\tRabbit:\n"

				+ "\t\tRabbits can only move by jumping over one adjacent obstacle, empty holes are NOT obstacles.\n"
				+ "\t\tOnce a rabbit is in a hole, it can be jumped over by other rabbits.\n"
				+ "\t\tSide note: Rabbits can jump out of their holes to faciliate another rabbit's path.\n\n"
				+ "\t\tRabbits can jump over a fox's waist, or from its head to tail or tail to head.\n\n"

				+ "\tFoxes: \n"

				+ "\t\tFoxes can slide depending on their initial direction, however many spots needed.\n\n"

				+ "\tMushrooms and holes are stationary.\n\n";

		String objective = "GAME OBJECTIVE: The objective of the game is to move the rabbits and foxes, through a series of movements\n"
				+ "around the obstacles untill all the rabbits are safely in their hole.\n";
		String howTo = "\n HOW TO PLAY: First, select the piece you'd like to move. Next, select the square you want to move it to.";

		// System.out.println(title + obstacles + movements + objective + abbreviations
		// + commands);
		return title + obstacles + movements + objective + howTo;
	}
		 */
		
		

		
}
