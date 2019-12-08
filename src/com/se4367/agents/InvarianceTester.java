package src.com.se4367.agents;

import java.util.*;

public class InvarianceTester {
	
	private static ArrayList<String> localVar;

	//Constructor 
	// ?? use constructor as landing pad to check all the patterns??
	public InvarianceTester(ArrayList<String> lv){
		localVar = lv;
		//System.out.println("InvarianaceTester called."); //debug line to verify execution
	}
	
	//Single Variables
	//check to see if the variable is always a constant value
	public String constantValue() {
		String first = localVar.get(0);
		for(String e : localVar){
			if(!e.equals(first)){
				return "Not Constant Value";
			}
		}
		return "Possible Constant Value";
	}
	
	//Uninitialized Value, has the variable been initialized or is it NULL
	public static void uninitialized(ArrayList<?> objs) {
		
	}
	
	//is the variable a part of a small set
	public static void smallSet(ArrayList<?> objs) {
		
	}
	
	
	//Single Numeric Variables
	//Does the variable always fall within a range between x and y
	public static void inRange(ArrayList<?> objs) {
		
	}
	
	//Is the value always non-zero
	public static void nonZero(ArrayList<?> objs) {
		
	}
	
	//Is the variable x == a mod(b) or is x != a mod(b)
	public static void modulus(ArrayList<?> objs) {
		
	}
	
	
	
	
	

}
