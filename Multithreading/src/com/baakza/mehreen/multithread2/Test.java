package com.baakza.mehreen.multithread2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
// remove when done
import java.io.File;
import java.io.FileInputStream;

public class Test {

	public static void main(String[] args) {

		// Assign URL to variable
		String page = "http://norvig.com/big.txt";

		// Declare URL object
		URL urlObject = null;

		try {
			// Instantiate here in case it fails
			urlObject = new URL(page);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			// Read the data
			StringBuffer buffer = new StringBuffer();
			String inputLine;

			// Decode data
			InputStreamReader inputStream = new InputStreamReader(urlObject.openStream());

			// Add back when done
			BufferedReader reader = new BufferedReader(inputStream);
			while ((inputLine = reader.readLine()) != null) {

				// Convert to lowercase
				inputLine = inputLine.toLowerCase();
				buffer.append(inputLine + "\n");
			}

			// Print size of text file
			System.out.println("Input data length: " + buffer.length() + "\n");

			// Create SharedResults object & assign to a variable
			SharedResults sharedData = new SharedResults();

			// Create 26 LongTask objects using an array of size 26
			LongTask[] task = new LongTask[26];

			// Set i value to prepare for loop below
			int i = 0;

			// Loop through each letter of the alphabet
			for (char letter = 'a'; letter <= 'z'; letter++) {

				// In each iteration, instantiate each LongTask object by calling its unique
				// array index
				task[i] = new LongTask(sharedData, buffer, letter, i);

				// Start the respective thread after creating each one
				task[i].start();

				// Increment index by one for the next iteration
				i++;

			}

			// Wait for all threads to complete using join method
			// (Use for-loop to once again iterate through all 26 array indices)
			for (i = 0; i < 26; i++) {
				task[i].join();
			}

			// Blank line for formatting
			System.out.println("");

			// Print the result from the shared object
			System.out.println("Alphabet count = " + sharedData.getResult());

			// Close all processes
			reader.close();
			inputStream.close();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
