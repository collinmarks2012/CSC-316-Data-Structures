package edu.ncsu.csc316.spell_checker.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ArrayBasedList implements WordList{
	
	private String listArray[];
	private static final int defaultSize = 25144;
	private int maxSize;
	private int listSize;
	
	public ArrayBasedList(int size) {
		setMaxSize(size);
		listSize = 0;
		listArray = new String[size];
	}
	public ArrayBasedList() {
		this(defaultSize);
	}

	@Override
	public void add(String word) {
		listArray[listSize++] = word;
	}

	@Override
	public void addSorted(String word) {
	   add(word);
	}

	public int size() {
		return listSize;
	}

	@Override
	public String get(int index) {
		return (String) listArray[index];
	}
	
	public String toString() {
		sort();
		String out = "WordList[";
		int dummy = 0;
		for (int i = 0; i <  this.listSize; i++) {
			out = out + get(i) + ", ";
			dummy = 1;
		}
		if (dummy == 1) {
			out = out.substring(0, out.length() - 2);
		}
		out = out + "]";
		return out;
	}
	
	public void sort() {	List<String> lst = new ArrayList<String>();
	for (int i = 0; i < listSize; i++) {
		lst.add((String) listArray[i]);
	}
	Collections.sort(lst, String.CASE_INSENSITIVE_ORDER);
	int k = 0;
	for(String s: lst) {
		listArray[k] = s;
		k++;
	}
	ArrayList<String> list = new ArrayList<String>();
	for (int i = 0; i < listSize; i++) {
		list.add(listArray[i].toString());
	}

// Store unique items in result.
	ArrayList<String> result = new ArrayList<>();

// Record encountered Strings in HashSet.
	HashSet<String> set = new HashSet<>();

// Loop over argument list.
	int numDel = 0;
	for (String item : list) {

    // If String is not in set, add it to the list and the set.
		if (!set.contains(item)) {
			result.add(item);
			set.add(item);
		} else {
			listSize--;
			numDel++;
		}
	}
	int i = 0;
	for (String item: result) {
		listArray[i] = item;
		i++;
	}
	for (int y = result.size(); y < (listSize + numDel); y++) {
		listArray[y] = null;
	}
}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
}