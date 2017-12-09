package puzzle11;
import java.io.*;
import java.util.ArrayList;

public class puzzle11 {
	
	private static ArrayList<Integer[]> history;
	
	public static void main(String[] args){
		history = new ArrayList<Integer[]>();
		//Integer[] blocks = {0,2,7,0};
		Integer[] blocks = {14,0,15,12,11,11,3,5,1,6,8,4,9,1,8,4}; 
		System.out.println(detectLoop(blocks));
	}
	
	private static Integer[] copyIntegerArray(Integer[] blocks){
		Integer[] newArray = new Integer[blocks.length];
		for (int i = 0; i < blocks.length; i++){
			newArray[i] = blocks[i];
		}
		return newArray;
	}
	
	private static void printlnIntegerArray(Integer[] blocks){
		System.out.println(toStringIntegerArray(blocks));
	}
	
	private static String toStringIntegerArray(Integer[] blocks){
		String newString = "";
		for (int i = 0; i < blocks.length; i++){
			newString += ("" + blocks[i] + " ");
		}
		return newString;
	}

	private static int detectLoop(Integer[] blocks){
		int count = 0;
		Integer[] newBlocks = copyIntegerArray(blocks);
		while (!isRepeat(newBlocks)){
			newBlocks = cycle(newBlocks);
			count++;
		}
		return count;
	}

	private static Integer[] cycle(Integer[] oldBlocks){
		Integer[] blocks = copyIntegerArray(oldBlocks);
		int highest = blocks[0];
		int highestIndex = 0;
		for (int i = 0; i < blocks.length; i++){
			if (blocks[i] > highest){
				highest = blocks[i];
				highestIndex = i;
			}
		}
		blocks[highestIndex] = 0;
		int redistribute = highest;
		int currentBlock = highestIndex + 1;
		while (redistribute > 0){
			blocks[currentBlock % blocks.length]++;
			redistribute--;
			currentBlock++;
		}
		return blocks;
		
	}
	
	private static boolean isRepeat(Integer[] blocks){
		for (int i = 0; i < history.size(); i++){
		}
		for (int i = 0; i < history.size(); i++){
			if (isMatch(blocks, history.get(i))){
				return true;
			}
		}
		history.add(blocks);
		return false;
		
	}

	private static boolean isMatch(Integer[] test1, Integer[] test2){
		if (test1.length != test2.length){
			return false;
		}
		for (int i = 0; i < test1.length; i++){
			if (test1[i] != test2[i]){
				return false;
			}
		}		
		return true;
	}
	
}
