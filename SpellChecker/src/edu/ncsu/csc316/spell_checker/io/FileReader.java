package edu.ncsu.csc316.spell_checker.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.spell_checker.list.WordList;

public class FileReader {

	public static WordList readFile(String fileName) {
		WordList list = new edu.ncsu.csc316.spell_checker.list.ArrayBasedList(25144);
		try {
			Scanner fS = new Scanner(new File(fileName));
			while (fS.hasNextLine()) {
				list.add(fS.nextLine());
			}	
			// need to insert the edge into an adjacency list
			fS.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot Find File! ");
			e.printStackTrace();
		}
		return list;
		
	}
}
