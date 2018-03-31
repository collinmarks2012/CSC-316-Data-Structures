package edu.ncsu.csc316.spell_checker.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.spell_checker.io.FileReader;
import edu.ncsu.csc316.spell_checker.list.ArrayBasedList;
import edu.ncsu.csc316.spell_checker.list.WordList;

public class TestSpellChecker {
	
	private SpellCheckerManager SCM;
	
	

	@Test
	public void testWordList() {
		WordList testWordList = new ArrayBasedList();
		testWordList.add("A");
		testWordList.add("B");
		testWordList.add("C");
		testWordList.add("D");
		testWordList.add("E");
		testWordList.add("F");
		testWordList.add("G");
		testWordList.add("H");
		assertEquals("WordList[A, B, C, D, E, F, G, H]", testWordList.toString());
		
		SpellCheckerManager SCM = new SpellCheckerManager(testWordList);
		
		WordList input = new ArrayBasedList();
		input.add("dufus");
		WordList output = SCM.spellCheck(input);
		assertEquals(1, output.size());
		assertEquals("dufus", output.get(0));
		
		}
	
	@Test
	public void testTeach() {
		WordList testWordList = new ArrayBasedList();
		testWordList.add("teach");
		
		SpellCheckerManager SCM = new SpellCheckerManager(testWordList);
		WordList gogo = new ArrayBasedList();
		gogo.add("teaches");
		gogo.add("Teachers");
		gogo.add("teaching");
		gogo.add("Teachering");
		gogo.add("Teacheringsly");
		WordList tester = new ArrayBasedList();
		tester.add("duh");
		WordList testerD = SCM.spellCheck(tester);
		assertEquals("duh", testerD.get(0));
		assertEquals(1, testerD.size());
		testerD = SCM.spellCheck(gogo);
		assertEquals(0, testerD.size());

	}
	
	@Test
	public void testDuplicate() {
		WordList testWordList = new ArrayBasedList();
		testWordList.add("A");
		testWordList.add("collin");
		testWordList.add("A");
		testWordList.add("B");
		testWordList.add("Casey");
		testWordList.add("Apple");
		assertEquals("WordList[A, Apple, B, Casey, collin]", testWordList.toString());
	}
	
	@Test
	public void testConstruct() {
		WordList dict = new ArrayBasedList();
		dict.add("a");
		dict.add("b");
		
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		
		WordList testWordList = new ArrayBasedList();
		testWordList.add("A");
		testWordList.add("collin");
		testWordList.add("A");
		testWordList.add("B");
		testWordList.add("Casey");
		testWordList.add("Apple");
		
		WordList actualWordList = SCM.spellCheck(testWordList);
		String expected = "WordList[Apple, Casey, collin]";
		assertEquals(expected, actualWordList.toString());
		assertEquals("Apple", actualWordList.get(0));
		assertEquals("Casey", actualWordList.get(1));
		assertEquals("collin", actualWordList.get(2));
	}	
	
	@Test
	public void testIssues() {
		WordList dict = new ArrayBasedList();
		dict.add("a");
		assertEquals(1, dict.size());
		dict.addSorted("b");
		assertEquals(2, dict.size());
		dict.add("b");
		assertEquals(3, dict.size());
		
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		
		WordList actualWordList = new ArrayBasedList();
		actualWordList.add("stupid");
		actualWordList = SCM.spellCheck(actualWordList);
		assertEquals("stupid", actualWordList.get(0));
		assertEquals(1, actualWordList.size());
	}
	
	@Test
	public void testSpellCheck() {
		WordList dict = new ArrayBasedList();
		dict.add("sunday");
		dict.add("monday");
		dict.add("tuesday");
		dict.add("wednesday");
		dict.add("thursday");
		dict.add("friday");
		dict.add("saturday");
		

		WordList textInput = new ArrayBasedList();
		
		textInput.add("winter");
		textInput.add("spring");
		textInput.add("summer");
		textInput.add("fall");
		textInput.add("sunday");
		
		SCM = new SpellCheckerManager(dict);
		
		
		String expected = "WordList[fall, spring, summer, winter]";
		WordList test = SCM.spellCheck(textInput);
		assertEquals(expected, test.toString());
		
		WordList textInput2 = new ArrayBasedList();
		textInput2.add("winter");
		textInput2.add("spring");
		textInput2.add("summer");
		textInput2.add("fall");
		textInput2.add("sunday");
		
		
		expected = "WordList[fall, spring, summer, winter]";
		test = SCM.spellCheck(textInput2);
		assertEquals(expected, test.toString());
		
	}
	
	@Test
	public void testRealDictionary() {
		WordList dict = FileReader.readFile("dict.txt");
		assertEquals("10th", dict.get(0));
		assertEquals("zygote", dict.get(25143));
		
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		
		WordList mWL = new ArrayBasedList();
		mWL.add("dufus");
		mWL.add("xyz");
		mWL.add("stupidButt");
		mWL.add("buttered");
		
		String exp = "WordList[dufus, stupidButt, xyz]";
		assertEquals(exp, SCM.spellCheck(mWL).toString());
		WordList iDK = new ArrayBasedList();
		iDK.add("Collin");
		assertEquals("Collin", SCM.spellCheck(iDK).get(0));
		iDK.add("Apple");
		assertEquals("Collin", SCM.spellCheck(iDK).get(0));
		iDK.add("bubca");
		assertEquals("bubca", SCM.spellCheck(iDK).get(0));
		assertEquals("Collin", SCM.spellCheck(iDK).get(1));
		WordList dog = dict;
		SCM = new SpellCheckerManager(dog);
		exp = "WordList[dufus, stupidButt, xyz]";
		assertEquals(exp, SCM.spellCheck(mWL).toString());
	}


	@Test
	public void testSpellCheck2RemoveCapitilization() {
		WordList dict = new ArrayBasedList();
		dict.add("the");
		dict.add("cook");
		dict.add("cake");
		dict.add("dish");
		dict.add("bake");
		dict.add("delicious");
		WordList textInput = new ArrayBasedList();
		textInput.add("The");
		textInput.add("cook's");
		textInput.add("cakes");
		textInput.add("dishes");
		textInput.add("cooked");
		textInput.add("baked");
		textInput.add("cooker");
		textInput.add("baker");
		textInput.add("cooking");
		textInput.add("baking");
		textInput.add("deliciously");
		textInput.add("works");
		textInput.add("yay");
		String expected = "WordList[works, yay]";
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		WordList test = SCM.spellCheck(textInput);
		assertEquals(expected, test.toString());
		
	}
	
	@Test
	public void testRealDictionary2() {
		WordList dict = FileReader.readFile("dict.txt");
		WordList textInput = new ArrayBasedList();
		textInput.add("Dorothy's");
		textInput.add("dragged");
		textInput.add("dropped");
		textInput.add("easily");
		textInput.add("garret");
		textInput.add("horribly");
		textInput.add("merrily");
		textInput.add("carried");
		textInput.add("color");
		textInput.add("cookstove");
		textInput.add("direction");
		textInput.add("directions");
		textInput.add("ladder");
		textInput.add("affectionately");
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		assertEquals("WordList[carried, color, cookstove, direction, directions, Dorothy's, dragged, dropped, easily, garret, horribly, merrily]", SCM.spellCheck(textInput).toString());
		WordList a = new ArrayBasedList();
		a.add("darkness");
		a.add("carried");
		a.add("color");
		a.add("cookstove");
		a.add("direction");
		a.add("directions");
		a.add("Dorothy\'s");
		a.add("dragged");
		a.add("dropped");
		a.add("easily");
		a.add("garret");
		a.add("horribly");
		a.add("merrily");
		a.add("ladder");
		a.add("choked");
		a.add("smiled");
		a.add("closed");
		WordList test = SCM.spellCheck(a);
		assertEquals("carried", test.get(0));
		assertEquals("color", test.get(1));
		assertEquals("cookstove", test.get(2));
		assertEquals("darkness", test.get(3));
		assertEquals("direction", test.get(4));
		assertEquals("directions", test.get(5));
		assertEquals("Dorothy\'s", test.get(6));
		assertEquals("dragged", test.get(7));
		assertEquals("dropped", test.get(8));
		assertEquals("easily", test.get(9));
		assertEquals("garret", test.get(10));
		assertEquals("horribly", test.get(11));
		assertEquals("merrily", test.get(12));
		assertNull(test.get(13));
		assertNull(test.get(14));
		}
	
	@Test
	public void testRealDictionary3() {
		WordList dict = FileReader.readFile("dict.txt");
		WordList textInput = new ArrayBasedList();
		textInput.add("ADVICE");
		textInput.add("Alice's");
		textInput.add("AND");
		textInput.add("angrily");
		textInput.add("Atheling");
		textInput.add("authority");
		textInput.add("barrowful");
		textInput.add("begged");
		textInput.add("better");
		textInput.add("BILL");
		textInput.add("caldron");
		textInput.add("CATERPILLAR");
		textInput.add("choked");
		textInput.add("CAUCUS");
		textInput.add("chatte");
		textInput.add("comfits");
		textInput.add("Dinah\'ll");
		textInput.add("dipped");
		textInput.add("daisies");
		textInput.add("ME");
		textInput.add("matters");
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		assertEquals("WordList[ADVICE, Alice's, AND, angrily, Atheling, "
				+ "authority, barrowful, begged, better, BILL, caldron, "
				+ "CATERPILLAR, CAUCUS, chatte, comfits, daisies, Dinah\'ll, dipped, ME]"
				, SCM.spellCheck(textInput).toString());
		}
	
	@Test
	public void testSize() {
		WordList dict = FileReader.readFile("dict.txt");
		SpellCheckerManager SCM = new SpellCheckerManager(dict);
		WordList textInput = new ArrayBasedList();
		textInput.add("misspelledWord");
		textInput.add("plecwa");
		textInput.add("catz");
		WordList test = SCM.spellCheck(textInput);
		assertEquals(3, test.size());
		textInput.add("carafkdjf");
		textInput.add("ILoveCasey");
		textInput.add("PJEORJOEJFI");
		test = SCM.spellCheck(textInput);
		assertEquals(6, test.size());
	}
}
