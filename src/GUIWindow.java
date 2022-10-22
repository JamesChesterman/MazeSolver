import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GUIWindow extends JFrame{
	private JButton[][] squares;
	private JButton solveButton, resetButton, backButton;
	private static String[][] maze;
	Color defaultColour = new Color(255, 255, 102);
	private int startColumn;
	private int endColumn;
	String[][] solvedMaze;
	private int row;
	private int column;
	
	public static String[][] getMaze(){
		return maze;
	}
	
	public GUIWindow(int rows, int columns) {
		// TODO Auto-generated constructor stub
		setTitle("Maze-Solver");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuScreen.class.getResource("images/MazeIcon2.JPG")));
		row = rows;
		column = columns;
		
		squares = new JButton[rows][columns];
		
		startColumn = 1;
		endColumn = 1;
		
		JSplitPane splitPane = new JSplitPane();
		
		JPanel gridPanel = new JPanel();     //top panel
		JPanel controlPanel = new JPanel();  //bottom panel
		
		setPreferredSize(new Dimension(700, 700));
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(splitPane);
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(600);
		splitPane.setTopComponent(gridPanel);
		splitPane.setBottomComponent(controlPanel);
		
		gridPanel.setLayout(new GridLayout(rows, columns));
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				squares[i][j] = new JButton();
				gridPanel.add(squares[i][j]);
				squares[i][j].addActionListener(actions);
				if((i == 0 || i == rows-1) || (j==0 || j==columns-1)){
					squares[i][j].setBackground(Color.BLACK);
				}else{
					squares[i][j].setBackground(defaultColour);
				}
				if(i==0 && j==startColumn){
					squares[i][j].setBackground(Color.BLUE);
				}
				if(i==rows-1 && j==endColumn){
					squares[i][j].setBackground(Color.RED);
				}
			}
		}
		
		solveButton = new JButton("Solve!");
		solveButton.setBounds(10, 10, 70, 30);
		solveButton.addActionListener(actions);
		controlPanel.add(solveButton);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(10, 50, 70, 30);
		resetButton.addActionListener(actions);
		controlPanel.add(resetButton);
		
		backButton = new JButton("Back");
		backButton.setBounds(10, 50, 70, 30);
		backButton.addActionListener(actions);
		controlPanel.add(backButton);
		
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		pack();
	}
	private ActionListener actions = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == solveButton){
				maze = new String[row][column];
				for(int i=0;i<row;i++){
					for(int j=0;j<column;j++){
						Color backgroundColour = squares[i][j].getBackground();
					    if(backgroundColour == Color.BLACK){
					    	maze[i][j] = "w";
					    }else if(backgroundColour == Color.BLUE){
					    	maze[i][j] = "s";
					    }else if(backgroundColour == Color.RED){
					    	maze[i][j] = "e";
					    }else{
					    	maze[i][j] = " ";
					    }
					    
					}
				}
				Main main = new Main();
				solvedMaze = main.getSolvedMaze();
				System.out.println(Arrays.deepToString(maze));
				for(int i=0;i<row;i++){
					for(int j=0;j<column;j++){
						if(solvedMaze[i][j] == "p"){
							squares[i][j].setBackground(Color.GREEN);
						}
					}
				}
			}
			if(e.getSource() == resetButton){
				GUIWindow guiWindow = new GUIWindow(row, column);
				//guiWindow.setVisible(true);
				setVisible(false);
			}
			if(e.getSource() == backButton){
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				setVisible(false);
			}
			
			Object source = e.getSource();
			for(int i=0;i<row;i++){
				for(int j=0;j<column;j++){
					if(source == squares[i][j]){
						if(i==0 && j!=0 && j!= column-1){
							squares[0][startColumn].setBackground(Color.BLACK);
							squares[i][j].setBackground(Color.BLUE);
							startColumn = j;
						}else if(i==row-1 && j!=0 && j!= column-1){
							squares[row-1][endColumn].setBackground(Color.BLACK);
							squares[i][j].setBackground(Color.RED);
							endColumn = j;
						}else if(squares[i][j].getBackground() == Color.BLACK && i != 0 && i!=row-1 && j!=0 && j!=column-1){
							squares[i][j].setBackground(defaultColour);
						}else{
							squares[i][j].setBackground(Color.BLACK);
						}
					}
				}
			}
		}
	};

}
