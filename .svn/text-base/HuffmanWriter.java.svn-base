import java.io.File;


public class HuffmanWriter {

	private BinaryFile output, input;
	private TextFile tf;
	private Node node;

	
	public void compressFile(String inputFile, String outputFile, String[] codeArray, Node node){
		File file = new File(outputFile);
		file.delete();
		output = new BinaryFile(outputFile, 'w');

		tf  = new TextFile(inputFile, 'r');
		output.writeChar('H');
		output.writeChar('F');
		serializeTree(node);
		Character c;
		
		while(!tf.EndOfFile()){
			c = tf.readChar();
			String s = codeArray[c.hashCode()];
			for(int i = 0; i < s.length(); i++){
				if(s.charAt(i) == '1'){
					output.writeBit(true);
				}else{
					output.writeBit(false);
				}
			}
		}
		output.close();
		
	}
	
	public void serializeTree(Node tree){
		if (tree == null){
			return;
		}

		if ((tree.getLeftChild() == null) && (tree.getRightChild() == null)){	
			output.writeBit(true);
			output.writeChar(tree.getValue());
			return;
			
		}

		output.writeBit(false);	
		serializeTree(tree.getLeftChild());
		serializeTree(tree.getRightChild());

	}
	
	public void decompressFile(String inputFile, String outputFile){
		File file = new File(outputFile);
		file.delete();
		tf = new TextFile(outputFile, 'w');
		input = new BinaryFile(inputFile, 'r');
		int i = 0;
		String s = "";

		while(i < 2){			
			Character c = input.readChar();
			s = s.concat(c.toString());
			i++;
		}
		
		if(s.equals("HF")){
			System.out.println("Decompressing...");
			if(input.readBit() == false){
				node  = new Node('.', 0);
				node.setLeftChild(rebuildTree());
				node.setRightChild(rebuildTree());
				
			}else{
				
				node = new Node(input.readChar(), 0);
			}
			
			while(!input.EndOfFile()){
				if(node.getLeftChild() == null && node.getRightChild() == null){
					tf.writeChar(node.getValue());
					input.readBit();

				}else{	
					traverseTree(node);

				}
				
			}	
			tf.close();
			System.out.println("Done!");

		}else{
			System.out.println("Cannot decompress file");
		}
		
	
	}
	
	public void traverseTree(Node node){
		if(node.getLeftChild() == null && node.getRightChild() == null){
			tf.writeChar(node.getValue());
			return;
		}
		if(input.readBit() == false){
			traverseTree(node.getLeftChild());
		}else{
			traverseTree(node.getRightChild());
		}
	}
	
	public Node rebuildTree(){
		if(input.readBit() == true){		
			Node n = new Node(input.readChar(), 0);
			return n;
		}
		Node node = new Node('.', 0);
		node.setLeftChild(rebuildTree());
		node.setRightChild(rebuildTree());
		return node;
	}
	
	public void printVerbose(){
		System.out.println("=====================");
		System.out.println("Huffman Tree");
		System.out.println("=====================");
		print(node, 1);
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


