package src.com.se4367.agents;

import java.util.*;

public class InvarianceTester {
	
	private static TreeSet<String> localVar;
	private HashMap<String, ArrayList<String>> invariantPatterns;

	//Constructor 
	// ?? use constructor as landing pad to check all the patterns??
	public InvarianceTester(SortedSet<String> lv){
		localVar = lv;
		//System.out.println("InvarianaceTester called."); //debug line to verify execution
	}
	
	//Single Variables
	//check to see if the variable is always a constant value
	public void constantValue() {
		if(localVar.size() == 1){
			invariantPatterns.put("Constant value pattern", localVar);
		}
	}
	
	//Uninitialized Value, has the variable been initialized or is it NULL
	public void uninitialized() {
		if(localVar.size() == 1 && localVar.first().equals("null")){
			invariantPatterns.put("Uninitialized pattern", localVar);
		}
	}
	
	//is the variable a part of a small set
	public static void smallSet() {
		if(localVar.size() <= 10)
			invariantPatterns.put("Small set pattern", localVar);
	}
	
	
	//Single Numeric Variables
	//Does the variable always fall within a range between x and y
	public static void inRange() {
		
	}
	
	//Is the value always non-zero
	public static void nonZero() {
		if (!(localVar.contains("0"))) {
			invariantPatterns.put("Non-zero pattern", localVar);
		}
			
	}
	
	//Is the variable x == a mod(b) or is x != a mod(b)
	public static void modulus() {
		
	}
	
	
	
	
	

}
