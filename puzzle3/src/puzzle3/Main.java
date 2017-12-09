package puzzle3;
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
			
			System.out.println(checkSum(in));
		}
		catch(Exception ex){
			System.out.println("you fucked up");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			
		}
	}
	
	private static int checkSum(ArrayList<ArrayList<String>> input){
		int checkSum = 0;
		for(int i = 0; i < input.size(); i++){
			int max = Integer.parseInt(input.get(i).get(0));
			int min = Integer.parseInt(input.get(i).get(0));
			for(int j = 0; j < input.get(i).size(); j++){
				int num = Integer.parseInt(input.get(i).get(j));
				if (num > max){
					max = num;
				}
				if (num < min){
					min = num;
				}
			}
			int lineSum = max - min;
			checkSum += lineSum;
		}
		return checkSum;
	}

}

