package com.se4367.agents;

import java.io.*;
import java.util.Arrays;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitListener extends RunListener {

	
    public void testRunStarted(Description description) throws Exception {
    	
    	if (null == CoverageCollection.testCase_Coverages)
		{
			CoverageCollection.testCase_Coverages = new Object2ObjectOpenHashMap<String, Object2ObjectOpenHashMap<String, IntSet>>();
		}
    	System.out.println("Starting test...");
    }
    
    public void testStarted(Description description) {
    	
    	CoverageCollection.testCase = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
    	CoverageCollection.coverage = new Object2ObjectOpenHashMap<String, IntSet>();
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
        	
        	Object2ObjectOpenHashMap<String, IntSet> caseCoverage = 
        			CoverageCollection.testCase_Coverages.get(testCaseName);
        	
            for (String className : caseCoverage.keySet()) {
            	int[] lines = caseCoverage.get(className).toIntArray();
            	
            	Arrays.sort(lines);
            	for (int i = 0; i < lines.length; i++) {
                	builder.append(className + ":" + lines[i] + "\n");
				}
            }
    }

    public void testFailure(Failure failure) throws Exception {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testAssumptionFailure(Failure failure) {
        System.out.println("Failed: " + failure.getDescription().getMethodName());
    }

    public void testIgnored(Description description) throws Exception {
        System.out.println("Ignored: " + description.getMethodName());
    }
}