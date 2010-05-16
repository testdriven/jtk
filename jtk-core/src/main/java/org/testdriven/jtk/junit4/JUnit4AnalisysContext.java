package org.testdriven.jtk.junit4;

import java.util.*;
import org.testdriven.jtk.TestCaseAssertion;
import org.testdriven.jtk.TestCaseMethod;

public class JUnit4AnalisysContext {

	// list of all found test case methods
	private final List<TestCaseMethod> testCaseMethods = new ArrayList<TestCaseMethod>();
	private final List<String> assertionsFilter;
	private String methodName;

	public JUnit4AnalisysContext(List<String> assertionsFilter) {
		this.assertionsFilter = assertionsFilter;
	}

	public TestCaseMethod[] getTestMethods() {
		return testCaseMethods.toArray(new TestCaseMethod[] {});
	}

	public List<String> getAssertionsFilter() {
		return assertionsFilter;
	}

	public String getCurrentMethodName() {
		return methodName;
	}

	public void addTestCaseMethod(TestCaseMethod testCaseMethod) {
		testCaseMethods.add(testCaseMethod);

	}

	public boolean hasTestCaseMehtods() {
		return !testCaseMethods.isEmpty();
	}

	public void addAssertion(TestCaseAssertion testCaseAssertion) {
		TestCaseMethod currentTestCaseMethod = testCaseMethods
				.get(testCaseMethods.size() - 1);
		currentTestCaseMethod.addAssertion(testCaseAssertion);
	}

	public void setCurrentMethodName(String name) {
		methodName = name;
	}

	public boolean matchesAssertionsFilter(String owner) {

		return hasTestCaseMehtods()
				&& assertionsFilter.contains(owner.replace("/", "."));
	}
}
