package edu.ncsu.csc316.spell_checker.rules;

public class SimplificationRules {
	
	public static String removeCapitilization(String key) {
		char[] chars = key.toCharArray();
	      chars[0] = (char) ((int) chars[0] + 32); // make lower case  
	      key = "";
	      for (int i = 0; i < chars.length; i++) {
	    	  key = key + chars[i];
	      }
	      return key;
	}
}
