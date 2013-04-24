
public class Driver {
	public static void main(String[] args) {
		ArgumentParser ap = new ArgumentParser(args);

		if(ap.hasFlag("-c")){				
			
			TreeBuilder tb = new TreeBuilder(args[args.length - 2], args[args.length - 1]);
			if(ap.hasFlag("-f")){
				tb.readChars("force");
			}else{
				tb.readChars("");
			}
			if(ap.hasFlag("-v")){
				tb.printVerbose();
			}

		}else if(ap.hasFlag("-u")){
			
			HuffmanWriter hw = new HuffmanWriter();
			
			hw.decompressFile(args[args.length - 2], args[args.length - 1]);
			if(ap.hasFlag("-v")){
				hw.printVerbose();
			}
		}
		

	
	}
}
