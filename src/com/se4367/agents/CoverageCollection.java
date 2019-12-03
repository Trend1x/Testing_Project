package com.se4367.agents;

import java.util.HashMap; 
import java.util.HashSet;
import java.util.ArrayList;
public class CoverageCollection {
	
	public static HashMap<String, HashMap<String, HashSet<Integer>>> testCase_Coverages;
	public static HashMap<String, HashSet<Integer>> coverage;
	public static String testCase;
	
	public static HashMap<String, HashMap<Integer, ArrayList<Object>>> localVar_Coverages;
	//public static HashMap<Integer, ArrayList<Object>> localVar_Values;
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
	
	public static void addVarCoverage(int index, Object localVar) {
		
		HashMap<Integer, ArrayList<Object>> varCoverage = localVar_Coverages.get(methodName);
		ArrayList<Object> varValue = varCoverage.get(index);
		if (varValue != null) {
			varValue.add(localVar);
		}
		else {
			varValue = new ArrayList<Object>();
			varValue.add(localVar);
			varCoverage.put(index, varValue);
		}
	}
	
	public static void setMethodName(String mName) {
		methodName = mName;
	}
	public static void addLocalVar(int local) {
		//numLocal.add(local);
		System.out.println("Local integer variable: " + local);
	}
	public static void addLocalVar(long local) {
		//numLocal.add(local);
		System.out.println("Local Long variable: " + local);
	}
	public static void addLocalVar(double local) {
		//numLocal.add(local);
		System.out.println("Local double variable: " + local);
	}
	public static void addLocalVar(float local) {
		//numLocal.add(local);
		System.out.println("Local float variable: " + local);
	}
	public static void addLocalVar(Object local) {
		//numLocal.add(local);
		if(local == null)
			System.out.println("Local object variable: NULL");
		else {
			String localClass = local.getClass().getName();
			if(localClass.equals("java.lang.String")) 
				System.out.println("Local string variable:" + local);
			else
				System.out.println("Local object variable: " + localClass);
		}			
	}
	

}
