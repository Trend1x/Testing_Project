package com.se4367.agents;


import java.lang.instrument.Instrumentation;




public class Agent{
	public static void premain(String agentArgs, Instrumentation inst){
		

		inst.addTransformer(new TestingClassFileTransformer());

	}
}