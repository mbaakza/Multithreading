package com.baakza.mehreen.multithread2;

public class LongTask extends Thread {

	// Instance variables
	private SharedResults sharedData;
	private StringBuffer inputData;
	private char target;
	private int turn;

	// Single constructor that takes above 3 arguments & stores them in instance
	// values
	public LongTask(SharedResults sharedData, StringBuffer inputData, char target, int turn) {
		this.sharedData = sharedData;
		this.inputData = inputData;
		this.target = target;
		this.turn = turn;

		// Also, create a name for this thread as Thread_<target>
		setName("Thread_" + target);
	}

	public void run() {

		// Print a list of running Threads to the console
		System.out.println(Thread.currentThread().getName() + " - Turn " + turn);

		// Assign initial value before incrementing
		int count = 0;

		// Iterate through each character present in inputData using length()
		for (int i = 0; i < inputData.length(); i++) {

			// Whenever the iteration matches the target character, increment by one
			if (inputData.charAt(i) == target) {
				count++;
			}
		}

		// Create ResultsEntry object with the above count and target character
		ResultsEntry entry = new ResultsEntry(count, target);

		// Invoke addToResults method of the shared results object
		sharedData.addToResults(this.turn, entry);
	}
}
