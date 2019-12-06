package com.se4367.agents;

import java.util.HashMap; 
import java.util.HashSet;
import java.util.ArrayList;
public class CoverageCollection {
	
	public static HashMap<String, HashMap<String, HashSet<Integer>>> testCase_Coverages;
	public static HashMap<String, HashSet<Integer>> coverage;
	public static String testCase;
	
	public static HashMap<String, HashMap<Integer, ArrayList<String>>> localVar_Coverages;
	public static String methodName;
	
	public static void addMethodLine(String className, Integer line){
    	//return if no test has been started
		if (coverage == null) {
    		return;
    	}
    	
		//create a new integer hash set to store the visited line #s
    	HashSet<Integer> lines = new HashSet<Integer>();
    	
    	//get the hashset of ints stored in the coverage hashmap. 
    	//if the set is not empty just add the new line, else initialize a new set and add the lines. 
    	lines = coverage.get(className);
        if (lines != null) {
        	lines.add(line);
        }
        else {
        	lines = new HashSet<Integer>();
        	lines.add(line);
            coverage.put(className, lines);
        }
    }
	
	public static void addVarCoverage(int index, String localVar) {
		HashMap<Integer, ArrayList<String>> varCoverage = localVar_Coverages.get(methodName);
		if (varCoverage == null) {
			varCoverage = new HashMap<Integer, ArrayList<String>>();
			localVar_Coverages.put(methodName, varCoverage);
		}
		ArrayList<String> varValue = varCoverage.get(index);
		if (varValue != null) {
			varValue.add(localVar);
		}
		else {
			varValue = new ArrayList<String>();
			varValue.add(localVar);
			varCoverage.put(index, varValue);
		}
	}
	
	public static void setMethodName(String mName) { methodName = mName; }
	
	public static void addLocalVar(int index, int    local) { addVarCoverage(index, Integer.toString(local)); }	
	public static void addLocalVar(int index, long   local) { addVarCoverage(index, Long.toString(local)); }
	public static void addLocalVar(int index, double local) { addVarCoverage(index, Double.toString(local)); }
	public static void addLocalVar(int index, float  local) { addVarCoverage(index, Float.toString(local)); }
	public static void addLocalVar(int index, Object local) {

		if(local == null)
			addVarCoverage(index, "null");
		else {
			addVarCoverage(index, "Object");
		}			
	}
}
