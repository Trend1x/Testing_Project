package com.se4367.agents;

import java.io.*;

import java.util.HashMap; 
import java.util.HashSet;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Iterator;
import java.util.ArrayList;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;



public class JUnitListener extends RunListener {

	private static int i = 1;

    public void testRunStarted(Description description) throws Exception {
    		

    	if (null == CoverageCollection.testCase_Coverages){
			CoverageCollection.testCase_Coverages = new HashMap<String, HashMap<String, HashSet<Integer>>>();
		}
    	if (null == CoverageCollection.localVar_Coverages) {
    		CoverageCollection.localVar_Coverages = new HashMap<String, HashMap<Integer, ArrayList<String>>>();
    	}
    	System.out.println("Starting tests...");
    }
    
    public void testStarted(Description description) {
    	
    	CoverageCollection.testCase = "[TEST] " + description.getClassName() + ":" + description.getMethodName() + ":" + i;
    	CoverageCollection.coverage = new HashMap<String, HashSet<Integer>>();
    	i++;
    }

    public void testFinished(Description description) throws Exception {
    	
    	CoverageCollection.testCase_Coverages.put(CoverageCollection.testCase, CoverageCollection.coverage);

    }

    public void testRunFinished(Result result) throws Exception {

    	System.out.println("Testing Finished.\n\n");
        
        File fout = new File("stmt-cov.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        StringBuilder builder = new StringBuilder();
        for (String testCaseName : CoverageCollection.testCase_Coverages.keySet()) {
        	builder.append(testCaseName + "\n");
        	
        	HashMap<String, HashSet<Integer>> caseCoverage = 
        			CoverageCollection.testCase_Coverages.get(testCaseName);
        	
            for (String className : caseCoverage.keySet()) {
            	HashSet<Integer> lines = caseCoverage.get(className);
            	
            	Iterator<Integer> i = lines.iterator();
            	while(i.hasNext()){
                	builder.append(className + ":" + i.next() + "\n");
				}
            }
        }
        bw.write(builder.toString());
        bw.close();
        
        fout = new File("local_vars.txt");
        fos = new FileOutputStream(fout);
        bw = new BufferedWriter(new OutputStreamWriter(fos));

        builder = new StringBuilder();
        for (String methodName : CoverageCollection.localVar_Coverages.keySet()) {
        	builder.append(methodName + "\n");
        	
        	HashMap<Integer, ArrayList<String>> localVar = 
        			CoverageCollection.localVar_Coverages.get(methodName);
        	
            for (Integer index : localVar.keySet()) {
            	ArrayList<String> vars = localVar.get(index);
            	SortedSet<String> setVars = new TreeSet<String>();
            	for (String localvar: vars) {
            		setVars.add(localvar);
            	}
            	builder.append("Index: " + index + "\n" + vars + "\nSet: " + setVars + "\n");
            	
            }
            builder.append("\n\n");
        }        
        bw.write(builder.toString());
        bw.close();
    }

    public void testFailure(Failure failure) {
    	CoverageCollection.testCase = "[TEST FAILED] " + failure.getDescription().getClassName() + ":" + failure.getDescription().getMethodName();
    	CoverageCollection.testCase_Coverages.put(CoverageCollection.testCase, CoverageCollection.coverage);
    }
    
    public void testAssumptionFailure(Failure failure) {
    	CoverageCollection.testCase = "[TEST ASSUMPTION FAILED] " + failure.getDescription().getClassName() + ":" + failure.getDescription().getMethodName();
    	CoverageCollection.testCase_Coverages.put(CoverageCollection.testCase, CoverageCollection.coverage);
    }
}