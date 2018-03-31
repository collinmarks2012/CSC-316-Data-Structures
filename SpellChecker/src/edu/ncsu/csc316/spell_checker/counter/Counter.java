package edu.ncsu.csc316.spell_checker.counter;

public class Counter {
	
	private static int totalProbes;
	
	private static int totalLookUps;
	
	private static int totalWordsChecked;
	
	public Counter() {
		setTotalProbes(0);
		setTotalLookUps(0);
		setTotalWordsChecked(0);
	}
	
	public static void incrementProbes() {
		setTotalProbes(getTotalProbs() + 1);
	}
	
	public static void incrementLookUps() {
		setTotalLookUps(getTotalLookUps() + 1);
	}
	
	public static void incrementWordsChecked() {
		setTotalWordsChecked(getTotalWordsChecked() + 1);
	}

	public static int getTotalProbs() {
		return totalProbes;
	}

	public static void setTotalProbes(int totalProbes) {
		Counter.totalProbes = totalProbes;
	}

	public static int getTotalLookUps() {
		return totalLookUps;
	}

	public static void setTotalLookUps(int totalLookUps) {
		Counter.totalLookUps = totalLookUps;
	}

	public static int getTotalWordsChecked() {
		return totalWordsChecked;
	}

	public static void setTotalWordsChecked(int totalWordsChecked) {
		Counter.totalWordsChecked = totalWordsChecked;
	}

}
