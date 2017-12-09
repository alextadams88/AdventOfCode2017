package puzzle10;
import java.io.*;
import java.util.ArrayList;

public class puzzle10 {
	public static void main(String[] args){
		try {
			BufferedReader input = new BufferedReader(new FileReader("input.txt"));
			ArrayList<Integer> instructions = new ArrayList<Integer>();
			String nextLine;
			while((nextLine = input.readLine()) != null){
				instructions.add(Integer.parseInt(nextLine));
			}
			System.out.println(findExit(instructions));
			
		}
		catch (Exception ex){
			System.out.println("Goof");
		}
		
	}
	
	private static int findExit(ArrayList<Integer> instructions){
		int steps = 0; 
		int position = 0; 
		while (position < instructions.size() && position >= 0){
			int newPosition = position + instructions.get(position);
			if (instructions.get(position) >= 3){
				instructions.set(position, instructions.get(position) - 1);
			}
			else {
				instructions.set(position, instructions.get(position) + 1);
			}
			position = newPosition;
			steps++;
		}
		return steps;
	}

}
