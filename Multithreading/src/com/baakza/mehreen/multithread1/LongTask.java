package com.baakza.mehreen.multithread1;

public class LongTask extends Thread {

	// Instance variables
	private SharedResults sharedData;
	private StringBuffer inputData;
	private char target;

	// Single constructor that takes above 3 arguments & stores them in instance
	// values
	public LongTask(SharedResults sharedData, StringBuffer inputData, char target) {
		this.sharedData = sharedData;
		this.inputData = inputData;
		this.target = target;

		// Also, create a name for this thread as Thread_<target>
		setName("Thread_" + target);
	}

	public void run() {

		// Print a list of running Threads to the console
		System.out.println("Thread " + Thread.currentThread().getName() + " running");

		// Assign initial value before incrementing
		int count = 0;

		// iterate through each character of the Strings present in inputData
		for (int i = 0; i < inputData.length(); i++) {

			// whenever the iteration matches the target character, increment by one
			if (inputData.charAt(i) == target) {
				count++;
			}
		}

		// Blank line to make output pretty
		System.out.println("");

		// Create ResultsEntry object with the above count and target character
		ResultsEntry entry = new ResultsEntry(count, target);

		// Invoke addToResults method of the shared results object
		sharedData.addToResults(entry);
	}

}
