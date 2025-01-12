/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: BAACharacterPanel uses multithreading, and painting to paint and 
control images.  Provides methods to manipulate images and state of the game.
*****************************************************************************/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.media.AudioClip;

@SuppressWarnings("serial")
public class BAACharacterPanel extends JPanel implements Runnable {
	public static final Color Brown = new Color(153, 102, 0);             //creates class attributes 
	public static final Color LightBlue = new Color(135, 206, 235);
	static Toolkit tk = Toolkit.getDefaultToolkit(); // Declare
	public Image protagonist, antagonist, apple, bushes, tree1, tree2, tree3, sun, cloud1, cloud2, cloud3;
	private int ProtagonistImageXPOS = 10, AntagonistImageYPOS = 10, totalMove = 0, maxMove = 0;
	final int ProtagonistImageYPOS = 505;
	JTextField questionTextField;
	JLabel questionTextLabel;
	BAANumbers rn = new BAANumbers();
	private int randomXPOS = rn.ranXPOS();
	int num1, num2;
	private boolean flag = true, pauseFlag = false, moveFlag = false;
	Thread Antagonist = new Thread(this);
	Thread Apple = new Thread(this);
	Thread Protagonist = new Thread(this);
	public static writeLeaderBoard LeaderBoard;
	private AudioClip audioClip;
	double volume = .8;

	BAACharacterPanel() { // Constructor
		LeaderBoard = new writeLeaderBoard();
		protagonist = tk.getImage("images/coworm.png"); // From the subfolder
		antagonist = tk.getImage(generateAntagonist()); //sets to random bugs
		apple = tk.getImage("images/Apple.png");
		bushes = tk.getImage("images/bushes.png");
		tree1 = tk.getImage("images/tree1.png");
		tree2 = tk.getImage("images/tree2.png");
		tree3 = tk.getImage("images/tree3.png");
		sun = tk.getImage("images/dasun.png");
		cloud1 = tk.getImage("images/cloud1.png");
		cloud2 = tk.getImage("images/cloud2.png");
		cloud3 = tk.getImage("images/cloud3.png");
		BAAPanel.musicHandler = new MusicHandler(); // create object of MusicHandler
	}

	public void begin() { // starts all threads
		Antagonist.start();
		Apple.start();
		Protagonist.start();
		generateQuestion(); //generate different questions
	}

	public void paint(Graphics g) { //paint method to paint
		super.paintComponent(g);
		Graphics2D gg = (Graphics2D) g;
		gg.setBackground(Color.cyan);

		gg.setColor(Color.cyan);
		gg.drawImage(cloud1, 200, 300, this);
		gg.drawImage(cloud2, 100, 150, this);
		gg.drawImage(cloud2, 400, 200, this);
		gg.drawImage(cloud3, 600, 100, this);
		gg.drawImage(cloud1, 300, 50, this);
		gg.drawImage(cloud2, 500, 400, this);
		gg.drawImage(cloud1, -10, 350, this);

		gg.drawImage(tree2, 434, ProtagonistImageYPOS - 260, this);
		gg.drawImage(tree1, 10, ProtagonistImageYPOS - 150, this);
		gg.drawImage(tree3, 210, ProtagonistImageYPOS - 75, this);
		gg.drawImage(tree3, 610, ProtagonistImageYPOS - 100, this);
		gg.drawImage(tree1, 725, ProtagonistImageYPOS - 150, this);

		gg.drawImage(sun, 0, 10, this);
		gg.drawImage(bushes, 0, 495, this);
		gg.drawImage(antagonist, randomXPOS, AntagonistImageYPOS, this);
		gg.drawImage(apple, randomXPOS, ProtagonistImageYPOS, this);
		gg.drawImage(protagonist, ProtagonistImageXPOS, ProtagonistImageYPOS, this);

		AntagonistImageYPOS += 1;

		if (moveFlag) { //if the moveFlag is set worm can move
			ProtagonistImageXPOS += 2;
			totalMove += 2;

			if (totalMove == maxMove) { //if worm has moved alloted move amount
				moveFlag = false;  //not allowed to move
				totalMove = 0; //reset move amount 
				maxMove = 0;  //reset allowed move
			}

		}

	}

	public synchronized void run() {
		while (flag) {

			if (pauseFlag) { //if pause flag is true then threads sleep
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					return;
				}
				continue;
			}

			if ((AntagonistImageYPOS > ProtagonistImageYPOS - 20)) { //if bug gets to apple first
				File file = new File("sounds/chomp.wav"); // sound effect
				BAAPanel.musicHandler.soundPlayer(file, audioClip, false, volume); // call method to play the sound
				file = new File("sounds/awe.wav"); // play the background music...
				BAAPanel.musicHandler.soundPlayer(file, audioClip, false, volume); // call method to play the sound
				pauseFlag = true;        //pause game and reset moves
				totalMove = 0;
				maxMove = 0;
				JOptionPane.showMessageDialog(null, "Game Over!");
				BAAPanel.getRestartButton().setVisible(true);
				leaderboard();

			} else if ((ProtagonistImageXPOS > randomXPOS - 20)) { //if player gets to apple first
				File file = new File("sounds/crunch.wav"); // play the background music...
				BAAPanel.musicHandler.soundPlayer(file, audioClip, false, volume); // call method to play the sound
				totalMove = 0;  //reset allowed move amount
				maxMove = 0;
				moveFlag = false; 
				pauseFlag = true;  //pauses game until JOptionPane message is accepted
				JOptionPane.showMessageDialog(null, "You got the Apple!");
				BAAPanel.score += 10; //increase score by 10 
				BAAPanel.scoreButtonTextField.setText(String.valueOf(BAAPanel.score)); //update score
				ProtagonistImageXPOS = 0;  //reset position of images
				AntagonistImageYPOS = 0;
				randomXPOS = rn.ranXPOS();
				pauseFlag = false;
				generateQuestion(); //generate question
				antagonist = tk.getImage(generateAntagonist());
				BAAPanel.getQuestionTextLabel().setText(this.getQuestion());

			}

			try {
				Thread.sleep(100);  //alters speed of images

			} catch (InterruptedException e) {
			}
			repaint();

		}

	}

	public void stop() { //pauses game
		pauseFlag = true;

	}

	public void restart() { //restarts game with new userName
		BAAPanel.score = 0; //sets all fields to zero
		BAAPanel.scoreButtonTextField.setText(String.valueOf(BAAPanel.score));
		totalMove = 0;
		ProtagonistImageXPOS = 0;
		AntagonistImageYPOS = 0;
		maxMove = 0;

		String userName = JOptionPane.showInputDialog("Please input your username (or cancel if you want to remain anonymous): "); //new username
		if(userName == null || userName.equals("")){ //if null or empty
			userName = "Anonymous"; //anon
		}
		BAAPanel.setUsername(userName);
		
		pauseFlag = false; //starts game
		BAAPanel.getRestartButton().setVisible(false);
		repaint();
	}

	public void resume() { //unpause game
		pauseFlag = false;
	}

	public void move(String input) throws NumberFormatException { //increases allowed move amount for all correct inputs

		if (input.equals("") || input.equals(null)) {  //if input is null or empty
			throw new NullPointerException();  //throw null pointer exception
		}
		int answer = Integer.parseInt(input); //sets an answer in to parsed input

		if (answer == (num1 + num2)) {  //if the answer is equal to num1 + num2
			File file = new File("sounds/correct.wav"); // play the background music...
			BAAPanel.musicHandler.soundPlayer(file, audioClip, false, volume); // call method to play the sound
			maxMove += 100; //increase maxMove by 100
			moveFlag = true; //allow move
			generateQuestion(); //generate new question
			BAAPanel.getQuestionTextLabel().setText(this.getQuestion());

		} else {
			File file = new File("sounds/wrong.wav"); // play the background music...
			BAAPanel.musicHandler.soundPlayer(file, audioClip, false, volume); // call method to play the sound
		}

	}

	public void generateQuestion() {  //generate numbers for question
		num1 = rn.ranNum();
		num2 = rn.ranNum();
	}

	public String generateAntagonist() {  //returns random png of bug
		int antCase = rn.ranBugNum();
		String[] antImages = new String[] { "images/Bee.png", "images/fly.png", "images/Spider.png" };
		return antImages[antCase];
	}

	public String getQuestion() { //gets question string
		return num1 + " + " + num2 + " = ";
	}

	public static void leaderboard(){
		LeaderBoard.collectPrevious();  //write and read leaderboards
		LeaderBoard.writeData(BAAPanel.getUsername(), BAAPanel.score);
		LeaderBoard.readData();
	}

}
