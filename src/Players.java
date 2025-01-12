/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: Players object stores username and scores.  Provides methods to
return name and score.  Provides a toString method to print out all information.
*****************************************************************************/
public class Players{
	 private String Name;
	 private int scores;
	    public Players(String Name, int scores){  //constructor to set fields to the parameters
	    	this.Name = Name;
	    	this.scores = scores;
	   
	    }
	    public String getName() { //getter method for name
	    	return this.Name;
	    }
	    public int getScore() { // getter method for number
	    	return this.scores;
	    }
	   
	    public String toString() { //prints out the local attributes
	    	return "<html><br> " + this.Name + " : " +this.scores + " <html>" + "\n"; 
	    }
	
}
