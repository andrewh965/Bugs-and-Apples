/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description:RegexValidator is used to detect names and scores withing a textfile.
A line is entered into the methods in which the the regular matcher will
find the appropriate expression and return it.    
*****************************************************************************/


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;



public class RegexValidator {
 private final static String regularExpName = "\\s[A-Za-z]{1,20}\\s"; //regular expression for name lab req: must be in Last, First format
 private final static String regularInt = "\\s[0-9]+"; // regular expression for YN lab req: must be able to handle lowercase and uppercase y or n
 
	public static String regCheckName(String checkedString)  {    //checks and returns names
		 Pattern checkRegex = Pattern.compile(regularExpName);       //creates Pattern object to use pattern methods
         Matcher regexMatcher = checkRegex.matcher(checkedString);   //creates Matcher object to use matcher methods
         String results = "";    //creates Pattern object to use pattern methods
   
       

              while (regexMatcher.find()) {//while regex finds
     	         
     	      }
              regexMatcher.find(0);  //find first occurance of regex
              results = regexMatcher.group(0);  //results = first occurance of regex match
      
          return results;
	}
	
	public static String regCheckScore(String checkedString) {      //checks for scores
		 Pattern checkRegex = Pattern.compile(regularInt);       //creates Pattern object to use pattern methods
         Matcher regexMatcher = checkRegex.matcher(checkedString);   //creates Matcher object to use matcher methods
         String results = "";  //creates Pattern object to use pattern methods
              while (regexMatcher.find()) {  //while regex finds 	      
     	      }     
              regexMatcher.find(0); //find first occurance of regex
              results = regexMatcher.group(0);   //results = first occurance of regex match
       
           return results;

	}
	
}