package org.testdriven.jtk.junit4;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.testdriven.jtk.TestCaseAssertion;
import org.testdriven.jtk.TestCaseMethod;

public class TestCaseMethodVisitor extends MethodVisitor {

	private final JUnit4AnalisysContext analisys;
	private int currentLineNumber;

	public TestCaseMethodVisitor(JUnit4AnalisysContext analisys) {
		super(Opcodes.ASM4);
		this.analisys = analisys;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, boolean visible) {
		String methodName = analisys.getCurrentMethodName();
		if (name.contains(JUnit4AnalisysEngine.JUNIT4_TEST_ANNOTATION)) {
			TestCaseMethod testCaseMethod = new TestCaseMethod(methodName,
					currentLineNumber);
			analisys.addTestCaseMethod(testCaseMethod);
		}
		return super.visitAnnotation(name, visible);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {

		if (analisys.matchesAssertionsFilter(owner)) {

			TestCaseAssertion testCaseAssertion = new TestCaseAssertion(
					owner.replace("/", "."), currentLineNumber);
			analisys.addAssertion(testCaseAssertion);

		}

	}

	@Override
	public void visitLineNumber(int lineNumber, Label arg1) {
		this.currentLineNumber = lineNumber;
	}
}
