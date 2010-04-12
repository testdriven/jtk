package org.testdriven.jtk;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ByteCodeAnalyzer {

	public static final String JUNIT4_TEST_ANNOTATION = "org/junit/Test";
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

}
