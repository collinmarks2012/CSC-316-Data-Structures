package edu.ncsu.csc316.spell_checker.hash_table;

import edu.ncsu.csc316.spell_checker.rules.SimplificationRules;

public class HashTable {
	
	int dummy = 0;

   private static class ListNode {
      String key;
      @SuppressWarnings("unused")
	private String value;
      ListNode next;
	public void setValue(String value) {
		this.value = value;
	}
   }
   private ListNode[] table;
   private int count;
   public HashTable() {
      table = new ListNode[64];
   }
   
   public void insert(String value) {
	  String key = value;      
      assert key != null;      
      int bucket = hash(key);      
      ListNode list = table[bucket];
      while (list != null) {
         if (list.key.equals(key))
            break;
         list = list.next;
      }
      if (list != null) {
         list.setValue(value);
      }
      else {
         if (count >= 0.75*table.length) {
            resize();
            bucket = hash(key);
         }
         ListNode newNode = new ListNode();
         newNode.key = key;
         newNode.setValue(value);
         newNode.next = table[bucket];
         table[bucket] = newNode;
         count++;
      }
   }

//   public boolean lookUp(String key, String orig) {	  
//	   if (key.length() == 0) {
//		  return false;
//	  }
//   int bucket = hash(key);
//   ListNode list = table[bucket];
//   while (list != null) {
//      if (list.key.equals(key))
//         return true;
//      list = list.next;
//   }
   
   // at the point we need to try again with a manipulation on the word
   // we try to remove downCase the first letter of the word
   
//   
//   if ((int) key.charAt(key.length() - 1) == "d".hashCode() && key.charAt(key.length() - 2) == "d".hashCode()) {
//	   return lookUp(key.substring(0, key.length() - 1)); // chop off s
//   }
//   
//   if ((int) key.charAt(0) >= "A".hashCode() && (int) key.charAt(0) <= "Z".hashCode()) { // is upper case
// 	  return lookUp(SimplificationRules.removeCapitilization(key));
//   }
//   
//   // we try to remove 's portion from word and try again
//   if ((int) key.charAt(key.length() - 1) == "s".hashCode() && (int) key.charAt(key.length() - 2) == "'".hashCode()) {
// 	  return lookUp(key.substring(0, key.length() - 2));
//   }
//	    // chop off e only if we tried removing s i.e. try removing e
//   
//   if ((int) key.charAt(key.length() - 1) == "d".hashCode() && key.charAt(key.length() - 2) == "e".hashCode()) {
//	   triedED = true;
//	   return lookUp(key.substring(0, key.length() - 2)); // chop off s
//   }
//   
//   if (triedED) {
//	   triedED = false;
//	   return lookUp(key+"e");
//   }
//   
//   if ((int) key.charAt(key.length() - 1) == "r".hashCode() && key.charAt(key.length() - 2) == "e".hashCode()) {
//	   triedER = true;
//	   return lookUp(key.substring(0, key.length() - 2)); // chop off s
//   }
//   
//   if (triedER) {
//	   triedER = false;
//	   return lookUp(key+"e");
//   }
//   
//   if ((int) key.charAt(key.length() - 1) == "g".hashCode() && 
//		   key.charAt(key.length() - 2) == "n".hashCode() &&
//		   key.charAt(key.length() - 3) == "i".hashCode()){
//	   triedING = true;
//	   return lookUp(key.substring(0, key.length() -3));
//   }
//   
//   if (triedING) {
//	   triedING = false;
//	   return lookUp(key+"e");
//   }
//   
//   if ((int) key.charAt(key.length() - 1) == "y".hashCode() && key.charAt(key.length() - 2) == "l".hashCode()) {
//	   triedER = true;
//	   return lookUp(key.substring(0, key.length() - 2)); // chop off s
//   }
//   
//   if ((int) key.charAt(key.length() - 1) == "s".hashCode()) {
//	   triedS = true;
//	   return lookUp(key.substring(0, key.length() - 1)); // chop off s
//   }
//   if ((int) key.charAt(key.length() - 1) == "e".hashCode() && triedS) {
//	   triedS = false;
//	   return lookUp(key.substring(0, key.length() - 1));
//   }
//   
//   
//   
//   return false;
   public boolean lookUp(String key) {	  
	   if (key.length() == 0) {
		  return false;
	  }
	   int bucket = hash(key);
	   ListNode list = table[bucket];
	   while (list != null) {
		   if (list.key.equals(key))
			   return true;
		   list = list.next;
	   }
	   
	   


	   // at the point we need to try again with a manipulation on the word
	   // we try to remove downCase the first letter of the word
	   if ((int) key.charAt(0) >= "A".hashCode() && (int) key.charAt(0) <= "Z".hashCode()) { // is upper case
		   return lookUp(SimplificationRules.removeCapitilization(key));
	   }
	   
	   if ((int) key.charAt(key.length() - 2) == "'".hashCode() && (int) key.charAt(key.length() - 1) == "s".hashCode()) {
		   return lookUp(key.substring(0, key.length() - 2));
	   }
	   
	   if ((int) key.charAt(key.length() - 1) == "s".hashCode()) {
		   key = key.substring(0, key.length() - 1); // remove s and try again
		   if (key.length() == 0) {
				  return false;
			  }
			   bucket = hash(key);
			   list = table[bucket];
			   while (list != null) {
				   if (list.key.equals(key))
					   return true;
				   list = list.next;
			   }
			   if ((int) key.charAt(key.length() - 1) == "e".hashCode()) {
				   key = key.substring(0, key.length() - 1); // remove e and try again
				   return lookUp(key);
			   } else {
				   return lookUp(key);
			   }
	   } else  if ((int) key.charAt(key.length() - 1) == "r".hashCode()) {
		   key = key.substring(0, key.length() - 1); // remove s and try again
		   if (key.length() == 0) {
				  return false;
			  }
			   bucket = hash(key);
			   list = table[bucket];
			   while (list != null) {
				   if (list.key.equals(key))
					   return true;
				   list = list.next;
			   }
			   if ((int) key.charAt(key.length() - 1) == "e".hashCode()) {
				   key = key.substring(0, key.length() - 1); // remove e and try again
				   return lookUp(key);
			   } else {
				   return lookUp(key);
			   }
	   } else  if ((int) key.charAt(key.length() - 1) == "d".hashCode()) {
		   key = key.substring(0, key.length() - 1); // remove d and try again
		   if (key.length() == 0) {
				  return false;
			  }
			   bucket = hash(key);
			   list = table[bucket];
			   while (list != null) {
				   if (list.key.equals(key))
					   return true;
				   list = list.next;
			   }
			   if ((int) key.charAt(key.length() - 1) == "e".hashCode()) {
				   key = key.substring(0, key.length() - 1); // remove e and try again
				   return lookUp(key);
			   } else {
				   return lookUp(key);
			   }
	   } else  if ((int) key.charAt(key.length() - 1) == "g".hashCode() &&
			   (int) key.charAt(key.length() -2 ) == "n".hashCode() &&
			   (int) key.charAt(key.length() - 3) == "i".hashCode()) {
		   
		   key = key.substring(0, key.length() - 3); // remove ing and try again
		   if (key.length() == 0) {
				  return false;
		   }
		   bucket = hash(key);
		   list = table[bucket];
		   while (list != null) {
			   if (list.key.equals(key))
				   return true;
				   list = list.next;
			   }
		   if (lookUp(key) == false) {
			   return lookUp(key + "e");
		   } else {
			   return lookUp(key);
		   }
	   } else if ((int) key.charAt(key.length() - 1) == "y".hashCode() &&
			   (int) key.charAt(key.length() -2 ) == "l".hashCode()) {
		   return lookUp(key.substring(0, key.length() - 2));
	   } else  {
		   return false;
	   }
	   
	   
   }
//// we try to remove 's portion from word and try again
//if ((int) key.charAt(key.length() - 1) == "s".hashCode() && (int) key.charAt(key.length() - 2) == "'".hashCode()) {
//	  return lookUp(key.substring(0, key.length() - 2), orig);
//}
//if ((int) key.charAt(key.length() - 1) == "s".hashCode() && (int) key.charAt(key.length() - 2) == "e".hashCode()) {
//	 	  triedS  = false;
//	 	  return lookUp(key.substring(0, key.length() - 1), orig); // remove s
//	   }
//if ((int) orig.charAt(orig.length() - 1) == "s".hashCode() && (int) orig.charAt(orig.length() - 2) == "e".hashCode() ) {
//	 	  if (!triedS) {
//	 		  triedS = true;
//	 		  return lookUp(key.substring(0, key.length() - 1), orig);  // remove e from es
//	 	  } 
//	   }
//
//if ((int) key.charAt(key.length() - 1) == "d".hashCode() && (int) key.charAt(key.length() - 2) == "e".hashCode()) {
//	  triedED = false;
//	  return lookUp(key.substring(0, key.length() - 2), orig); // remove ed
//}
//if ((int) orig.charAt(orig.length() - 1) == "d".hashCode() && (int) orig.charAt(orig.length() - 2) == "e".hashCode() ) {
//	  if (!triedED) {
//		  triedED = true;
//		  return lookUp(key+"e",orig);
//	  } else {
//		  if (!removedEFromED) {
//			  removedEFromED = true;
//			  return lookUp(key.substring(0, key.length() - 1), orig); // remove e
//		  }
//	  }
//}
//
//if ((int) key.charAt(key.length() - 1) == "r".hashCode() && (int) key.charAt(key.length() - 2) == "e".hashCode()) {
//	  triedER = true;
//	  return lookUp(key.substring(0, key.length() - 2), orig); // remove er
//}
//
//if (triedER) {
//	triedER = false;
//	return lookUp(key+"e", orig);
//}
//
//if ((int) orig.charAt(orig.length() - 1) == "r".hashCode() && (int) orig.charAt(orig.length() - 2) =="e".hashCode()) {
//	  if (triedER) {
//		  triedER = false;
//		  return lookUp(key+"e",orig);
//	  } else {
//		  if (!removedEFromER) {
//			  triedER = false;
//			  removedEFromER = true;
//			  return lookUp(key.substring(0, key.length() - 1), orig); // remove e
//		  }
//	  }
//	  
//} else if ((int) key.charAt(key.length() - 1) == "r".hashCode() && (int) key.charAt(key.length() - 2) == "e".hashCode()) {
//	  if (key.length() < orig.length()) {
//		  return lookUp(key+"e",orig);
//	  } else {
//		  return false;
//	  } 
//}
//if ((int) key.charAt(key.length() - 1) == "g".hashCode() && (int) key.charAt(key.length() - 2) == "n".hashCode() && (int) key.charAt(key.length() - 3) == "i".hashCode()) {
//	  return lookUp(key.substring(0, key.length() - 3), orig); // remove ing
//}
//
//if ((int) orig.charAt(orig.length() -1) == "g".hashCode() && (int) orig.charAt(orig.length() -2) == "n".hashCode() && (int) orig.charAt(orig.length() -3) == "i".hashCode()) {
//	  if (key.length() < orig.length()) {
//		  return lookUp(key+"e",orig);
//	  } else {
//		  return false;
//	  }
//}
//if ((int) key.charAt(key.length() - 1) == "y".hashCode() && (int) key.charAt(key.length() - 2) == "l".hashCode()) {
//	  return lookUp(key.substring(0, key.length() - 2), orig); // remove ly
//}
//// we try to remove s portion from word and try again
//if ((int) key.charAt(key.length() - 1) == "s".hashCode()) {
//	  return lookUp(key.substring(0, key.length() - 1), orig); // remove s
//}
//
//if ((int) key.charAt(key.length() -1) == "d".hashCode()) {
//	  return lookUp(key.substring(0,  key.length() - 1), orig);
//}
//
//
//
//
//
//
//// If we get to this point, we know that the key does
//// not exist in the table.
//triedED = false;
//removedEFromER = false;
//triedER =  false;
//removedEFromED = false;
//return false;
//}
   
  
   
   
   // If we get to this point, we know that the key does
   // not exist in the table.
   
   /**
    * Return the number of key/value pairs in the table.
    */
   public int size() {
      return count;
   }
   private int hash(Object key) {
      return (Math.abs(key.hashCode())) % table.length;
   }

   private void resize() {
      ListNode[] newtable = new ListNode[table.length*2];
      for (int i = 0; i < table.length; i++) {
          ListNode list = table[i]; 
         while (list != null) {
            ListNode next = list.next;
            int hash = (Math.abs(list.key.hashCode())) % newtable.length;
            list.next = newtable[hash];
            newtable[hash] = list;
            list = next;
         }
      }
      table = newtable;
   } 
}