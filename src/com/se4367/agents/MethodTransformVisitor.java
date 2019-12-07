package com.se4367.agents;

import java.util.ArrayList;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String mName;
	String methodname;
	String desc;
	String stat;
	boolean isStatic;
	ArrayList<Integer> localIndex;
    public MethodTransformVisitor(final MethodVisitor mv, String name, String methodname, String desc, int access) {
        super(ASM5, mv);
        this.mName=name;
        this.methodname = methodname;
        this.desc = desc;
        this.isStatic = (access & Opcodes.ACC_STATIC) != 0;
        if (isStatic)
        	stat = "Static";
        else
        	stat = "Nonstatic";	
        this.localIndex = new ArrayList<Integer>();
    }
    
    public void visitCode() {
    	//CoverageCollection.setMethodName(mName);
    	mv.visitLdcInsn(mName + "\n" + stat +" " + methodname + " " + desc);
    	mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "setMethodName", "(Ljava/lang/String;)V", false);
    	super.visitCode();
    }
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
    
    public void visitVarInsn(int opcode, int var) {
    	
        if (isLoadOp(opcode) && !(localIndex.contains(var))) {
        	
        	localIndex.add(var);
        	if(var != 0 || isStatic) {
        		mv.visitLdcInsn(var);  		
        		mv.visitVarInsn(opcode, var);       		
        		extractLocalVar(opcode);
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
            //case ALOAD:
                return true;
            default:
                return false;
        }
    }
    
    private void extractLocalVar(int opcode) {
    	switch (opcode) {
        case ILOAD: 
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(II)V", false);
            break;
        case LLOAD: 
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(IJ)V", false);
            break;
        case FLOAD: 
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(IF)V", false);
            break;
        case DLOAD: 
            mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(ID)V", false);
            break;
        case ALOAD: 
        	mv.visitMethodInsn(INVOKESTATIC, "com/se4367/agents/CoverageCollection", "addLocalVar", "(ILjava/lang/Object;)V", false);
            break;
        default:
            throw new RuntimeException("Invalid LOAD code: " + opcode);
    	}
    }
    
    //lets the visitor know they have reached the end of the method
    @Override
    public void visitEnd() 
    {
    	super.visitEnd();
    }
}	