/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: writeLeaderBoard handles all I/O methods.  It provides methods to 
write data into a textfile, methods to read data within the file, and a method
to read a file and return data within that file.   
*****************************************************************************/
import java.io.*;
import javax.swing.JOptionPane;

public class writeLeaderBoard {
	File file = new File("LeaderBoard.txt"); //writes to LeaderBoard.txt
	String allData = ""; //all data collected
	BAAPlayerBase players = new BAAPlayerBase(); //creates BAAPlayerBase object for ArrayList

	public void writeData(String name, int score) {  //writes data into LeaderBoard.txt

		try {
			PrintWriter output = new PrintWriter(file);  //creates new print writer object
			players.adding(name, score);  //adds parameter name and score
			
			output.append(players.printing());  //appends all toString of players to file
			output.close();
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "File not found");
		}

	}

	public void readData() {  //reads text file and return JOptionPane of all data
		try {
			BufferedReader in = new BufferedReader(new FileReader(file)); //bufferedreader for file
			String line = in.readLine(); //reads each line
			while (line != null) { //if line isnt null
				allData += line; //adds line to allData
				line = in.readLine(); //reads next line
			}
			JOptionPane.showMessageDialog(null, allData);  //show message for allData
			allData = ""; 
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File not found");
		}
	}
	
	 public void collectPrevious() {  //collects previous top scores to append to current top scores
	 	
		try {
		BufferedReader in = new BufferedReader(new FileReader(file)); //buffered reader for file
		String line = in.readLine();	//reads line
		while (line != null ) {		//while line not null
		      players.adding(RegexValidator.regCheckName(line).replace(" ", ""),           //add username without spaces
		    		  Integer.valueOf(RegexValidator.regCheckScore(line).replace(" ", "")));  //add scores without spaces
			  line = in.readLine(); //read next line
				if(line == null) {
					break;
				}	
			}
		in.close();
		}catch(IOException e) {
			
		}
		
	}

}
