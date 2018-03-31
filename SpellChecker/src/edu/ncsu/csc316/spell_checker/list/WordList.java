package edu.ncsu.csc316.spell_checker.list;

public interface WordList {
	
	public void add(String word);
	
	public void addSorted(String word);
	
	public int size();
	
	public String get(int index);

	public void sort();

}
