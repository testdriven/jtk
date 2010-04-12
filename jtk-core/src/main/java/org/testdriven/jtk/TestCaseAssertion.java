package org.testdriven.jtk;



public class TestCaseAssertion {

	private final String asserttionClass;
	private final Integer lineNumber;

	public TestCaseAssertion(String asserttionClass, Integer lineNumber) {
		this.asserttionClass = asserttionClass;
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getAssertionClass() {
		return asserttionClass;
	}

}
