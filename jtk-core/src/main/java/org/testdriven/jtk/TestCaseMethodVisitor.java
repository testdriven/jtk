package org.testdriven.jtk;

import java.util.List;
import java.util.Stack;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.commons.EmptyVisitor;

public class TestCaseMethodVisitor extends EmptyVisitor {

	private final Stack<Integer> methodLines;
	private final ByteCodeAnalyzer byteCodeAnalyzer;

	public TestCaseMethodVisitor(ByteCodeAnalyzer byteCodeAnalyzer) {
		this.byteCodeAnalyzer = byteCodeAnalyzer;
		this.methodLines = new Stack<Integer>();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, boolean visible) {
		String methodName = byteCodeAnalyzer.getMethodName();
		if (name.contains(ByteCodeAnalyzer.JUNIT4_TEST_ANNOTATION)) {
			TestCaseMethod testCaseMethod = new TestCaseMethod(methodName);
			byteCodeAnalyzer.addTestCaseMethod(testCaseMethod);
		}
		return super.visitAnnotation(name, visible);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {

		if (byteCodeAnalyzer.hasTestCaseMehtods()) {
			if (owner.equals("org/fest/assertions/Assertions")
					|| owner.equals("org/junit/Assert")) {

				TestCaseAssertion testCaseAssertion = new TestCaseAssertion(
						owner, methodLines.peek());
				byteCodeAnalyzer.addAssertion(testCaseAssertion);

			}
		}
	}

	@Override
	public void visitLineNumber(int arg0, Label arg1) {
		methodLines.add(arg0);
	}

}
