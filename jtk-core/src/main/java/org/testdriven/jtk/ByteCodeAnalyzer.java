package org.testdriven.jtk;

import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

public class ByteCodeAnalyzer {

	private static final String JUNIT4_TEST_ANNOTATION = "org/junit/Test";
	private final InputStream inputStream;

	public ByteCodeAnalyzer(InputStream inputStream) {
		this.inputStream = inputStream;

	}

	public TestCaseMethod[] getTestMethods() throws IOException {
		ClassReader reader = new ClassReader(inputStream);

		final Stack<String> methods = new Stack<String>();
		final List<TestCaseMethod> testMethodsNames = new ArrayList<TestCaseMethod>();

		final MethodVisitor methodVisitor = new TestCaseMethodVisitor(methods,
				testMethodsNames);
		final ClassVisitor classVisitor = new TestCaseClassVisitor(methods,
				methodVisitor);

		reader.accept(classVisitor, 0);

		return testMethodsNames.toArray(new TestCaseMethod[methods.size()]);
	}

	private class TestCaseMethodVisitor extends EmptyVisitor {

		private final Stack<String> methods;
		private final List<TestCaseMethod> testCaseMethods;

		public TestCaseMethodVisitor(Stack<String> methods,
				List<TestCaseMethod> testCaseMethods) {
			this.methods = methods;
			this.testCaseMethods = testCaseMethods;
		}

		@Override
		public AnnotationVisitor visitAnnotation(String name, boolean visible) {
			System.out.println(format("visitAnnotation(%s,%b)", name, visible));
			String methodName = methods.pop();
			if (name.contains(JUNIT4_TEST_ANNOTATION)) {
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
			System.out.println(owner + " " + name + " " + desc);

			if (!testCaseMethods.isEmpty()) {
				if (owner.equals("org/fest/assertions/Assertions")) {

					testCaseMethods.get(testCaseMethods.size() - 1)
							.addAssertion(new TestCaseAssertion());
				}
			}
		}

	}

	private static class TestCaseClassVisitor extends EmptyVisitor {

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
			System.out.println(format("visitMethod(%d,%s,%s,%s,%s)", access,
					name, desc, signature, exceptions));
			methods.push(name);
			return methodVisitor;
		}
	}
}
