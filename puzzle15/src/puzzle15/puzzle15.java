package puzzle15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class puzzle15 {
	
	private static HashMap<String, Integer> registers;

	static class Instruction{
		String register;
		String command;
		int value;
		Conditional condition;
		
	}
	
	static class Conditional{
		String register;
		String comparison;
		int value;
	}
	

	
	public static void main(String[] args) {
		try{
			registers = new HashMap<String, Integer>();
			BufferedReader input = new BufferedReader(new FileReader("input.txt"));
			String nextLine;
			while ((nextLine = input.readLine()) != null){
				Instruction nextInstruction = processLine(nextLine);
				processInstruction(nextInstruction);
			}
			System.out.println(findLargest(registers));
			input.close();
		}
		catch (IOException ex){
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
		}

	}
	
	public static Instruction processLine(String line){
		String[] args = line.split(" ");
		Instruction newInstruction = new Instruction();
		newInstruction.register = args[0];
		newInstruction.command = args[1];
		newInstruction.value = Integer.parseInt(args[2]);
		Conditional newCondition = new Conditional();
		newCondition.register = args[4];
		newCondition.comparison = args[5];
		newCondition.value = Integer.parseInt(args[6]);
		newInstruction.condition = newCondition;
		return newInstruction;
	}
	
	public static void processInstruction(Instruction instruction){
		if (!registers.containsKey(instruction.register)){
			registers.put(instruction.register, 0);
		}
		if (passesCondition(instruction.condition)){
			if (instruction.command.equals("inc")){
				registers.put(instruction.register, (registers.get(instruction.register) + instruction.value));
			}
			else{
				registers.put(instruction.register, (registers.get(instruction.register) - instruction.value));
			}
		}
	}
	
	public static Boolean passesCondition(Conditional condition){
		if (!registers.containsKey(condition.register)){
			registers.put(condition.register, 0);
		}
		int value = registers.get(condition.register);
		switch (condition.comparison){
			case ">": 	return (value > condition.value);
			case "<":	return (value < condition.value);
			case ">=" : return (value >= condition.value);
			case "==" :	return (value == condition.value);
			case "<=" : return (value <= condition.value);
			case "!=" : return (value != condition.value);
			default:  return false;		
		}
	}
	
	public static int findLargest(HashMap<String, Integer> registers){
		int max = Integer.MIN_VALUE;
		Iterator<Entry<String, Integer>> iter = registers.entrySet().iterator();
		while (iter.hasNext()){
			int newValue = iter.next().getValue();
			if (newValue > max){
				max = newValue;
			}
		}
		return max;
	}
}
