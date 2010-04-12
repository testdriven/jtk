package org.testdriven.jtk;

import java.util.Stack;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

public class TestCaseClassVisitor extends EmptyVisitor {

	private final Stack<String> methods;
	private final MethodVisitor methodVisitor;

	public TestCaseClassVisitor(Stack<String> methods,
			MethodVisitor methodVisitor) {
		this.methods = methods;
		this.methodVisitor = methodVisitor;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		methods.push(name);
		return methodVisitor;
	}
}
