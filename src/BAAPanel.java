/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: BAAPanel creates all buttons, labels, text fields and questions.
It also does all of the event handling when the user interacts with the
window.  
*****************************************************************************/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javafx.scene.media.AudioClip;

@SuppressWarnings("serial")
public class BAAPanel extends JPanel implements ActionListener{
    public static int score = 0;           //create buttons, labels, music handler etc.
    private static String questionString = " ", userName = "";
    private JTextArea letterTextArea;
    Font f;
	public static JTextField scoreButtonTextField, questionTextField;
	private JLabel scoreLabel;
	private static JLabel questionTextLabel;
	private static JButton startButton, stopButton, quitButton,resumeButton, LeaderBoard, instruction;
	private static JButton restartButton;
	BAACharacterPanel gp;
	private AudioClip audioClip;
	public  static MusicHandler musicHandler;
	public static final Color Brown = new Color(153, 102, 0);
	public static final Color LightBlue = new Color(135,206,235);
	writeLeaderBoard lb = new writeLeaderBoard();
	
	public BAAPanel() {  //default constructor
		
		this.setLayout(new BorderLayout());
		
		JPanel controlPanel = new JPanel();      //creates JPanel to hold all buttons for controls
		controlPanel.setBackground(LightBlue);
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		startButton = new JButton("Start", new ImageIcon("images/play.gif"));    //create buttons and add action listeners
		startButton.addActionListener(this);
		controlPanel.add(startButton);
		
		instruction = new JButton("How to play", new ImageIcon("images/question.png")); 
		instruction.addActionListener(this);
		controlPanel.add(instruction);
		
		LeaderBoard = new JButton("Leader Board", new ImageIcon("images/leaderboard.png"));
		LeaderBoard.addActionListener(this);
		LeaderBoard.setVisible(true);
		controlPanel.add(LeaderBoard);
		
		
		resumeButton = new JButton("Resume", new ImageIcon("images/resume.png"));
		resumeButton.addActionListener(this);
		controlPanel.add(resumeButton);
		resumeButton.setVisible(false);
		
		stopButton = new JButton("Stop", new ImageIcon("images/stop.gif"));
		stopButton.addActionListener(this);
		stopButton.setVisible(false);
		controlPanel.add(stopButton);
		
		quitButton = new JButton("Quit", new ImageIcon("images/Shutdown.gif"));
		quitButton.addActionListener(this);
		controlPanel.add(quitButton);
		
		restartButton = new JButton("Restart?", new ImageIcon("images/restart.png"));
		restartButton.addActionListener(this);
		restartButton.setVisible(false);
		controlPanel.add(restartButton);
		
		f = new Font("SansSerif", Font.BOLD, 20);
		JPanel scorePanel = new JPanel();   //score panel to hold score
		scorePanel.setBackground(LightBlue);
		scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		controlPanel.add(scorePanel);
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setFont(f);
		scorePanel.add(scoreLabel);
		
		
		scoreButtonTextField = new JTextField(3);
		scoreButtonTextField.setEditable(false);
		scoreButtonTextField.setFocusable(false);
		scoreButtonTextField.setFont(f);
	
		scorePanel.add(scoreButtonTextField);
	
		this.add(controlPanel, BorderLayout.NORTH);
		
		
		f = new Font("SansSerif", Font.BOLD, 48);
		JPanel questionPanel = new JPanel();    //question panel for questions and to make ground
		questionPanel.setBackground(Brown);
	    questionTextLabel = new JLabel(questionString); 
		questionTextLabel.setVisible(false);
		questionPanel.add(questionTextLabel);
		questionTextLabel.setFont(f);
		questionTextField = new JTextField(5);
		
		questionTextField.setEditable(false);
		questionTextField.addActionListener(this);	
		questionTextField.setFont(f);
		questionPanel.add(questionTextField);
		this.add(questionPanel, BorderLayout.SOUTH);
	
	
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(LightBlue);
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		

		// letter text field
		letterTextArea = new JTextArea(300,40);
		letterTextArea.setBackground(LightBlue);
		letterTextArea.setEditable(false);
		displayPanel.add(letterTextArea);
		this.add(displayPanel, BorderLayout.CENTER);
		
		File file = new File("sounds/titleTheme.wav");  //play the background music...
		musicHandler = new MusicHandler();			//create object of MusicHandler
		musicHandler.soundPlayer(file, audioClip, true, .1);  //call method to play the sound
		
		gp = new BAACharacterPanel();
		displayPanel = gp;
		gp.setBackground(LightBlue);
		displayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(displayPanel, BorderLayout.CENTER);
		
		 f = new Font("SansSerif", Font.BOLD, 48);
		letterTextArea.setFont(f);
	
		
	}
	
	public static void setQuestion(String userIn) {  //setter method for question
		questionString = userIn;
	}
	
	public static String getQuestion() { //getter method for question
		return questionString;
	}
	
	public static void setUsername(String inputUserName) { //setter for user name
		userName = inputUserName;
	}
	public static String getUsername() { //getter for user name
		return userName;
	}
	public static JLabel getQuestionTextLabel() { //getter for question label
		return questionTextLabel;
	}
	public static JButton getRestartButton() { //getter for restart
		return restartButton;
	}
	

	
	public void actionPerformed(ActionEvent e) {  //action handler for all buttons
		Object source = e.getSource();
		if (source == quitButton) {  //exit window when quit
			gp.stop();
			BAACharacterPanel.leaderboard();
			System.exit(0);
		}
		else if (source == stopButton) {  //calls stop method from BAACharacterPanel
			resumeButton.setVisible(true);
			questionTextField.setEditable(false);
			gp.stop();  //pauses images
		}

		
		else if (source == startButton) {   //starts the game 
			userName = JOptionPane.showInputDialog("Please input your username (or cancel if you want to remain anonymous): "); //gets userInput for username
			if(userName == null || userName.equals("")){ //if user does not enter anything 
				userName = "Anonymous"; //user is anon
			}
			questionTextField.setEditable(true);
			startButton.setVisible(false);
			questionTextLabel.setVisible(true);
			stopButton.setVisible(true);
			
		
			gp.begin(); //begins the threads
			gp.resume();
			questionString = gp.getQuestion(); //sets the first question
		    questionTextLabel.setText(questionString);
		    
	
			
			
		
		}else if(source == resumeButton) {  //resume button resumes game using resume method
			resumeButton.setVisible(false);
			questionTextField.setEditable(true);
			gp.resume();
		}else if(source == questionTextField) { //gets user input answers
			try {  //exception handling for answers
			String userInput = questionTextField.getText().replace(" ", "");  //replaces spaces with nothing
			gp.move(userInput);
			
			questionTextField.setText(""); //resets question field
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Letters are not numbers"); //if letters are input
				questionTextField.setText("");
			}catch(NullPointerException ex) {  //if no nunmber is detected
				JOptionPane.showMessageDialog(null, "Please input a number");
				
			} 
		}else if(source == restartButton) { //restart game
			gp.restart();
		}else if(source == LeaderBoard) {// leaderboard
			lb.readData();
		}else if(source == instruction) { //how to play
			JOptionPane.showMessageDialog(null, "Press start to begin the game.  Answer the math question and"
					+ " get to the apple before the other bugs!");
		}
		
		
		
	}

}