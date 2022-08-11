package autocomplete;

import java.util.Comparator;
import java.util.List;



public class BinarySearchForAll {
	// flag indicating whether a key occurs at all in the list
	public static final int NOT_FOUND = -1;

	/**
	 * Returns the index of the first element in aList that equals key
	 *
	 * @param aList
	 *            Ordered (via comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Index of first item in aList matching key or -1 if not in aList
	 **/
	public static <Key> int firstIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		return locateFirst(aList, key, 0, aList.size()-1, comparator);
		
	}
	
	private static <Key> int locateFirst(List<Key> x, Key key, int start, int end, Comparator<Key> comparator) {
		int mid = (start + end) / 2;
		Key keyMid = x.get(mid);
		
		// list is empty
		if (start>end) {
			return -1;
		}
		// if they are equal, return that key
		else if (comparator.compare(keyMid, key) == 0) {
			// if mid is zero (list is size 1) or mid to the right is the same as the key
			if (mid == 0 || comparator.compare(key, x.get(mid-1)) != 0) {
				return mid;
			} 
			return locateFirst(x, key, start, mid-1, comparator);
		} 
		// if it is smaller, go left
		else if (comparator.compare(key, keyMid) < 0) {
			return locateFirst(x, key, start, mid-1, comparator);
		} 
		// if it is larger, go right
		else {
			return locateFirst(x, key, mid+1, end, comparator);
		}
	}



	/**
	 * Returns the index of the last element in aList that equals key
	 * 
	 * @param aList
	 *            Ordered (via comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Location of last item of aList matching key or -1 if no such key.
	 **/
	public static <Key> int lastIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		return locateLast(aList, key, 0, aList.size()-1, comparator);
	}
	
	private static <Key> int locateLast(List<Key> x, Key key, int start, int end, Comparator<Key> comparator) {
		int mid = (start + end)/ 2;
		Key keyMid = x.get(mid);
		
		// list is empty
		if (start>end) {
			return -1;
		}
		// if they are equal, return that key
		else if (comparator.compare(keyMid, key) == 0) {
			// if the mid is the end or the adjacent to the left is the same as the key IS THIS != or ==
			if (mid == x.size()-1 || comparator.compare(key, x.get(mid+1)) != 0)  {
				return mid;
			} 
			return locateLast(x, key, mid+1, end, comparator);
		} 
		// if it is smaller, go left
		else if (comparator.compare(key, keyMid) < 0) {
			return locateLast(x, key, start, mid-1, comparator);
		} 
		// if it is larger, go right
		else {
			return locateLast(x, key, mid+1, end, comparator);
		}
	}

	
//	public static <E> void main(String args[]) {
//		ArrayList<E> aList = new ArrayList<E>();
//		aList.add(("a", 1));
//		
//	}
}
