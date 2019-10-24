package com.se4367.agents;

import java.util.HashMap; 
import java.util.Map;
import java.util.HashSet;

public class CoverageCollection {
	
	public static HashMap<String, HashMap<String, HashSet<Integer>>> testCase_Coverages;
	public static HashMap<String, HashSet<Integer>> coverage;
	public static String testCase;
	
	
	public static void addMethodLine(String className, Integer line){
    	if (coverage == null) {
    		return;
    	}
    	
    	HashSet<Integer> lines = new HashSet<Integer>();
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

}
