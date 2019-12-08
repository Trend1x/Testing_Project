package src.com.se4367.agents;

import java.util.*;

public class InvarianceTester {
	
	private SortedSet<String> localVar;
	private ArrayList<String> invariantPatterns;

	//Constructor 
	// ?? use constructor as landing pad to check all the patterns??
	public InvarianceTester(SortedSet<String> lv){
		localVar = lv;
		invariantPatterns = new ArrayList<String>();
		//System.out.println("InvarianaceTester called."); //debug line to verify execution
		constantValue();
		uninitialized();
		smallSet();
		nonZero();
		inRange();
	}
	
	public ArrayList<String> getPatterns(){
		return invariantPatterns;
	}
	//Single Variables
	//check to see if the variable is always a constant value
	public  void constantValue() {
		if(localVar.size() == 1){
			invariantPatterns.add("Constant value pattern" + localVar);
		}
	}
	
	//Uninitialized Value, has the variable been initialized or is it NULL
	public void uninitialized() {
		if(localVar.size() == 1 && localVar.first().equals("null")){
			invariantPatterns.add("Uninitialized pattern");
		}
	}
	
	//is the variable a part of a small set
	public void smallSet() {
		if(localVar.size() <= 10)
			invariantPatterns.add("Small set pattern" + localVar);
	}
	
	
	//Single Numeric Variables
	//Does the variable always fall within a range between x and y
	public void inRange() {
		
		if(isAllNum() && localVar.size() > 1) {
			SortedSet<Double> range = new TreeSet<Double>();
			for(String var: localVar) {
				range.add(Double.valueOf(var));
			}
			invariantPatterns.add("Range: [" + range.first() + ", " + range.last() + "]");			
		}		
	}
	
	//Is the value always non-zero
	public void nonZero() {
		if (!(localVar.contains("0")) && !(localVar.size() ==1 && localVar.first() == "null")) {
			invariantPatterns.add("Non-zero pattern");
		}
			
	}
	
	//Is the variable x == a mod(b) or is x != a mod(b)
	public void modulus() {
		
	}
	
	public boolean isAllNum() {
		for(String var: localVar) {
			if(var.equals("null") || var.equals("Object"))
				return false;				
		}
		return true;
	}
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append("Candidate invariant patterns:\n");
		for(String pattern: invariantPatterns) {
			output.append(pattern + "\n");
		}
		return output.toString();
	}
}
