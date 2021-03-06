package com.se4367.agents;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class TestingClassFileTransformer implements ClassFileTransformer{

		public byte[] transform(ClassLoader classloader, String s, Class<?> someClass, ProtectionDomain protectionDomain, byte[] bytes)throws IllegalClassFormatException {
			

			//Read and change the bytecode if it belongs to the package being tested. Returns the original bytecode if it does not match the package.
			if(s.startsWith("org/apache/commons/dbutil") || s.startsWith("org/joda/time")){
				ClassReader cr = new ClassReader(bytes);
				ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
				ClassTransformVisitor ca = new ClassTransformVisitor(cw);
				cr.accept(ca, 0);
				return cw.toByteArray();
			}
			return bytes;
		}
}
