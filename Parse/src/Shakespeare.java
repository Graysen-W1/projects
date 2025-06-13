import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * This program determines how many unique words are in "shakespeare.txt" file.
 * 
 * This program details alphabetically the first and last 5 of the "shakespeare.txt" file.
 * 
 * This program writes all this information into a file named "shake.txt".
 * 
 * 
 * @version 1.0, 4/11/2025
 * @author Graysen Wargo
 * 
 */


public class Shakespeare {
	public static void main(String[] args) throws FileNotFoundException {
		
		Set<String> letters = new HashSet<>();
		Map<String, Integer> word = new HashMap<>();
		
		Scanner scnr = new Scanner(new FileInputStream("shakespeare.txt"));
		PrintWriter exit = new PrintWriter(new FileOutputStream("shake.txt"));
		
		// tutoring help
		// this removes all non-alphabetical characters
		while (scnr.hasNext()) {
			String words = scnr.next().trim().toLowerCase();
			words = words.replaceAll("[^a-zA-Z]", "");
			if (words.equals("")) {
				continue;
			}
			
			word.put(words, word.getOrDefault(words, 0) + 1);
			letters.add(words);
			
		}
		
		exit.println("All Unique Words: " + letters.size());
		
		// this converts keys to a list
		ArrayList<String> wordss = new ArrayList<>(word.keySet());
		// sorts the list alphabetically 
		Collections.sort(wordss);
		
		
		// tutoring help
		// this loops to display the first 5 words alphabetically
		exit.println("\nFirst 5 Words Alphabetically:");
		for (int i = 0; i < 5 && i < wordss.size(); i++) {
			exit.println(wordss.get(i));
		}	
		
		
		// this loops to display the last 5 words alphabetically
		exit.println("\nLast 5 Words Alphabetically:"); 
			for (int i = wordss.size() - 5; i < wordss.size(); i++) {
				exit.println(wordss.get(i));
			}
			
		// this makes a copy of the original word list to use frequently 
		ArrayList<String> wordCopy = new ArrayList<>(wordss);
		
		// this will find the most frequent words (top 5)
		ArrayList<String> manyWords = new ArrayList<>();
		ArrayList<Integer> manyCounts = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			maxElement(wordCopy, word, manyWords, manyCounts);
		}
		
		exit.println("\nTop 5 Most Frequent Words:");
		for (int i = 0; i < manyWords.size(); i++) {
			exit.println(manyWords.get(i) + ": " + manyCounts.get(i));
		}
		
		exit.close();
		
	}
		
	// tutoring help
	// this private helper method helps to find the most frequent word, 
	// adds it to the list, and then removes it
	private static void maxElement(ArrayList<String> wordList, Map<String, Integer> wordMap, 
								   ArrayList<String> manyWords, ArrayList<Integer> manyCounts) {
			
		int maxIndex = 0;
			for (int i = 1; i < wordList.size(); i++) {
				if (wordMap.get(wordList.get(i)) > wordMap.get(wordList.get(maxIndex))) {
					maxIndex = i;
				}
			}
			
			String mostFrequentWord = wordList.get(maxIndex);
			manyWords.add(mostFrequentWord);
			manyCounts.add(wordMap.get(mostFrequentWord));
			
			wordMap.remove(mostFrequentWord);
			wordList.remove(maxIndex);		
	}
}
