package edu.ncsu.csc316.file_compressor.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.ncsu.csc316.file_compressor.list.WordBankList;

public class FileCompressorManager {
	
	
	public static void main(String[] args) throws IOException {
		
		new FileCompressorManager();
		System.out.println("Please Input File Name to be Decompressed or Compressed:  "); // Display the string.
        Scanner scanner = new Scanner(System.in);
        String fileName;
        fileName = scanner.nextLine();
        scanner.close();
        System.out.println(processFile(fileName));
    }
   
	/**
	the file was compressed
	* The processFile method processes the file with the given name to
	* determine whether to compress or decompress the file.
	* If the file type is invalid or the file cannot be found, an exception
	* is thrown.
	* @param fileName the name of the file to process
	* @return "DECOMPRESS" if the file was decompressed, or "COMPRESS" if
	 * @throws IOException 
	* @throws InvalidInputFileTypeException if the file is not a valid .txt file
	*/
	public static String processFile(String fileName) throws IOException {
		if (!fileName.contains(".txt")) {
			throw new IOException("Must Be A .txt File!");
		} else{
			// FileReader reads text files in the default encoding.
			if (fileName.contains("-compressed")) { //case where it is compressed
				return decompress(fileName);
            } else {
            	return compress(fileName);
            }         
        }
	}

	private static String compress(String fileName) throws IOException {
			Scanner fS = new Scanner(new File(fileName), "utf-8");
			String[] parts = fileName.split("/");
			String parsedFileName = parts[parts.length - 1];
			String newFileName = parsedFileName.substring(0,  parsedFileName.length() - 4) + "-compressed.txt";
			FileWriter fw = new FileWriter("output/compressed/"  + newFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			WordBankList wordBank = new WordBankList(); 
			StringBuilder wordle = new StringBuilder();
			StringBuilder lineOutput = new StringBuilder();
			lineOutput.append("0 ");
			StringBuilder output = new StringBuilder();
			int preBytes = 0;
			int postBytes = 0;
			while (fS.hasNextLine()) {
				String line = fS.nextLine();
				char[] charsM = line.toCharArray();
				for(char c: charsM) {
					preBytes++;
					if (Character.isAlphabetic(c)) {
						wordle.append(c);
					} else { //character is either special or a space
						if (wordle.length() > 0) {
							int inIt = wordBank.remove(wordle.toString());
							if (inIt > 0) { // Case where wordBank contains the word
								lineOutput.append(inIt);
								wordle = new StringBuilder();
							}
							if (wordle.length() > 0) { //case where the word wasnt in wordbank
								wordBank.addToFront(wordle.toString());
								lineOutput.append(wordle.toString());
								wordle = new StringBuilder();
							}
						}
						lineOutput.append(c);
					}
				}
				//Case where word is left over
				if (wordle.length() > 0) {
					int inIt = wordBank.remove(wordle.toString());
					if (inIt > 0) { // Case where wordBank contains the word
						lineOutput.append(inIt);
						wordle = new StringBuilder();
					}
					if (wordle.length() > 0) { //case where the word wasnt in wordbank
						wordBank.addToFront(wordle.toString());
						lineOutput.append(wordle.toString());
						wordle = new StringBuilder();
					}
				}
				wordle = new StringBuilder();
				//Handles last portion of line
				lineOutput.append("\n");
				output.append(lineOutput);
				postBytes = postBytes + lineOutput.length() - 1;
				lineOutput = new StringBuilder();
			}
			output.append("\n");
			output.append("0 Uncompressed: " + preBytes + " bytes;  Compressed: " + (postBytes - 2)  + " bytes\n");
			bw.write(output.toString());
			bw.close();
			fS.close();
			return "COMPRESS";
	}

	private static String decompress(String fileName) throws IOException {
		Scanner fS = new Scanner(new File(fileName), "utf-8");	
		String[] parts = fileName.split("/");
		fileName = parts[parts.length - 1];
		String newFileName = fileName.substring(0,  fileName.length() - 15) + ".txt"; //remove compressed extension
		FileWriter fw = new FileWriter("output/decompressed/" + newFileName);
		BufferedWriter bw = new BufferedWriter(fw);
		WordBankList wordBank = new WordBankList();	
		StringBuilder wordle = new StringBuilder();
		StringBuilder lineOutput = new StringBuilder();
		StringBuilder output = new StringBuilder();
		StringBuilder nordle = new StringBuilder();
		int numZero = 0;
		while (fS.hasNextLine()) {
			lineOutput = new StringBuilder();
			wordle = new StringBuilder();
			String line = fS.nextLine();
			// Look for last 0 in list
			Scanner zero = new Scanner(line);
			if (zero.hasNextInt() && zero.nextInt() == 0) {
				numZero++;
				line = line.substring(2, line.length());
				if (numZero == 2) {
					bw.write(output.toString());
					zero.close();
					bw.close();
					fS.close();
					return "DECOMPRESS";
				}
			}
			zero.close();
			char[] charsM = line.toCharArray();
			for (char c : charsM) {
					if (Character.isDigit(c)) {
						nordle.append(c);
					} else { //then need to process nordle
						if (nordle.length() > 0) {
							int dum = Integer.parseInt(nordle.toString());
							if (wordBank.get(dum - 1) != null) {
								String word = wordBank.get(dum - 1);
								lineOutput.append(word);
								wordBank.remove(word);
								nordle = new StringBuilder();
							}
						}
						if (Character.isAlphabetic(c)) {
							wordle.append(c);
						} else { // Must be a special char or a spce char
							if (wordle.length() > 0) {
								wordBank.addToFront(wordle.toString());
								lineOutput.append(wordle);
								wordle = new StringBuilder();
							}
							lineOutput.append(c);}
						}
			}
			if (wordle.length() > 0) {
					wordBank.addToFront(wordle.toString());
					lineOutput.append(wordle.toString());
					wordle = new StringBuilder();
			}
			if (nordle.length() > 0) {
				int dum = Integer.parseInt(nordle.toString()) - 1;
				String word = wordBank.get(dum);
				lineOutput.append(word);
				wordBank.remove(word);
				nordle = new StringBuilder();
			}
			lineOutput.append("\n");
			output.append(lineOutput);
			lineOutput = new StringBuilder();
			wordle = new StringBuilder();
			nordle = new StringBuilder();
		}
		bw.close();
		fS.close();
		return "DECOMPRESS";
	}
}