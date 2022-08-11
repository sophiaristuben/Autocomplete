package autocomplete;


import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Term implements Comparable<Term> {
	public long weight;
	public String query;

	/**
	 * Initializes a term with the given query string and weight.
	 * 
	 * @param query
	 *            word to be stored
	 * @param weight
	 *            associated frequency
	 */
	public Term(String query, long weight) {
		this.weight = weight;
		this.query = query;
	}

	/**
	 * @return comparator ordering elts by descending weight
	 */
	public static Comparator<Term> byReverseWeightOrder() {
		return (Term a, Term b) -> {
			// if a has a greater weight, return -1 because reverse order
			if (a.weight > b.weight) {return -1;}
			
			// if a is smaller, return 1
			if (a.weight < b.weight) {return 1;}
			
			// if they are equal return 0
			else {return 0;}
		};  
	}

	/**
	 * @param r
	 *            Number of initial characters to use in comparing words
	 * @return comparator using lexicographic order, but using only the first r
	 *         letters of each word
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		return (Term a, Term b) -> {
			// create int variable for the shortest length between r and a
			int minA = Integer.min(r, a.query.length());
			int minB = Integer.min(r, b.query.length());
			
			// compare the substrings from 0 to the shortest length
			return a.query.substring(0, minA).compareTo(b.query.substring(0, minB));	
		};
	}

	/**
	 * @param that
	 *            Term to be compared
	 * @return -1, 0, or 1 depending on whether the word for THIS is
	 *		   lexicographically smaller, same or larger than THAT
	 */
	public int compareTo(Term that) {
		// if this query is lexicographically smaller then return -1
		if (this.query.compareTo(that.query) < 0) {return -1;}
		
		// if this query is lexicographically larger return 1
		if (this.query.compareTo(that.query) > 0) {return 1;}
		
		// if they equal return 0
		else {return 0;}
	}

	/**
	 * @return a string representation of this term in the following format: the
	 *         weight, followed by 2 tabs, followed by the word.
	 **/
	public String toString() {
		return "Term: " + weight + "\t\t" + query;
	}
	
	
	public static void main(String[] args) {
        ArrayList<Term> tester = new ArrayList<Term>();
        Term help = new Term("help", 3);
        tester.add(help);
        Term hello = new Term ("hello", 4);
        tester.add(hello);
        Term hopper = new Term ("hopper", 5);
        tester.add(hopper);
        
        Collections.sort(tester, byReverseWeightOrder());
        System.out.println(tester);
        
        Collections.sort(tester, byPrefixOrder(3));
        System.out.println(tester);
	}

}
