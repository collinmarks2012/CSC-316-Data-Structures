package edu.ncsu.csc316.bridgemanager.util;

public interface List<E> {
	
	void add(E value);
	
	int getSize();

	E get(int i);

	void setItemAtEnd(E item);

	void swap(int i, int i2);

	void decrementSize();

}
