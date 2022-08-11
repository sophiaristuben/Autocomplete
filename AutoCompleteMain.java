package autocomplete;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AutoCompleteMain {

	public static void main(String[] args) {
		int itemPrint = Integer.parseInt(args[0]);
		String fileName = args[1];
		    	
        try {
        	// open the file from the run configurations
           	BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
           	int numLines = Integer.parseInt(fileReader.readLine());
           	
           	// make an array list of terms to store the file data in
           	ArrayList<Term> listTerms = new ArrayList<Term>();
           	
           	// loop through the file 
           	for (int i = 1; i < numLines; i++) {
           		String fileLine = fileReader.readLine(); 
           		fileLine = fileLine.trim(); // get rid of extra space
           		String[] fileArray = fileLine.split("\t", 3); // put into array list with the query and long
           		Term fileTerm = new Term(fileArray[1], Long.valueOf(fileArray[0])); // add to array list of terms
           		listTerms.add(fileTerm);
           	}
           	
           	// prompt user to type in a prefix
           	Scanner input = new Scanner(System.in);
           	System.out.println("Enter a new prefix: ");
           	String prefix = input.nextLine();  
           	
           	// get all the terms that match the prefix into new list of terms
           	Autocomplete autofile = new Autocomplete(listTerms);
           	List<Term> matchPrefix = autofile.allMatches(prefix);

           	System.out.println("There are " + matchPrefix.size() + " matches.");
           	
           	// sort the weights
           	Comparator<Term> weightComp = Term.byReverseWeightOrder();
           	Collections.sort(matchPrefix, weightComp);
           	
           	// find the amount of matches to print
           	int minEnd;
           	if (itemPrint < matchPrefix.size()){
           		minEnd = itemPrint;
           	}
           	else {minEnd = matchPrefix.size();}
           	
           	System.out.println("The " + minEnd + " largest matches are:");

           // print the matches
           	for(int i = 0; i < minEnd; i++) {
           		System.out.println(matchPrefix.get(i));
           	}
           	
           	input.close();
           	fileReader.close();
        }
       	catch (IOException e){
       		System.out.println("FileNotFoundException");
       	}
       	
	}
}
