package org.testdriven.jtk;

import java.util.ArrayList;
import java.util.List;

public class TestCaseMethod {

	private final String methodName;
	private final List<TestCaseAssertion> testCaseAssertions = new ArrayList<TestCaseAssertion>();
        private final int lineNumber;

	public TestCaseMethod(String methodName,int lineNumber) {
		this.methodName = methodName;
                this.lineNumber = lineNumber;
	}

	public String getMethodName() {
		return methodName;
	}

        public int getLineNumber(){
            return lineNumber;
        }

	public TestCaseAssertion[] getAssertions() {
		return testCaseAssertions
				.toArray(new TestCaseAssertion[testCaseAssertions.size()]);
	}

	public void addAssertion(TestCaseAssertion testCaseAssertion) {
		testCaseAssertions.add(testCaseAssertion);
	}

}
