package org.testdriven.jtk;

import java.util.List;

public class AnalyzerResults {
    private final TestCase[] testCases;

    AnalyzerResults(List<TestCase> testCases) {
        this.testCases = testCases.toArray(new TestCase[]{});
    }

	public TestCase[] getTestCases() {
		return testCases;
	}

}
