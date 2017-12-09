package puzzle4;
import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		try{
			BufferedReader input = new BufferedReader(new FileReader("input.txt"));
			ArrayList<ArrayList<String>> in  = new ArrayList<ArrayList<String>>();
			String newLine;
			while ((newLine = input.readLine()) != null){
				String[] line = newLine.split("\t");
				ArrayList<String> arrayLine = new ArrayList<String>();
				for (int i = 0; i < line.length; i++){
					arrayLine.add(line[i]);
				}
				in.add(arrayLine);
			}
			
			System.out.println(evenlyDivisibleSum(in));
		}
		catch(Exception ex){
			System.out.println("you fucked up");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			
		}
	}
	
	private static int evenlyDivisibleSum(ArrayList<ArrayList<String>> input){
		int evenlyDivisbleSum = 0;
		for(int i = 0; i < input.size(); i++){
			for(int j = 0; j < input.get(i).size(); j++){
				int num = Integer.parseInt(input.get(i).get(j));
				for (int k = 0; k < input.get(i).size(); k++){
					if (k != j){
						int newNum = Integer.parseInt(input.get(i).get(k));
						if (num % newNum == 0){
							evenlyDivisbleSum += num / newNum;
						}
					}
				}
			}
		}
		return evenlyDivisbleSum;
	}

}
