package com.baakza.mehreen.multithread2;

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
	public synchronized void addToResults(int turn, ResultsEntry entry) {

		int size = results.size();

		// Only print threads waiting for their turn, not the current thread
		if (turn != size) {
			System.out.print("Calling " + Thread.currentThread().getName() + " Turn " + turn + ", ");
			System.out.println("WhoseTurn " + size + "...Wait");
		}

		// Tell all threads (whose array index doesn't match their turn) to wait
		while (turn != size) {
			try {
				wait();
				size = results.size(); // Update the size variable
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Otherwise, add the given entry to results
		this.results.add(entry);

		System.out.println("Calling " + Thread.currentThread().getName() + " Turn " + turn + ", "
				+ Thread.currentThread().getName() + " is adding " + entry);

		// Notify the other threads
		notifyAll();
		System.out.println("   " + "Cumulative result is " + results + "\n");
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
