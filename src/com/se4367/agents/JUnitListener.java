package com.se4367.agents;

import java.io.*;
import java.util.Arrays;

import java.util.HashMap; 
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.Failure;


public class JUnitListener extends RunListener {

	
    public void testRunStarted(Description description) throws Exception {
    	
    	if (null == CoverageCollection.testCase_Coverages)
		{
			CoverageCollection.testCase_Coverages = new HashMap<String, HashMap<String, HashSet<Integer>>>();
		}
    	System.out.println("Starting test...");
    }
    
    public void testStarted(Description description) {
    	
    	CoverageCollection.testCase = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
    	CoverageCollection.coverage = new HashMap<String, HashSet<Integer>>();
    }

    public void testFinished(Result result) throws Exception {
    	CoverageCollection.testCase_Coverages.put(CoverageCollection.testCase, CoverageCollection.coverage);
    }

    public void testRunFinished(Description description) throws Exception {

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
    }

 /*   public void testFailure(Failure failure) throws Exception {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testAssumptionFailure(Failure failure) {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testIgnored(Description description) throws Exception {
        System.out.println("Ignored: " + description.getMethodName());
    }
*/}