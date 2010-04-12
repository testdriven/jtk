package org.testdriven.jtk;

import java.util.List;
import java.util.Stack;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.commons.EmptyVisitor;

public class TestCaseMethodVisitor extends EmptyVisitor {

	private final Stack<String> methods;
	private final Stack<Integer> methodLines;
	private final List<TestCaseMethod> testCaseMethods;

	public TestCaseMethodVisitor(Stack<String> methods,
			List<TestCaseMethod> testCaseMethods) {
		this.methods = methods;
		this.testCaseMethods = testCaseMethods;
		this.methodLines = new Stack<Integer>();
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, boolean visible) {
		String methodName = methods.pop();
		if (name.contains(ByteCodeAnalyzer.JUNIT4_TEST_ANNOTATION)) {
			testCaseMethods.add(new TestCaseMethod(methodName));
		}
		return super.visitAnnotation(name, visible);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {

		if (!testCaseMethods.isEmpty()) {
			if (owner.equals("org/fest/assertions/Assertions")
					|| owner.equals("org/junit/Assert")) {

				testCaseMethods.get(testCaseMethods.size() - 1).addAssertion(
						new TestCaseAssertion(owner,methodLines.peek()));
			}
		}
	}

	@Override
	public void visitLineNumber(int arg0, Label arg1) {
		System.out.println("visitLineNumber " + arg0);
		methodLines.add(arg0);
	}

}
