package com.baakza.mehreen.multithread1;

import java.util.ArrayList;

public class SharedResults {

	// Instance variable
	private ArrayList<ResultsEntry> results;

	// Default constructor to initialize ResultsEntry
	public SharedResults() {
		this.results = new ArrayList<ResultsEntry>();
	}

	// Void method that adds given ResultsEntry argument to the end of results
	// Synchronization handled with this method
	public synchronized void addToResults(ResultsEntry entry) {
		this.results.add(entry);

		// Print name of current thread, the entry it added, 
		// & shared results data structure
		System.out.println(Thread.currentThread().getName() + " is adding " + entry);
		System.out.println("Cumulative Results are " + results);
	}

	// Returns sum of count entry values in shared results data structure
	// Synchronization handled with this method
	public synchronized int getResult() {
		
		// Assign initial value before incrementing
		int sum = 0;

		// For every entry object in results ArrayList, increment sum by count
		for (ResultsEntry entry : results) {
			sum += entry.getCount();
		}

		return sum;
	}

}
