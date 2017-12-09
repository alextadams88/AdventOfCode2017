package puzzle14;
import java.util.ArrayList;
import java.io.*;

public class Main {
	
	private static ArrayList<TreeNode> roots;
	
	public static void main(String[] args){
		try {
			roots = new ArrayList<TreeNode>();
			buildTree();
			System.out.println(findAnswer());
		}
		catch (IOException ex){
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
		}	
	}
	
	private static void buildTree() throws IOException{
		BufferedReader input = new BufferedReader(new FileReader("input.txt"));
		String newLine;
		while ((newLine = input.readLine()) != null){
			processLine(newLine);
		}
		if (roots.size() == 1){
			System.out.println(roots.get(0).getName());
		}
		else{
			System.out.println("Too many roots.");
		}
		input.close();
	}
	
	private static void processLine(String line){
		String[] args = line.split(" ");
		TreeNode lineRoot = null;
		for (int i = 0; i < roots.size(); i++){
			TreeNode currentRoot = roots.get(i).findChild(args[0]);
			if (currentRoot != null){
				lineRoot = currentRoot;
			}
		}
		if (lineRoot == null){
			lineRoot = new TreeNode();
			lineRoot.setName(args[0]);
		}
		args[1] = args[1].replaceAll("\\(","");
		args[1] = args[1].replaceAll("\\)","");
		lineRoot.setWeight(Integer.parseInt(args[1]));
		for (int i = 3; i < args.length; i++){
			addChild(lineRoot, args[i]);
		}
		if (lineRoot.getParent() == null){
			roots.add(lineRoot);
		}
	}
	
	private static void addChild(TreeNode root, String childName){
		childName = childName.replaceAll(",", "");
		TreeNode newChild = null;
		for (int i = 0; i < roots.size(); i++){
			TreeNode currentChild = roots.get(i).findChild(childName);
			if (currentChild != null){
				newChild = currentChild;
			}
		}
		if (newChild == null){
			newChild = new TreeNode();
			newChild.setName(childName);
		}
		newChild.setParent(root);
		root.addChild(newChild);
		for (int i = 0; i < roots.size(); i++){
			if (roots.get(i).getName().equals(newChild.getName())){
				roots.remove(i);
			}
		}
	}
	
	private static int findAnswer(){
		TreeNode problematicChild = roots.get(0);
		TreeNode newProblemChild = roots.get(0);
		while ((newProblemChild = findUnbalancedChild(problematicChild)) != null){
			problematicChild = newProblemChild;
		}
		TreeNode problemParent = problematicChild.getParent();
		TreeNode[] potentialProblemKids = problemParent.getChildren();
		int[] weights = new int[potentialProblemKids.length];
		for(int i = 0; i < weights.length; i++){
			weights[i] = getDiscWeight(potentialProblemKids[i]) + potentialProblemKids[i].getWeight();
		}
		int targetWeight = findCommonWeight(weights);
		return (targetWeight - getDiscWeight(problematicChild));
		
	}
	
	private static TreeNode findUnbalancedChild(TreeNode node){
		TreeNode[] children = node.getChildren();
		if (children.length == 0){
			System.out.println("ERROR: looking for unbalanced children on a node with no children!"); 
		}
		int[] weights = new int[children.length];
		for (int i = 0; i < children.length; i++){
			weights[i] = getDiscWeight(children[i]) + children[i].getWeight();
		}
		int commonWeight = findCommonWeight(weights);
		for (int i = 0; i < children.length; i++){
			if (weights[i] != commonWeight){
				return children[i];
			}
		}
		return null;
	}
	
	//searches an int[] for the first two numbers it finds that match. This is sufficient for our purpose. 
	private static int findCommonWeight(int[] weights){
		for (int i = 0; i < weights.length; i++){
			for (int j = i + 1; j < weights.length; j++){
				if (weights[i] == weights[j]){
					return weights[i];
				}
			}
		}
		return -1;
	}
	
	private static int getDiscWeight(TreeNode node){
		int discWeight = 0;
		TreeNode[] children = node.getChildren();
		for (int i = 0; i < children.length; i++){
			discWeight += children[i].getWeight();
			discWeight += getDiscWeight(children[i]);
		}
		return discWeight;
	}
}
