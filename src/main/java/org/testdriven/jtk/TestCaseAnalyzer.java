package org.testdriven.jtk;

import java.io.File;

public class TestCaseAnalyzer {

	private final File[] testCases;

	public TestCaseAnalyzer(File[] testCases) {
		this.testCases = testCases;
	}

	public File[] getTestCases() {
		return testCases;
	}

}
