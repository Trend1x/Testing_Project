package com.se4367.agents;

import java.util.ArrayList;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String mName;
	ArrayList<Integer> localIndex;
    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName=name;
        this.localIndex = new ArrayList<Integer>();
    }
    
    /*public void visitCode() {
    	//CoverageCollection.setMethodName(mName);
    	//mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(Ljava/lang/Object;)V", false);
    	super.visitCode();
    }*/
    //visits line of code along the path of the called method and parameters
    public void visitLineNumber(int line, Label start) {
    	if (0 != line) {
	    	
			mv.visitLdcInsn(mName);
			mv.visitLdcInsn(line);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
			mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addMethodLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);

    	}
    	super.visitLineNumber(line, start);
    }
    
    /*public void visitLocalVariable(java.lang.String name, java.lang.String descriptor, java.lang.String signature, Label start, Label end, int index){
    	mv.visitLdcInsn(name);
    	mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "printLocalVarName", "(Ljava/lang/String;)V", false);
    	super.visitLocalVariable(name, descriptor, signature, start, end, index);
    }*/
    
    /*public void visitFrame(int type, int numLocal, java.lang.Object[] local, int numStack, java.lang.Object[] stack){
    	
    	mv.visitLdcInsn(numLocal);
    	mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
    	mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(Ljava/lang/Integer;)V", false);
    	
    	super.visitFrame(type, numLocal, local, numStack, stack);
    }*/
    
    public void visitVarInsn(int opcode, int var) {
        if (isLoadOp(opcode) && !(localIndex.contains(var))) {
        	localIndex.add(var);
            //mv.visitLdcInsn(var);
        	if(var != 0) {      
        		mv.visitLdcInsn(var);  		
        		mv.visitVarInsn(opcode, var);       		
        		extractLocalVar(opcode);
    			//mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
                //mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", getMethodDesc(opcode), false);
        	}
        	
        }
        super.visitVarInsn(opcode, var);
    }
    
    private boolean isLoadOp(int opcode) {
        switch (opcode) {
            case ILOAD:
            case LLOAD:
            case FLOAD:
            case DLOAD:
            case ALOAD:
                return true;
            default:
                return false;
        }
    }
    
    private void extractLocalVar(int opcode) {
    	switch (opcode) {
        case ILOAD: 
        	//mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(II)V", false);
            break;
        case LLOAD: 
        	//mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);	
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(IJ)V", false);
            break;
        case FLOAD: 
        	//mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(IF)V", false);
            break;
        case DLOAD: 
        	//mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(ID)V", false);
            break;
        case ALOAD: 
        	//mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        	mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(ILjava/lang/Object;)V", false);
            break;
        default:
            throw new RuntimeException("Invalid store code: " + opcode);
    	}
    }
    
    /*private int getLoadInsn(int opcode) {
        switch (opcode) {
            case ISTORE: return ILOAD;
            case LSTORE: return LLOAD;
            case FSTORE: return FLOAD;
            case DSTORE: return DLOAD;
            case ASTORE: return ALOAD;
            default:
                throw new RuntimeException("Invalid store code: " + opcode);
        }
    }*/
    
    /*private String getMethodDesc(int opcode) {
    	switch (opcode) {
        case ILOAD: return "(Ljava/lang/Integer;)V";
        case LLOAD: return "(Ljava/lang/Long;)V";
        case FLOAD: return "(Ljava/lang/Float;)V";
        case DLOAD: return "(Ljava/lang/Double;)V";
        case ALOAD: return "(Ljava/lang/Object;)V";
        default:
            throw new RuntimeException("Invalid store code: " + opcode);
    	}
    }*/
    
    //lets the visitor know they have reached the end of the method
    @Override
    public void visitEnd() 
    {
    	super.visitEnd();
    }
}	