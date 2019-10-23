package src.com.se4367.agents;

import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class CoverageCollection {
	
	public static Object2ObjectOpenHashMap<String, Object2ObjectOpenHashMap<String, IntSet>> testCase_Coverages;
	public static Object2ObjectOpenHashMap<String, IntSet> coverage;
	public static String testCase;
	
	public static void addMethodLine(String className, Integer line){
    	if (coverage == null) {
    		return;
    	}
    	
    	IntSet lines = coverage.get(className);
        if (lines != null) {
        	lines.add(line);
        }
        else {
        	lines = new IntOpenHashSet(new int[]{line});
            coverage.put(className, lines);
        }
    }

}
