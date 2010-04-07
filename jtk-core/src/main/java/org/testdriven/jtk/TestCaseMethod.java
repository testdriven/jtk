package org.testdriven.jtk;

import java.util.ArrayList;
import java.util.List;

public class TestCaseMethod {

	private final String methodName;
	private final List<TestCaseAssertion> testCaseAssertions = new ArrayList<TestCaseAssertion>();

	public TestCaseMethod(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public TestCaseAssertion[] getAssertions() {
		return testCaseAssertions
				.toArray(new TestCaseAssertion[testCaseAssertions.size()]);
	}

	public void addAssertion(TestCaseAssertion testCaseAssertion) {
		testCaseAssertions.add(testCaseAssertion);
	}

}
