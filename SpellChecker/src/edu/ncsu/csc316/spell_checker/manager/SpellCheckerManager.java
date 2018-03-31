package edu.ncsu.csc316.spell_checker.manager;
import edu.ncsu.csc316.spell_checker.hash_table.HashTable;
import edu.ncsu.csc316.spell_checker.io.FileReader;
import edu.ncsu.csc316.spell_checker.list.ArrayBasedList;
import edu.ncsu.csc316.spell_checker.list.WordList;

public class SpellCheckerManager {
	
	private static HashTable hashTable;
	
	public SpellCheckerManager(WordList dictionary) {
		WordList wL = new ArrayBasedList();
		wL = dictionary;
		wL.sort();
		hashTable = new HashTable();
		for (int i = 0; i < wL.size(); i++) {
			hashTable.insert(wL.get(i));
		}
	}
	
	public SpellCheckerManager(String dictionaryFileName) {
		WordList wL = new ArrayBasedList();
		wL = FileReader.readFile(dictionaryFileName);
		wL.sort();
		hashTable = new HashTable();
		for (int i = 0; i < wL.size(); i++) {
			hashTable.insert(wL.get(i));
		}
	}	
	
	public  WordList spellCheck(WordList inputText) {
		WordList out = new ArrayBasedList();
		for (int i = 0; i < inputText.size(); i++) {
			if(!hashTable.lookUp(inputText.get(i))) {
				out.add(inputText.get(i));
			}
		}
		out.sort();
		return out;
	}
}
