package autocomplete;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Autocomplete { // should implement the AutocompleteInterface 
	List<Term> toSort;
	List<Term> prefixList;
	List<Term> subPrefixList;
	
	//constructor
	public Autocomplete(List<Term> termList) {
		this.toSort = termList;
		//sorting the list
		//Collections.sort(toSort, Term.byReverseWeightOrder());
	}
	
	/**
	* @param prefix
	* string to be matched
	* @return List of all matching terms in descending order by weight
	*/
	public List<Term> allMatches(String prefix) {
		// create a new comparator by prefix order
		Comparator<Term> preComp = Term.byPrefixOrder(prefix.length());
		
		//sort the list of Terms by prefix order
		Collections.sort(toSort, preComp);
		
		//make prefix into a term 
		Term prefixTerm = new Term(prefix, 0); 
		
		//variable for first index where the prefix occurs in the sorted list
		int firstIndex = BinarySearchForAll.firstIndexOf(toSort, prefixTerm, preComp);
		
		//variable for last index where the prefix occurs in the sorted list
		int lastIndex = BinarySearchForAll.lastIndexOf(toSort, prefixTerm, preComp);
		
		//if the prefix doesn't exist in the list of terms, don't make a new list
		if (firstIndex == -1 || lastIndex == -1) {
			prefixList = new ArrayList<Term>(); 
			return prefixList;
		}
		else {
			//adding terms with prefix to the list of terms
			//assign the sublist to be between the indexes in toSort
			prefixList = toSort.subList(firstIndex, lastIndex+1);
			}
			return prefixList;
		}
		
	public static void main(String[] args) {
       	ArrayList<Term> listTerms = new ArrayList<Term>();
       	listTerms.add(new Term("help", 7));
       	listTerms.add(new Term("happy", 3));
       	listTerms.add(new Term("cake", 10));
       	
       	System.out.println(listTerms);
       	

       	ArrayList<Term> listTerm = new ArrayList<Term>();
       	listTerm.add(new Term("Gardone Val Trompia, Italy", 10952));
       	listTerm.add(new Term("Enghien-les-Bains, France", 10951));
       	listTerm.add(new Term("San Antonio Suchitepéquez", 10951));
       	
       	System.out.println(listTerm);

       	
       	Autocomplete autofile = new Autocomplete(listTerms);
       	List<Term> matchPrefix = autofile.allMatches("hel");
       	System.out.println(matchPrefix);
       	
       	Autocomplete autofiles = new Autocomplete(listTerm);
       	List<Term> matchPrefixes = autofiles.allMatches("Gar");
       	System.out.println(matchPrefixes);
       	
	}
}

