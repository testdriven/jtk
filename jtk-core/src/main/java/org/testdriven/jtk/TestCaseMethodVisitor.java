package org.testdriven.jtk;

import java.util.List;
import java.util.Stack;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.commons.EmptyVisitor;
import static java.lang.String.format;

public class TestCaseMethodVisitor extends EmptyVisitor {

	private final Stack<String> methods;
	private final List<TestCaseMethod> testCaseMethods;

	public TestCaseMethodVisitor(Stack<String> methods,
			List<TestCaseMethod> testCaseMethods) {
		this.methods = methods;
		this.testCaseMethods = testCaseMethods;
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
	public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
		return super.visitAnnotation(arg0, arg1);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {

		if (!testCaseMethods.isEmpty()) {
			if (owner.equals("org/fest/assertions/Assertions")
					|| owner.equals("org/junit/Assert")) {

				testCaseMethods.get(testCaseMethods.size() - 1).addAssertion(
						new TestCaseAssertion());
			}
		}
	}
}
