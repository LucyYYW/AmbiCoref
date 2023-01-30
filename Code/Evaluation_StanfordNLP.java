import java.util.Iterator;
import java.util.Properties;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Arrays;
import java.io.*;


import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.coref.data.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.IntPair;

public class AmbiCoref {

	private static byte getCorefResult_ECO_TOP(String data, StanfordCoreNLP pipeline) {
		Annotation document = new Annotation(data);
		
		String[] tokens = data.split("\\s+");
		String name1 = tokens[0];
		
		int name2_start_index = 2;
		if (name1.equals("The")) {
			name1 = tokens[1].replace("-", " - ");
			name2_start_index = 3;
		}
		
		int index_by = Arrays.asList(tokens).indexOf("by");

		if (index_by != -1) {
			name2_start_index = index_by + 1;
		}
		
		String name2 = tokens[name2_start_index];
		if (name2.equals("the")) {
			name2 = tokens[name2_start_index+1].replace("-", " - ");;
		}
		

		String pronoun = "he";
		if (data.contains("she")) {
			pronoun = "she";
		}
		
		
		
	    pipeline.annotate(document);
	    
	    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
	    	String ccStr = cc.toString();
	    	
	    	if (ccStr.contains(pronoun)) {
	    		/* Uncomment one of the following to get num of mentions */
	    		
	    		//-------------DETERM *************/
	    		Iterator<IntPair> iterator = cc.getMentionMap().keySet().iterator();
	    		int num_mentions = cc.getMentionMap().get(iterator.next()).size();
//	    		
	    		//-------------STAT or neural *************
//	    		int num_mentions = cc.getMentionMap().size();
	    		
	    		/* finish getting num of mentions ********* */
	    		
	    		if (num_mentions <= 1) {
	        		continue;
	        	}
	    		if (ccStr.contains(name1) && !ccStr.contains(name2)) {
	    			return 1;
	    		} else if (ccStr.contains(name2) && !ccStr.contains(name1)) {
	    			return 2;
	    		} else if (ccStr.contains(name1) && ccStr.contains(name2)) {
	    			return 3;
	    		} else {
	    			return 4;
	    		}
	    	}
	      	  
	    }
	 
		return -1;
	  }
	
	
	private static byte getCorefResult_ECS(String data, StanfordCoreNLP pipeline) {
		Annotation document = new Annotation(data);
		
		String[] tokens = data.split("\\s+");
		String name1 = tokens[0];
		
		int name2_start_index = 2;
		if (name1.equals("The")) {
			name1 = tokens[1].replace("-", " - ");
			name2_start_index = 3;
		}
		
		int index_by = Arrays.asList(tokens).indexOf("by");

		if (index_by != -1) {
			name2_start_index = index_by + 1;
		}
		
		String name2 = tokens[name2_start_index];
		if (name2.equals("the")) {
			name2 = tokens[name2_start_index+1].replace("-", " - ");;
		}
		

		String pronoun = "him";
		if (data.contains("her")) {
			pronoun = "her";
		}
		
	    pipeline.annotate(document);
	    
	    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
	    	String ccStr = cc.toString();

	    	if (ccStr.contains(pronoun)) {
	    		
	    		/* Uncomment one of the following to get num of mentions */
	    		
	    		//-------------DETERM *************/
	    		Iterator<IntPair> iterator = cc.getMentionMap().keySet().iterator();
	    		int num_mentions = cc.getMentionMap().get(iterator.next()).size();
//	    		
	    		//-------------STAT or neural *************
//	    		int num_mentions = cc.getMentionMap().size();
	    		
	    		/* finish getting num of mentions ********* */
	    		
	    		if (num_mentions <= 1) {
	        		continue;
	        	}
	    		if (ccStr.contains(name1) && !ccStr.contains(name2)) {
	    			return 1;
	    		} else if (ccStr.contains(name2) && !ccStr.contains(name1)) {
	    			return 2;
	    		} else if (ccStr.contains(name1) && ccStr.contains(name2)) {
	    			return 3;
	    		} else {
	    			return 4;
	    		}
	    	}
	      	  
	    }
	 
		return -1;
		
	}
	
	
	
	private static byte getCorefResult_IC(String data, StanfordCoreNLP pipeline) {
		Annotation document = new Annotation(data);
		
		String[] tokens = data.split("\\s+");
		String name1 = tokens[0];
		
		if (name1.equals("The")) {
			name1 = tokens[1].replace("-", " - ");
		}
		
		int index_bc = Arrays.asList(tokens).indexOf("because");
		
		String name2 = tokens[index_bc-1].replace("-", " - ");
		
		String pronoun = tokens[index_bc+1];
		
	    pipeline.annotate(document);
	    
	    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
	    	String ccStr = cc.toString();

	    	if (ccStr.contains(pronoun)) {
	    		
	    		/* Uncomment one of the following to get num of mentions */
	    		
	    		//-------------DETERM *************/
	    		Iterator<IntPair> iterator = cc.getMentionMap().keySet().iterator();
	    		int num_mentions = cc.getMentionMap().get(iterator.next()).size();
//	    		
	    		//-------------STAT or neural *************
//	    		int num_mentions = cc.getMentionMap().size();
	    		
	    		/* finish getting num of mentions ********* */
	    		
	    		if (num_mentions <= 1) {
	        		continue;
	        	}
	    		if (ccStr.contains(name1) && !ccStr.contains(name2)) {
	    			return 1;
	    		} else if (ccStr.contains(name2) && !ccStr.contains(name1)) {
	    			return 2;
	    		} else if (ccStr.contains(name1) && ccStr.contains(name2)) {
	    			return 3;
	    		} else {
	    			return 4;
	    		}
	    	}
	      	  
	    }
	 
		return -1;
  }
  	
	
  public static void main(String[] args) throws Exception {
	  
	  Properties props = new Properties();
		
		/* *********  Uncomment one of the following to set property ************/
		
//		------------DETERM:
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");

//		--------------STAT:
//	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, depparse, coref");
//	    props.setProperty("coref.algorithm", "statistical");
	    
//	    --------------NEURAL
//	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref");
//	    props.setProperty("coref.algorithm", "neural");
	    
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    
	    /* *********  finising setting pipeline ************/
	  
	  
	String[] fileNames14 = {
			"ECO-1_unambiguous",
			"ECO-1_ambiguous"
			"ECO-2_unambiguous",
			"ECO-2_ambiguous",
			"TOP_unambiguous",
			"TOP_ambiguous",
			"TOP_unambiguous",
			"TOP_unambiguous",
			"TOP_ambiguous"
	};
	
	String[] fileNames2 = {
			"ECS-1_unambiguous",
			"ECS-1_ambiguous",
			"ECS-2_unambiguous",
			"ECS-2_ambiguous"
	};
	
	String[] fileNames3 = {
			"IC_unambiguous",
			"IC_ambiguous"
	};
			
	for (String fileName : fileNames14){
		File inputFile = new File("src/"+ fileName+".txt"); //need to change: choose the correct function based on the template type

		/*  finish selecting file      */
		Scanner myReader = new Scanner(inputFile);
		
		int count_total = 0;
		int count_pos1 = 0;
		int count_pos2 = 0;
		int count_both = 0;
		int count_other = 0;
		
		while (myReader.hasNextLine()) {

			
			String data = myReader.nextLine();
			
			/* Run model  *******/
		    byte result = getCorefResult_ECO_TOP(data, pipeline); //need to change: choose the correct function based on the template type
		    /* *finish running the model**/
		    
		    count_total += 1;
		   
		    if (result == 1) {
		    	count_pos1 += 1;
		    } else if (result == 2) {
		    	count_pos2 += 1;
		    } else if (result == 3) {
		    	count_both += 1;
		    } else if (result == 4) {
		    	count_other += 1;
		    }
		   
		}
		
	    myReader.close();
	    
	    // output
	    System.out.println("-------------------------------------------");
	    System.out.println(fileName);
	    System.out.println("%Name1 = "+ String.format("%.5f", (double) count_pos1 / count_total));
	    System.out.println("%Name2 = "+ String.format("%.5f", (double) count_pos2 / count_total));
	    System.out.println("%Missing = "+ String.format("%.5f",(double) (count_total-count_pos1-count_pos2-count_both-count_other)/count_total));
	    System.out.println("%Both = "+ String.format("%.5f", (double) count_both / count_total));
	    System.out.println("%Other = "+ String.format("%.5f", (double) count_other / count_total));
	    System.out.println("-------------------------------------------");

	  
	}
  
  
  for (String fileName : fileNames2){
		File inputFile = new File("src/"+ fileName+".txt"); //need to change: choose the correct function based on the template type

		/*  finish selecting file      */
		Scanner myReader = new Scanner(inputFile);
		
		int count_total = 0;
		int count_pos1 = 0;
		int count_pos2 = 0;
		int count_both = 0;
		int count_other = 0;
		
		while (myReader.hasNextLine()) {

			
			String data = myReader.nextLine();
			
			/* Run model  *******/
		    byte result = getCorefResult_ECS(data, pipeline); //need to change: choose the correct function based on the template type
		    /* *finish running the model**/
		    
		    count_total += 1;
		   
		    if (result == 1) {
		    	count_pos1 += 1;
		    } else if (result == 2) {
		    	count_pos2 += 1;
		    } else if (result == 3) {
		    	count_both += 1;
		    } else if (result == 4) {
		    	count_other += 1;
		    }
		   
		}
		
	    myReader.close();
	    
	    // output
	    System.out.println("-------------------------------------------");
	    System.out.println(fileName);
	    System.out.println("%Name1 = "+ String.format("%.5f", (double) count_pos1 / count_total));
	    System.out.println("%Name2 = "+ String.format("%.5f", (double) count_pos2 / count_total));
	    System.out.println("%Missing = "+ String.format("%.5f",(double) (count_total-count_pos1-count_pos2-count_both-count_other)/count_total));
	    System.out.println("%Both = "+ String.format("%.5f", (double) count_both / count_total));
	    System.out.println("%Other = "+ String.format("%.5f", (double) count_other / count_total));
	    System.out.println("-------------------------------------------");

	  
	}
  
  
  for (String fileName : fileNames3){
		File inputFile = new File("src/"+ fileName+".txt"); //need to change: choose the correct function based on the template type

		/*  finish selecting file      */
		Scanner myReader = new Scanner(inputFile);
		
		int count_total = 0;
		int count_pos1 = 0;
		int count_pos2 = 0;
		int count_both = 0;
		int count_other = 0;
		
		while (myReader.hasNextLine()) {

			
			String data = myReader.nextLine();
			
			/* Run model  *******/
		    byte result = getCorefResult_IC(data, pipeline); //need to change: choose the correct function based on the template type
		    /* *finish running the model**/
		    
		    count_total += 1;
		   
		    if (result == 1) {
		    	count_pos1 += 1;
		    } else if (result == 2) {
		    	count_pos2 += 1;
		    } else if (result == 3) {
		    	count_both += 1;
		    } else if (result == 4) {
		    	count_other += 1;
		    }
		   
		}
		
	    myReader.close();
	    
	    // output
	    System.out.println("-------------------------------------------");
	    System.out.println(fileName);
	    System.out.println("%Name1 = "+ String.format("%.5f", (double) count_pos1 / count_total));
	    System.out.println("%Name2 = "+ String.format("%.5f", (double) count_pos2 / count_total));
	    System.out.println("%Missing = "+ String.format("%.5f",(double) (count_total-count_pos1-count_pos2-count_both-count_other)/count_total));
	    System.out.println("%Both = "+ String.format("%.5f", (double) count_both / count_total));
	    System.out.println("%Other = "+ String.format("%.5f", (double) count_other / count_total));
	    System.out.println("-------------------------------------------");

	  
	}
	
  }
	
}