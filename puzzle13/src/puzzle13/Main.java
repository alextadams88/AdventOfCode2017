package puzzle13;
import java.util.ArrayList;
import java.io.*;

public class Main {
	
	private static ArrayList<TreeNode> roots;
	
	public static void main(String[] args){
		try {
			roots = new ArrayList<TreeNode>();
			buildTree();
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
}
