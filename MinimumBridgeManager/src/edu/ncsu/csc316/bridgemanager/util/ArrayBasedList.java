package edu.ncsu.csc316.bridgemanager.util;

import java.util.Arrays;

import edu.ncsu.csc316.bridgemanager.edge.Edge;

public class ArrayBasedList<E> implements List<E> {
	private int size = 0;
	private Object elementData[] = {};
	

	
	public ArrayBasedList() {
		elementData = new Object[5000];
	}
	
	public ArrayBasedList(int n) {
		elementData = new Object[n];
	}
	@Override
	// by default it will add to the back of the linked list
	public void add(E value) {
		if (getSize() == elementData.length) {
			ensureCapacity();
		}
		elementData[size++] = value;
	}
	
	@SuppressWarnings("unchecked")
	public E get(int i) {
		/*if (i < 0 || i >= getSize()) {
			throw new IndexOutOfBoundsException("Index: " + i + ", Size " + getSize());
		}*/
		return (E) elementData[i];
	}
	
	@SuppressWarnings("unchecked")
	public E remove(int i) {
		if (i < 0 || i >= getSize()) {
			throw new IndexOutOfBoundsException("Index: " + i + ", Size " + getSize());
		}
		Object trash = elementData[i];
		for (int j = i; j < getSize(); j++) {
			elementData[j] = elementData[j+1];
		}
		size--;
		return (E) trash;
	}
	
	private void ensureCapacity() {
	    int newIncreasedCapacity = elementData.length * 2;
	    elementData = Arrays.copyOf(elementData, newIncreasedCapacity);
	  }
	
	public void display() {
	      System.out.print("Displaying list : ");
	      for(int i=0;i<size;i++){
	             System.out.print(elementData[i]+" ");
	      }
	  }

	public int getSize() {
		return size;
	}

	public void setItemAtEnd(E item) {
		elementData[getSize()] = item;
		size++;
	}
	public void swap(int a, int b) {
		Object temp = elementData[a];
		elementData[a] = elementData[b];
		elementData[b] = temp;
		
	}
	public void decrementSize() {
		size--;
		
	}
	public void add(int index, E item) {
		elementData[index] = item;
		size++;
	}
	public void set(int index, E item) {
		elementData[index] = item;
	}
}
