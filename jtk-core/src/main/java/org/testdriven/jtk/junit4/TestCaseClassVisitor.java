package org.testdriven.jtk.junit4;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TestCaseClassVisitor extends ClassVisitor {

    private final JUnit4AnalisysContext analisys;
    private final MethodVisitor methodVisitor;

    public TestCaseClassVisitor(JUnit4AnalisysContext byteCodeAnalyzer,
            MethodVisitor methodVisitor) {
    	super(Opcodes.ASM4);
        this.analisys = byteCodeAnalyzer;
        this.methodVisitor = methodVisitor;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
            String signature, String[] exceptions) {

        analisys.setCurrentMethodName(name);
        return methodVisitor;
    }
}
