/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: BAANumbers is used to create random numbers for X positions,
random numbers and numbers to randomize bugs
*****************************************************************************/
import java.util.Random;

public class BAANumbers {
	private int width = 804;
	private final int minxpos = 400, maxpos = 726;
	Random rn = new Random();

	public int ranXPOS() { //randome int between 400 to 726
		int ranNumber = (int) (minxpos + ((maxpos - minxpos) * rn.nextDouble()));
		return ranNumber;
	}

	public int ranNum() {  //random int from 0 to 19
		int ranNumber = rn.nextInt(21);
		return ranNumber;
	}

	public int ranBugNum() {  //random into from 0-2
		int ranNumber = rn.nextInt(3);
		return ranNumber;
	}

}
