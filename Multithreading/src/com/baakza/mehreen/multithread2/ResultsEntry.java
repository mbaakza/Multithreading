package com.baakza.mehreen.multithread2;

public class ResultsEntry {

	// Instance variables
	private int count;
	private char target;

	// Single constructor with both variables
	public ResultsEntry(int count, char target) {
		this.count = count;
		this.target = target;
	}

	// Public get methods for both variables
	public int getCount() {
		return count;
	}

	public char getTarget() {
		return target;
	}

	// Public toString method that returns a string in the format <target,count>
	public String toString() {
		return ("<" + target + "," + count + ">");
	}

}
