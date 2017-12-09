package puzzle14;
import java.util.ArrayList;

public class TreeNode {

	private TreeNode parent;
	private ArrayList<TreeNode> children;
	private String name;
	private int weight;
	
	public TreeNode(){
		this.parent = null;
		this.children = new ArrayList<TreeNode>();
		this.name = "";
		this.weight = 0;
	}
	
	public TreeNode(String name){
		this.name = name;
		this.parent = null;
		this.children = new ArrayList<TreeNode>();
		this.weight = 0;
	}
	
	public TreeNode(TreeNode parent){
		this.name = "";
		this.parent = parent;
		this.children = new ArrayList<TreeNode>();
		this.weight = 0;
	}
	
	public TreeNode(ArrayList<TreeNode> children){
		this.name = "";
		this.parent = null;
		this.children = children;
		this.weight = 0;
	}
	
	public TreeNode(TreeNode parent, TreeNode child){
		this.parent = parent;
		this.children = new ArrayList<TreeNode>();
		this.children.add(child);
		this.weight = 0;
		this.name = "";
	}
	
	public TreeNode(TreeNode parent, ArrayList<TreeNode> children){
		this.parent = parent;
		this.children = children;
		this.name = "";
		this.weight = 0;
	}
	
	public void addChild(TreeNode child){
		children.add(child);
	}
	
	public void setParent(TreeNode parent){
		this.parent = parent;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public TreeNode[] getChildren(){
		TreeNode[] nodes = new TreeNode[children.size()];
		for (int i = 0; i < children.size(); i++){
			nodes[i] = children.get(i);
		}
		return nodes;
	}
	
	public int numChildren(){
		return children.size();
	}
	
	public TreeNode getParent(){
		return parent;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public String getName(){
		return name;
	}
	
	public TreeNode findChild(String name){
		if (this.name.equals(name)){
			return this;
		}
		if (children == null){
			return null;
		}
		for (int i = 0; i < children.size(); i++){
			TreeNode newChild = children.get(i).findChild(name);
			if (newChild != null){
				return newChild;
			}
		}
		return null;
	}
	
}
