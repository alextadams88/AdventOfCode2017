package puzzle7;

import java.io.*;

public class passphrase {

	public static void main(String[] args) {
		try {
		BufferedReader input = new BufferedReader(new FileReader("input.txt"));
		
		int validPhrases = 0;
		String nextLine;
		while((nextLine = input.readLine()) != null){
			if (isValid(nextLine)){
				validPhrases++;
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
					if (terms[i].equals(terms[j])){
						return false;
					}
				}
			}
		}
		return true;
	}

}
