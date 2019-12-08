package src.com.se4367.agents;

import java.util.*;

public class InvarianceTester {
	
	private static SortedSet<String> localVar;
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
	public String uninitialized() {
		if(localVar.first() == null){
			return "Uninitialized";
		}
		return "Initialized";
	}
	
	//is the variable a part of a small set
	public static void smallSet() {
		
	}
	
	
	//Single Numeric Variables
	//Does the variable always fall within a range between x and y
	public static void inRange() {
		
	}
	
	//Is the value always non-zero
	public static void nonZero() {
		
	}
	
	//Is the variable x == a mod(b) or is x != a mod(b)
	public static void modulus() {
		
	}
	
	
	
	
	

}
