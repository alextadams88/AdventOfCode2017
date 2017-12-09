package puzzle18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	static class Group{
		int score;
		ArrayList<Group> nestedGroups;
		
		public Group(){
			score = 1;
			nestedGroups = new ArrayList<Group>();
		}
	}
	
	public static ArrayList<Group> rootGroups;
	public static int totalScore;
	public static int garbageCount;

	public static void main(String[] args) {
		totalScore = 0;
		garbageCount = 0;
		rootGroups = new ArrayList<Group>();
		try{
			BufferedReader input = new BufferedReader(new FileReader("input.txt"));
			char nextChar;
			while ((nextChar = (char)input.read()) != 65535){
				if (nextChar == '!'){
					input.read();
				}
				if (nextChar == '<'){
					discardGarbage(input);
				}
				if (nextChar == '{'){
					Group newGroup = new Group();
					newGroup.score = 1;
					totalScore += 1;
					rootGroups.add(newGroup);
					processGroup(newGroup, input);
				}
			}
			System.out.println(totalScore);
			System.out.println(garbageCount);
		}
		catch (IOException ex){
			System.out.println(ex);
		}
	}
	
	private static void discardGarbage(BufferedReader input) throws IOException{
		char nextChar;
		while ((nextChar = (char)input.read()) != -1){
			if (nextChar == '!'){
				input.read();
			}
			else if (nextChar == '>'){
				return;
			}
			else {
				garbageCount++;
			}
		}
	}
	
	private static void processGroup(Group rootGroup, BufferedReader input) throws IOException{
		char nextChar;
		while ((nextChar = (char)input.read()) != -1){
			if (nextChar == '!'){
				input.read();
			}
			if (nextChar == '<'){
				discardGarbage(input);
			}
			if (nextChar == '{'){
				Group newGroup = new Group();
				newGroup.score = rootGroup.score + 1;
				totalScore += rootGroup.score + 1;
				rootGroup.nestedGroups.add(newGroup);
				processGroup(newGroup, input);
			}
			if (nextChar == '}'){
				return;
			}
		}
	}

}
