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
	private final List<String> assertionsFilter;
	// stack of all visited methods names
	private final Stack<String> methodNames = new Stack<String>();
	// list of all found test case methods
	private final List<TestCaseMethod> testCaseMethods = new ArrayList<TestCaseMethod>();

	public ByteCodeAnalyzer(InputStream inputStream,
			List<String> assertionsFilter) {
		this.inputStream = inputStream;
		this.assertionsFilter = assertionsFilter;
	}

	public TestCaseMethod[] getTestMethods() throws IOException {
		ClassReader reader = new ClassReader(inputStream);

		final MethodVisitor methodVisitor = new TestCaseMethodVisitor(this);
		final ClassVisitor classVisitor = new TestCaseClassVisitor(this,
				methodVisitor);

		reader.accept(classVisitor, 0);

		return testCaseMethods.toArray(new TestCaseMethod[testCaseMethods.size()]);
	}

	public List<String> getAssertionsFilter() {
		return assertionsFilter;
	}

	public String getMethodName() {
		return methodNames.pop();
	}

	public void addTestCaseMethod(TestCaseMethod testCaseMethod) {
		testCaseMethods.add(testCaseMethod);

	}

	public boolean hasTestCaseMehtods() {
		return !testCaseMethods.isEmpty();
	}

	public void addAssertion(TestCaseAssertion testCaseAssertion) {
		testCaseMethods.get(testCaseMethods.size() - 1).addAssertion(
				testCaseAssertion);
	}

	public void addMethodName(String name) {
		methodNames.push(name);
	}

}
