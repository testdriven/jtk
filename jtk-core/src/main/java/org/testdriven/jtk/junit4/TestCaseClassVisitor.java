package org.testdriven.jtk.junit4;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

public class TestCaseClassVisitor extends EmptyVisitor {

	private final JUnit4AnalisysEngine byteCodeAnalyzer;
	private final MethodVisitor methodVisitor;

	public TestCaseClassVisitor(JUnit4AnalisysEngine byteCodeAnalyzer,
			MethodVisitor methodVisitor) {
		this.byteCodeAnalyzer = byteCodeAnalyzer;
		this.methodVisitor = methodVisitor;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		byteCodeAnalyzer.addMethodName(name);
		return methodVisitor;
	}
}
