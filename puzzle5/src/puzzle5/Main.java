package puzzle5;
import java.io.*;
import java.lang.Math;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println(buildGrid(1));
		System.out.println(buildGrid(12));
		System.out.println(buildGrid(23));
		System.out.println(buildGrid(1024));
		System.out.println(buildGrid(325489));
	}
	
	private static String buildGrid(int input){
		int xCoord = 0;
		int yCoord = 0;
		int currentNum = 1;
		int nextXMove = 1;
		int nextYMove = 1;
		while (currentNum <= input){
				if (currentNum + Math.abs(nextXMove) > input){
					if (nextXMove < 0){
						int newMove = input - currentNum;
						xCoord = xCoord - newMove;
					}
					else {
						int newMove = input - currentNum;
						xCoord = xCoord + newMove;
					}
					int answer = Math.abs(xCoord) + Math.abs(yCoord);
					return ("(" + xCoord + "," + yCoord + ") - Answer is " + answer);
				}
				else{
					xCoord += nextXMove;
					currentNum += Math.abs(nextXMove);
					if (nextXMove < 0){
						nextXMove = (nextXMove + ((Math.abs(nextXMove) * 2) + 1));
					}
					else {
						nextXMove = (nextXMove - ((Math.abs(nextXMove) * 2) + 1));
					}
					//System.out.println("X: " + xCoord + " Y: " + yCoord + " currentNum: " + currentNum);
				}
				if (currentNum + Math.abs(nextYMove) > input){
					if (nextYMove < 0){
						int newMove = input - currentNum;
						yCoord = yCoord - newMove;
					}
					else {
						int newMove = input - currentNum;
						yCoord = yCoord + newMove;
					}
					int answer = Math.abs(xCoord) + Math.abs(yCoord);
					return ("(" + xCoord + "," + yCoord + ") - Answer is " + answer);
				}
				else {
					yCoord += nextYMove;
					currentNum += Math.abs(nextYMove);
					if (nextYMove < 0){
						nextYMove = (nextYMove + ((Math.abs(nextYMove) * 2) + 1));
					}
					else {
						nextYMove = (nextYMove - ((Math.abs(nextYMove) * 2) + 1));
					}
					//System.out.println("X: " + xCoord + " Y: " + yCoord + " currentNum: " + currentNum);
				}
		}
		return ("Something isn't right.");
	
	}
	
	
}
