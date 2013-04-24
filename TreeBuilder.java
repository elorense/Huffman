/* Author:  Edric Orense
 * File:    TreeBuilder.java
 * Purpose: Creates a tree of characters in a file based on their frequency 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TreeBuilder {
	private TextFile tf;
	private HashMap<Character, Integer> charFreq;
	private String textFile, outputFile;
	private ArrayList<Node> nodeSet;
	private Node node;
	private String[] bitStringArray;
	private HuffmanWriter hw;
	private int originalFileSize, compressedFileSize;
	
	public TreeBuilder(String textFile, String outputFile){
		charFreq = new HashMap<Character, Integer>();
		nodeSet = new ArrayList<Node>();
		this.textFile = textFile;
		hw = new HuffmanWriter();
		this.outputFile = outputFile;

	}
	
	public void readChars(String flag){
		tf = new TextFile(textFile, 'r');
		originalFileSize = 0;
		System.out.println("Reading in file...");
		while(!tf.EndOfFile()){
			char c = tf.readChar();
			originalFileSize++;
			if(charFreq.containsKey(c)){

				charFreq.put(c, charFreq.get(c) + 1);
			}else{
				charFreq.put(c, 1);
			}
		
		}
		
		originalFileSize = originalFileSize * 8;
				
		createNodes();
		computeCompressedFileSize();

		tf.rewind();
		
		if(flag.equals("force")){
			System.out.println("Compressing...");
			hw.compressFile(textFile, outputFile, bitStringArray, nodeSet.get(0));
			System.out.println("Done!");
		}else if(originalFileSize > compressedFileSize){
			System.out.println("Compressing...");
			hw.compressFile(textFile, outputFile, bitStringArray, nodeSet.get(0));
			System.out.println("Done!");
		}else{
			System.out.println("Compressed file would be bigger than original file. To compress, add flag \"-f\"");
		}
		
		
	}
	
	public int getFileSize(){
		return originalFileSize;
	}
	
	public void computeCompressedFileSize(){ 
		computeCompressedTree(nodeSet.get(0));
		compressedFileSize+=48;
		int size = 0;
		for(Character c : charFreq.keySet()){
			size = charFreq.get(c) * bitStringArray[c.hashCode()].length();
			compressedFileSize += size;
		}
		compressedFileSize+=(8 - (compressedFileSize%8));
	}
	
	public int getCompressedFileSize(){
		return compressedFileSize;
	}
	
	public void computeCompressedTree(Node node){
		if(node == null){
			return;
		}
		
		if(node.getLeftChild() == null && node.getRightChild() == null){
			compressedFileSize = compressedFileSize + 9;
			return;
		}
		
		compressedFileSize+=1;
		computeCompressedTree(node.getLeftChild());
		computeCompressedTree(node.getRightChild());

	}
	
	public void createNodes(){
		for(Character c : charFreq.keySet()){

				Node node = new Node(c, charFreq.get(c));
				nodeSet.add(node);
			
		}
		Collections.sort(nodeSet);
		
		while(nodeSet.size() > 1){
			node = new Node('.', (nodeSet.get(0).getFrequency() + nodeSet.get(1).getFrequency()));
			node.setLeftChild(nodeSet.get(0));
			node.setRightChild(nodeSet.get(1));	
			nodeSet.remove(0);
			nodeSet.remove(0);
			nodeSet.add(node);
			Collections.sort(nodeSet);
			
		}
		
		String bitString = "";
		bitStringArray = new String[256];
		if(nodeSet.get(0).getLeftChild() == null && nodeSet.get(0).getRightChild() == null){
			bitStringArray[nodeSet.get(0).getValue().hashCode()] = "1";

		}else{
			createBitStrings(nodeSet.get(0), bitString);
		}

	}
	
	public void createBitStrings(Node tree, String bitString){

		if (tree == null){
			return;
		}
		if((tree.getLeftChild() != null)){
			createBitStrings(tree.getLeftChild(), bitString.concat("0"));

		}
		if((tree.getRightChild() != null)){
			createBitStrings(tree.getRightChild(), bitString.concat("1"));

		}
		if ((tree.getLeftChild() == null) && (tree.getRightChild() == null)){	
			bitStringArray[tree.getValue().hashCode()] = bitString;
			return;
			
		}
		
	}
	
	public void printVerbose(){
		System.out.println("======================");
		System.out.println("Character Frequencies");
		System.out.println("====================== ");
		for(Character c : charFreq.keySet()){
			System.out.println("-----------------------------------");
			System.out.println("ASCII: " + c.hashCode() + " | Frequency: " + charFreq.get(c));
			System.out.println("-----------------------------------");
		}
		
		System.out.println("=====================");
		System.out.println("Huffman Tree");
		System.out.println("=====================");
		print(nodeSet.get(0), 1);
		
		System.out.println("======================");
		System.out.println("Huffman Codes");
		System.out.println("====================== ");
				
		for(int i = 0; i < bitStringArray.length; i++){
			if(!(bitStringArray[i] == null)){
				System.out.println("-------------------------------------------");
				System.out.println("ASCII: " + i + " " + " | Code: " + bitStringArray[i]);
				System.out.println("-------------------------------------------");
			}
		}
		
	}
	
	private void print(Node tree, int indent) {
		if (tree != null) {
			for(int i=0; i<indent; i++) {
				System.out.print("\t");
				
			}
			
			System.out.println(tree.getValue() + " : "	 + tree.getFrequency());
			print(tree.getLeftChild(), indent + 1);
			print(tree.getRightChild(), indent + 1);
		}
		
	}

}

