

public class Node implements Comparable<Node>{
	private int frequency;
	private Character value;
	private Node leftChild, rightChild;
	
	public Node(char value, int frequency){
		this.setValue(value);
		this.setFrequency(frequency);
	}
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public int compareTo(Node arg0) {
		return this.getFrequency() - arg0.getFrequency();
	}

	public Character getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	
}
