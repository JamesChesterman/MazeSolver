import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainMenuScreen extends JFrame{
	JButton mazeButton, exitButton;
	JTextField rowTextField, colTextField;
	
	public MainMenuScreen() {
		// TODO Auto-generated constructor stub
		setTitle("Maze-Solver: Main Menu Screen");
		
		JLabel titleLabel = new JLabel("Welcome to Maze-Solver");
		titleLabel.setBounds(10, 10, 150, 30);
		
		JTextArea instructionArea = new JTextArea("Click squares to change them from walls to empty squares and vice versa. Click on the top row where you want the blue square (the start) to be and on the bottom row click where you want the red square (the end) to be.");
		instructionArea.setLineWrap(true);
		instructionArea.setWrapStyleWord(true);
		instructionArea.setBounds(170, 10, 200, 120);
		
		JLabel rowLabel = new JLabel("Rows:");
		rowLabel.setBounds(10, 50, 80, 30);
		
		rowTextField = new JTextField();
		rowTextField.setBounds(100, 50, 50, 30);
		
		JLabel colLabel = new JLabel("Columns:");
		colLabel.setBounds(10, 90, 80, 30);
		
		colTextField = new JTextField();
		colTextField.setBounds(100, 90, 50, 30);
		
		mazeButton = new JButton("Make Maze!");
		mazeButton.setBounds(10, 150, 120, 30);
		mazeButton.addActionListener(actions);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(140, 150, 120, 30);
		exitButton.addActionListener(actions);
		
		add(instructionArea);
		add(titleLabel);
		add(rowLabel);
		add(rowTextField);
		add(colLabel);
		add(colTextField);
		add(mazeButton);
		add(exitButton);
		setSize(400, 220);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuScreen.class.getResource("images/MazeIcon2.JPG")));
	}
	private ActionListener actions = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == mazeButton){
				try{
					GUIWindow guiWindow = new GUIWindow(Integer.valueOf(rowTextField.getText()), Integer.valueOf(colTextField.getText()));
					setVisible(false);
				}catch(Exception ex){
					System.out.println("Ensure that row and column values are integers");
				}
				
			}
			if(e.getSource() == exitButton){
				System.exit(0);
			}
		}
	};

}
