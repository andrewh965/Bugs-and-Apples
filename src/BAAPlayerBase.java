/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: BAAPlayerBase creates an ArrayList to hold Players objects.  Provides
methods to add, delete, sort, and print the arrayList.  
*****************************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class BAAPlayerBase {
	ArrayList<Players> playerBase = new ArrayList<>(); // arrayList for player objects
	String printString; // printString used for printing
	Iterator<Players> itr = playerBase.iterator(); // iterator to traverse arrayList of objects
	String found;

	public void adding(String Name, int scores) { // adding adds a new Player object to ArrayList and sets its
        this.deleting(Name, scores); // if the name and score is already in the list, delete it
		playerBase.add(new Players(Name, scores)); //adds to arrayList
        
	}

	public void deleting(String name, int scores) { // delete traverses the ArrayList and removes the object if name is found
		for (Iterator<Players> iter = playerBase.iterator(); iter.hasNext();) { // iterator traverses list
			Players player = iter.next();
			if ((player.getName().equals(name)) && (player.getScore() == scores)) { // if the next iterator name = parameter name
				iter.remove(); // remove
			}
		}
	}

	public void sorting() { // sorts playerBase based off score
		Collections.sort(playerBase, new Comparator<Players>() { // sort method called with playerBase and Comparator
																	// passed as parameters

			@Override
			public int compare(Players player1, Players player2) { // method returns friend in alphabetical order based
																	// on score
				return Integer.valueOf(player2.getScore()).compareTo(player1.getScore());
			}

		});
	}

	public String printing() { // creates string to display information

		int maxLeader = 0;
		printString = ""; // empties string before updating
		this.sorting();
		for (Players iterator : playerBase) { // enhanced for loop
			if (maxLeader < 5) { //collects top five scores
				printString += iterator.toString(); // print string = all of playerBase player toString

			} else {
				break;
			}

			maxLeader++;
		}

		return printString;

	}

	

}
