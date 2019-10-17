package com.se4367.agents;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String mName;
	int line;
	
    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName=name;
    }

    // method coverage collection
    @Override
    public void visitCode(){
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn(mName+" executed");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	super.visitCode();
    }
    
    
    //visit label
    @Override
    public void visitLabel(Label label) {
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn("line " + line + "  executed");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	super.visitLabel(label);
    }
    
    
    //visit line
    public void visitLineNumber(int linenumber, Label start) {
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn("line " + linenumber + " executed");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	super.visitLineNumber(linenumber, start);
    	line = linenumber;
    }
}	