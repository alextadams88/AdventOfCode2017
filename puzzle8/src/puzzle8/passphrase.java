package puzzle8;

import java.io.*;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class passphrase {

	public static void main(String[] args) {
		try {
		BufferedReader input = new BufferedReader(new FileReader("input.txt"));
		
		int validPhrases = 0;
		String nextLine;
		while((nextLine = input.readLine()) != null){
			if (isValid(nextLine)){
				validPhrases++;
				System.out.println(nextLine + " is valid.");
			}
			else {
				System.out.println (nextLine + " is invalid.");
			}
		}
		System.out.println(validPhrases);
		input.close();
		}
		catch (Exception ex){
			System.out.println("you done goofed");
		}
		

	}
	
	public static boolean isValid(String phrase){
		String[] terms = phrase.split(" ");
		
		for (int i = 0; i < terms.length; i++){
			for (int j = 0; j < terms.length; j++){
				if (j != i){
					if (isAnagram(terms[i], terms[j])){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean isAnagram(String first, String second){
		HashMap<Character, Integer> countFirst = new HashMap<Character, Integer>();
		HashMap<Character, Integer> countSecond = new HashMap<Character, Integer>();
		for (int i = 0; i < first.length(); i++){
			if (countFirst.containsKey(first.charAt(i))){
				countFirst.put(first.charAt(i), countFirst.get(first.charAt(i) + 1));
			}
			else {
				countFirst.put(first.charAt(i), 1);
			}
		}
		for (int i = 0; i < second.length(); i++){
			if (countSecond.containsKey(second.charAt(i))){
				countSecond.put(second.charAt(i), countSecond.get(second.charAt(i) + 1));
			}
			else {
				countSecond.put(second.charAt(i), 1);
			}
		}
		
		Iterator<Character> keysFirst = countFirst.keySet().iterator();
		while (keysFirst.hasNext()){
			Character nextChar = keysFirst.next();
			if (!countSecond.containsKey(nextChar)){
				return false;
			}
			if (countFirst.get(nextChar) != (countSecond.get(nextChar))){
				return false;
			}
		}
		
		Iterator<Character> keysSecond = countSecond.keySet().iterator();
		while (keysSecond.hasNext()){
			Character nextChar = keysSecond.next();
			if (!countFirst.containsKey(nextChar)){
				return false;
			}
			if (countSecond.get(nextChar) != (countFirst.get(nextChar))){
				return false;
			}
		}
		return true;
	}	
}
