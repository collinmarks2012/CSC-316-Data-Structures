package edu.ncsu.csc316.familytree.util;

import edu.ncsu.csc316.familytree.person.Person;

public class PersonList implements List<Person> 
{
	private Node head;
	private static int counter;
	
	public PersonList() {
	}

	@Override
	public Person get(int index) {
		    	  Node tmp = head;
			      for (int k = 0; k < index; k++) tmp = tmp.next;
			      return tmp.data;
	}

	@Override
	public void add(Person value) {
		 Node current = head;

	        // as you mentioned, this is the base case
	        if(current == null) {
	            head = new Node(value);
	            head.setNext(null);
	        }

	        // you should understand this part thoroughly :
	        // this is the code that traverses the list.
	        // the germane thing to see is that when the 
	        // link to the next node is null, we are at the 
	        // end of the list.
	        else {
	            while(current.getNext() != null)
	                current = current.getNext();

	            // add new node at the end
	        Node toAppend = new Node(value);
	            current.setNext(toAppend);
	        }
	        incrementCounter();
	}
	private static int getCounter() {
		return counter;
	}
 
	private static void incrementCounter() {
		counter++;
	}

	@Override
	public int size() {
		return getCounter();
	}
	
	private class Node {
		Node next;
		Person data;
		
		public Node(Person dataValue) {
			next = null;
			data = dataValue;
		}
		
		public void setNext(Node nextValue) {
			next = nextValue;
		}
		public Node getNext() {
			return next;
		}
	}
	
}