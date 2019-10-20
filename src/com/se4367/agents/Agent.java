package com.se4367.agents;

//asm libraries
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

//java libraries 
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;



public class Agent{
	public static void premain(String agentArgs, Instrumentation inst){
		inst.addTransformer(new ClassFileTransformer() {
			@Override
			public byte[] transform(ClassLoader classloader, String s, Class<?> someClass, ProtectionDomain protectionDomain, byte[] bytes)throws IllegalClassFormatException {
				
				if(s.contentEquals("org.apache.commons.db\r\n" + 
						"utils")) {
					ClassReader cr = new ClassReader(bytes);
					ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
					ClassTransformVisitor ca = new ClassTransformVisitor(cw);
					cr.accept(ca, 0);
					return cw.toByteArray();
				}
				return bytes;
			}
		});

	}
}